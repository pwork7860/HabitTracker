package com.habittracker.app.login.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.habittracker.app.config.GoogleConfig;
import com.habittracker.app.jwt.svc.iface.JwtSvc;
import com.habittracker.app.login.data.dto.request.GoogleloginRequest;
import com.habittracker.app.login.data.dto.request.LoginRequest;
import com.habittracker.app.login.data.dto.response.LoginResponse;
import com.habittracker.app.login.data.model.LoginIdentity;
import com.habittracker.app.login.enums.LoginSource;
import com.habittracker.app.login.repo.iface.LoginIdentityRepo;
import com.habittracker.app.login.service.iface.LoginSvc;
import com.habittracker.app.login.utils.LoginAdapter;
import com.habittracker.app.user.data.model.User;
import com.habittracker.app.user.repo.iface.UserRepo;
import com.habittracker.app.user.service.iface.UserSvc;
import com.habittracker.app.user.utils.UserAdapter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginSvcImpl implements LoginSvc {

    private final AuthenticationManager manager;
    private final JwtSvc jwtSvc;
    private final GoogleConfig googleConfig;
    private final UserSvc userSvc;
    private final UserRepo userRepo;
    private final LoginIdentityRepo loginIdentityRepo;


    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),
                        request.getPassword()));
        return LoginResponse.builder()
                .token(jwtSvc.getToken(
                        LoginAdapter.getUserfromAuth(authentication)))
                .build();
    }

    @Override
    @Transactional
    public LoginResponse googleLogin(GoogleloginRequest request) {
        GoogleIdToken.Payload payload = verifyIdToken(request.getIdToken());
        Optional<LoginIdentity> loginIdentity =
                loginIdentityRepo.findByLoginSourceAndLoginSourceId(
                LoginSource.GOOGLE, payload.getSubject());
        if (loginIdentity.isPresent() ) {
            User user = userRepo.findById(loginIdentity.get().getUserId())
                    .orElseThrow(() -> new RuntimeException("Login identity exists but user was not found"));
                return LoginResponse .builder()
                        .token(jwtSvc.getToken(user))
                        .build();
        }
        User newUser = UserAdapter.getUserthroughPayload(payload);
        userRepo.save(newUser);
        loginIdentityRepo.save(
                LoginAdapter.getloginIdentity(payload, newUser.getId()));
        return LoginResponse.builder()
                .token(jwtSvc.getToken(newUser))
                .userDetailsResponse(UserAdapter.getUserDetailsResponse(newUser))
                .build();
    }

    @SneakyThrows
    private GoogleIdTokenVerifier getVerifier() {
        return new GoogleIdTokenVerifier
                .Builder(GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance())
                        .setAudience(Collections.singletonList(googleConfig.getClientId()))
                        .build();
    }

    @SneakyThrows
    private GoogleIdToken.Payload verifyIdToken(String idToken) {
        GoogleIdToken googleIdToken = getVerifier().verify(idToken);
        if (googleIdToken != null) {
            return googleIdToken.getPayload();
        }
        return null;
    }

}

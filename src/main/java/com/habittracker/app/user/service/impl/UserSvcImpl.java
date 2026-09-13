package com.habittracker.app.user.service.impl;

import com.habittracker.app.commons.enums.Status;
import com.habittracker.app.jwt.svc.utils.AuthenticationUtils;
import com.habittracker.app.user.data.dto.requests.CreateUserReq;
import com.habittracker.app.user.data.dto.requests.UpdatePasswordReq;
import com.habittracker.app.user.data.dto.requests.UpdateUserReq;
import com.habittracker.app.user.data.dto.response.CreateUserRes;
import com.habittracker.app.user.data.dto.response.PasswordUpdateReponse;
import com.habittracker.app.user.data.dto.response.UpdateUserRes;
import com.habittracker.app.user.data.dto.response.UserDetailsResponse;
import com.habittracker.app.user.data.model.User;
import com.habittracker.app.user.exception.DuplicateUserEmailException;
import com.habittracker.app.user.exception.UserNotFoundException;
import com.habittracker.app.user.repo.iface.UserRepo;
import com.habittracker.app.user.service.iface.UserSvc;
import com.habittracker.app.user.utils.UserAdapter;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSvcImpl implements UserSvc {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    @Override
    public CreateUserRes createUser(CreateUserReq req) {
       Optional<User> existingUser =
                userRepo.findByEmailId(req.getEmailId());
       if (existingUser.isPresent()) {
           throw new DuplicateUserEmailException();
       }
        User user = UserAdapter.getUser(req);
        user.setPassword(encoder.encode(req.getPassword()));
        User savedUser = userRepo.save(user);
        return UserAdapter.getCreateUserResponse(savedUser);
    }

    @Override
    public UserDetailsResponse getUser() {
        String userId = AuthenticationUtils.getUserIdFromAuth();
        Optional<User> user = userRepo.findById(userId);
        if (user.isPresent()) {
            return UserAdapter.getUserDetailsResponse(user.get());
        } else {
            throw new UserNotFoundException(userId);
        }

    }

    @Override
    public User getUserbyEmailid(String emailId) {
        Optional<User> user = userRepo.findByEmailId(emailId);
        return user.orElse(null);
    }

    @Override
    public List<UserDetailsResponse> getAllusers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map(UserAdapter::getUserDetailsResponse)
                .toList();
    }

    @Override
    public UpdateUserRes updateUser(UpdateUserReq req) {
        String userId = AuthenticationUtils.getUserIdFromAuth();
        Optional<User> user = userRepo.findById(userId);
        if (user.isPresent()) {
            User currentuser = user.get();
            if (StringUtils.isNotBlank(req.getName())) {
                currentuser.setName(req.getName());
            }
            if (StringUtils.isNotBlank(req.getEmailId())) {
                currentuser.setEmailId(req.getEmailId());
            }
            if (StringUtils.isNotBlank(req.getPhoneNumber())) {
                currentuser.setPhoneNumber(req.getPhoneNumber());
            }
            if(StringUtils.isNotBlank(req.getTimeZone())) {
                currentuser.setTimeZone(req.getTimeZone());
            }
            currentuser.setUpdatedAt(Instant.now());
           User updatedUser = userRepo.save(currentuser);
           return UserAdapter.getUpdateUserResponse(updatedUser);
        }
        throw new UserNotFoundException(userId);
    }

    @Override
    public void deleteUser() {
        String userId = AuthenticationUtils.getUserIdFromAuth();
        User user = userRepo.findById(userId).orElseThrow();
        user.setStatus(Status.DELETED);
        userRepo.save(user);
    }

    @Override
    public PasswordUpdateReponse updatePassword(
            UpdatePasswordReq req, String userId) {
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (req.getOldPassword().equals(user.getPassword())) {
                user.setPassword(req.getNewPassword());
            }
            userRepo.save(user);
            return PasswordUpdateReponse.builder()
                    .message("Password Updated Successfully")
                    .build();
        }
        throw new UserNotFoundException(userId);
    }
}

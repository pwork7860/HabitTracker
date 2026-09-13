package com.habittracker.app.jwt.svc.iface;


import com.habittracker.app.user.data.dto.response.UserDetailsResponse;
import com.habittracker.app.user.data.model.User;
import io.jsonwebtoken.Claims;

public interface JwtSvc {

   public String getToken(User user);

   public Claims validateToken(String token);


}

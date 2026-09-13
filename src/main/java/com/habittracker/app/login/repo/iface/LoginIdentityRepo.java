package com.habittracker.app.login.repo.iface;

import com.habittracker.app.login.data.model.LoginIdentity;
import com.habittracker.app.login.enums.LoginSource;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LoginIdentityRepo extends MongoRepository<LoginIdentity, String> {

    Optional<LoginIdentity> findByLoginSourceAndLoginSourceId(
            LoginSource loginSource, String loginSourceId);
}

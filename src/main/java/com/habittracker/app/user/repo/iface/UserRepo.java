package com.habittracker.app.user.repo.iface;


import com.habittracker.app.user.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {


    Optional<User> findByEmailId(String emailId);

    Optional<User> findByName(String name);

}

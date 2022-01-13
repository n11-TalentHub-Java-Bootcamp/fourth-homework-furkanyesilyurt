package com.furkanyesilyurt.fourthHomework.dao;

import com.furkanyesilyurt.fourthHomework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {

    User findByFirstName(String firstName);

}

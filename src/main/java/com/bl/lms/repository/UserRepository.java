package com.bl.lms.repository;

import com.bl.lms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByFirstName(String firstName);

    User findByEmail(String email);

    Optional<User> findById(long id);
}

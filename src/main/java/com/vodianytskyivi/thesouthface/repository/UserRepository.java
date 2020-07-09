package com.vodianytskyivi.thesouthface.repository;

import com.vodianytskyivi.thesouthface.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByActivationCode(String code);
}

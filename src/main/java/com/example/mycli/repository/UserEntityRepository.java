package com.example.mycli.repository;

import com.example.mycli.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByLogin(String login);
    UserEntity findById(long clientID);
}

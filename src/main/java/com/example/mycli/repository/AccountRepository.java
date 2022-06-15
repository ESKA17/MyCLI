package com.example.mycli.repository;

import com.example.mycli.model.Account;
import com.example.mycli.model.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    @Query(value = "SELECT NEXTVAL('account_seq')", nativeQuery = true)

    Long getNextSeriesId();

    Account findAccountById(String id);

    Account findTopByOrderByIdDesc();

    List<Account> findAll();

    List<Account> findAllByUserEntity(UserEntity userEntity);

    Account findAccountByIdAndUserEntity(String id, UserEntity userEntity);

}

package com.example.mycli.repository;

import com.example.mycli.model.Account;
import com.example.mycli.model.UserEntity;
import com.example.mycli.server.AccountType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    @Query(value = "SELECT NEXTVAL('account_seq')", nativeQuery = true)
    Long getNextSeriesId();

    Account findAccountById(String id);

    Account findTopByOrderByIdDesc();

    List<Account> findAll();

    List<Account> findAllByUserEntity(UserEntity userEntity);

    Optional<Account> findAccountByIdAndUserEntity(String id, UserEntity userEntity);

    List<Account> findAccountByUserEntity_Id(long clientID);
    List<Account> findAccountByUserEntity_IdAndAccountType(long clientID, AccountType accountType);
    Optional<Account> findAccountByWithdrawalAllowedAndUserEntity_Id(boolean withdrawalAllowed, long clientID);
    Optional<Account> findAccountByUserEntity_IdAndId(long clientID, String accountID);

}

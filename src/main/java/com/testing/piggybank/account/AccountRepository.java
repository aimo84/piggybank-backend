package com.testing.piggybank.account;

import com.testing.piggybank.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findAll();
    List<Account> findAllByUserId(long accountId);
}

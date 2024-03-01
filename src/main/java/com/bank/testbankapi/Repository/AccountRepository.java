package com.bank.testbankapi.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.testbankapi.Model.Account;


@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {


    public Optional<Account> findByUserFirstNameAndUserLastName(String firstName, String lastName);

}
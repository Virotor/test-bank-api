package com.bank.testbankapi.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.testbankapi.Model.User;


@Repository
public interface UserRegistrationRepository extends CrudRepository<User, Long> {
    
    public Optional<User> findByFirstNameAndLastName(String firstName, String lastName);

}
 
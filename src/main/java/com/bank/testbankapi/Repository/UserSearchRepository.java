package com.bank.testbankapi.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bank.testbankapi.Model.User;


@Repository
public interface UserSearchRepository extends PagingAndSortingRepository<User,Long> {
    

    
    public Optional<User> findByPhonesValue(String value);

    public Optional<User> findByEmailsValue(String value);

    public List<User> findByBirthDayAfter(Date birthDay, Pageable pageable);


    public List<User> findByFirstNameStartingWithAndLastNameStartingWith(String firstName, String lastName, Pageable pageable);
}

package com.bank.testbankapi.Repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.testbankapi.Model.Account;

import jakarta.transaction.Transactional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    public Optional<Account> findByUserFirstNameAndUserLastName(String firstName, String lastName);

    @Query(value = "CALL transfer_money(:id_from, :id_to, :amount_transfer);", nativeQuery = true)
    @Modifying
    @Transactional
    Integer transferMoney(@Param("id_from") Long id_from, @Param("id_to") Long id_to,
            @Param("amount_transfer") BigDecimal amount_transfer);

    @Query(value = ("CALL add_percent()"), nativeQuery = true)
    @Modifying
    @Transactional
    void addPercent();

}
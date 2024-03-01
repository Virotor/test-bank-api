package com.bank.testbankapi.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bank.testbankapi.Model.Phone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, Long> {

        public Optional<Phone> findByValue(String value);

        @Transactional
        @Modifying
        @Query(value = "UPDATE phone " +
                        "SET value = :newValue " +
                        "WHERE value = :oldValue ", nativeQuery = true)
        public Integer updatePhone(@NotBlank @Param("newValue") String newValue,
                        @Param("oldValue") @NotNull String oldValue);

        @Transactional
        @Modifying
        public void deleteByValue(String value);

}

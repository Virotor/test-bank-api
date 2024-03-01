package com.bank.testbankapi.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bank.testbankapi.Model.Email;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Repository
public interface EmailRepository extends CrudRepository<Email, Long> {

        public Optional<Email> findByValue(String value);

        @Transactional
        @Modifying
        @Query(value = "UPDATE email " +
                        "SET value = :newValue " +
                        "WHERE value = :oldValue", nativeQuery = true)
        public Integer updateEmail(@NotBlank @Param("newValue") String newValue,
                        @NotNull @Param("oldValue") String oldValue);

        @Transactional
        @Modifying
        public void deleteByValue(String value);
}

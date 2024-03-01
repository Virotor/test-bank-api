package com.bank.testbankapi.Model;

import java.math.BigDecimal;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.Comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Check(constraints =  "amount >= 0")
@Check(constraints =  "amount_percent >= 0")
public class Account {
    
    @Id
    @JsonIgnore
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("Основной депозит")
    private BigDecimal amount;

    @Comment("Процент на счёте")
    private BigDecimal amount_percent;
    
    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=false)
    @JsonBackReference(value = "user_account")
    private User user;
}

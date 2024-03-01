package com.bank.testbankapi.Model;

import java.math.BigDecimal;

import org.hibernate.annotations.Check;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Check(constraints =  "amount >= 0")
public class Account {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    
    private BigDecimal amount;

    
    private BigDecimal amount_percent;
    
    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=false)
    @JsonBackReference(value = "user_account")
    private User user;
}

package com.bank.testbankapi.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
    uniqueConstraints =  @UniqueConstraint(columnNames = {"value"}),
    indexes = @Index(name = "value_ind_phone", columnList = "value")
)
public class Phone extends Contact {


}

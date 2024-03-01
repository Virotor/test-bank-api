package com.bank.testbankapi.Model;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank_users", uniqueConstraints = @UniqueConstraint(columnNames = { "last_name",
        "fisrt_name" }), indexes = {
                @Index(name = "f_nam_l_name", columnList = "firstName , lastName"),
                @Index(name = "birh_dat", columnList = "birthDay"), })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference(value = "user_phones")
    private Set<Phone> phones;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference(value = "user_emails")
    private Set<Email> emails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference(value = "user_accounts")
    private Set<Account> account;

    private Date birthDay;

    private String lastName;

    private String firstName;

    @JsonIgnore
    private String password;

}

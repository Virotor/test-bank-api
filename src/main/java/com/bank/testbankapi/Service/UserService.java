package com.bank.testbankapi.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bank.testbankapi.Model.User;
import com.bank.testbankapi.Repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByName(username).orElseThrow(()-> 
            new UsernameNotFoundException( String.format("User %s not found", username)
            ));
        return new org.springframework.security.core.userdetails.User(
            user.getFirstName()
            ,user.getPassword()
            ,List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    private Optional<User> findByName(String firstName) {
      return userRepository.findByFirstName(firstName);
    }
    
}

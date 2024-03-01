package com.bank.testbankapi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bank.testbankapi.DTO.AppError;
import com.bank.testbankapi.Model.User;
import com.bank.testbankapi.Repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final ContactService contactService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
        return new org.springframework.security.core.userdetails.User(
                user.getFirstName() + '/' + user.getLastName(), user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public Optional<User> findByName(String name) {
        String[] names = name.split("/");
        if (names.length < 2) {
            throw new IllegalArgumentException("Name less than two part");
        }
        return userRepository.findByFirstNameAndLastName(names[0], names[1]);
    }

    public Optional<User> findByFirstNameAndLastName(String firsName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firsName, lastName);
    }

    public ResponseEntity<?> updateUserEmail(String oldEmail, String newEmail) {
        if (contactService.isUsedEmail(newEmail)) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.CONFLICT.value(), "Нельзя изменить почту"), HttpStatus.CONFLICT);
        }
        var res = contactService.updateEmail(oldEmail, newEmail);
        if (res > 0) {
            return ResponseEntity.ok("Данные пользователя успешно изменены");
        }
        return new ResponseEntity<>(
                new AppError(HttpStatus.NOT_FOUND.value(), "Данные пользователя не удалось изменить"),
                HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<?> updateUserPhone(String oldPhone, String newPhone) {
        if (contactService.isUsedPhone(newPhone)) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.CONFLICT.value(), "Нельзя изменить номер телефона"), HttpStatus.CONFLICT);
        }
        var res = contactService.updatePhone(oldPhone, newPhone);
        if (res > 0) {
            return ResponseEntity.ok("Данные пользователя успешно изменены");
        }
        return new ResponseEntity<>(
                new AppError(HttpStatus.NOT_FOUND.value(), "Данные пользователя не удалось изменить"),
                HttpStatus.NOT_FOUND);

    }

    public User getUserByToken() {
        var username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().split("/");
        var user = findByFirstNameAndLastName(username[0], username[1]);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User %s %s not found", username[0], username[1]));
        }
        return user.get();
    }

    public User createUser(@NonNull User user) {
        return userRepository.save(user);
    }

    public ResponseEntity<?> addPhone(String newPhone) {
        if (contactService.isUsedPhone(newPhone)) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.CONFLICT.value(), "Нельзя добавить этот номер телефона, он занят"),
                    HttpStatus.CONFLICT);
        }
        contactService.addNewPhone(newPhone, getUserByToken());
        return ResponseEntity.ok("Данные пользователя успешно изменены");

    }

    public ResponseEntity<?> addEmail(String newEmail) {
        if (contactService.isUsedEmail(newEmail)) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.CONFLICT.value(), "Нельзя добавить эту почту, она занята"),
                    HttpStatus.CONFLICT);
        }
        contactService.addNewEmail(newEmail, getUserByToken());
        return ResponseEntity.ok("Данные пользователя успешно изменены");

    }

    public ResponseEntity<?> deleteEmail(String deleteEmail) {
        try {
            contactService.deleteEmail(deleteEmail);
            return ResponseEntity.ok("Почта успешно удалена");
        } catch (Exception e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Не удалось удалить почту"),
                    HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<?> deletePhone(String deletePhone) {
        try {
            contactService.deletePhone(deletePhone);
            return ResponseEntity.ok("Номер успешно удалена");
        } catch (Exception e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Не удалось удалить почту"),
                    HttpStatus.NOT_FOUND);
        }
    }

}

package com.bank.testbankapi.Service;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.bank.testbankapi.Model.Email;
import com.bank.testbankapi.Model.Phone;
import com.bank.testbankapi.Model.User;
import com.bank.testbankapi.Repository.EmailRepository;
import com.bank.testbankapi.Repository.PhoneRepository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final PhoneRepository phoneRepository;
    private final EmailRepository emailRepository;

    public Phone savePhone(@NonNull Phone phone) {
        return phoneRepository.save(phone);
    }

    public Email saveEmail(@NonNull Email email) {
        return emailRepository.save(email);
    }

    public Integer updateEmail(@NotBlank String oldValue, @NotNull String newValue) {
        return emailRepository.updateEmail(newValue, oldValue);
    }

    public Integer updatePhone(@NotBlank String oldValue, @NotNull String newValue) {
        return phoneRepository.updatePhone(newValue, oldValue);
    }

    public boolean isUsedEmail(String value) {
        return emailRepository.findByValue(value).isPresent();
    }

    public boolean isUsedPhone(String value) {
        return phoneRepository.findByValue(value).isPresent();
    }

    public void addNewPhone(String newPhone, User user) {
        Phone phone = new Phone();
        phone.setValue(newPhone);
        phone.setUser(user);
        phone = phoneRepository.save(phone);
    }

    public void addNewEmail(String newEmail, User user) {
        Email email = new Email();
        email.setValue(newEmail);
        email.setUser(user);
        email = emailRepository.save(email);
    }

    public void deleteEmail(String deleteEmail) {
        emailRepository.deleteByValue(deleteEmail);
    }

    public void deletePhone(String deletePhone) {
        phoneRepository.deleteByValue(deletePhone);
    }

}

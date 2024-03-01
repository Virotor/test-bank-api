package com.bank.testbankapi.Utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bank.testbankapi.Repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000 * 60);
                    accountRepository.addPercent();
                } catch (Exception e) {
                    log.debug(e.getMessage());
                }
            }
        });
        t.start();
    }

}

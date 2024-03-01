// package com.bank.testbankapi;

// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertThat;

// import java.math.BigDecimal;
// import java.time.LocalDate;

// import org.junit.ClassRule;
// import org.junit.jupiter.api.Test;
// import org.junit.runner.RunWith;
// import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.Application;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.util.TestPropertyValues;
// import org.springframework.context.ApplicationContextInitializer;
// import org.springframework.context.ConfigurableApplicationContext;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.testcontainers.containers.PostgreSQLContainer;

// import com.bank.testbankapi.Repository.AccountRepository;
// import com.bank.testbankapi.Service.AccountService;

// import jakarta.transaction.Transactional;
// import lombok.RequiredArgsConstructor;

// @RunWith(SpringRunner.class)
// @SpringBootTest(classes = Application.class)
// @ActiveProfiles("tc")
// @ContextConfiguration(initializers = {TestTransferMoney.Initializer.class})
// @RequiredArgsConstructor
// public class TestTransferMoney   {

//     private final AccountRepository accountRepository;
  

//     @ClassRule
//     public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
//       .withDatabaseName("db_bank")
//       .withUsername("postgres")
//       .withPassword("12345Qqq");

//     @Test
//     @Transactional
//     public void givenUsersInDB_WhenUpdateStatusForNameModifyingQueryAnnotationNative_ThenModifyMatchingUsers() {
//            accountRepository.transferMoney(1L, 2L, new BigDecimal(1.0));
//            assertEquals(1, 1);
//     }

//     static class Initializer
//       implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//         public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//             TestPropertyValues.of(
//               "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
//               "spring.datasource.username=" + postgreSQLContainer.getUsername(),
//               "spring.datasource.password=" + postgreSQLContainer.getPassword()
//             ).applyTo(configurableApplicationContext.getEnvironment());
//         }
//     }
// }
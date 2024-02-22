package lv.course.testing.frameworks.service.account;

import lv.course.testing.frameworks.constants.AccountConstants;
import lv.course.testing.frameworks.entity.Account;
import lv.course.testing.frameworks.entity.Person;
import lv.course.testing.frameworks.service.account.delegate.AccountNumberGenerator;
import lv.course.testing.frameworks.service.account.delegate.InterestRateCalculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

class AccountServiceTest_Frameworked {

    @Mock private InterestRateCalculator interestRateCalculator;
    @Mock private AccountNumberGenerator accountNumberGenerator;
    @InjectMocks private AccountService service;

    private Person person;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        person = new Person();
    }

    @Test
    void addAccount_Cloned() {
        Account account = new Account();
        account.setId(2);
        account.setAccountNumber("accNum");
        account.setAccountHolder(person);
        person.getAccounts().add(account);
        account.setAccountGroup("1g");
        account.setInterestRate(0.2);
        account.setCredit(false);
        account.setGrowthFactor("f4k");
        account.setLiabilityType("TE");

        when(accountNumberGenerator.generateAccountNumber("1g", true))
                .thenReturn("ccNum3");

        service.addAccount(person, true);

        Assertions.assertThat(person.getAccounts()).hasSize(2).contains(account);
        Account result = person.getAccounts().stream()
                        .filter(a -> a != account)
                                .findFirst()
                                        .orElse(null);

        Assertions.assertThat(result)
                .isNotNull()
                .hasFieldOrPropertyWithValue("accountNumber", "ccNum3")
                .hasFieldOrPropertyWithValue("liabilityType", AccountConstants.DEFAULT_LIABILITY)
                .isEqualToIgnoringGivenFields(account, "id");
    }
}
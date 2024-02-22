package lv.course.testing.frameworks.service.account;

import lv.course.testing.frameworks.entity.Account;
import lv.course.testing.frameworks.entity.Person;
import lv.course.testing.frameworks.external.CurrentTaxService;
import lv.course.testing.frameworks.service.account.delegate.AccountNumberGenerator;
import lv.course.testing.frameworks.service.account.delegate.InterestRateCalculator;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class AccountServiceTest_Vanilla {

    private AccountService service;

    private Person person;

    public static void main(String[] args) {
        AccountServiceTest_Vanilla testClass = new AccountServiceTest_Vanilla();
        Arrays.stream(testClass.getClass().getDeclaredMethods())
                .filter(m -> m.getName().contains("test_"))
                .filter(m -> m.getReturnType().getSimpleName().equalsIgnoreCase("void"))
                .forEach(m -> {
                    m.setAccessible(true);
                    try {
                        m.invoke(testClass);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new AssertionError(e.getCause());
                    }
                });
        System.out.println("Testing passed");
    }

    void test_addAccount_Cloned() {
        service = new AccountService(
                new InterestRateCalculatorImpl(null),
                new AccountNumberGeneratorImpl()
        );
        person = new Person();

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

        service.addAccount(person, true);

        assert person.getAccounts().size() == 2;

        Account result = person.getAccounts().stream()
                .filter(a -> a != account)
                .findFirst()
                .orElse(null);

        assert result != null;
        assert "1g".equals(result.getAccountGroup());
        if (!"GF".equals(result.getGrowthFactor())) {
            throw new AssertionError("growthFactor is not 'GF'");
        }
    }

    private static class InterestRateCalculatorImpl extends InterestRateCalculator {

        public InterestRateCalculatorImpl(CurrentTaxService currentTaxService) {
            super(currentTaxService);
        }

        @Override
        public double calculateInterestRate(Double initialRate, String factorType, String liabilityType) {
            return 0.34;
        }
    }

    private static class AccountNumberGeneratorImpl extends AccountNumberGenerator {

        @Override
        public String generateAccountNumber(String accountGroup, boolean credit) {
            return "ccNum3";
        }
    }

}

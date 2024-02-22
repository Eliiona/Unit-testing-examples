package lv.course.testing.frameworks.service.account;


import lombok.RequiredArgsConstructor;
import lv.course.testing.frameworks.constants.AccountConstants;
import lv.course.testing.frameworks.entity.Account;
import lv.course.testing.frameworks.entity.Person;
import lv.course.testing.frameworks.service.account.delegate.AccountNumberGenerator;
import lv.course.testing.frameworks.service.account.delegate.InterestRateCalculator;

@RequiredArgsConstructor
public class AccountService {

    private final InterestRateCalculator interestRateCalculator;
    private final AccountNumberGenerator accountNumberGenerator;

    public void addAccount(Person person, boolean credit) {
        Account newAccount = person.getAccounts().stream()
                .findFirst()
                .map(Account::cloneEntity)
                .orElseGet(this::createNew);
        newAccount.setAccountNumber(accountNumberGenerator.generateAccountNumber(newAccount.getAccountGroup(), credit));
        newAccount.setCredit(credit);
        newAccount.setAccountHolder(person);
        person.getAccounts().add(newAccount);
    }

    public void updateInterestRate(Account account, Double initialRate) {
        double newRate = interestRateCalculator.calculateInterestRate(
                initialRate, account.getGrowthFactor(), account.getLiabilityType());
        account.setInterestRate(newRate);
    }

    private Account createNew() {
        Account newAccount = new Account();
        newAccount.setAccountGroup(AccountConstants.DEFAULT_GROUP);
        newAccount.setGrowthFactor(AccountConstants.DEFAULT_GROWTH);
        newAccount.setLiabilityType(AccountConstants.DEFAULT_LIABILITY);
        newAccount.setInterestRate(AccountConstants.DEFAULT_INTEREST);
        return newAccount;
    }
}

package lv.course.testing.equals.service.account.delegate;

import java.util.Map;

public class AccountNumberGenerator {

    private static final Map<String, String> GROUP_ID = Map.of(
            "1", "A",
            "2", "B",
            "3", "C"
    );
    private long creditSeq = 0L;
    private long debitSeq = 0L;

    public String generateAccountNumber(String accountGroup, boolean credit) {
        String prefix = GROUP_ID.getOrDefault(accountGroup, "U");
        String suffix;
        long sequence;
        if (credit) {
            suffix = "C";
            sequence = creditSeq;
            creditSeq += 1;
        } else {
            suffix = "D";
            sequence = debitSeq;
            debitSeq += 1;
        }
        if (sequence > 99999) {
            throw new UnsupportedOperationException("Sequence numbering expired!");
        }
        return String.format("%s%5s%s", prefix, sequence, suffix);
    }
}

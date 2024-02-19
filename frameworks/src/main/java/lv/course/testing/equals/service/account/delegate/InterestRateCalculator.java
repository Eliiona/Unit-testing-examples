package lv.course.testing.equals.service.account.delegate;

import lombok.RequiredArgsConstructor;
import lv.course.testing.equals.external.CurrentTaxService;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class InterestRateCalculator {

    private static final Map<String, Double> FACTOR_MAP = Map.of(
            "QA", 0.25,
            "HA", 0.5,
            "N/A", 1.0
    );
    private static final Map<String, Double> LIABILITY_MAP = Map.of(
            "MO", 12.0,
            "WI", 0.5,
            "none", 10.0
    );
    private static final double DEFAULT_FACTOR = 1.5;
    private static final double DEFAULT_LIABILITY = 2.0;

    private final CurrentTaxService currentTaxService;

    public double calculateInterestRate(Double initialRate, String factorType, String liabilityType) {
        double rate = Optional.ofNullable(initialRate)
                .orElseGet(currentTaxService::getCurrentTax);
        double factor = FACTOR_MAP.getOrDefault(factorType, DEFAULT_FACTOR);
        double liability = LIABILITY_MAP.getOrDefault(liabilityType, DEFAULT_LIABILITY);
        return Math.pow(rate, factor) * liability;
    }
}

package sioux.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sioux.business.LicensePlateUseCase;
import sioux.domain.CompareLicensePlates;

@Service
@AllArgsConstructor
public class LicensePlateUseCaseImpl implements LicensePlateUseCase {
    private static final String HARDCODED_STRING = "C 9999999";

    @Override
    public boolean checkStringSimilarity(String stringToCheck) {
        double similarity = CompareLicensePlates.calculateSimilarity(HARDCODED_STRING, stringToCheck);
        return similarity > 0.5;
    }
}

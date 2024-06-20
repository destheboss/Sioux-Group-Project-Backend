package sioux.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sioux.business.LicensePlateUseCase;
import sioux.domain.CompareLicensePlates;
import sioux.persistence.AppointmentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class LicensePlateUseCaseImpl implements LicensePlateUseCase {
    private final AppointmentRepository appointmentRepository;

    @Override
    public boolean checkStringSimilarity(String stringToCheck) {
        List<String> licensePlatesForToday = getAllLicensePlatesForToday();

        for (String licensePlate : licensePlatesForToday) {
            double similarity = CompareLicensePlates.calculateSimilarity(licensePlate, stringToCheck);
            if (similarity > 0.5) {
                return true;
            }
        }
        return false;
    }


    public List<String> getAllLicensePlatesForToday() {
        return appointmentRepository.findLicensePlatesForCurrentDate();
    }
}

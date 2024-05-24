package sioux.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sioux.business.CreateAppointmentUseCase;
import sioux.domain.CreateAppointmentRequest;
import sioux.domain.CreateAppointmentResponse;
import sioux.persistence.AppointmentRepository;
import sioux.persistence.EmployeeRepository;
import sioux.persistence.entity.AppointmentEntity;
import sioux.persistence.entity.EmployeeEntity;

@Service
@AllArgsConstructor
public class CreateAppointmentUseCaseImpl implements CreateAppointmentUseCase {
    private final AppointmentRepository appointmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public CreateAppointmentResponse createAppointment(CreateAppointmentRequest request) {
        EmployeeEntity employeeEntity = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));

        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .date(request.getDate())
                .time(request.getTime())
                .employee(employeeEntity)
                .build();

        AppointmentEntity savedEntity = appointmentRepository.save(appointmentEntity);

        return CreateAppointmentResponse.builder()
                .appointmentId(savedEntity.getId())
                .build();
    }
}

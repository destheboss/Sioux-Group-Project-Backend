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
import sioux.service.EmailService;

@Service
@AllArgsConstructor
public class CreateAppointmentUseCaseImpl implements CreateAppointmentUseCase {
    private final AppointmentRepository appointmentRepository;
    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;

    @Override
    public CreateAppointmentResponse createAppointment(CreateAppointmentRequest request) {
        EmployeeEntity employeeEntity = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));

        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .date(request.getDate())
                .time(request.getTime())
                .employee(employeeEntity)
                .build();

        AppointmentEntity savedAppointment = appointmentRepository.save(appointmentEntity);

        String employeeEmail = savedAppointment.getEmployee().getEmail();
        String subject = "New Appointment Created";
        String text = String.format("Dear %s,%n%nAn appointment has been scheduled for you on %s at %s.",
                savedAppointment.getEmployee().getFirstName(),
                savedAppointment.getDate(),
                savedAppointment.getTime());

        emailService.sendAppointmentNotification(employeeEmail, subject, text);

        return CreateAppointmentResponse.builder()
                .appointmentId(savedAppointment.getId())
                .build();
    }
}

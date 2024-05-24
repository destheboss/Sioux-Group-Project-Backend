package sioux.business.impl;

import sioux.domain.Appointment;
import sioux.persistence.entity.AppointmentEntity;
import sioux.persistence.entity.EmployeeEntity;

final class AppointmentConverter {
    private AppointmentConverter(){
    }
    public static Appointment convertToDomain(AppointmentEntity appointment){
        return Appointment.builder()
                .id(appointment.getId())
                .date(appointment.getDate())
                .time(appointment.getTime())
                .employeeId(appointment.getEmployee().getId())
                .build();
    }

    public static AppointmentEntity convertToEntity(Appointment appointment, EmployeeEntity employeeEntity) {
        return AppointmentEntity.builder()
                .id(appointment.getId())
                .date(appointment.getDate())
                .time(appointment.getTime())
                .employee(employeeEntity)
                .build();
    }
}

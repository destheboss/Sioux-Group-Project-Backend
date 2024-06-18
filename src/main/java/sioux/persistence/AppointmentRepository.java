package sioux.persistence;

import sioux.persistence.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findByEmployee_Id(Long employeeId);
    List<AppointmentEntity> findByStartTime(Date date);
}

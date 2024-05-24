package sioux.controller;

import lombok.AllArgsConstructor;
import sioux.business.*;
import sioux.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AppointmentController {
    private final CreateAppointmentUseCase createAppointmentUseCase;
    private final GetAppointmentUseCase getAppointmentUseCase;
    private final DeleteAppointmentUseCase deleteAppointmentUseCase;
    private final UpdateAppointmentUseCase updateAppointmentUseCase;
    private final GetAllAppointmentsUseCase getAppointmentsUseCase;

    @PostMapping()
    public ResponseEntity<CreateAppointmentResponse> createAppointment(@RequestBody CreateAppointmentRequest request) {
        CreateAppointmentResponse response = createAppointmentUseCase.createAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable(value = "id") final long id){
        final Optional<Appointment> appointmentOptional = getAppointmentUseCase.getAppointment(id);
        if(appointmentOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(appointmentOptional.get());

    }
    @DeleteMapping("{AppointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable int appointmentId){
        deleteAppointmentUseCase.deleteAppointment(appointmentId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> updateAppointment(@PathVariable("id") long id,
                                                  @RequestBody @Validated UpdateAppointmentRequest request) {
        request.setId(id);
        updateAppointmentUseCase.updateAppointment(request);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<GetAllAppointmentsResponse> getAppointments(){
        GetAllAppointmentsResponse response = getAppointmentsUseCase.getAppointments();
        return ResponseEntity.ok(response);
    }
}

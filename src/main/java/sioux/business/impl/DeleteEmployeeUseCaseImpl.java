package sioux.business.impl;

import sioux.business.DeleteEmployeeUseCase;
import sioux.persistence.EmployeeRepository;

public class DeleteEmployeeUseCaseImpl implements DeleteEmployeeUseCase {
    private EmployeeRepository employeeRepository;

    @Override
    public void deleteEmployee(long employeeId) {
        this.employeeRepository.deleteById(employeeId);
    }
}

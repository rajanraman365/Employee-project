package Employee.project.Employee.Response;

import lombok.Data;

@Data
public class EmployeeTaxDTO {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private Double yearlySalary;
    private Double taxAmount;
    private Double cessAmount;
}

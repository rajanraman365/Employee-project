package Employee.project.Employee.Request;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EmployeeDTO {
    @NotNull(message = "Employee ID is mandatory")
    private Long employeeId;

    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Date of Joining is mandatory")
    private String dateOfJoining;

    @NotBlank(message = "Salary is mandatory")
    private String salary;

    private String phoneNumber;

}

package Employee.project.Employee.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"employee_details\"")
public class EmployeeDAO {

    @Id
    @Column(name = "\"id\"", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Employee ID is mandatory")
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @NotBlank(message = "First Name is mandatory")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull(message = "Date of Joining is mandatory")
    @Column(name = "doj", nullable = false)
    private String dateOfJoining;

    @NotNull(message = "Salary is mandatory")
    @Column(name = "salary", nullable = false)
    private Double salary;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EmployeePhoneNumberDAO> phoneNumbers;
}

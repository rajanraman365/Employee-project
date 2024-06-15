package Employee.project.Employee.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"employee_phone_numbers\"")
public class EmployeePhoneNumberDAO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"id\"", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeDAO employee;

    @Pattern(regexp = "\\d{10}", message = "Phone number should be 10 digits")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
}

package Employee.project.Employee.Controller;

import Employee.project.Employee.Repo.EmployeeRepository;
import Employee.project.Employee.Request.EmployeeDTO;
import Employee.project.Employee.Response.BaseResponse;
import Employee.project.Employee.Response.EmployeeTaxDTO;
import Employee.project.Employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/save")
    public BaseResponse createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.SaveEmployee(employeeDTO);
    }

    @PostMapping("/calculate")
    public List<EmployeeTaxDTO> calculateTaxForCurrentFinancialYear() {
        return employeeService.calculateTaxForCurrentFinancialYear();
    }

}

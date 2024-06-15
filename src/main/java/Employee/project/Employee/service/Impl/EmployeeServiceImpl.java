package Employee.project.Employee.service.Impl;

import Employee.project.Employee.DAO.EmployeeDAO;
import Employee.project.Employee.DAO.EmployeePhoneNumberDAO;
import Employee.project.Employee.Repo.EmployeePhoneNumberDAORepo;
import Employee.project.Employee.Repo.EmployeeRepository;
import Employee.project.Employee.Request.EmployeeDTO;
import Employee.project.Employee.Response.BaseResponse;
import Employee.project.Employee.Response.EmployeeTaxDTO;
import Employee.project.Employee.Utility.EmployeeUtility;
import Employee.project.Employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl  implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeePhoneNumberDAORepo employeePhoneNumberDAORepo;
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    @Override
    public BaseResponse SaveEmployee(EmployeeDTO employeeDTO) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.setEmployeeId(employeeDTO.getEmployeeId());
        employeeDAO.setFirstName(employeeDTO.getFirstName());
        employeeDAO.setLastName(employeeDTO.getLastName());
        employeeDAO.setEmail(employeeDTO.getEmail());
        employeeDAO.setDateOfJoining(employeeDTO.getDateOfJoining());
        employeeDAO.setSalary(Double.parseDouble(employeeDTO.getSalary()));
        EmployeeDAO savedEmployee = employeeRepository.save(employeeDAO);
        List<EmployeePhoneNumberDAO> phoneNumbers = new ArrayList<>();
        if (employeeDTO.getPhoneNumber() != null) {
            for (String phone : employeeDTO.getPhoneNumber().split(",")) {
                EmployeePhoneNumberDAO phoneNumberDAO = new EmployeePhoneNumberDAO();
                phoneNumberDAO.setPhoneNumber(phone.trim());
                phoneNumberDAO.setEmployee(savedEmployee);
                phoneNumbers.add(phoneNumberDAO);
            }
            employeePhoneNumberDAORepo.saveAll(phoneNumbers);
            savedEmployee = employeeRepository.findById(savedEmployee.getId()).orElse(null);
        }
        BaseResponse response = new BaseResponse();
        response.setData(savedEmployee);
        response.setResult(EmployeeUtility.getResult());
        return response;
    }

    @Override
    public List<EmployeeTaxDTO> calculateTaxForCurrentFinancialYear() {
        LocalDate currentYearStart = LocalDate.now().withMonth(4).withDayOfMonth(1);
        List<EmployeeDAO> employees = employeeRepository.findAll();
        List<EmployeeTaxDTO> employeeTaxDTOList = employees.stream()
                .map(employee -> {
                    double yearlySalary = calculateYearlySalary(employee, currentYearStart);
                    double taxAmount = calculateTaxAmount(yearlySalary);
                    double cessAmount = calculateCess(yearlySalary);

                    EmployeeTaxDTO employeeTaxDTO = new EmployeeTaxDTO();
                    employeeTaxDTO.setEmployeeId(employee.getEmployeeId());
                    employeeTaxDTO.setFirstName(employee.getFirstName());
                    employeeTaxDTO.setLastName(employee.getLastName());
                    employeeTaxDTO.setYearlySalary(yearlySalary);
                    employeeTaxDTO.setTaxAmount(taxAmount);
                    employeeTaxDTO.setCessAmount(cessAmount);

                    return employeeTaxDTO;
                })
                .collect(Collectors.toList());

        return employeeTaxDTOList;
    }

    private double calculateYearlySalary(EmployeeDAO employee, LocalDate currentYearStart) {
        LocalDate doj = LocalDate.parse(employee.getDateOfJoining(), dateFormatter);
        LocalDate startOfMonthAfterDOJ = doj.withDayOfMonth(1).plusMonths(1);

        if (startOfMonthAfterDOJ.isAfter(currentYearStart)) {
            return 0;
        }

        int monthsWorked = (int) ChronoUnit.MONTHS.between(startOfMonthAfterDOJ, currentYearStart.plusMonths(1));
        double monthlySalary = employee.getSalary();
        return monthlySalary * monthsWorked;
    }

    private double calculateTaxAmount(double yearlySalary) {
        if (yearlySalary <= 250000) {
            return 0;
        } else if (yearlySalary > 250000 && yearlySalary <= 500000) {
            return (yearlySalary - 250000) * 0.05;
        } else if (yearlySalary > 500000 && yearlySalary <= 1000000) {
            return 12500 + (yearlySalary - 500000) * 0.1;
        } else {
            return 62500 + (yearlySalary - 1000000) * 0.2;
        }
    }

    private double calculateCess(double yearlySalary) {
        if (yearlySalary > 2500000) {
            return (yearlySalary - 2500000) * 0.02;
        } else {
            return 0;
        }
    }
}
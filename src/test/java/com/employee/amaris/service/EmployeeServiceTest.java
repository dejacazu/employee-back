package com.employee.amaris.service;

import com.employee.amaris.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testGetSingleEmployee() {
        // Datos de prueba
        String id = "1";
        Employee employee = new Employee();
        employee.setId("1");
        employee.setName("Tiger Nixon");
        employee.setSalary(320800);
        employee.setAge(61);
        employee.setImage("");

        // Configurar el mock de RestTemplate
        when(restTemplate.getForEntity("http://dummy.restapiexample.com/api/v1/employee/" + id, Employee.class))
                .thenReturn(new ResponseEntity<>(employee, HttpStatus.OK));

        // Llamar al método a probar
        ResponseEntity<Employee> response = employeeService.getSingleEmployee(id);

        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee.getId(), response.getBody().getId());
        assertEquals(employee.getName(), response.getBody().getName());
        assertEquals(employee.getSalary() * 12, response.getBody().getAnnualSalary());
    }

    @Test
    void testListAllEmployees() {
        // Datos de prueba
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setId("1");
        employee1.setName("Tiger Nixon");
        employee1.setSalary(320800);
        employee1.setAge(61);
        employee1.setImage("");
        employees.add(employee1);

        Employee employee2 = new Employee();
        employee2.setId("2");
        employee2.setName("Garrett Winters");
        employee2.setSalary(170750);
        employee2.setAge(63);
        employee2.setImage("");
        employees.add(employee2);

        // Configurar el mock de RestTemplate
        when(restTemplate.exchange(
                "http://dummy.restapiexample.com/api/v1/employees",
                org.springframework.http.HttpMethod.GET,
                null,
                new org.springframework.core.ParameterizedTypeReference<List<Employee>>() {}))
                .thenReturn(new ResponseEntity<>(employees, HttpStatus.OK));

        // Llamar al método a probar
        ResponseEntity<List<Employee>> response = employeeService.listAllEmployees();

        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals(employee1.getSalary() * 12, response.getBody().get(0).getAnnualSalary());
        assertEquals(employee2.getSalary() * 12, response.getBody().get(1).getAnnualSalary());
    }
}

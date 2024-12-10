package com.employee.amaris.service;


import com.employee.amaris.config.CookieInterceptor;
import com.employee.amaris.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Service
public class EmployeeService {

    @Autowired
    RestTemplate restTemplate;

    public EmployeeService() {
        // Crea una instancia de RestTemplate con la fábrica de solicitudes HTTP
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // Deshabilita la redirección para manejar la cookie nosotros mismos
        requestFactory.setOutputStreaming(false);
        restTemplate = new RestTemplate(requestFactory);
        // Agrega el interceptor para manejar las cookies
        restTemplate.setInterceptors(Collections.singletonList(new CookieInterceptor()));
    }

    public ResponseEntity<Employee> getSingleEmployee(String id) {
        try {
            Employee employee;
            employee = getSingleData("http://dummy.restapiexample.com/api/v1/employee/" + id);
            float annual = employee.getSalary() * 12;
            employee.setAnnualSalary(annual);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (RestClientException e) {
            log.print("Error al obtener el empleado con id: " + id + " " + e);
            throw e;
        }
    }

    public ResponseEntity<List<Employee>> listAllEmployees() {
        try {
            List<Employee> employees = getListData("http://dummy.restapiexample.com/api/v1/employees");
            for (Employee employee : employees) {
                float annual = employee.getSalary() * 12;
                employee.setAnnualSalary(annual);
            }
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (RestClientException e) {
            log.print("Error al obtener la lista de empleados" + e);
            throw e;
        }
    }

    private List<Employee> getListData(String url) throws RestClientException {
        return restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {})
                .getBody();
    }

    private Employee getSingleData(String url) throws RestClientException {
        return restTemplate.getForEntity(url, Employee.class).getBody();
    }
}

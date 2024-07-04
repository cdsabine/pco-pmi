package com.pco.pco.controller.subclassappuser;

import com.pco.pco.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/user/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
}

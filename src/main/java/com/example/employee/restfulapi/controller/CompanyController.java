package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {
    //在此处完成Company API
    @Autowired
    CompanyRepository companyRepository;

    @GetMapping
    public List<Company> getCompanyList() {
        return companyRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Company getCompany(@PathVariable(value = "id") Long id) {
        return companyRepository.findOne(id);
    }

    @GetMapping(value = "/{id}/employees")
    public Set<Employee> getCompanyEmployees(@PathVariable(value = "id") Long id) {
        return companyRepository.findOne(id).getEmployees();
    }

    @GetMapping(value = "/page/{pageNo}/pageSize/{pageSize}")
    public Page<Company> getPageCompany(@PathVariable(value = "pageNo") int pageNo, @PathVariable(value = "pageSize") int pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize);
        return companyRepository.findAll(pageable);
    }

    @PostMapping
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    @PutMapping(value = "/{id}")
    public Company updateCompany(@PathVariable(value = "id") Long id,
                                 @RequestParam(value = "companyName") String companyName,
                                 @RequestParam(value = "employeesNumber") Integer employeesNumber) {
        Company company = companyRepository.getOne(id);
        company.setCompanyName(companyName);
        company.setEmployeesNumber(employeesNumber);
        return companyRepository.save(company);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCompany(@PathVariable(value = "id") Long id) {
        companyRepository.delete(id);
    }
}

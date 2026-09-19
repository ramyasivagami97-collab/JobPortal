package com.example.JobPortal.Controller;

import com.example.JobPortal.Entity.Company;
import com.example.JobPortal.Entity.Job;
import com.example.JobPortal.Service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {
  private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @PostMapping("/companies")
    public ResponseEntity<Company> addCompany(@RequestBody Company company){
        Company addedCompany = companyService.addCompany(company);
        return ResponseEntity.ok(addedCompany);
    }

    @GetMapping("/companies")
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<Company>getCompanyById(@PathVariable Long id){
        Company companyById = companyService.getCompanyById(id);
        return ResponseEntity.ok(companyById);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company){
        Company updatedCompany = companyService.updateCompany(id, company);
        return ResponseEntity.ok(updatedCompany);
    }
    @GetMapping("/companies/{id}/jobs")
    public ResponseEntity<List<Job>> getJobsByCompany(@PathVariable Long id){
        List<Job> jobs = companyService.getJobsByCompany(id);
        return ResponseEntity.ok(jobs);
    }

    @DeleteMapping("/companies/{id}")
    public void deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
    }

}

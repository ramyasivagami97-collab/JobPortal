package com.example.JobPortal.Service;

import com.example.JobPortal.Entity.Company;
import com.example.JobPortal.Entity.Job;
import com.example.JobPortal.Repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    public Company addCompany(Company company){
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }
    public Company getCompanyById(Long id){
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company ID is not available"));
    }

    public Company updateCompany(Long id, Company company) {
        if (!companyRepository.existsById(id)) {
            throw new RuntimeException("Company ID is not available");
        }
        company.setId(id);
        return companyRepository.save(company);
    }

    public List<Job> getJobsByCompany(Long companyId){
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("ompany ID is not available"));

        return company.getJobs();

    }
    public void deleteCompany(Long id){
        if(!companyRepository.existsById(id)){
            throw new RuntimeException("Company ID is not available");
        }
        companyRepository.deleteById(id);
    }


}

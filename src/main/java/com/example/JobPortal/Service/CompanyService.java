package com.example.JobPortal.Service;

import com.example.JobPortal.DTO.CompanyRequestDTO;
import com.example.JobPortal.DTO.CompanyResponseDTO;
import com.example.JobPortal.DTO.JobResponseDTO;
import com.example.JobPortal.Entity.Company;
import com.example.JobPortal.Entity.Job;
import com.example.JobPortal.Exception.companyNotFoundExceptions;
import com.example.JobPortal.Repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    public CompanyResponseDTO addCompany(CompanyRequestDTO request){
        Company addedCompany = new Company();

        addedCompany.setName(request.name());
        addedCompany.setDescription(request.description());
        addedCompany.setLocation(request.location());

        Company savedCompany = companyRepository.save(addedCompany);
        return new CompanyResponseDTO(
                savedCompany.getId(),
                savedCompany.getName(),
                savedCompany.getDescription(),
                savedCompany.getLocation()
        );
    }

    public List<CompanyResponseDTO> getAllCompanies(){
        List<Company> companyList = companyRepository.findAll();
      return  companyList.stream()
                .map(company -> new CompanyResponseDTO(
                        company.getId(),
                        company.getName(),
                        company.getDescription(),
                        company.getLocation()
                )).toList();
    }
    public CompanyResponseDTO getCompanyById(Long id){

        Company getCompanyId = companyRepository.findById(id)
                .orElseThrow(() -> new companyNotFoundExceptions("No Company Found!"));

        return new CompanyResponseDTO(
                getCompanyId.getId(),
                getCompanyId.getName(),
                getCompanyId.getDescription(),
                getCompanyId.getLocation()
        );

    }

    public CompanyResponseDTO updateCompany(Long id, CompanyRequestDTO request)
    {
        Company checkedId = companyRepository.findById(id)
                .orElseThrow(() -> new companyNotFoundExceptions("No Company Found!"));

        checkedId.setName(request.name());
        checkedId.setDescription(request.description());
        checkedId.setLocation(request.location());

        Company updatedCompany = companyRepository.save(checkedId);
        return new CompanyResponseDTO(
                updatedCompany.getId(),
                updatedCompany.getName(),
                updatedCompany.getDescription(),
                updatedCompany.getLocation()
        );

   }

    public List<JobResponseDTO> getJobsByCompany(Long companyId){
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new companyNotFoundExceptions("No Company Found!"));

        List<Job> jobList = company.getJobs();

    return jobList.stream()
            .map(job -> new JobResponseDTO(
                    job.getId(),
                    job.getTitle(),
                    job.getDescription(),
                    job.getLocation(),
                    job.getSalary(),
                    job.getDepartment()
            )).toList();

    }

    public Page<CompanyResponseDTO> getCompanyPaginated(int page, int size){

        Sort sortOrder = Sort.by("location").ascending()
                .and(Sort.by("name").descending());

        Pageable pageable = PageRequest.of(page, size, sortOrder);
        Page<Company> companyPage = companyRepository.findAll(pageable);

        return companyPage.map(
                company -> new CompanyResponseDTO(
                        company.getId(),
                        company.getName(),
                        company.getDescription(),
                        company.getLocation()
                )
        );
    }

    public void deleteCompany(Long id){
        if(!companyRepository.existsById(id)){
            throw new companyNotFoundExceptions("No Company Found!");
        }
        companyRepository.deleteById(id);
    }


}

package com.example.JobPortal.Controller;

import com.example.JobPortal.DTO.CompanyRequestDTO;
import com.example.JobPortal.DTO.CompanyResponseDTO;
import com.example.JobPortal.DTO.JobResponseDTO;
import com.example.JobPortal.Service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CompanyResponseDTO> addCompany(@Valid @RequestBody CompanyRequestDTO request){
        CompanyResponseDTO addedCompany = companyService.addCompany(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCompany);
    }

    @GetMapping("/companies")
    public List<CompanyResponseDTO> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyResponseDTO>getCompanyById(@PathVariable Long id){
        CompanyResponseDTO companyById = companyService.getCompanyById(id);
        return ResponseEntity.ok(companyById);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<CompanyResponseDTO> updateCompany(@PathVariable Long id,@Valid @RequestBody CompanyRequestDTO request){
        CompanyResponseDTO updatedCompany = companyService.updateCompany(id, request);
        return ResponseEntity.ok(updatedCompany);
    }
    @GetMapping("/companies/{id}/jobs")
    public ResponseEntity<List<JobResponseDTO>> getJobsByCompany(@PathVariable Long id){
        List<JobResponseDTO> jobs = companyService.getJobsByCompany(id);
        return ResponseEntity.ok(jobs);
    }
    @GetMapping("/companies/paginated")
    public ResponseEntity<Page<CompanyResponseDTO>>getCompanyPaginated(@RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int size){
        Page<CompanyResponseDTO> companyResponseDTOPage = companyService.getCompanyPaginated(page, size);
        return ResponseEntity.ok(companyResponseDTOPage);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){

        companyService.deleteCompany(id);
        return ResponseEntity.ok("Company has been deleted!");
    }

}

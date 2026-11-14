package com.example.JobPortal.Controller;

import com.example.JobPortal.DTO.SkillsRequestDTO;
import com.example.JobPortal.DTO.SkillsResponseDTO;
import com.example.JobPortal.Entity.Skills;
import com.example.JobPortal.Service.SkillsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class SkillsController {

    private final SkillsService skillsService;

    public SkillsController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @PostMapping("/skills")
    public ResponseEntity<SkillsResponseDTO> addSkill(@Valid @RequestBody SkillsRequestDTO request){
        SkillsResponseDTO newSkills = skillsService.addSkill(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSkills);
    }

    @GetMapping("/skills")
    public List<SkillsResponseDTO> getAllSkills(){
        return skillsService.getAllSkills();
    }

    @PutMapping("/skills/{id}")
    public ResponseEntity<SkillsResponseDTO> updateSkills(@PathVariable Long id,@Valid  @RequestBody SkillsRequestDTO request){
        SkillsResponseDTO updatedSkills = skillsService.updateSkills(id, request);
        return ResponseEntity.ok(updatedSkills);
    }

    @DeleteMapping("/skills/{id}")
    public ResponseEntity<String> deleteSkills(@PathVariable Long id){
        skillsService.deleteSkills(id);
        return ResponseEntity.ok("Skill has been deleted!");
    }
}

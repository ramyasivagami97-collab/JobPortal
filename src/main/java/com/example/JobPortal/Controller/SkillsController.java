package com.example.JobPortal.Controller;

import com.example.JobPortal.Entity.Skills;
import com.example.JobPortal.Service.SkillsService;
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
    public ResponseEntity<Skills> addSkill(@RequestBody Skills skills){
        Skills newSkills = skillsService.addSkill(skills);
        return ResponseEntity.ok(newSkills);
    }

    @GetMapping("/skills")
    public List<Skills> getAllSkills(){
        return skillsService.getAllSkills();
    }

    @PutMapping("/skills/{id}")
    public ResponseEntity<Skills> updateSkills(@PathVariable Long id, @RequestBody Skills skills){
        Skills updatedSkills = skillsService.updateSkills(id, skills);
        return ResponseEntity.ok(updatedSkills);
    }

    @DeleteMapping("/skills/{id}")
    public void deleteSkills(@PathVariable Long id){
        skillsService.deleteSkills(id);
    }
}

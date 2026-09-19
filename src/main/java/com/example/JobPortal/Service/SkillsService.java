package com.example.JobPortal.Service;

import com.example.JobPortal.Entity.Skills;
import com.example.JobPortal.Repository.SkillsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillsService {

    private final SkillsRepository skillsRepository;

    public SkillsService(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }

    public Skills addSkill(Skills skills){
        return skillsRepository.save(skills);
    }

    public List<Skills> getAllSkills(){
        return skillsRepository.findAll();
    }

    public Skills updateSkills(Long id, Skills skills){
        if(! skillsRepository.existsById(id)){
            throw  new RuntimeException(" No Skills found!");
        }

        skills.setId(id);
        return skillsRepository.save(skills);
    }

    public void deleteSkills(Long id){
        if(! skillsRepository.existsById(id)){
            throw  new RuntimeException(" No Skills found!");
        }
        skillsRepository.deleteById(id);
    }


}

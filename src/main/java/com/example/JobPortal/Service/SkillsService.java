package com.example.JobPortal.Service;

import com.example.JobPortal.DTO.SkillsRequestDTO;
import com.example.JobPortal.DTO.SkillsResponseDTO;
import com.example.JobPortal.DTO.UserResponseDTO;
import com.example.JobPortal.Entity.Skills;
import com.example.JobPortal.Exception.skillNotFoundExceptions;
import com.example.JobPortal.Repository.SkillsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillsService {

    private final SkillsRepository skillsRepository;

    public SkillsService(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }

    public SkillsResponseDTO addSkill(SkillsRequestDTO request){
        Skills skill = new Skills();
        skill.setName(request.name());

        Skills savedSkills = skillsRepository.save(skill);
        return new SkillsResponseDTO(
                savedSkills.getId(),
                savedSkills.getName()
        );
    }

    public List<SkillsResponseDTO> getAllSkills(){
      List<Skills> skillsList = skillsRepository.findAll();
      List<SkillsResponseDTO> responseList = new ArrayList<>();

      for(Skills skills: skillsList){
          SkillsResponseDTO response = new SkillsResponseDTO(
                  skills.getId(),
                  skills.getName()
          );
          responseList.add(response);
      }
       return responseList;
    }

    public SkillsResponseDTO updateSkills(Long id, SkillsRequestDTO request){
       Skills existingSkill = skillsRepository.findById(id)
               .orElseThrow(() -> new skillNotFoundExceptions("Skills Not Found!"));

        existingSkill.setName(request.name());

        Skills updatedSkills = skillsRepository.save(existingSkill);

        return new SkillsResponseDTO(
                updatedSkills.getId(),
                updatedSkills.getName()
        );
    }

    public void deleteSkills(Long id){
        if(! skillsRepository.existsById(id)){
            throw  new skillNotFoundExceptions("Skills Not Found!");
        }
        skillsRepository.deleteById(id);
    }


}

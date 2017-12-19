package com.switchfully.rest.funiversity.service;

import com.switchfully.rest.funiversity.domain.Professor;
import com.switchfully.rest.funiversity.domain.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<Professor> getProfessors() {
        return professorRepository.getProfessors();
    }

    public Professor getProfessor(Integer id) {
        Professor professor = professorRepository.getProfessor(id);
        isProfessorPresent(professor);
        return professor;
    }

    public Professor updateProfessor(Integer id, Professor updatedProfessor) {
        if (updatedProfessor.getId() != null) {
            throw new IllegalArgumentException("No ID can be present on a Professor object passed to update");
        }
        isProfessorPresent(professorRepository.getProfessor(id));
        updatedProfessor.setId(id);
        return professorRepository.updateProfessor(updatedProfessor);
    }

    public Professor createProfessor(Professor professor) {
        if (professor.getId() != null) {
            throw new IllegalArgumentException("No ID can be present on a Professor object passed for creation");
        }
        return professorRepository.storeProfessor(professor);
    }

    public void deleteProfessor(Integer id) {
        isProfessorPresent(professorRepository.getProfessor(id));
        professorRepository.deleteProfessor(id);
    }

    private void isProfessorPresent(Professor professor) {
        if (professor == null) {
            throw new IllegalArgumentException("We could not find a professor for the provided ID");
        }
    }
}

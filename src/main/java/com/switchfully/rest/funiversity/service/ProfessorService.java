package com.switchfully.rest.funiversity.service;

import com.switchfully.rest.funiversity.domain.Professor;
import com.switchfully.rest.funiversity.domain.ProfessorRepository;
import com.switchfully.rest.funiversity.service.exceptions.IllegalFieldFoundException;
import com.switchfully.rest.funiversity.service.exceptions.IllegalFieldFoundException.CrudAction;
import com.switchfully.rest.funiversity.service.exceptions.UnknownResourceException;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

import static com.switchfully.rest.funiversity.service.exceptions.IllegalFieldFoundException.CrudAction.CREATE;
import static com.switchfully.rest.funiversity.service.exceptions.IllegalFieldFoundException.CrudAction.UPDATE;

@Named
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    @Inject
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<Professor> getProfessors() {
        return professorRepository.getProfessors();
    }

    public Professor getProfessor(Integer id) {
        assertProfessorIsPresent(professorRepository.getProfessor(id));
        return professorRepository.getProfessor(id);
    }

    public Professor createProfessor(Professor professor) {
        assertProfessorIdIsNotPresent(professor, CREATE);
        return professorRepository.storeProfessor(professor);
    }

    public Professor updateProfessor(Integer id, Professor updatedProfessor) {
        assertProfessorIdIsNotPresent(updatedProfessor, UPDATE);
        assertProfessorIsPresent(professorRepository.getProfessor(id));
        updatedProfessor.setId(id);
        return professorRepository.updateProfessor(updatedProfessor);
    }

    public void deleteProfessor(Integer id) {
        assertProfessorIsPresent(professorRepository.getProfessor(id));
        professorRepository.deleteProfessor(id);
    }

    private void assertProfessorIsPresent(Professor queriedProfessorById) {
        if (queriedProfessorById == null) {
            throw new UnknownResourceException("ID", Professor.class.getSimpleName());
        }
    }

    private void assertProfessorIdIsNotPresent(Professor providedProfessor, CrudAction action) {
        if (providedProfessor.getId() != null) {
            throw new IllegalFieldFoundException("ID", Professor.class.getSimpleName(), action);
        }
    }
}

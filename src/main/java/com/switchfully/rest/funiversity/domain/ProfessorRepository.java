package com.switchfully.rest.funiversity.domain;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProfessorRepository {

    private static int databaseIndex = 0;
    private Map<Integer, Professor> professors;

    public ProfessorRepository() {
        professors = new HashMap<>();
        storeProfessor(new Professor("Albert", "Einstein"));
    }

    public Professor storeProfessor(Professor professor) {
        professor.setId(databaseIndex++);
        professors.put(professor.getId(), professor);
        return professor;
    }

    public List<Professor> getProfessors() {
        return Collections.unmodifiableList(new ArrayList<>(professors.values()));
    }

    public Professor getProfessor(Integer id) {
        return professors.get(id);
    }

    public Professor updateProfessor(Professor updatedProfessor) {
        professors.put(updatedProfessor.getId(), updatedProfessor);
        return updatedProfessor;
    }

    public void deleteProfessor(Integer idToRemove) {
        professors.remove(idToRemove);
    }
}

package com.switchfully.rest.funiversity.domain;

import javax.inject.Named;
import java.util.*;

/**
 * This repository contains its own in-memory 'database'
 * Although this class will be managed by the Spring container
 * as a stateless bean (@Named), it does contain state (our 'database').
 * Obviously, this is bad practice. However, for the sake of the exercise,
 * it's the implementation we go with.
 */
@Named
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

    /**
     * A real-world production application with a real database
     * should never have a method like this!
     */
    public void clear() {
        databaseIndex = 1;
        professors.clear();
    }
}

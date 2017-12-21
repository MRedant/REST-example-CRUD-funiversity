package com.switchfully.rest.funiversity.resources;

import com.switchfully.rest.funiversity.domain.Professor;

import javax.inject.Named;

import static com.switchfully.rest.funiversity.domain.Professor.ProfessorBuilder.professor;

@Named
public class ProfessorMapper {

    ProfessorDto toDto(Professor professor) {
        return ProfessorDto.professorDto()
                .withId(professor.getId())
                .withFirstname(professor.getFirstname())
                .withLastname(professor.getLastname());
    }

    Professor toDomain(ProfessorDto professorDto) {
        return professor()
                .withId(professorDto.getId())
                .withFirstname(professorDto.getFirstname())
                .withLastname(professorDto.getLastname())
                .build();
    }

}

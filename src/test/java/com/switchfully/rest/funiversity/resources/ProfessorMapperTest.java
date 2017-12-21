package com.switchfully.rest.funiversity.resources;

import com.switchfully.rest.funiversity.domain.Professor;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static com.switchfully.rest.funiversity.domain.Professor.ProfessorBuilder.professor;
import static com.switchfully.rest.funiversity.resources.ProfessorDto.professorDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ProfessorMapperTest {

    private ProfessorMapper professorMapper;

    @Before
    public void instantiateMapper() {
        professorMapper = new ProfessorMapper();
    }

    @Test
    public void toDto_givenProfessor_thenMapAllFieldsToProfessorDto() {
        Professor professor = professor()
                .withId(5)
                .withFirstname("Marky")
                .withLastname("Markson")
                .build();

        ProfessorDto professorDto = professorMapper.toDto(professor);

        assertThat(professorDto)
                .isEqualToComparingFieldByField(professor);
    }

    @Test
    public void toDomain_givenProfessorDto_thenMapAllFieldsToProfessor() {
        ProfessorDto professorDto = professorDto()
                .withId(2)
                .withFirstname("Pauly")
                .withLastname("Paulson");

        Professor professor = professorMapper.toDomain(professorDto);

        assertThat(professor)
                .isEqualToComparingFieldByField(professorDto);
    }

}
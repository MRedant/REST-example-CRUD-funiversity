package com.switchfully.rest.funiversity.service;

import com.switchfully.rest.funiversity.domain.Professor;
import com.switchfully.rest.funiversity.domain.ProfessorRepository;
import com.switchfully.rest.funiversity.service.exceptions.IllegalFieldFoundException;
import com.switchfully.rest.funiversity.service.exceptions.UnknownResourceException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.switchfully.rest.funiversity.domain.ProfessorTestBuilder.aProfessor;
import static com.switchfully.rest.funiversity.service.exceptions.IllegalFieldFoundException.CrudAction.CREATE;
import static com.switchfully.rest.funiversity.service.exceptions.IllegalFieldFoundException.CrudAction.UPDATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProfessorServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private ProfessorRepository repository;

    @InjectMocks
    private ProfessorService service;

    @Test
    public void getProfessors_given3Professors_thenReturnListOf3Professors() {
        ArrayList<Professor> expectedProfessors = newArrayList(aProfessor().build(),
                aProfessor().build(),
                aProfessor().build());
        when(repository.getProfessors())
                .thenReturn(expectedProfessors);

        List<Professor> actualProfessors = service.getProfessors();

        assertThat(actualProfessors)
                .containsExactly(expectedProfessors.toArray(new Professor[3]));
    }

    @Test
    public void getProfessor_givenExistingId_thenReturnProfessor() {
        Professor expectedProfessor = aProfessor().withId(901).build();
        when(repository.getProfessor(901))
                .thenReturn(expectedProfessor);

        Professor actualProfessor = service.getProfessor(901);

        assertThat(actualProfessor).isEqualToComparingFieldByField(expectedProfessor);
    }

    @Test
    public void getProfessor_givenNonExistingId_thenThrowException() {
        when(repository.getProfessor(5))
                .thenReturn(null);

        expectedException.expect(UnknownResourceException.class);
        expectedException.expectMessage(String.format("We could not find a %s for the provided ID", Professor.class.getSimpleName()));

        service.getProfessor(5);
    }

    @Test
    public void createProfessor_givenAProfessorWithoutIdToCreate_thenCreateProfessor() {
        Professor providedProfessor = aProfessor()
                .withoutId()
                .withFirstname("Nemo")
                .withLastname("Fishborn")
                .build();
        Professor expectedProfessor = aProfessor()
                .withId(1)
                .withFirstname(providedProfessor.getFirstname())
                .withLastname(providedProfessor.getLastname())
                .build();
        when(repository.storeProfessor(providedProfessor)).thenReturn(expectedProfessor);

        Professor actualProfessor = service.createProfessor(providedProfessor);

        assertThat(actualProfessor).isEqualToComparingFieldByField(expectedProfessor);
    }

    @Test
    public void createProfessor_givenAProfessorWithIdToCreate_thenThrowException() {
        Professor providedProfessor = aProfessor().withId(4).build();

        expectedException.expect(IllegalFieldFoundException.class);
        expectedException.expectMessage(String.format("No ID can be present on a %s object passed for %s", Professor.class.getSimpleName(), CREATE.getLabel()));

        service.createProfessor(providedProfessor);
    }

    @Test
    public void updateProfessor_givenAnExistingIdAndAProfessorWithoutIdToUpdate_thenUpdateTheProfessor() {
        Professor providedProfessor = aProfessor()
                .withoutId()
                .withFirstname("Doggo")
                .withLastname("Doggenstein")
                .build();
        Professor expectedProfessor = aProfessor()
                .withId(65)
                .withFirstname("Doggo")
                .withLastname("Doggenstein")
                .build();
        when(repository.getProfessor(65)).thenReturn(expectedProfessor);
        when(repository.updateProfessor(providedProfessor)).thenReturn(expectedProfessor);

        Professor actualProfessor = service.updateProfessor(65, providedProfessor);

        assertThat(actualProfessor).isEqualToComparingFieldByField(expectedProfessor);
    }

    @Test
    public void updateProfessor_givenANonExistingIdAndAProfessorWithoutIdToUpdate_thenThrowException() {
        when(repository.getProfessor(65)).thenReturn(null);

        expectedException.expect(UnknownResourceException.class);
        expectedException.expectMessage(String.format("We could not find a %s for the provided ID", Professor.class.getSimpleName()));

        service.updateProfessor(65, aProfessor().withoutId().build());
    }

    @Test
    public void updateProfessor_givenAnExistingIdAndAProfessorWithIdToUpdate_thenThrowException() {
        expectedException.expect(IllegalFieldFoundException.class);
        expectedException.expectMessage(String.format("No ID can be present on a %s object passed for %s", Professor.class.getSimpleName(), UPDATE.getLabel()));

        service.updateProfessor(65, aProfessor().withId(25).build());
    }

    @Test
    public void deleteProfessor_givenAnExistingId_thenDeleteProfessor() {
        when(repository.getProfessor(5)).thenReturn(aProfessor().build());

        service.deleteProfessor(5);

        verify(repository).deleteProfessor(5);
    }

    @Test
    public void deleteProfessor_givenANonExistingId_thenThrowException() {
        when(repository.getProfessor(5)).thenReturn(null);

        expectedException.expect(UnknownResourceException.class);
        expectedException.expectMessage(String.format("We could not find a %s for the provided ID", Professor.class.getSimpleName()));

        service.deleteProfessor(5);
    }

}
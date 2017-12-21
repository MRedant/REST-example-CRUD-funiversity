package com.switchfully.rest.funiversity.resources;

import com.switchfully.rest.funiversity.Application;
import com.switchfully.rest.funiversity.domain.ProfessorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static com.switchfully.rest.funiversity.domain.ProfessorTestBuilder.aProfessor;
import static com.switchfully.rest.funiversity.resources.ProfessorDto.professorDto;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfessorControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Inject
    private ProfessorRepository repository;

    /**
     * We mimic Transactional database behavior using our clear() method.
     * Never do this for a real world application, with a real database.
     */
    @Before
    public void clearDatabase() {
        repository.clear();
    }

    @Test
    public void getProfessors(){
        repository.storeProfessor(aProfessor().build());
        repository.storeProfessor(aProfessor().build());

        ProfessorDto[] professors = new TestRestTemplate()
                .getForObject(format("http://localhost:%s/%s", port, "professors"), ProfessorDto[].class);

        assertThat(professors).hasSize(2);
    }

    @Test
    public void getProfessor(){
        repository.storeProfessor(aProfessor().withoutId().withFirstname("Niels").withLastname("Onwheels").build());
        repository.storeProfessor(aProfessor().withoutId().withFirstname("Miels").withLastname("Onfeels").build());
        repository.storeProfessor(aProfessor().withoutId().withFirstname("Piels").withLastname("Onsteels").build());

        ProfessorDto professorDto = new TestRestTemplate()
                .getForObject(format("http://localhost:%s/%s/%s", port, "professors", 2), ProfessorDto.class);

        assertThat(professorDto).isNotNull();
        assertThat(professorDto.getId()).isEqualTo(2);
        assertThat(professorDto.getFirstname()).isEqualTo("Miels");
        assertThat(professorDto.getLastname()).isEqualTo("Onfeels");
    }

    @Test
    public void createProfessor() {
        ProfessorDto professorDto = new TestRestTemplate()
                .postForObject(format("http://localhost:%s/%s", port, "professors"),
                        professorDto().withFirstname("Donald").withLastname("Truck"),
                        ProfessorDto.class);

        assertThat(professorDto.getId()).isEqualTo(1);
        assertThat(professorDto.getFirstname()).isEqualTo("Donald");
        assertThat(professorDto.getLastname()).isEqualTo("Truck");
    }

    @Test
    public void updateProfessor() {
        repository.storeProfessor(aProfessor().withoutId().withFirstname("Kim").withLastname("Kardashian").build());

        new TestRestTemplate()
                .put(format("http://localhost:%s/%s/%s", port, "professors", 1),
                        professorDto().withFirstname("Kam").withLastname("Kirdishain"),
                        ProfessorDto.class);

        assertThat(repository.getProfessor(1))
                .isEqualToComparingFieldByField(professorDto()
                        .withId(1)
                        .withFirstname("Kam")
                        .withLastname("Kirdishain")
                );
    }

    @Test
    public void deleteProfessor() {
        repository.storeProfessor(aProfessor().withoutId().withFirstname("Remo").withLastname("Vedonck").build());

        new TestRestTemplate()
                .delete(format("http://localhost:%s/%s/%s", port, "professors", 1),
                        ProfessorDto.class);

        assertThat(repository.getProfessors()).isEmpty();
    }


}
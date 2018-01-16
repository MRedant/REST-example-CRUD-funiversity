package com.switchfully.rest.funiversity.resources;

import com.switchfully.rest.funiversity.domain.Professor;
import com.switchfully.rest.funiversity.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {

    private ProfessorService professorService;
    private ProfessorMapper professorMapper;

    @Inject
    public ProfessorController(ProfessorService professorService, ProfessorMapper professorMapper) {
        this.professorService = professorService;
        this.professorMapper = professorMapper;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ProfessorDto> getProfessor() {
        return professorService.getProfessors().stream()
                .map(professorMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProfessorDto getProfessor(@PathVariable("id") Integer id) {
        return professorMapper
                .toDto(professorService.getProfessor(id));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProfessorDto createProfessor(@RequestBody Professor professor) {
        return professorMapper
                .toDto(professorService.createProfessor(professor));
    }

    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProfessorDto updateProfessor(@PathVariable Integer id, @RequestBody Professor professor) {
        return professorMapper
                .toDto(professorService.updateProfessor(id, professor));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfessor(@PathVariable Integer id) {
        professorService.deleteProfessor(id);
    }

}

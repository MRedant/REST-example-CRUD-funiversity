package com.switchfully.rest.funiversity.resources;

import com.switchfully.rest.funiversity.domain.Professor;
import com.switchfully.rest.funiversity.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {

    private ProfessorService professorService;

    @Inject
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Professor> getProfessor() {
        return professorService.getProfessors();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Professor getProfessor(@PathVariable("id") Integer id) {
        return professorService.getProfessor(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Professor createProfessor(@RequestBody Professor professor) {
        return professorService.createProfessor(professor);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Professor updateProfessor(@PathVariable Integer id, @RequestBody Professor professor) {
        return professorService.updateProfessor(id, professor);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfessor(@PathVariable Integer id) {
        professorService.deleteProfessor(id);
    }

}

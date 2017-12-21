package com.switchfully.rest.funiversity.resources;

public class ProfessorDto {

    private Integer id;
    private String firstname;
    private String lastname;

    public static ProfessorDto professorDto() {
        return new ProfessorDto();
    }

    public ProfessorDto withId(Integer id) {
        this.id = id;
        return this;
    }

    public ProfessorDto withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ProfessorDto withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}

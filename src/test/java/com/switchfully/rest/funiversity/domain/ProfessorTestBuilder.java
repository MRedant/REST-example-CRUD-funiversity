package com.switchfully.rest.funiversity.domain;

import com.switchfully.rest.funiversity.domain.Professor.ProfessorBuilder;

public class ProfessorTestBuilder {

    private Integer id;
    private String firstname;
    private String lastname;

    private ProfessorTestBuilder() {
        id = 0;
        firstname = "Fien";
        lastname = "Lammens";

    }

    public static ProfessorTestBuilder aProfessor() {
        return new ProfessorTestBuilder();
    }

    public Professor build() {
        return ProfessorBuilder.professor()
                .withId(id)
                .withFirstname(firstname)
                .withLastname(lastname)
                .build();
    }

    public ProfessorTestBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ProfessorTestBuilder withoutId() {
        this.id = null;
        return this;
    }

    public ProfessorTestBuilder withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ProfessorTestBuilder withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }
}


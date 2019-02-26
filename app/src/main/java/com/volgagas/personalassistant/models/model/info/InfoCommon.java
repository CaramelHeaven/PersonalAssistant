package com.volgagas.personalassistant.models.model.info;

import java.util.List;

/**
 * Created by CaramelHeaven on 11:02, 26/02/2019.
 */
public class InfoCommon {

    private PersonSalary salary;
    private PersonData personData;
    private List<PersonCertificates> personCertificates;
    private List<PersonSkills> personSkills;

    public PersonData getPersonData() {
        return personData;
    }

    public List<PersonCertificates> getPersonCertificates() {
        return personCertificates;
    }

    public List<PersonSkills> getPersonSkills() {
        return personSkills;
    }

    public void setPersonData(PersonData personData) {
        this.personData = personData;
    }

    public void setPersonCertificates(List<PersonCertificates> personCertificates) {
        this.personCertificates = personCertificates;
    }

    public void setPersonSkills(List<PersonSkills> personSkills) {
        this.personSkills = personSkills;
    }

    public PersonSalary getSalary() {
        return salary;
    }

    public void setSalary(PersonSalary salary) {
        this.salary = salary;
    }
}

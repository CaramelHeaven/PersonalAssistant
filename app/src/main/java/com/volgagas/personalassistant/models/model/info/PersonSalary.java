package com.volgagas.personalassistant.models.model.info;

/**
 * Created by CaramelHeaven on 11:21, 26/02/2019.
 */
public class PersonSalary {
    private String salary;

    @Override
    public String toString() {
        return "PersonSalary{" +
                "salary='" + salary + '\'' +
                '}';
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}

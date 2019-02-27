package com.volgagas.personalassistant.models.mapper.info;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.info.PersonSalary;
import com.volgagas.personalassistant.models.network.SalaryResponse;

/**
 * Created by CaramelHeaven on 11:21, 26/02/2019.
 */
public class SalaryResponseToPersonSalary extends Mapper<SalaryResponse, PersonSalary> {
    @Override
    public PersonSalary map(SalaryResponse value) {
        PersonSalary salary = new PersonSalary();
        fillData(salary, value);

        return salary;
    }

    @Override
    protected void fillData(PersonSalary personSalary, SalaryResponse salaryResponse) {
        if (salaryResponse.getValue().size() > 0) {
            personSalary.setSalary(String.valueOf(salaryResponse.getValue().get(0).getNetPay()));
        } else {
            personSalary.setSalary("ЗП неопределена");
        }
    }
}

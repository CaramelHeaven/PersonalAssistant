package com.volgagas.personalassistant.models.mapper.info;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.info.PersonData;
import com.volgagas.personalassistant.models.network.PersonDataResponse;

/**
 * Created by CaramelHeaven on 12:46, 25/02/2019.
 */
public class PersonDataResponseToPersonData extends Mapper<PersonDataResponse, PersonData> {
    @Override
    public PersonData map(PersonDataResponse value) {
        PersonData personData = new PersonData();
        fillData(personData, value);

        return personData;
    }

    @Override
    protected void fillData(PersonData personData, PersonDataResponse personDataResponse) {

    }
}

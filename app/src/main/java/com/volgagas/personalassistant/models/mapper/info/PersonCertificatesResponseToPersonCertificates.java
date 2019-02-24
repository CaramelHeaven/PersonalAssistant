package com.volgagas.personalassistant.models.mapper.info;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.info.PersonCertificates;
import com.volgagas.personalassistant.models.network.PersonCertificatesResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 14:09, 24/02/2019.
 */
public class PersonCertificatesResponseToPersonCertificates extends Mapper<PersonCertificatesResponse, List<PersonCertificates>> {
    @Override
    public List<PersonCertificates> map(PersonCertificatesResponse value) {
        List<PersonCertificates> personCertificates = new ArrayList<>();
        fillData(personCertificates, value);

        return personCertificates;
    }

    @Override
    protected void fillData(List<PersonCertificates> personCertificates, PersonCertificatesResponse response) {

    }
}

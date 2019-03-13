package com.volgagas.personalassistant.models.mapper.info;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.info.PersonCertificates;
import com.volgagas.personalassistant.models.network.PersonCertificatesResponse;
import com.volgagas.personalassistant.models.network.info.PersonCertificatesNetwork;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

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
        for (PersonCertificatesNetwork network : response.getValue()) {
            PersonCertificates certificates = new PersonCertificates();

            certificates.setEndTime(network.getEndDate());
            certificates.setNotes(network.getNotes());
            certificates.setPartyNumber(network.getPartyNumber());
            certificates.setStartTime(network.getStartDate());
            certificates.setCertificate(network.getCertificateTypeId());

            personCertificates.add(certificates);
        }
    }
}

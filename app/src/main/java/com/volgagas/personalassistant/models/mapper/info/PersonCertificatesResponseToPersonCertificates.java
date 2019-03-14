package com.volgagas.personalassistant.models.mapper.info;

import com.github.aakira.expandablelayout.Utils;
import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.info.PersonCertificates;
import com.volgagas.personalassistant.models.network.PersonCertificatesResponse;
import com.volgagas.personalassistant.models.network.info.PersonCertificatesNetwork;
import com.volgagas.personalassistant.utils.UtilsDateTimeProvider;

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

            certificates.setNotes(network.getNotes());
            certificates.setPartyNumber(network.getPartyNumber());
            certificates.setTime(mapDates(network.getStartDate(), network.getEndDate()));
            certificates.setCertificate(network.getCertificateTypeId());

            personCertificates.add(certificates);
        }
    }

    private String mapDates(String startDate, String endDate) {
        StringBuilder builder = new StringBuilder("От " + UtilsDateTimeProvider.formatBirthday(startDate) + " до ");
        String endTime = UtilsDateTimeProvider.formatBirthday(endDate);

        if (endTime.contains("1900")) {
            return builder.append("неопределенного ср.").toString();
        }

        return builder.append(endTime).toString();
    }
}

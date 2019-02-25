package com.volgagas.personalassistant.models.mapper.info;

import com.volgagas.personalassistant.models.model.info.PersonCertificates;
import com.volgagas.personalassistant.models.model.info.PersonData;
import com.volgagas.personalassistant.models.model.info.PersonSkills;
import com.volgagas.personalassistant.models.network.PersonCertificatesResponse;
import com.volgagas.personalassistant.models.network.PersonDataResponse;
import com.volgagas.personalassistant.models.network.PersonSkillsResponse;

import java.util.List;

/**
 * Created by CaramelHeaven on 14:08, 24/02/2019.
 */
public class InfoMapper {
    private final PersonCertificatesResponseToPersonCertificates personCertificatesResponseToPersonCertificates;
    private final PersonSkillsResponseToPersonSkills personSkillsResponseToPersonSkills;
    private final PersonDataResponseToPersonData personDataResponseToPersonData;

    public InfoMapper(PersonCertificatesResponseToPersonCertificates personCertificatesResponseToPersonCertificates,
                      PersonSkillsResponseToPersonSkills personSkillsResponseToPersonSkills,
                      PersonDataResponseToPersonData personDataResponseToPersonData) {
        this.personCertificatesResponseToPersonCertificates = personCertificatesResponseToPersonCertificates;
        this.personSkillsResponseToPersonSkills = personSkillsResponseToPersonSkills;
        this.personDataResponseToPersonData = personDataResponseToPersonData;
    }

    public List<PersonCertificates> map(PersonCertificatesResponse response) {
        return personCertificatesResponseToPersonCertificates.map(response);
    }

    public List<PersonSkills> map(PersonSkillsResponse response) {
        return personSkillsResponseToPersonSkills.map(response);
    }

    public PersonData map(PersonDataResponse response) {
        return personDataResponseToPersonData.map(response);
    }
}

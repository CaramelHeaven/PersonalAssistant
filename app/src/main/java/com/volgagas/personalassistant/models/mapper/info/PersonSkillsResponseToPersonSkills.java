package com.volgagas.personalassistant.models.mapper.info;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.info.PersonSkills;
import com.volgagas.personalassistant.models.network.PersonSkillsResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 14:11, 24/02/2019.
 */
public class PersonSkillsResponseToPersonSkills extends Mapper<PersonSkillsResponse, List<PersonSkills>> {
    @Override
    public List<PersonSkills> map(PersonSkillsResponse value) {
        List<PersonSkills> personSkills = new ArrayList<>();
        fillData(personSkills, value);

        return personSkills;
    }

    @Override
    protected void fillData(List<PersonSkills> personSkills, PersonSkillsResponse personSkillsResponse) {

    }
}

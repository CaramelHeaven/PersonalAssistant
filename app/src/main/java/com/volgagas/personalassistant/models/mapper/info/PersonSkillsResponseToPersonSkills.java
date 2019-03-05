package com.volgagas.personalassistant.models.mapper.info;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.info.PersonSkills;
import com.volgagas.personalassistant.models.network.PersonSkillsResponse;
import com.volgagas.personalassistant.models.network.info.PersonSkillsNetwork;

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
        for (PersonSkillsNetwork network : personSkillsResponse.getValue()) {
            PersonSkills skills = new PersonSkills();
            skills.setSkillName(network.getSkillId());
            skills.setLevel(network.getLevelId());
            initialPicture(skills);

            personSkills.add(skills);
        }
    }

    private void initialPicture(PersonSkills skills) {
        if (skills.getSkillName().contains("1")) {
            skills.setImageReference(0);
        } else if (skills.getSkillName().contains("MS Share")) {
            skills.setImageReference(R.drawable.ic_sharepoint);
        } else {
            switch (skills.getSkillName()) {
                case "MS Outlook":
                    skills.setImageReference(R.drawable.ic_outlook);
                    break;
                case "MS PowerPoint":
                    skills.setImageReference(R.drawable.ic_powerpoint);
                    break;
                case "MS Word":
                    skills.setImageReference(R.drawable.ic_word);
                    break;
                case "Service Focus":
                    skills.setImageReference(0);
                    break;
                case "Continuous Impr":
                    skills.setImageReference(0);
                    break;
                case "Self-Developmen":
                    skills.setImageReference(0);
                    break;
                case "Decision Making":
                    skills.setImageReference(0);
                    break;
                case "Responsibility":
                    skills.setImageReference(0);
                    break;
                case "Collaborating":
                    skills.setImageReference(0);
                    break;
                case "Communicating":
                    skills.setImageReference(0);
                    break;
                case "Coaching and Me":
                    skills.setImageReference(0);
                    break;
                case "Diversity":
                    skills.setImageReference(0);
                    break;
                case "Creativity":
                    skills.setImageReference(0);
                    break;
                case "Initiative":
                    skills.setImageReference(0);
                    break;
                case "Adaptability":
                    skills.setImageReference(0);
                    break;
                case "Results Focus":
                    skills.setImageReference(0);
                    break;
            }
        }
    }
}

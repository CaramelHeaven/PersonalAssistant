package com.volgagas.personalassistant.models.mapper.info;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.info.PersonSkills;
import com.volgagas.personalassistant.models.network.PersonSkillsResponse;
import com.volgagas.personalassistant.models.network.info.PersonSkillsNetwork;

import java.nio.channels.SelectionKey;
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
                case "MS Excel":
                    skills.setImageReference(R.drawable.ic_excel);
                    break;
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
                    skills.setSkillName("Целеустремленность");
                    skills.setImageReference(R.drawable.ic_service_focus);
                    break;
                case "Continuous Impr":
                    skills.setImageReference(R.drawable.ic_continuous_impovment);
                    break;
                case "Self-Developmen":
                    skills.setSkillName("Самообучаемость");
                    skills.setImageReference(R.drawable.ic_self_development);
                    break;
                case "Decision Making":
                    skills.setSkillName("Desicion Making");
                    skills.setImageReference(R.drawable.ic_decision_making);
                    break;
                case "Responsibility":
                    skills.setSkillName("Ответственность");
                    skills.setImageReference(R.drawable.ic_responsibility);
                    break;
                case "Collaborating":
                    skills.setSkillName("Сотрудничество");
                    skills.setImageReference(R.drawable.ic_collaborating);
                    break;
                case "Communicating":
                    skills.setSkillName("Коммуникативность");
                    skills.setImageReference(R.drawable.ic_communicating);
                    break;
                case "Coaching and Me":
                    skills.setSkillName("Тренировочный скилл");
                    skills.setImageReference(R.drawable.ic_coaching_and_me);
                    break;
                case "Diversity":
                    skills.setSkillName("Разнообразность");
                    skills.setImageReference(R.drawable.ic_diversity);
                    break;
                case "Creativity":
                    skills.setSkillName("Креативность");
                    skills.setImageReference(R.drawable.ic_creativity);
                    break;
                case "Initiative":
                    skills.setImageReference(0);
                    break;
                case "Adaptability":
                    skills.setImageReference(0);
                    break;
                case "Results Focus":
                    skills.setSkillName("Результативность");
                    skills.setImageReference(R.drawable.ic_results_focus);
                    break;
            }
        }
    }
}

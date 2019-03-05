package com.volgagas.personalassistant.models.model.info;

import android.content.Context;

/**
 * Created by CaramelHeaven on 13:42, 24/02/2019.
 */
public class PersonSkills {
    private String skillName;
    private String level;
    private int imageReference;

    @Override
    public String toString() {
        return "PersonSkills{" +
                "skillName='" + skillName + '\'' +
                ", level='" + level + '\'' +
                ", imageReference=" + imageReference +
                '}';
    }

    public int getIntId(Context context, int imageInt) {
        String name = context.getResources().getResourceEntryName(imageInt);
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getImageReference() {
        return imageReference;
    }

    public void setImageReference(int imageReference) {
        this.imageReference = imageReference;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

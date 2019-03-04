package com.volgagas.personalassistant.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volgagas.personalassistant.models.network.info.PersonSkillsNetwork;

import java.util.List;

/**
 * Created by CaramelHeaven on 13:48, 24/02/2019.
 */
public class PersonSkillsResponse {
    @SerializedName("value")
    @Expose
    private List<PersonSkillsNetwork> value = null;

    public List<PersonSkillsNetwork> getValue() {
        return value;
    }
}

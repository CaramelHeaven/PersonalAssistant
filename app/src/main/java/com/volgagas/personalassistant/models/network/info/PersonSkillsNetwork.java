package com.volgagas.personalassistant.models.network.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 19:21, 04/03/2019.
 */
public class PersonSkillsNetwork {
    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("PartyNumber")
    @Expose
    private String partyNumber;
    @SerializedName("LevelType")
    @Expose
    private String levelType;
    @SerializedName("SkillId")
    @Expose
    private String skillId;
    @SerializedName("LevelDate")
    @Expose
    private String levelDate;
    @SerializedName("Certifier")
    @Expose
    private String certifier;
    @SerializedName("RatingId")
    @Expose
    private String ratingId;
    @SerializedName("Verified")
    @Expose
    private String verified;
    @SerializedName("YearsOfExperience")
    @Expose
    private Integer yearsOfExperience;
    @SerializedName("RatingLevelExaminer")
    @Expose
    private String ratingLevelExaminer;
    @SerializedName("LevelId")
    @Expose
    private String levelId;

    public String getOdataEtag() {
        return odataEtag;
    }

    public String getPartyNumber() {
        return partyNumber;
    }

    public String getLevelType() {
        return levelType;
    }

    public String getSkillId() {
        return skillId;
    }

    public String getLevelDate() {
        return levelDate;
    }

    public String getCertifier() {
        return certifier;
    }

    public String getRatingId() {
        return ratingId;
    }

    public String getVerified() {
        return verified;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public String getRatingLevelExaminer() {
        return ratingLevelExaminer;
    }

    public String getLevelId() {
        return levelId;
    }
}

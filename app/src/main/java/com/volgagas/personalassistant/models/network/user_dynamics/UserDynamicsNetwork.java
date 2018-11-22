package com.volgagas.personalassistant.models.network.user_dynamics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 17:48, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class UserDynamicsNetwork {
    @SerializedName("PersonnelNumber")
    @Expose
    private String personnelNumber;
    @SerializedName("PersonBirthCountryRegion")
    @Expose
    private String personBirthCountryRegion;
    @SerializedName("ElectronicLocationId")
    @Expose
    private String electronicLocationId;
    @SerializedName("MotherBirthCountryRegion")
    @Expose
    private String motherBirthCountryRegion;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("CitizenshipCountryRegion")
    @Expose
    private String citizenshipCountryRegion;
    @SerializedName("NativeLanguageId")
    @Expose
    private String nativeLanguageId;
    @SerializedName("NameAlias")
    @Expose
    private String nameAlias;
    @SerializedName("LastNamePrefix")
    @Expose
    private String lastNamePrefix;
    @SerializedName("PhoneticMiddleName")
    @Expose
    private String phoneticMiddleName;
    @SerializedName("KnownAs")
    @Expose
    private String knownAs;
    @SerializedName("DeceasedDate")
    @Expose
    private String deceasedDate;
    @SerializedName("PersonalTitle")
    @Expose
    private String personalTitle;
    @SerializedName("ProfessionalTitle")
    @Expose
    private String professionalTitle;
    @SerializedName("NationalityCountryRegion")
    @Expose
    private String nationalityCountryRegion;
    @SerializedName("NameSequenceDisplayAs")
    @Expose
    private String nameSequenceDisplayAs;
    @SerializedName("PersonalSuffix")
    @Expose
    private String personalSuffix;
    @SerializedName("IsFulltimeStudent")
    @Expose
    private String isFulltimeStudent;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("DisabledVerificationDate")
    @Expose
    private String disabledVerificationDate;
    @SerializedName("BirthDate")
    @Expose
    private String birthDate;
    @SerializedName("PartyNumber")
    @Expose
    private String partyNumber;
    @SerializedName("PartyType")
    @Expose
    private String partyType;
    @SerializedName("IsDisabled")
    @Expose
    private String isDisabled;
    @SerializedName("FatherBirthCountryRegion")
    @Expose
    private String fatherBirthCountryRegion;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("EthnicOriginId")
    @Expose
    private String ethnicOriginId;
    @SerializedName("AllowRehire")
    @Expose
    private String allowRehire;
    @SerializedName("PhoneticLastName")
    @Expose
    private String phoneticLastName;
    @SerializedName("Education")
    @Expose
    private String education;
    @SerializedName("PhoneticFirstName")
    @Expose
    private String phoneticFirstName;
    @SerializedName("ProfessionalSuffix")
    @Expose
    private String professionalSuffix;
    @SerializedName("LanguageId")
    @Expose
    private String languageId;
    @SerializedName("MiddleName")
    @Expose
    private String middleName;
    @SerializedName("PersonBirthCity")
    @Expose
    private String personBirthCity;

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public String getPersonBirthCountryRegion() {
        return personBirthCountryRegion;
    }

    public String getElectronicLocationId() {
        return electronicLocationId;
    }

    public String getMotherBirthCountryRegion() {
        return motherBirthCountryRegion;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCitizenshipCountryRegion() {
        return citizenshipCountryRegion;
    }

    public String getNativeLanguageId() {
        return nativeLanguageId;
    }

    public String getNameAlias() {
        return nameAlias;
    }

    public String getLastNamePrefix() {
        return lastNamePrefix;
    }

    public String getPhoneticMiddleName() {
        return phoneticMiddleName;
    }

    public String getKnownAs() {
        return knownAs;
    }

    public String getDeceasedDate() {
        return deceasedDate;
    }

    public String getPersonalTitle() {
        return personalTitle;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public String getNationalityCountryRegion() {
        return nationalityCountryRegion;
    }

    public String getNameSequenceDisplayAs() {
        return nameSequenceDisplayAs;
    }

    public String getPersonalSuffix() {
        return personalSuffix;
    }

    public String getIsFulltimeStudent() {
        return isFulltimeStudent;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGender() {
        return gender;
    }

    public String getDisabledVerificationDate() {
        return disabledVerificationDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPartyNumber() {
        return partyNumber;
    }

    public String getPartyType() {
        return partyType;
    }

    public String getIsDisabled() {
        return isDisabled;
    }

    public String getFatherBirthCountryRegion() {
        return fatherBirthCountryRegion;
    }

    public String getName() {
        return name;
    }

    public String getEthnicOriginId() {
        return ethnicOriginId;
    }

    public String getAllowRehire() {
        return allowRehire;
    }

    public String getPhoneticLastName() {
        return phoneticLastName;
    }

    public String getEducation() {
        return education;
    }

    public String getPhoneticFirstName() {
        return phoneticFirstName;
    }

    public String getProfessionalSuffix() {
        return professionalSuffix;
    }

    public String getLanguageId() {
        return languageId;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPersonBirthCity() {
        return personBirthCity;
    }
}

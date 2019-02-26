package com.volgagas.personalassistant.models.model.info;

/**
 * Created by CaramelHeaven on 12:46, 25/02/2019.
 */
public class PersonData {
    private String birthDate;
    private String addressStreet;
    private String contactPhone;

    @Override
    public String toString() {
        return "PersonData{" +
                "birthDate='" + birthDate + '\'' +
                ", addressStreet='" + addressStreet + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                '}';
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}

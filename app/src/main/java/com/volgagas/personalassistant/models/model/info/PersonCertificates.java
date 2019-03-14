package com.volgagas.personalassistant.models.model.info;

/**
 * Created by CaramelHeaven on 13:41, 24/02/2019.
 */
public class PersonCertificates {
    private String partyNumber;
    private String time;
    private String notes;
    private String certificate;

    @Override
    public String toString() {
        return "PersonCertificates{" +
                "partyNumber='" + partyNumber + '\'' +
                ", time='" + time + '\'' +
                ", notes='" + notes + '\'' +
                ", certificate='" + certificate + '\'' +
                '}';
    }

    public String getPartyNumber() {
        return partyNumber;
    }

    public void setPartyNumber(String partyNumber) {
        this.partyNumber = partyNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

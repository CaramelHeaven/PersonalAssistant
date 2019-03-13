package com.volgagas.personalassistant.models.model.info;

/**
 * Created by CaramelHeaven on 13:41, 24/02/2019.
 */
public class PersonCertificates {
    private String partyNumber;
    private String startTime;
    private String endTime;
    private String notes;
    private String certificate;

    @Override
    public String toString() {
        return "PersonCertificates{" +
                "partyNumber='" + partyNumber + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
}

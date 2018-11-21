package com.volgagas.personalassistant.models.model;

/**
 * Created by CaramelHeaven on 7:38, 21.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class Contract {
    private String id;
    private String title;
    private String description;
    private String contractor;
    private String dateEnd;
    private String projectName;
    private String typeOfDocument;
    private String personCreated;
    private String createdTime;

    private boolean testedByAnEconomist;
    private boolean testedByAnDirectorApproval;
    private String subdivisionName;
    private String informedNames;
    private String sumOfContractor;
    private String taskStatus;

    private String executiveDirector;

    @Override
    public String toString() {
        return "Contract{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", contractor='" + contractor + '\'' +
                ", dateEnd='" + dateEnd + '\'' +
                ", projectName='" + projectName + '\'' +
                ", typeOfDocument='" + typeOfDocument + '\'' +
                ", personCreated='" + personCreated + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", testedByAnEconomist=" + testedByAnEconomist +
                ", testedByAnDirectorApproval=" + testedByAnDirectorApproval +
                ", subdivisionName='" + subdivisionName + '\'' +
                ", informedNames='" + informedNames + '\'' +
                ", sumOfContractor='" + sumOfContractor + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                ", executiveDirector='" + executiveDirector + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTypeOfDocument() {
        return typeOfDocument;
    }

    public void setTypeOfDocument(String typeOfDocument) {
        this.typeOfDocument = typeOfDocument;
    }

    public String getPersonCreated() {
        return personCreated;
    }

    public void setPersonCreated(String personCreated) {
        this.personCreated = personCreated;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isTestedByAnEconomist() {
        return testedByAnEconomist;
    }

    public void setTestedByAnEconomist(boolean testedByAnEconomist) {
        this.testedByAnEconomist = testedByAnEconomist;
    }

    public boolean isTestedByAnDirectorApproval() {
        return testedByAnDirectorApproval;
    }

    public void setTestedByAnDirectorApproval(boolean testedByAnDirectorApproval) {
        this.testedByAnDirectorApproval = testedByAnDirectorApproval;
    }

    public String getSubdivisionName() {
        return subdivisionName;
    }

    public void setSubdivisionName(String subdivisionName) {
        this.subdivisionName = subdivisionName;
    }

    public String getInformedNames() {
        return informedNames;
    }

    public void setInformedNames(String informedNames) {
        this.informedNames = informedNames;
    }

    public String getSumOfContractor() {
        return sumOfContractor;
    }

    public void setSumOfContractor(String sumOfContractor) {
        this.sumOfContractor = sumOfContractor;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getExecutiveDirector() {
        return executiveDirector;
    }

    public void setExecutiveDirector(String executiveDirector) {
        this.executiveDirector = executiveDirector;
    }
}

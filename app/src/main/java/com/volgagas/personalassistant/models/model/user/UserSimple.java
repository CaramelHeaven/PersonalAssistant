package com.volgagas.personalassistant.models.model.user;

/**
 * Created by CaramelHeaven on 17:13, 09/01/2019.
 */
public class UserSimple {
    private String id;
    private String name;
    private String photo;

    @Override
    public String toString() {
        return "UserSimple{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

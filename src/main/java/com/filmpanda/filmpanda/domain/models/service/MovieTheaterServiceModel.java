package com.filmpanda.filmpanda.domain.models.service;

import java.util.List;

public class MovieTheaterServiceModel {

    private String id;
    private String name;
    private String address;
    private String link;
    private String phoneNumber;
    private List<ScreeningServiceModel> screenings;

    public MovieTheaterServiceModel() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ScreeningServiceModel> getScreenings() {
        return screenings;
    }

    public void setScreenings(List<ScreeningServiceModel> screenings) {
        this.screenings = screenings;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

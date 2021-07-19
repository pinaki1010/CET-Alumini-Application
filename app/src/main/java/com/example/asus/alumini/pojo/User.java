package com.example.asus.alumini.pojo;

public class User {
    private String name;
    private String email;
    private String phone;
    private String roll;
    private String age;
    private String gender;
    private String photo;
    private String type;
    private String year;

    public User(String name, String email, String phone, String roll, String age, String gender, String photo, String type, String year) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.roll = roll;
        this.age = age;
        this.gender = gender;
        this.photo = photo;
        this.type = type;
        this.year = year;
    }

    public User() {
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

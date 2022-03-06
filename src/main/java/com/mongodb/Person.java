package com.mongodb;

import java.util.List;

public class Person {
    private String firstname;
    private String surename;
    private String twitter;
    private int age;
    private List<Address> addresses;

    public String getFirstname() {
        return firstname;
    }

    public Person setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getSurename() {
        return surename;
    }

    public Person setSurename(String surename) {
        this.surename = surename;
        return this;
    }

    public String getTwitter() {
        return twitter;
    }

    public Person setTwitter(String twitter) {
        this.twitter = twitter;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public Person setAddresses(List<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", surename='" + surename + '\'' +
                ", twitter='" + twitter + '\'' +
                ", age=" + age +
                ", addresses=" + addresses +
                '}';
    }
}

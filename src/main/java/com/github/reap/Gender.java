package com.github.reap;

public enum Gender {
    MALE("Mies"), FEMALE("Nainen");
    
    private final String name;

    private Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

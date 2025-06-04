package org.rewedigital.models;

public class Person extends Owner {
    private String ignId;

    public String getIgnId() {
        return ignId;
    }

    public Person(String ignId, String name) {
        super(name);
        this.ignId = ignId;
    }
}

package org.rewedigital.models;

public class Company extends Owner {
    private String bId;

    public String getbId() {
        return bId;
    }

    public Company(String bId, String name) {
        super(name);
        this.bId = bId;
    }
}

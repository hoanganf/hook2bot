package com.anit.domain.model.value;

/**
 * @author anit
 */
public class Ref extends StringValue {
    private Ref(String value) {
        super(value);
    }

    public static Ref of(String value) {
        return new Ref(value);
    }

    public boolean isMaster() {
        return getValue().equals("refs/heads/master");
    }

    public boolean isProduction() {
        return getValue().equals("refs/heads/production");
    }

    public boolean isStaging() {
        return getValue().equals("refs/heads/staging");
    }

    public String extractBranch() {
        return getValue().split("/")[2];
    }

}


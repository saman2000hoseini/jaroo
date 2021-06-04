package model;

import java.io.Serializable;

public class Handshake implements Serializable {
    private final String username;
    private final boolean joined;

    public Handshake(String username, boolean joined) {
        this.username = username;
        this.joined = joined;
    }

    public String getUsername() {
        return username;
    }

    public boolean joined() {
        return joined;
    }
}

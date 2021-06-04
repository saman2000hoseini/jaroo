package model;

import java.io.Serializable;
import java.util.Vector;

public class OnlineUsers implements Serializable {
    private final Vector<String> users;

    public OnlineUsers(Vector<String> users) {
        this.users = users;
    }

    public Vector<String> getUsers() {
        return users;
    }
}

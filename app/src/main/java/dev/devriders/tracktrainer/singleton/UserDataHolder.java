package dev.devriders.tracktrainer.singleton;

import dev.devriders.tracktrainer.models.Usuario;

public class UserDataHolder {
    private static UserDataHolder instance;
    private Usuario currentUser;

    private UserDataHolder() {
        // Constructor privado
    }

    public static synchronized UserDataHolder getInstance() {
        if (instance == null) {
            instance = new UserDataHolder();
        }
        return instance;
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }
}

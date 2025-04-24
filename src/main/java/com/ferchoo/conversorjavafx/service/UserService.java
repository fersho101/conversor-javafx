package com.ferchoo.conversorjavafx.service;

import com.ferchoo.conversorjavafx.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Map<String, User> usersDatabase;

    public UserService() {
        this.usersDatabase = new HashMap<>();
        //registro prueba
        register("admin", "admin123");
    }

    public boolean register(String username, String password) {
        if (usersDatabase.containsKey(username)) {
            return false;
        }
        usersDatabase.put(username, new User(username, password));
        return true;
    }

    /**
     * Autentica un usuario
     *
     * @return El objeto User si las credenciales son válidas, null en caso contrario
     */
    public boolean authenticate(String username, String password) {
        User user = usersDatabase.get(username);
        return user != null && user.getPassword().equals(password);

    }

    /**
     * Obtiene todos los usuarios (útil para debugging)
     */
    public Map<String, User> getAllUsers() {
        return new HashMap<>(usersDatabase);
    }


}

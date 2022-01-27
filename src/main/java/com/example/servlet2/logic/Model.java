package com.example.servlet2.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {

    private static final Model instance = new Model();

    private Map<Integer, User> model;

    public static Model getInstance(){
        return instance;
    }

    private Model(){
        model = new HashMap<>();

        model.put(1, new User("Ivan", "Ivanov", 55555));
        model.put(2, new User("Petr", "Petrov", 66666));
        model.put(3, new User("Sidor", "Sidorov", 77777));
    }

    public void add(User user, int id){
        model.put(id, user);
    }

    public Map<Integer, User> getFromList(){
        return model;
    }

    public void deleteUser(int id){
        model.remove(id);
    }

    public User updateUser(int id, String name, String surname, double salary){
        model.get(id).setName(name);
        model.get(id).setSurname(surname);
        model.get(id).setSalary(salary);
        return model.get(id);
    }

    public boolean isExists(int id){
        return model.containsKey(id);
    }
}

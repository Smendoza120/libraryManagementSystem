package services;

import models.User;
import structures.LinkedList;

public class UserService {
    private LinkedList userList;

    public UserService() {
        this.userList = new LinkedList();
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public User findUserById(String id) {
        return userList.findById(id);
    }
}

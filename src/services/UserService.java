package services;

import models.User;
import structures.LinkedList;

import java.util.List;

public class UserService {
    private LinkedList userList;
    private static int userCounter = 1;

    public UserService() {
        this.userList = new LinkedList();
    }

    public void addUser(User user) {
        user.setId(generateUserId());
        userList.add(user);
    }

    public User findUserById(String id) {
        return userList.findById(id);
    }

    public List<User> findAllUsers() {
        return userList.getAllUsers();
    }

    public User findUserByNameOrDocument(String searchValue) {
        if (searchValue.matches("\\d+")) {
            return userList.findByDocumentNumber(searchValue);
        } else {
            return userList.findByName(searchValue);
        }
    }

    public List<User> getAllUsers() {
        return userList.getAllUsers();
    }

    private String generateUserId() {
        return "U" + (userCounter++);
    }
}


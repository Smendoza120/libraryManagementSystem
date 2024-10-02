package structures;

import models.User;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedList implements Iterable<User> {
    private Node head;

    private class Node {
        User user;
        Node next;

        public Node(User user) {
            this.user = user;
            this.next = null;
        }
    }

    public void add(User user) {
        Node newNode = new Node(user);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public User findById(String id) {
        Node temp = head;

        while (temp != null) {
            if (temp.user.getId().equals(id)) {
                return temp.user;
            }
            temp = temp.next;
        }
        return null;
    }

    public User findByName(String name) {
        Node temp = head;

        while (temp != null) {
            if (temp.user.getName().equalsIgnoreCase(name)) {
                return temp.user;
            }
            temp = temp.next;
        }

        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Node temp = head;

        while (temp != null) {
            users.add(temp.user);
            temp = temp.next;
        }

        return users;
    }

    public User findByDocumentNumber(String documentNumber) {
        Node temp = head;

        while (temp != null) {

            if (temp.user.getDocumentNumber().equals(documentNumber)) {
                return temp.user;
            }
            temp = temp.next;
        }

        return null;
    }

    @Override
    public Iterator<User> iterator() {
        return new Iterator<User>() {
            private LinkedList.Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public User next() {
                User user = current.user;
                current = current.next;
                return user;
            }
        };
    }
}

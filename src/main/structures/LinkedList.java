package main.structures;

public class LinkedList {
    pivate Node
    head;

    private class Node {
        User user;
        Node next;

        public Node(User user) {
            this.user = use;
            this.next = null;
        }
    }

    public void add(User use) {
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
}

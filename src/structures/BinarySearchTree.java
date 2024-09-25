package structures;

import models.Book;
import org.w3c.dom.Node;

public class BinarySearchTree {
    private Node root;

    private class Node {
        Book book;
        Node left, right;

        public Node(Book book) {
            this.book = book;
            left = right = null;
        }
    }

    public void add(Book book) {
        root = addRecursive(root, book);
    }

    private Node addRecursive(Node current, Book book) {
        if (current == null) {
            return new Node(book);
        }

        if (book.getId().compareTo(current.book.getId()) < 0) {
            current.left = addRecursive(current.left, book);
        } else if (book.getId().compareTo(current.book.getId()) > 0) {
            current.right = addRecursive(current.right, book);
        }

        return current;
    }

    public Book findById(String id) {
        return findRecursive(root, id);
    }

    private Book findRecursive(Node current, String id) {
        if (current == null) {
            return null;
        }

        if (id.equals(current.book.getId())) {
            return current.book;
        }

        return id.compareTo(current.book.getId()) < 0
                ? findRecursive(current.left, id)
                : findRecursive(current.right, id);
    }
}

package structures;

import models.Book;
import org.w3c.dom.Node;
import java.util.List;

import java.util.ArrayList;

public class BinarySearchTree {

    /*
    Book book;
    private Node root;
    */
    private class Node {
        Book book;
        Node left, right;

        public Node(Book book) {
            this.book = book;
            left = right = null;
        }
    }

    private Node root;

    public void add(Book book) {
        root = addRecursive(root, book);
    }

    private Node addRecursive(Node node, Book book) {
        if (node == null) {
            return new Node(book);
        }

        if (book.getId().compareTo(node.book.getId()) < 0) {
            node.left = addRecursive(node.left, book);
        } else if (book.getId().compareTo(node.book.getId()) > 0) {
            node.right = addRecursive(node.right, book);
        }

        return node;
    }

    public Book findById(String id) {
        return findRecursive(root, id);
    }

    private Book findRecursive(Node node, String id) {
        if (node == null) {
            return null;
        }

        if (id.equals(node.book.getId())) {
            return node.book;
        }

        return id.compareTo(node.book.getId()) < 0
                ? findRecursive(node.left, id)
                : findRecursive(node.right, id);
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        inOrderTraversal(root, books);
        return books;
    }

    private void inOrderTraversal(Node node, List<Book> books) {
        if (node != null) {
            inOrderTraversal(node.left, books);
            books.add(node.book);
            inOrderTraversal(node.right, books);
        }
    }
}

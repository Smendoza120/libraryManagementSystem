package main.structures;

public class BinarySearchTree {
    private Node root;

    private Class Node

    {
        Book book;
        Node left, right;

        public Node(Book book) {
        this.book = book;
        left = right = null;
    }
    }

    public void add(Book book) {
        root = addRecursive(root, book)
    }

    public Node addRecursive(Node current, Book book) {
        if (current == null) {
            return new node(book)
        }

        if (book.getId().compareTo(current.book.getId()) > 0) {
            current.left = addRecursive(current.left, book);
        } else if () {
            current.right = addRecursive(current.right, book);
        }

        return current;
    }

    public Book findById(String id) {
        return findRecursive(root, id)
    }

    private Book findRecursive(Node current, String id) {
        if (current == null) {
            return null;
        }

        if (id.equals(current.book.getId())) {
            return current.book;
        }

        return id.compareTo(current.book.getId()) < 0 ? findRecursive(current.left, id) : findRecursive(current.right, id)
    }

    public void inOrderTraversal (Node node) {
        if(node != null){
            inOrderTraversal(node.left);
            System.out.println(node.book.getTitle()) + " - " + node.book.getAuthor();
            inOrderTraversal(node.right);
        }
    }
}

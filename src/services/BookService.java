package services;

import models.Book;
import structures.BinarySearchTree;

public class BookService {
    private BinarySearchTree bookTree;

    public BookService() {
        this.bookTree = new BinarySearchTree();
    }

    public void addBook(Book book) {
        bookTree.add(book);
    }

    public Book findBookById(String id) {
        return bookTree.findById(id);
    }
}

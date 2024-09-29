package services;

import models.Book;
import structures.BinarySearchTree;

import java.util.List;
import java.util.ArrayList;

public class BookService {
    private BinarySearchTree bookTree;
    private static int bookCounter = 1;
    private List<Book> books = new ArrayList();

    public BookService() {
        this.bookTree = new BinarySearchTree();
    }

    public void addBook(Book book) {
        book.setId(generateBookId());
        bookTree.add(book);
        books.add(book);
    }

    public Book findBookById(String id) {
        return bookTree.findById(id);
    }

    public Book findBookByQuery(String query) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(query) || book.getAuthor().equalsIgnoreCase(query) || book.getGenre().equalsIgnoreCase(query)) {
                return book;
            }
        }
        return null;
    }


    public List<Book> searchBooks(String query) {
        List<Book> matchingBooks = new ArrayList<>();

        for (Book book : books) {
            boolean matchesTitle = book.getTitle().equalsIgnoreCase(query);
            boolean matchesAuthor = book.getAuthor().equalsIgnoreCase(query);
            boolean matchesGenre = book.getGenre().equalsIgnoreCase(query);

            if (matchesTitle || matchesAuthor || matchesGenre) {
                matchingBooks.add(book);
            }

        }

        return matchingBooks;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public String generateBookId() {
        return "B" + (bookCounter++);
    }
}

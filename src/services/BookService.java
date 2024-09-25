package services;

import models.Book;
import structures.BinarySearchTree;

import java.util.List;
import java.util.ArrayList;

public class BookService {
    private BinarySearchTree bookTree;
    private static int bookCounter = 1;

    public BookService() {
        this.bookTree = new BinarySearchTree();
    }

    public void addBook(Book book) {
        book.setId(generateBookId());
        bookTree.add(book);
    }

    public Book findBookById(String id) {
        return bookTree.findById(id);
    }

    public List<Book> searchBooks(String title, String author, String genre) {
        List<Book> matchingBooks = new ArrayList<>();

        if (title == null) title = "";
        if (author == null) author = "";
        if (genre == null) genre = "";

        for (Book book : bookTree.getAllBooks()) {
            boolean matches = true; // Suponemos que coincide hasta que se demuestre lo contrario

            if (!title.isEmpty() && !book.getTitle().equalsIgnoreCase(title)) {
                matches = false; // No coincide en el título
            }
            if (!author.isEmpty() && !book.getAuthor().equalsIgnoreCase(author)) {
                matches = false; // No coincide en el autor
            }
            if (!genre.isEmpty() && !book.getGenre().equalsIgnoreCase(genre)) {
                matches = false; // No coincide en el género
            }

            if (matches) {
                matchingBooks.add(book); // Agregamos el libro que coincide
            }
        }

        return matchingBooks;
    }

    public List<Book> getAllBooks() {
        return bookTree.getAllBooks();
    }

    public String generateBookId() {
        return "B" + (bookCounter++);
    }
}

package controllers;

import models.Book;
import models.User;
import services.BookService;
import services.UserService;

public class LibraryController {
    private BookService bookService;
    private UserService userService;

    public LibraryController() {
        this.bookService = new BookService();
        this.userService = new UserService();
    }

    public void addBook(Book book) {
        bookService.addBook(book);
    }

    public void addUser(User user) {
        userService.addUser(user);
    }

    public Book findBookById(String id) {
        return bookService.findBookById(id);
    }

    public User findUserById(String id) {
        return userService.findUserById(id);
    }
}

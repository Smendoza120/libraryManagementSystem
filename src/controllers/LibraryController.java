package controllers;

import models.Book;
import models.User;
import services.BookService;
import services.LoanService;
import services.UserService;

import java.time.LocalDate;
import java.util.List;

public class LibraryController {
    private BookService bookService;
    private UserService userService;
    private LoanService loanService;

    public LibraryController() {
        this.userService = new UserService();
        this.bookService = new BookService();
        this.loanService = new LoanService(userService, bookService);
    }

    //Library section
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public void addBook(Book book) {
        bookService.addBook(book);
    }

    public Book findBookById(String id) {
        return bookService.findBookById(id);
    }

    public User findUserById(String id) {
        return userService.findUserById(id);
    }

    public List<Book> searchBooks(String query) {
        return bookService.searchBooks(query);
    }

    //User Section
    public void addUser(User user) {
        userService.addUser(user);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public User searchUser(String searchValue) {
        return userService.findUserByNameOrDocument(searchValue);
    }

    //Loan Section
    public void loanBook(String userQuery, String bookQuery) {
        User user = userService.findUserByNameOrDocument(userQuery);
        Book book = bookService.findBookByQuery(bookQuery);

        if (book != null && user != null) {
            LocalDate loanDate = LocalDate.now();
            LocalDate returnDate = loanDate.plusWeeks(2);

            loanService.addLoan(user, book, loanDate, returnDate);
        } else {
            System.out.println("Libro o usuario no encontrado.");
        }
    }

    public void returnBook(String bookId, String userId) {
        Book book = bookService.findBookById(bookId);
        User user = userService.findUserById(userId);

        if (book != null && user != null) {
            loanService.returnBook(book, user);
        } else {
            System.out.println("Libro o usuario no encontrado.");
        }
    }

    public void listUserLoans(String userId) {
        User user = userService.findUserById(userId);

        if (user != null) {
            loanService.getLoansByUser(user).forEach(loan -> {
                System.out.println("Libro: " + loan.getBook().getTitle() + ", Fecha de préstamo: " + loan.getLoanDate());
            });
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}

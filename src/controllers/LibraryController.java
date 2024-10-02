package controllers;

import models.Book;
import models.Loan;
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

    public void returnBook(String bookQuery, String userQuery) {
        User user = userService.findUserByNameOrDocument(userQuery);
        if (user == null) {
            System.out.println("Usuario no encontrado con la búsqueda: " + userQuery);
        } else {
            System.out.println("Usuario encontrado: " + user.getName() + " (Documento: " + user.getDocumentNumber() + ")");
        }

        Book book = bookService.findBookByQuery(bookQuery);
        if (book == null) {
            System.out.println("Libro no encontrado con la búsqueda: " + bookQuery);
        } else {
            System.out.println("Libro encontrado: " + book.getTitle() + " (Autor: " + book.getAuthor() + ")");
        }

        if (book != null && user != null) {
            loanService.returnBook(book, user);
        } else {
            System.out.println("Libro o usuario no encontrado. No se puede realizar la devolución.");
        }
    }

    public void listUserLoans(String userQuery) {
        User user = userService.findUserByNameOrDocument(userQuery);

        if (user != null) {
            List<Loan> loans = loanService.getLoansByUser(user);

            if (loans.isEmpty()) {
                System.out.println("No hay libros prestados para el usuario: " + user.getName());
            } else {
                System.out.println("--- Libros prestados por " + user.getName() + " ---");

                for (Loan loan : loans) {
                    System.out.println("Título: " + loan.getBook().getTitle() +
                            ", Fecha de préstamo: " + loan.getLoanDate() +
                            ", Fecha de devolución: " + loan.getReturnDate());
                }
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    public void listAllLoans() {
        List<Loan> allLoans = loanService.getAllLoans();

        if (allLoans.isEmpty()) {
            System.out.println("No hay libros prestados.");
        } else {
            System.out.println("--- Lista de libros prestados ---");
            for (Loan loan : allLoans) {
                System.out.println("Título: " + loan.getBook().getTitle() +
                        ", Prestado a: " + loan.getUser().getName() +
                        ", Fecha de préstamo: " + loan.getLoanDate() +
                        ", Fecha de devolución: " + loan.getReturnDate());
            }
        }
    }
}

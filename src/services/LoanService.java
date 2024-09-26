package services;

import models.Book;
import models.Loan;
import models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanService {
    private List<Loan> loanList = new ArrayList<>();
    private UserService userService;
    private BookService bookService;

    public LoanService(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    public void addLoan(String userQuery, String bookId) {
        if (!book.isAvailable()) {
            System.out.println("El libro ya está prestado.");
            return false;
        }

        LocalDate loanDate = LocalDate.now();
        LocalDate returnDate = loanDate.plusWeeks(2);
        Loan loan = new Loan(book, user, loanDate, returnDate);

        book.setCurrentLoan(loan);
        user.addLoan(loan);
        loans.add(loan);

        return true;
    }

    public void returnBook(Book book, User user) {
        Loan loan = book.getCurrentLoan();

        if (loan != null && loan.getUser().equals(user)) {
            book.setCurrentLoan(null);
            user.removeLoan(loan);
            System.out.println("Libro devuelto con éxito.");
        } else {
            System.out.println("No se encontró préstamo para este libro por parte de este usuario.");
        }
    }

    public List<Loan> getLoansByUser(User user) {
        return user.getActiveLoans();
    }

    public Loan getLoanByBook(Book book) {
        return book.getCurrentLoan();
    }

    public List<Loan> getAllLoans(){
        return loanList;
    }

}

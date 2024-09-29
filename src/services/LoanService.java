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

    public void addLoan(String userQuery, String bookQuery, LocalDate loanDate, LocalDate returnDate) {
        User user = userService.findUserByNameOrDocument(userQuery);

        if(user == null){
            System.out.println("Usuario no encontrado.");
            return;
        }

        Book book = bookService.findBookByQuery(bookQuery);

        if(book == null){
            System.out.println("Libro no encontrado.");
            return;
        }

        if(book.isAvailable()){
            System.out.println("El libro ya está prestado.");
            return;
        }

        Loan newLoan = new Loan(book, user, loanDate, returnDate);
        loanList.add(newLoan);

        book.setCurrentLoan(newLoan);

        System.out.println("Préstamo agregado con éxito para el libro " + book.getTitle() + " a " + user.getName());
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

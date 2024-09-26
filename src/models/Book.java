package models;

public class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;
    private Loan currentLoan;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = true;
        this.currentLoan = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Loan getCurrentLoan() {
        return currentLoan;
    }

    public void setCurrentLoan(Loan loan) {
        this.currentLoan = loan;
        this.isAvailable = (loan == null);
    }


}

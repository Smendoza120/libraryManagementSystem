package models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private String documentType;
    private String documentNumber;
    private List<Loan> activeLoans;

    public User(String id, String name, String documentType, String documentNumber) {
        this.id = id;
        this.name = name;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.activeLoans = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void addLoan(Loan loan) {
        activeLoans.add(loan);
    }

    public void removeLoan(Loan loan) {
        activeLoans.remove(loan);
    }

    public List<Loan> getActiveLoans() {
        return activeLoans;
    }
}

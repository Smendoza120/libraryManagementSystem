package structures;

import models.Loan;

import java.util.LinkedList;
import java.util.Queue;

public class LoanQueue {
    private Queue<Loan> loanQueue;

    public LoanQueue(){
        this.loanQueue = new LinkedList<>();
    }

    public void enqueue(Loan loan){
        loanQueue.add(loan);
    }

    public Loan dequeue() {
        return loanQueue.poll();
    }

    public boolean isEmpty() {
        return loanQueue.isEmpty();
    }

    public int size(){
        return loanQueue.size();
    }

    public Loan peek(){
        return loanQueue.peek();
    }

    public void clear(){
        loanQueue.clear();
    }
}

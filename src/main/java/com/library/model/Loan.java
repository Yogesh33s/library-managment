package com.library.model;

import java.time.LocalDate;

public class Loan {

    private int bookId;
    private int memberId;
    private LocalDate borrowDate;

    public Loan(int bookId, int memberId) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = LocalDate.now();
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }
}

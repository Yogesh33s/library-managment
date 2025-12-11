package com.library.service;

import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Loan;
import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;

import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {

    private final BookRepository bookRepo;
    private final MemberRepository memberRepo;
    private final List<Loan> loans = new ArrayList<>();

    public LibraryService(BookRepository bookRepo, MemberRepository memberRepo) {
        this.bookRepo = bookRepo;
        this.memberRepo = memberRepo;
    }

    // ---------------------- BOOK METHODS ----------------------

    public Book addBook(String title, String author, String isbn, int copies) {
        return bookRepo.save(new Book(0, title, author, isbn, copies));
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public List<Book> searchBooks(String query) {
        return bookRepo.search(query);
    }

    // ---------------------- MEMBER METHODS ----------------------

    public Member addMember(String name, String email) {
        return memberRepo.save(new Member(0, name, email));
    }

    public List<Member> getAllMembers() {
        return memberRepo.findAll();
    }

    // ---------------------- BORROW / RETURN METHODS ----------------------

    public boolean borrowBook(int bookId, int memberId) {
        Optional<Book> ob = bookRepo.findById(bookId);
        Optional<Member> om = memberRepo.findById(memberId);

        if (ob.isEmpty() || om.isEmpty()) {
            return false;
        }

        Book book = ob.get();

        if (!book.borrowOne()) {
            return false;
        }

        loans.add(new Loan(bookId, memberId));
        return true;
    }

    public boolean returnBook(int bookId, int memberId) {
        Optional<Loan> loan = loans.stream()
                .filter(l -> l.getBookId() == bookId && l.getMemberId() == memberId)
                .findFirst();

        if (loan.isEmpty()) {
            return false;
        }

        loans.remove(loan.get());

        bookRepo.findById(bookId).ifPresent(Book::returnOne);
        return true;
    }

    // ---------------------- BORROWED LIST ----------------------

    public List<String> borrowedList() {
        return loans.stream()
                .map(l -> {
                    String bookTitle = bookRepo.findById(l.getBookId())
                            .map(Book::getTitle)
                            .orElse("[Unknown Book]");

                    String memberName = memberRepo.findById(l.getMemberId())
                            .map(Member::getName)
                            .orElse("[Unknown Member]");

                    return String.format("%s borrowed by %s on %s",
                            bookTitle, memberName, l.getBorrowDate());
                })
                .collect(Collectors.toList());
    }
}

package com.library;

import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import com.library.service.LibraryService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryServiceTest {

    @Test
    void borrowAndReturnBookShouldUpdateCopiesCorrectly() {

        BookRepository br = new BookRepository();
        MemberRepository mr = new MemberRepository();
        LibraryService svc = new LibraryService(br, mr);

        var book = svc.addBook("Java Programming", "Author A", "ISBN123", 1);
        var member = svc.addMember("Yogesh", "yogesh@example.com");

        // Borrow should succeed
        assertTrue(svc.borrowBook(book.getId(), member.getId()));

        // Borrow again should fail because no copies left
        assertFalse(svc.borrowBook(book.getId(), member.getId()));

        // Return should succeed
        assertTrue(svc.returnBook(book.getId(), member.getId()));

        // Borrow should work again
        assertTrue(svc.borrowBook(book.getId(), member.getId()));
    }
}

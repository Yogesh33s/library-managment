package com.library.repository;

import com.library.model.Book;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class BookRepository {

    private final Map<Integer, Book> books = new HashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public Book save(Book book) {
        int id = idCounter.getAndIncrement();
        Book newBook = new Book(id, book.getTitle(), book.getAuthor(), book.getIsbn(), book.getCopiesAvailable());
        books.put(id, newBook);
        return newBook;
    }

    public Optional<Book> findById(int id) {
        return Optional.ofNullable(books.get(id));
    }

    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    public boolean delete(int id) {
        return books.remove(id) != null;
    }

    public List<Book> search(String keyword) {
        String key = keyword.toLowerCase();
        return books.values().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(key)
                        || b.getAuthor().toLowerCase().contains(key))
                .collect(Collectors.toList());
    }

    public void clear() {
        books.clear();
    }
}

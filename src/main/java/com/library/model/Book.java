package com.library.model;

public class Book {

    private int id;
    private String title;
    private String author;
    private String isbn;
    private int copiesAvailable;

    public Book(int id, String title, String author, String isbn, int copiesAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.copiesAvailable = copiesAvailable;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public boolean borrowOne() {
        if (copiesAvailable <= 0) {
            return false;
        }
        copiesAvailable--;
        return true;
    }

    public void returnOne() {
        copiesAvailable++;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s by %s (ISBN: %s) - Available: %d",
                id, title, author, isbn, copiesAvailable);
    }
}

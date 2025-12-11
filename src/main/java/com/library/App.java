package com.library;

import com.library.model.Book;
import com.library.model.Member;
import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import com.library.service.LibraryService;

import java.util.List;
import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        BookRepository br = new BookRepository();
        MemberRepository mr = new MemberRepository();
        LibraryService svc = new LibraryService(br, mr);

        boolean running = true;

        while (running) {
            printMenu();
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1" -> addBook(svc);
                case "2" -> listBooks(svc);
                case "3" -> addMember(svc);
                case "4" -> listMembers(svc);
                case "5" -> borrowBook(svc);
                case "6" -> returnBook(svc);
                case "7" -> searchBooks(svc);
                case "8" -> listBorrowed(svc);
                case "0" -> {
                    System.out.println("Exiting system...");
                    running = false;
                }
                default -> System.out.println("Invalid option, try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== Library Management System =====");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Add Member");
        System.out.println("4. View All Members");
        System.out.println("5. Borrow Book");
        System.out.println("6. Return Book");
        System.out.println("7. Search Books");
        System.out.println("8. List Borrowed Books");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addBook(LibraryService svc) {
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter Copies: ");
        int copies = Integer.parseInt(scanner.nextLine());

        Book b = svc.addBook(title, author, isbn, copies);
        System.out.println("Book added: " + b);
    }

    private static void listBooks(LibraryService svc) {
        List<Book> books = svc.getAllBooks();
        if (books.isEmpty()) System.out.println("No books found.");
        else books.forEach(System.out::println);
    }

    private static void addMember(LibraryService svc) {
        System.out.print("Enter Member Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Member Email: ");
        String email = scanner.nextLine();

        Member m = svc.addMember(name, email);
        System.out.println("Member added: " + m);
    }

    private static void listMembers(LibraryService svc) {
        List<Member> members = svc.getAllMembers();
        if (members.isEmpty()) System.out.println("No members found.");
        else members.forEach(System.out::println);
    }

    private static void borrowBook(LibraryService svc) {
        System.out.print("Enter Book ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Member ID: ");
        int memberId = Integer.parseInt(scanner.nextLine());

        boolean success = svc.borrowBook(bookId, memberId);
        System.out.println(success ? "Borrowed successfully!" : "Borrow failed!");
    }

    private static void returnBook(LibraryService svc) {
        System.out.print("Enter Book ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Member ID: ");
        int memberId = Integer.parseInt(scanner.nextLine());

        boolean success = svc.returnBook(bookId, memberId);
        System.out.println(success ? "Returned successfully!" : "Return failed!");
    }

    private static void searchBooks(LibraryService svc) {
        System.out.print("Enter search query: ");
        String query = scanner.nextLine();

        List<Book> results = svc.searchBooks(query);
        if (results.isEmpty()) System.out.println("No matching books found.");
        else results.forEach(System.out::println);
    }

    private static void listBorrowed(LibraryService svc) {
        List<String> results = svc.borrowedList();
        if (results.isEmpty()) System.out.println("No books are currently borrowed.");
        else results.forEach(System.out::println);
    }
}

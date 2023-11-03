package com.devskiller.library.service;

import com.devskiller.library.model.Book;
import com.devskiller.library.model.BookBorrowing;
import com.devskiller.library.model.User;
import com.devskiller.library.repository.BooksRepository;
import com.devskiller.library.repository.BorrowingsRepository;

import java.util.Arrays;

public class BookService {
    private final BooksRepository booksRepository;
    private final BorrowingsRepository borrowingsRepository;

    public BookService(BooksRepository booksRepository, BorrowingsRepository borrowingsRepository) {
        this.booksRepository = booksRepository;
        this.borrowingsRepository = borrowingsRepository;
    }

    public void borrowBook(User user, Book book) {
        BookBorrowing bookBorrowing = new BookBorrowing(book);
        borrowingsRepository.save(user,Arrays.asList(bookBorrowing));
    }

    public void returnBook(User user, Book book) {
        throw new UnsupportedOperationException("returnBook");
    }

    public void addBookCopy(Book book) {
        throw new UnsupportedOperationException("addBookCopy");
    }

    public void removeBookCopy(Book book) {
        throw new UnsupportedOperationException("removeBookCopy");
    }

    public int getAvailableBookCopies(Book book) {
        throw new UnsupportedOperationException("getAvailableBookCopies");
    }
}

package com.devskiller.library.service;

import com.devskiller.library.exception.BookNotAvailableException;
import com.devskiller.library.exception.BookNotFoundException;
import com.devskiller.library.model.Book;
import com.devskiller.library.model.BookBorrowing;
import com.devskiller.library.model.User;
import com.devskiller.library.repository.BooksRepository;
import com.devskiller.library.repository.BorrowingsRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BookService {
    private final BooksRepository booksRepository;
    private final BorrowingsRepository borrowingsRepository;

    public BookService(BooksRepository booksRepository, BorrowingsRepository borrowingsRepository) {
        this.booksRepository = booksRepository;
        this.borrowingsRepository = borrowingsRepository;
    }

    public void borrowBook(User user, Book book) {

        List<BookBorrowing> byUser = borrowingsRepository.getByUser(user);
        removeBookCopy(book);
        borrowingsRepository.save(user,byUser);

    }

    public void returnBook(User user, Book book) {
        List<BookBorrowing> byUser = borrowingsRepository.getByUser(user);

    }

    public void addBookCopy(Book book) {
        int availableBookCopies = getAvailableBookCopies(book);

        if(availableBookCopies == 0) {
            booksRepository.save(book,1);
        } else {
            booksRepository.save(book,availableBookCopies + 1);
        }
    }

    public void removeBookCopy(Book book)  {


        int availableBookCopies = getAvailableBookCopies(book);

        if(availableBookCopies == 0){

            throw new BookNotAvailableException();
        } else {

            booksRepository.save(book,availableBookCopies - 1);
        }


    }

    public int getAvailableBookCopies(Book book) {
        Optional<Integer> bookCount = booksRepository.getBookCount(book);

        int present = bookCount.orElseThrow( new BookNotFoundException());
        Integer count;
        if (present) {
            count = bookCount.get();


        } else {

            throw new BookNotFoundException();
        }
        return count;
    }
}

package com.devskiller.library.exception;

public class BookAlreadyBorrowedException extends RuntimeException {

    public BookAlreadyBorrowedException(String msg){
        super(msg);
    }

}

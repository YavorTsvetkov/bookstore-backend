package com.yavor.bookstore.web.rest.controllers;

import com.yavor.bookstore.web.rest.resources.BookResource;

import java.util.List;

public interface BookRepository {

    List<BookResource> getAllBooks();

    BookResource saveBook(BookResource book);
}

package org.anastasia.peopleinfoapplication.controller;

import org.anastasia.peopleinfoapplication.dto.BookDto;
import org.anastasia.peopleinfoapplication.mappers.BookMapper;
import org.anastasia.peopleinfoapplication.model.Book;
import org.anastasia.peopleinfoapplication.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @Autowired
    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping("/api/v1/book")
    public BookDto addBook(@RequestBody BookDto bookDto) {
        return bookMapper.toBookDto(bookService.save(bookMapper.toEntity(bookDto)));
    }

    @PostMapping("/api/v1/list_of_books")
    public List<BookDto> addListOfBooks(@RequestBody List<BookDto> bookDtos) {
        List<Book> booksEntity = bookDtos.stream()
                .map(bookMapper::toEntity)
                .collect(Collectors.toList());

        return bookService.saveAll(booksEntity).stream()
                .map(bookMapper::toBookDto)
                .collect(Collectors.toList());
    }
    @DeleteMapping("/api/v1/book/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        bookService.deleteById(id);
    }
    @PutMapping("/api/v1/book/action/{bookId}")
    public void untieBookFromPerson(@PathVariable("bookId") Long bookId) {
        bookService.untieBookFromPerson(bookId);
    }
}

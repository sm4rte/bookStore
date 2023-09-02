package gpt.task.bookStore.controller;

import gpt.task.bookStore.dto.request.BookCreateRequestDto;
import gpt.task.bookStore.dto.request.BookUpdateRequestDto;
import gpt.task.bookStore.dto.response.BookResponseDto;
import gpt.task.bookStore.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<Page<BookResponseDto>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        Page<BookResponseDto> booksPage = bookService.getAllBooks(page, size, sortField, sortDirection);
        return ResponseEntity.ok(booksPage);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseDto> getById(@PathVariable("bookId") Long id) {
        log.info("Trying to get book with the id: " + id + "!");
        BookResponseDto book = bookService.getById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody BookCreateRequestDto requestDto) throws Exception {
        log.info("Trying to add new book: " + requestDto.getTitle());
        bookService.createBook(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book created successfully!");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody BookUpdateRequestDto requestDto) throws Exception {
        log.info("Trying to update book with id: " + requestDto.getId() + "!");
        bookService.updateBook(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book updated successfully!");
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> delete(@PathVariable("bookId") Long id) {
        log.info("Trying to delete book with id: " + id + "!");
        bookService.deleteById(id);
        return ResponseEntity.ok("Book with id " + id + " successfully deleted!");
    }
}

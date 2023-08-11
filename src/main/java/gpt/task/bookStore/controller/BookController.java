package gpt.task.bookStore.controller;

import gpt.task.bookStore.dto.request.BookCreateRequestDto;
import gpt.task.bookStore.dto.request.BookUpdateRequestDto;
import gpt.task.bookStore.dto.response.BookResponseDto;
import gpt.task.bookStore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/{bookId}")
    public BookResponseDto getByID(@PathVariable("bookId") Long id) {
        return bookService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody BookCreateRequestDto requestDto) throws Exception {
        bookService.createBook(requestDto);
    }

    @PutMapping
    public void update(@RequestBody BookUpdateRequestDto requestDto) throws Exception {
        bookService.updateBook(requestDto);
    }

}

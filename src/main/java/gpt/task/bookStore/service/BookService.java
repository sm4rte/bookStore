package gpt.task.bookStore.service;

import gpt.task.bookStore.dto.request.BookCreateRequestDto;
import gpt.task.bookStore.dto.request.BookUpdateRequestDto;
import gpt.task.bookStore.dto.response.BookResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface BookService {

    BookResponseDto getById(Long id);

    void createBook(BookCreateRequestDto requestDto) throws Exception;

    void updateBook(BookUpdateRequestDto requestDto) throws Exception;

    void deleteById(Long id);

    Page<BookResponseDto> getAllBooks(int page, int size, String sortField, String sortDirection);
}

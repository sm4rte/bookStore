package gpt.task.bookStore.service.impl;

import gpt.task.bookStore.dto.request.BookCreateRequestDto;
import gpt.task.bookStore.dto.request.BookUpdateRequestDto;
import gpt.task.bookStore.dto.response.BookResponseDto;
import gpt.task.bookStore.entity.Book;
import gpt.task.bookStore.exception.BookNotFoundException;
import gpt.task.bookStore.exception.DuplicateItemException;
import gpt.task.bookStore.repository.BookRepository;
import gpt.task.bookStore.service.BookService;
import gpt.task.bookStore.validator.book.BookCreateRequestValidator;
import gpt.task.bookStore.validator.book.BookUpdateRequestValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookCreateRequestValidator createRequestValidator;
    private final BookUpdateRequestValidator updateRequestValidator;
    private final ModelMapper modelMapper;


    @Override
    public Page<BookResponseDto> getAllBooks(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Book> booksPage = bookRepository.findAll(pageable);
        return booksPage.map(this::convertToResponseDto);
    }

    private BookResponseDto convertToResponseDto(Book book) {
        return modelMapper.map(book, BookResponseDto.class);
    }

    @Override
    public BookResponseDto getById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            BookResponseDto responseDto = new BookResponseDto();
            modelMapper.map(book, responseDto);
            return responseDto;
        } else {
            throw new BookNotFoundException("Book with id " + id + " not found!");
        }
    }

    @Override
    public void createBook(BookCreateRequestDto requestDto) {
        createRequestValidator.validate(requestDto);
        try {
            Book book = new Book();
            modelMapper.map(requestDto, book);
            bookRepository.save(book);
        }
        catch (DataIntegrityViolationException e){
            throw new DuplicateItemException("The book with title " + requestDto.getTitle() + " already exists!");
        }
    }

    @Override
    public void updateBook(BookUpdateRequestDto requestDto) {
        updateRequestValidator.validate(requestDto);
        Book book = bookRepository.getById(requestDto.getId());
        modelMapper.map(requestDto, book);
        bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new BookNotFoundException("Book with id   " + id + " not found!");
        }
        bookRepository.deleteById(id);
    }
}

package gpt.task.bookStore.service.impl;

import gpt.task.bookStore.dto.request.BookCreateRequestDto;
import gpt.task.bookStore.dto.request.BookUpdateRequestDto;
import gpt.task.bookStore.dto.response.BookResponseDto;
import gpt.task.bookStore.entity.Book;
import gpt.task.bookStore.repository.BookRepository;
import gpt.task.bookStore.service.BookService;
import gpt.task.bookStore.validator.BookCreateRequestValidator;
import gpt.task.bookStore.validator.BookUpdateRequestValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    public BookResponseDto getById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            BookResponseDto responseDto = new BookResponseDto();
            modelMapper.map(book, responseDto);
            return responseDto;
        }
        return null;
    }

    @Override
    public void createBook(BookCreateRequestDto requestDto) throws Exception {
        createRequestValidator.validate(requestDto);
        Book book = new Book();
        modelMapper.map(requestDto, book);
        bookRepository.save(book);
    }

    @Override
    public void updateBook(BookUpdateRequestDto requestDto) throws Exception {
        updateRequestValidator.validate(requestDto);
        Book book = bookRepository.getById(requestDto.getId());
        modelMapper.map(requestDto, book);
        bookRepository.save(book);

    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}

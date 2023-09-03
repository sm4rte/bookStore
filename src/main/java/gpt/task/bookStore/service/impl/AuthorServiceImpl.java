package gpt.task.bookStore.service.impl;

import gpt.task.bookStore.dto.request.AuthorCreateRequestDto;
import gpt.task.bookStore.dto.request.AuthorUpdateRequestDto;
import gpt.task.bookStore.dto.response.AuthorResponseDto;
import gpt.task.bookStore.entity.Author;
import gpt.task.bookStore.exception.BookNotFoundException;
import gpt.task.bookStore.exception.DuplicateItemException;
import gpt.task.bookStore.repository.AuthorRepository;
import gpt.task.bookStore.service.AuthorService;
import gpt.task.bookStore.validator.author.AuthorCreateRequestValidator;
import gpt.task.bookStore.validator.author.AuthorUpdateRequestDtoValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;
    private final AuthorCreateRequestValidator createRequestValidator;
    private final AuthorUpdateRequestDtoValidator updateRequestDtoValidator;

    @Override
    public Page<AuthorResponseDto> searchAuthors(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Author> authors = authorRepository.searchByAttribute(keyword, pageable);
        return authors.map(author -> modelMapper.map(author, AuthorResponseDto.class));
    }

    @Override
    public Page<AuthorResponseDto> getAllAuthors(int page, int size, String sortField, String sortDirection) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortField));
        Page<Author> authors = authorRepository.findAll(pageable);
        return authors.map(author -> modelMapper.map(author, AuthorResponseDto.class));
    }

    @Override
    public void createAuthor(AuthorCreateRequestDto createRequestDto) {
        createRequestValidator.validate(createRequestDto);

        try {
            Author author = new Author();
            modelMapper.map(createRequestDto, author);
            authorRepository.save(author);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateItemException("Author with email: " + createRequestDto.getEmail() + " already exists!");
        }
    }

    @Override
    public void updateAuthor(AuthorUpdateRequestDto updateRequestDto) {
        updateRequestDtoValidator.validate(updateRequestDto);
        Author author = authorRepository.getById(updateRequestDto.getId());
        modelMapper.map(updateRequestDto, author);
        authorRepository.save(author);
    }

    @Override
    public AuthorResponseDto getbyId(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            AuthorResponseDto responseDto = new AuthorResponseDto();
            modelMapper.map(author, responseDto);
            return responseDto;
        }
        throw new BookNotFoundException("Author with id " + id + " not found!");
    }

    @Override
    public void deleteById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty()) {
            throw new BookNotFoundException("Author with id " + " not found!");
        }
        authorRepository.deleteById(id);
    }
}

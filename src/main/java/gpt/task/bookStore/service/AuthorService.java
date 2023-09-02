package gpt.task.bookStore.service;

import gpt.task.bookStore.dto.request.AuthorCreateRequestDto;
import gpt.task.bookStore.dto.request.AuthorUpdateRequestDto;
import gpt.task.bookStore.dto.response.AuthorResponseDto;
import gpt.task.bookStore.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface AuthorService {

    void createAuthor(AuthorCreateRequestDto createRequestDto);

    void updateAuthor(AuthorUpdateRequestDto updateRequestDto);

    AuthorResponseDto getbyId(Long id);

    void deleteById(Long id);

    Page<AuthorResponseDto> getAllAuthors(int page, int size, String sortField, String sortDirection);

}

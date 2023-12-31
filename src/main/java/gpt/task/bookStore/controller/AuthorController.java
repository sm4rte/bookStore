package gpt.task.bookStore.controller;

import gpt.task.bookStore.dto.request.AuthorCreateRequestDto;
import gpt.task.bookStore.dto.request.AuthorUpdateRequestDto;
import gpt.task.bookStore.dto.response.AuthorResponseDto;
import gpt.task.bookStore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/author")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/search")
    public ResponseEntity<Page<AuthorResponseDto>> searchAuthors(@RequestParam String keyword,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size ){
        log.info("Searching for items: " + keyword);
        Page<AuthorResponseDto> authors = authorService.searchAuthors(keyword, page, size);
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/authors")
    public ResponseEntity<Page<AuthorResponseDto>> getAllAuthors(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size,
                                                                 @RequestParam(defaultValue = "fullName") String sortField,
                                                                 @RequestParam(defaultValue = "desc") String sortDirection) {
        log.info("Trying to get multiple Authors!");
        Page<AuthorResponseDto> authors = authorService.getAllAuthors(page, size, sortField, sortDirection);
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorResponseDto> getById(@PathVariable("authorId") Long id) {
        log.info("Trying to get author with id " + id + "!");
        AuthorResponseDto responseDto = authorService.getbyId(id);
        if (responseDto != null) {
            return ResponseEntity.ok(responseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody AuthorCreateRequestDto requestDto) {
        log.info("Trying to add new author: " + requestDto.getFullName());
        authorService.createAuthor(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Author created successfully!");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody AuthorUpdateRequestDto requestDto) {
        log.info("Trying to update author with id: " + requestDto.getId() + "!");
        authorService.updateAuthor(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Author updated successfully!");
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<String> deleteById(@PathVariable("authorId") Long id) {
        log.info("Trying to delete author with id: " + id + "!");
        authorService.deleteById(id);
        return ResponseEntity.ok("Author with id: " + id + " is deleted!");
    }
}

package gpt.task.bookStore.validator.book;

import gpt.task.bookStore.dto.request.BookCreateRequestDto;
import gpt.task.bookStore.exception.InvalidRequestException;
import gpt.task.bookStore.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class BookCreateRequestValidator implements Validator<BookCreateRequestDto> {
    @Override
    public void validate(BookCreateRequestDto target) {
        if (target.getAuthorId() == null) throw new InvalidRequestException("Please enter the author id correctly!");
        if (target.getPrice() < 0) throw new InvalidRequestException("Invalid price value!");
        if (target.getPublishedYear() < 0) throw new InvalidRequestException("Invalid published year!");
        if (target.getTitle().isBlank()) throw new InvalidRequestException("Please enter the title!");
    }
}
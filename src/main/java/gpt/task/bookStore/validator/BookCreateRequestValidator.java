package gpt.task.bookStore.validator;

import gpt.task.bookStore.dto.request.BookCreateRequestDto;
import gpt.task.bookStore.exception.InvalidRequestException;
import org.springframework.stereotype.Component;

@Component
public class BookCreateRequestValidator implements Validator<BookCreateRequestDto> {
    @Override
    public void validate(BookCreateRequestDto target) {
        if (target.getAuthor().isBlank()) throw new InvalidRequestException("Please enter the author correctly!");
        if (target.getPrice() < 0) throw new InvalidRequestException("Invalid price value!");
        if (target.getPublishedYear() < 0) throw new InvalidRequestException("Invalid published year!");
        if (target.getTitle().isBlank()) throw new InvalidRequestException("Please enter the title!");
    }
}
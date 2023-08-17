package gpt.task.bookStore.validator;

import gpt.task.bookStore.dto.request.BookCreateRequestDto;
import gpt.task.bookStore.dto.request.BookUpdateRequestDto;
import gpt.task.bookStore.exception.InvalidRequestException;
import org.springframework.stereotype.Component;

@Component
public class BookUpdateRequestValidator implements Validator<BookUpdateRequestDto> {
    @Override
    public void validate(BookUpdateRequestDto target) {
        if (target.getAuthor().isBlank()) throw new InvalidRequestException("Please enter the author correctly!");
        if (target.getPrice() < 0) throw new InvalidRequestException("Price is not correct!");
        if (target.getPublishedYear() < 0) throw new InvalidRequestException("Uncorrect input!");
        if (target.getTitle().isBlank()) throw new InvalidRequestException("Please enter the title!");
    }
}

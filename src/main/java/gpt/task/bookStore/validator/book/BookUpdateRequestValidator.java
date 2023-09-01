package gpt.task.bookStore.validator.book;

import gpt.task.bookStore.dto.request.BookUpdateRequestDto;
import gpt.task.bookStore.exception.InvalidRequestException;
import gpt.task.bookStore.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class BookUpdateRequestValidator implements Validator<BookUpdateRequestDto> {
    @Override
    public void validate(BookUpdateRequestDto target) {
        if (target.getAuthorId() == 0) throw new InvalidRequestException("Please enter the author id correctly!");
        if (target.getPrice() < 0) throw new InvalidRequestException("Price is not correct!");
        if (target.getPublishedYear() < 0) throw new InvalidRequestException("Uncorrect input!");
        if (target.getTitle().isBlank()) throw new InvalidRequestException("Please enter the title!");
    }
}

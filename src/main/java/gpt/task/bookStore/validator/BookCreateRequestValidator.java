package gpt.task.bookStore.validator;

import gpt.task.bookStore.dto.request.BookCreateRequestDto;
import org.springframework.stereotype.Component;

@Component
public class BookCreateRequestValidator implements Validator<BookCreateRequestDto> {
    @Override
    public void validate(BookCreateRequestDto target) throws Exception {
        if (target.getAuthor().isBlank()) throw new Exception("Please enter the author correctly!");
        if (target.getPrice() < 0) throw new Exception("Price is not correct!");
        if (target.getPublishedYear() < 0) throw new Exception("Uncorrect input!");
        if (target.getTitle().isBlank()) throw new Exception("Please enter the title!");
    }

}
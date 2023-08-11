package gpt.task.bookStore.validator;

import gpt.task.bookStore.dto.request.BookUpdateRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class BookUpdateRequestValidator implements Validator<BookUpdateRequestDto> {
    @Override
    public void validate(BookUpdateRequestDto target) throws Exception {
        if (target.getAuthor().isBlank()) throw new Exception("Please enter the author correctly!");
        if (target.getPrice() < 0) throw new Exception("Price is not correct!");
        if (target.getPublishedYear() < 0) throw new Exception("Uncorrect input!");
        if (target.getTitle().isBlank()) throw new Exception("Please enter the title!");
    }
}

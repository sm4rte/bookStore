package gpt.task.bookStore.validator.author;

import gpt.task.bookStore.dto.request.AuthorCreateRequestDto;
import gpt.task.bookStore.exception.InvalidRequestException;
import gpt.task.bookStore.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class AuthorCreateRequestValidator implements Validator<AuthorCreateRequestDto> {
    @Override
    public void validate(AuthorCreateRequestDto target) {
        if (target.getFullName().isBlank()) throw new InvalidRequestException("Please enter the name correctly!");
        if (target.getEmail().isBlank()) throw new InvalidRequestException("Please enter correct email!");
    }
}

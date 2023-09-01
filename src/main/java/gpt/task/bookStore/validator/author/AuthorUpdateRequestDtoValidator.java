package gpt.task.bookStore.validator.author;

import gpt.task.bookStore.dto.request.AuthorUpdateRequestDto;
import gpt.task.bookStore.exception.InvalidRequestException;
import gpt.task.bookStore.validator.Validator;
import org.springframework.stereotype.Component;

@Component
public class AuthorUpdateRequestDtoValidator implements Validator<AuthorUpdateRequestDto> {
    @Override
    public void validate(AuthorUpdateRequestDto target)  {
        if (target.getFullName().isBlank()) throw new InvalidRequestException("Please enter the name correctly");
        if (target.getEmail().isBlank()) throw new InvalidRequestException("Please enter correct email");
    }
}

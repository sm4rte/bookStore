package gpt.task.bookStore.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorCreateRequestDto {

    @NotBlank
    String fullName;

    @Email
    String email;

    String country;


}

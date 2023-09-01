package gpt.task.bookStore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorUpdateRequestDto {
    @NotBlank
    Long id;

    @NotBlank
    String fullName;

    @NotBlank
    String email;

    String country;
}

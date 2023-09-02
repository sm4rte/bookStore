package gpt.task.bookStore.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorResponseDto {

    Long id;

    String fullName;

    String email;

    String country;
}

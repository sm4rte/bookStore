package gpt.task.bookStore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCreateRequestDto {
    @NotBlank
    private String title;

    @NotBlank
    private Long authorId;

    @NotBlank
    private int publishedYear;

    @NotBlank
    private double price;
}

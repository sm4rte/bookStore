package gpt.task.bookStore.dto.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BookResponseDto {

    @NonNull
    private Long id;

    private String title;

    private String author;

    private int publishedYear;

    private double price;
}

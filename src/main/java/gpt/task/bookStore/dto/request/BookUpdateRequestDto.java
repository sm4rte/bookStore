package gpt.task.bookStore.dto.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BookUpdateRequestDto {

    @NonNull
    private Long id;

    private String title;

    private Long authorId;

    private int publishedYear;

    private double price;
}
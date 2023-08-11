package gpt.task.bookStore.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCreateRequestDto {
    private String title;

    private String author;

    private int publishedYear;

    private double price;
}

package gpt.task.bookStore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "book")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @Column(name = "publishedYear")
    private int publishedYear;

    @Column(name = "price")
    private double price;
}



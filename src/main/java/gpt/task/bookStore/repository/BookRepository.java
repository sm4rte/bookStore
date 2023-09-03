package gpt.task.bookStore.repository;

import gpt.task.bookStore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b where b.title like %?1% " +
            "or concat(b.publishedYear, ' ') like %?1% " +
            "or concat(b.price, ' ' ) like %?1% " +
            "or concat(b.authorId, ' ') like %?1%")
    Page<Book> searchByAttribute(String keyword, Pageable pageable);
}
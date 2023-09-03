package gpt.task.bookStore.repository;

import gpt.task.bookStore.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    @Query("select a from Author a where a.fullName like %?1% or a.email like %?1% or a.country like %?1%")
    Page<Author> searchByAttribute(String keyword, Pageable pageable);
}

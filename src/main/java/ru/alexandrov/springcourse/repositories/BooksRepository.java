package ru.alexandrov.springcourse.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexandrov.springcourse.models.Book;

import java.util.List;


@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitleStartingWithIgnoreCase(String starting);
    List<Book> findByTitleStartingWithIgnoreCase(String starting, Sort sort);
    Page<Book> findAll(Pageable pageable);
    List<Book> findAll(Sort sort);
}

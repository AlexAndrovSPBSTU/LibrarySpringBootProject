package ru.alexandrov.springcourse.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.alexandrov.springcourse.models.Book;
import ru.alexandrov.springcourse.models.Reader;
import ru.alexandrov.springcourse.repositories.BooksRepository;
import ru.alexandrov.springcourse.repositories.ReadersRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class BooksService {
    private final BooksRepository booksRepository;
    private final ReadersRepository readersRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, ReadersRepository readersRepository) {
        this.booksRepository = booksRepository;
        this.readersRepository = readersRepository;
    }

    public List<Book> index() {
        return booksRepository.findAll();
    }

    public Book show(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public Reader getBookOwner(int id) {
        return Objects.requireNonNull(booksRepository.findById(id).orElse(null)).getOwner();
    }

    public void save(Book book) {
        booksRepository.save(book);
    }

    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public void update(Book book) {
        System.out.println(book);
        System.out.println(book.getId());
        booksRepository.save(book);
    }

    public void free(int id) {
        booksRepository.findById(id).orElse(null).setOwner(null);
        booksRepository.findById(id).orElse(null).setDateTake(null);
    }

    public void assign(int bookId, int readerId) {
        Book book = booksRepository.findById(bookId).orElse(null);
        book.setOwner(readersRepository.findById(readerId).orElse(null));
        book.setDateTake(new Date());
    }

    public List<Book> findByQuery(String query) {
        return booksRepository.findByTitleStartingWithIgnoreCase(query);
    }

    public List<Book> findByQueryWithSorting(String query, Sort sort) {
        return booksRepository.findByTitleStartingWithIgnoreCase(query, sort);
    }

    public List<Book> findPage(int pageNum, int itemsPerPage) {
        return booksRepository.findAll(PageRequest.of(pageNum, itemsPerPage)).getContent();
    }
}

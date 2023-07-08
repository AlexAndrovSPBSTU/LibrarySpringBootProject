package ru.alexandrov.springcourse.services;

import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexandrov.springcourse.models.Book;
import ru.alexandrov.springcourse.models.Reader;
import ru.alexandrov.springcourse.repositories.ReadersRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReadersService {
    private final ReadersRepository readersRepository;

    @Autowired
    public ReadersService(ReadersRepository readersRepository) {
        this.readersRepository = readersRepository;
    }


    public List<Reader> index() {
        return readersRepository.findAll();
    }

    public Reader show(int id) {
        return readersRepository.findById(id).orElse(null);
    }

    public List<Book> getReaderBooks(int id) {
        List<Book> list = readersRepository.findById(id).orElse(null).getBooks();
        Hibernate.initialize(list);
        return list;
    }

    public void save(Reader reader) {
        readersRepository.save(reader);
    }

    public void delete(int id) {
        readersRepository.deleteById(id);
    }

    public void update(Reader reader) {
        readersRepository.save(reader);
    }

    public Optional<Reader> findReaderByName(String name) {
        return Optional.ofNullable(readersRepository.findByName(name));
    }

    public Optional<Reader> findReaderByNameAndIdNotIn(String name, Collection<Integer> ids) {
        return Optional.ofNullable(readersRepository.findByNameAndIdNotIn(name, ids));
    }
}

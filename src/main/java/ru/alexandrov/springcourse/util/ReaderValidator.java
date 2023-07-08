package ru.alexandrov.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alexandrov.springcourse.models.Reader;
import ru.alexandrov.springcourse.services.ReadersService;

import java.util.Collections;

@Component
public class ReaderValidator implements Validator {
    private final ReadersService readersService;

    @Autowired
    public ReaderValidator(ReadersService readersService) {
        this.readersService = readersService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Reader.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Reader reader = (Reader) target;
        if (reader.getId() == 0) {
            if (readersService.findReaderByName(reader.getName()).isPresent()) {
                errors.rejectValue("name", "", "Reader with this name is already exist");
            }
        } else {
            if (readersService.findReaderByNameAndIdNotIn(reader.getName(), Collections.singleton(reader.getId())).isPresent()) {
                errors.rejectValue("name", "", "Reader with this name is already exist");
            }
        }
    }
}

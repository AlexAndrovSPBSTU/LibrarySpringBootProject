package ru.alexandrov.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alexandrov.springcourse.models.Membership;
import ru.alexandrov.springcourse.models.Reader;
import ru.alexandrov.springcourse.services.ReadersService;
import ru.alexandrov.springcourse.util.ReaderValidator;

@Controller
@RequestMapping("/readers")
public class ReadersController {
    private final ReadersService readersService;
    private final ReaderValidator readerValidator;

    @Autowired
    public ReadersController(ReadersService readersService, ReaderValidator readerValidator) {
        this.readersService = readersService;
        this.readerValidator = readerValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("readers", readersService.index());
        return "readers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("reader", readersService.show(id));
        model.addAttribute("books", readersService.getReaderBooks(id));
        return "readers/show";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("reader") Reader reader, Model model) {
        model.addAttribute("memberships", Membership.values());
        return "readers/new";
    }

    @PostMapping()
    public String save(@ModelAttribute("reader") @Valid Reader reader, BindingResult bindingResult) {
        readerValidator.validate(reader, bindingResult);
        if (bindingResult.hasErrors()) {
            return "readers/new";
        }
        readersService.save(reader);
        return "redirect:/readers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        readersService.delete(id);
        return "redirect:/readers";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("memberships", Membership.values());
        model.addAttribute("reader", readersService.show(id));
        return "readers/edit";
    }

    @PatchMapping
    public String update(@ModelAttribute("reader") @Valid Reader reader, BindingResult bindingResult) {
        readerValidator.validate(reader, bindingResult);
        if (bindingResult.hasErrors()) {
            return "readers/edit";
        }
        readersService.update(reader);
        return "redirect:/readers";
    }
}

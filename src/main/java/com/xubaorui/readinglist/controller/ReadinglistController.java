package com.xubaorui.readinglist.controller;

import com.xubaorui.readinglist.entity.Book;
import com.xubaorui.readinglist.properties.AmazonProperties;
import com.xubaorui.readinglist.repository.ReadinglistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/readingList")
public class ReadinglistController {
    private AmazonProperties amazonProperties;

    ReadinglistRepository readinglistRepository;
    @Autowired
    public ReadinglistController(ReadinglistRepository readinglistRepository, AmazonProperties amazonProperties){
        this.readinglistRepository = readinglistRepository;
        this.amazonProperties = amazonProperties;

    }


    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader, Model model) {
        List<Book> readingList = readinglistRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader",reader);
            model.addAttribute("amazonID",amazonProperties.getAssociateId());
        }
        return "readingList";
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public String addToReadinglist(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        readinglistRepository.save(book);
        return "redirect:/readingList/{reader}";
    }
}

package com.xubaorui.readinglist.repository;

import com.xubaorui.readinglist.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadinglistRepository extends JpaRepository<Book,Long> {
    List<Book> findByReader(String reader);

}

package com.bookservice.repository;

import com.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Class containing Data Access Logic for Book Entity
 *
 * @author NIHARIKA GADDE
 */
public interface BooksRepository extends JpaRepository<Book, Long> {

    List<Book> findByIdIn(List<Long> ids);

}

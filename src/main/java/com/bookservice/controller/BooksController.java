package com.bookservice.controller;

import com.bookservice.constants.StringConstants;
import com.bookservice.exception.BookNotFoundException;
import com.bookservice.exception.NoBooksFoundException;
import com.bookservice.dto.BookDTO;
import com.bookservice.service.BooksService;
import com.bookservice.util.ControllersUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class Containing all apis related to book operation
 *
 * @author NIHARIKA GADDE
 */
@Slf4j
@RestController
@RequestMapping(value = "/books")
@Api(tags = {StringConstants.BOOK_SWAGGER_TAG})
public class BooksController {

    @Autowired
    private BooksService bookService;

    /**
     * Get all available books in the system
     *
     * @return List of BookDTOs {@link BookDTO}
     */
    @ApiOperation(value = "View list of available books", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No books present in the database")
    }
    )
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = this.bookService.findAll();
        if (null != books && !books.isEmpty()) {
            return ControllersUtil.getOkResponseEntity(books);
        } else {
            throw new NoBooksFoundException();
        }
    }

    /**
     * Get all books in the system from Book Ids
     *
     * @return List of BookDTOs {@link BookDTO}
     */
    @ApiOperation(value = "Get books from list of book IDs", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No books present in the database")
    }
    )
    @GetMapping(value = "/select/{book_ids}")
    public ResponseEntity<List<BookDTO>> getAllBooksFromIds(@ApiParam(value = "Book IDs that need to be fetched", required = true) @PathVariable("book_ids") String[] bookIds ) {
        List<Long> finalBookIds = new ArrayList<>();
        if (bookIds != null) {
            for (String bookId : bookIds) {
                finalBookIds.add(Long.valueOf(bookId));
            }
        }
        List<BookDTO> books = this.bookService.findByIdIn(finalBookIds);
        if (null != books && !books.isEmpty()) {
            return ControllersUtil.getOkResponseEntity(books);
        } else {
            throw new NoBooksFoundException();
        }
    }

    /**
     * Get an book from book id
     *
     * @param bookId
     * @return Found BookDTO {@link BookDTO}
     */
    @ApiOperation(value = "Search an book with an ID", response = BookDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved book"),
            @ApiResponse(code = 404, message = "No book present in the database with the given ID")
    }
    )
    @GetMapping(value = "/{book_id}")
    public ResponseEntity<BookDTO> getBook(@ApiParam(value = "Book's ID that need to be fetched", required = true) @PathVariable("book_id") Long bookId) {
        Optional<BookDTO> book = this.bookService.findById(bookId);
        return book.map(ControllersUtil::getOkResponseEntity)
                .orElseThrow( BookNotFoundException::new);
    }

    /**
     * Add a new book
     *
     * @param book {@link BookDTO} model
     * @return Added BookDTO {@link BookDTO}
     */
    @ApiOperation(value = "Add a new book", response = BookDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added book"),
            @ApiResponse(code = 500, message = "Some error happened during the operation")
    }
    )
    @PostMapping
    public ResponseEntity<BookDTO> addBook(@ApiParam(value = "Book model that needs to be added", required = true) @Valid @RequestBody BookDTO book) {
        Optional<BookDTO> savedBookDTO = this.bookService.save(book);
        return savedBookDTO.map(ControllersUtil::getCreatedResponseEntity)
                .orElseGet(ControllersUtil::getInternalServerErrorResponseEntity);
    }

    /**
     * Update an book using its book id
     *
     * @param bookId and updated book {@link BookDTO} model
     * @return Updated BookDTO {@link BookDTO}
     */
    @ApiOperation(value = "Update an book with an ID", response = BookDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated book"),
            @ApiResponse(code = 500, message = "Some error happened during the operation")
    }
    )
    @PutMapping(value = "/{book_id}")
    public ResponseEntity<BookDTO> updateBook(@ApiParam(value = "Book's ID that need to be updated", required = true) @PathVariable("book_id") Long bookId,
                                              @ApiParam(value = "Book model that needs to be updated", required = true) @Valid @RequestBody BookDTO book) {
        Optional<BookDTO> updatedBookDTO = this.bookService.updateById(bookId, book);
        return updatedBookDTO.map(ControllersUtil::getOkResponseEntity)
                .orElseGet(ControllersUtil::getInternalServerErrorResponseEntity);
    }

    /**
     * Delete an book using its book id
     *
     * @param bookId
     * @return HttpStatus 200 on Successful Delete
     */
    @ApiOperation(value = "Delete an book with an ID", response = HttpStatus.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted book"),
            @ApiResponse(code = 500, message = "Some error happened during the operation")
    }
    )
    @DeleteMapping(value = "/{book_id}")
    public HttpStatus deleteBook(@ApiParam(value = "Book's ID that need to be deleted", required = true) @PathVariable("book_id") Long bookId) {
        if (null != bookId) {
            this.bookService.deleteById(bookId);
            return HttpStatus.OK;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}

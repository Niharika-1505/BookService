package com.bookservice.util;

import com.bookservice.dto.ErrorDto;
import com.bookservice.dto.BookDTO;
import com.bookservice.model.Book;
import org.apache.commons.lang3.StringUtils;

/**
 * Utility class for all classes
 *
 * @author NIHARIKA GADDE
 */
public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Helper method for creating the {@link ErrorDto} from {@link Exception}
     *
     * @param ex Exception
     * @return {@link ErrorDto}
     */
    static ErrorDto getErrorDtoFromException(Exception ex) {
        return ErrorDto.builder()
                .exception(ex.getClass().getSimpleName())
                .message(ex.getMessage())
                .build();
    }

    /**
     * Helper method for validating the Book data
     *
     * @param book {@link Book}
     * @return {@link ErrorDto}
     */
    static boolean isBookDataValid(Book book) {
        boolean isValid = false;
        if (StringUtils.isNoneBlank(book.getName(), book.getAuthor(), book.getPublishedYear())
                && book.getPrice() != null && book.getPrice() > 0) {
            isValid = true;
        }
        return isValid;
    }

    public static BookDTO mapBookEntityToDTO(Book book) {
        if (null == book) {
            return null;
        }
        return BookDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .description(book.getDescription())
                .genre(book.getGenre())
                .price(book.getPrice())
                .publishedYear(book.getPublishedYear())
                .build();
    }

    public static Book convertToBookEntity(BookDTO bookDTO) {
        return Book.builder()
                .id(bookDTO.getId())
                .name(bookDTO.getName())
                .author(bookDTO.getAuthor())
                .description(bookDTO.getDescription())
                .genre(bookDTO.getGenre())
                .price(bookDTO.getPrice())
                .publishedYear(bookDTO.getPublishedYear())
                .build();
    }
}
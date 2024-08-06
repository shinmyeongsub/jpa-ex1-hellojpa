package hellojpa;

import jakarta.persistence.Entity;

@Entity
public class Book {
    private String author;
    private String isbn;
}

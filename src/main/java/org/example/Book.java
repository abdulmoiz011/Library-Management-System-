//  Book.java

package org.example;

public class Book {
    public int bookId;
    public String title,author,genre,availability;

    public Book(String _title,String _author,String _genre, String _availability, int _bookId){
        title=_title;
        author=_author;
        genre=_genre;
        availability=_availability;
        bookId=_bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getAvailability() {
        return availability;
    }

    public int getBookId() {
        return bookId;
    }

}

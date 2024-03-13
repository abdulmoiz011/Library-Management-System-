// Library.java

package org.example;

import java.util.ArrayList;

public class Library {
    ArrayList<User> _Users =new ArrayList<>();
    ArrayList<Book> _books =new ArrayList<>();


    public void addNewUser(User user){
        _Users.add(user);
    }

    public void addNewBook(Book book){
        _books.add(book);
    }

    public Book search_Books(String _title){
        for(Book book: _books){
            if(book.title==_title)
                return book;
        }
        return null;
    }

    public Book searchBooks(String _author){
        for(Book book: _books){
            if(book.author==_author)
                return book;
        }
        return  null;
    }


    public ArrayList<Book> checkingBooks(){
        return _books;
    }


}

// Library.java

package org.example;

import java.util.ArrayList;

public class Library {
    ArrayList<User> Users=new ArrayList<>();
    ArrayList<Book> Books=new ArrayList<>();


    public void addNewUser(User user){
        Users.add(user);
    }

    public void addNewBook(Book book){
        Books.add(book);
    }

    public Book serachBooks(String _title){
        for(Book book:Books){
            if(book.title==_title)
                return book;
        }
        return null;
    }

    public Book searchBooks(String _author){
        for(Book book:Books){
            if(book.author==_author)
                return book;
        }
        return  null;
    }


    public ArrayList<Book> checkingBooks(){
        return Books;
    }


}

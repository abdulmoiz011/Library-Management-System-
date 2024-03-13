// Main.java

import org.example.Book;
import org.example.Database;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] main){
String libOrUser="";
        do {
            System.out.println("Enter 1 to Login as User or Enter 2 to Login as a Librarian and Enter 3 to Exit");

            Scanner input=new Scanner(System.in);
             libOrUser=input.nextLine();
            if(libOrUser.equals("1")){
                System.out.println("Enter the Id of the User: ");
                String id=input.nextLine();
                if(Database.userLogin(id)) {

                    System.out.println("Successfully Logged in. ");
                    System.out.println("What do you wanna do:");
                    System.out.println("1. Book Collection");
                    System.out.println("2. Borrow Book");
                    String loggedInAction=input.nextLine();
                    if(loggedInAction.equals("1")) {
                        ArrayList<Book> books=new ArrayList<>();
                        books=Database.getBookCollection();
                        if(books.isEmpty())
                            System.out.println("There are no books");
                        else {
                            for (Book book : books) {
                                System.out.println("The id of the Book is: "+book.bookId);
                                System.out.println("The title of the Book is: "+ book.title);
                                System.out.println("The author of the Book is: "+book.author);
                                System.out.println("The Genre of the Book is: "+book.genre);
                                System.out.println("The availability of the Book is: "+(book.availability));
                                System.out.println();
                            }
                        }
                    }
                    else if(loggedInAction.equals("2")) {
                        System.out.println("Enter the Id of the Book you want to Borrow: ");
                        String borrowId= input.nextLine();
                        if(Database.borrowBook(borrowId, id))
                            System.out.println("The book has been borrowed");
                        else
                            System.out.println("Error Occurred: Couldn't borrow the Book Please try again later.");
                    }
                    else
                        System.out.println("Please Try again and give a valid input");

                }
                else
                    System.out.println("Wrong Credentials");
            }
            else if(libOrUser.equals("2")) {
                System.out.println("Enter Username of the Librarian: ");
                System.out.println("Hint: username is `admin`");
                String username=input.nextLine();
                if(username.equals("admin")) {
                    System.out.println("Enter what you wanna do: ");
                    System.out.println("1. To Show Book Collection");
                    System.out.println("2. To Add New Book");
                    System.out.println("3. To Add New User");
                    System.out.println("4. To return a Book");
                    System.out.println("5. To search for a Book");

                    int action=input.nextInt();
                    if(action==1) {
                        ArrayList<Book> books=new ArrayList<>();
                        books=Database.getBookCollection();
                        if(books.isEmpty())
                            System.out.println("There are no books");
                        else {
                            for (Book book : books) {
                                System.out.println("The id of the Book is: "+book.bookId);
                                System.out.println("The title of the Book is: "+ book.title);
                                System.out.println("The author of the Book is: "+book.author);
                                System.out.println("The Genre of the Book is: "+book.genre);
                                System.out.println("The availability of the Book is: "+book.availability);
                                System.out.println();
                            }
                        }

                    }
                    else if(action ==2) {
                        input=new Scanner(System.in);
                        System.out.println("Enter the title of the Book: ");
                        String title=input.nextLine();
                        System.out.println("Enter the author of the Book: ");
                        String author =input.nextLine();
                        System.out.println("Enter the genre of the Book: ");
                        String genre=input.nextLine();
                        if(Database.bookAddition(title,author,genre))
                            System.out.println("Successfully created Book");
                        else
                            System.out.println("Failed to create a book. Try Again Later.");

                    }
                    else if(action==3) {
                        input=new Scanner(System.in);
                        System.out.println("Enter the name of the user: ");
                        String name=input.nextLine();
                        System.out.println("Enter the Contact Info of the user: ");
                        String contactInfo=input.nextLine();

                        if(Database.userAddition(name,contactInfo))
                            System.out.println("Successfully created user");
                        else
                            System.out.println("Failed to create a user. Try Again Later.");
                    }
                    else if(action==4) {
                        System.out.println("Returning Books");
                        System.out.println("Enter the Book Id u wanna return");
                        int b_id = input.nextInt();
                        System.out.println("Enter the User Id who wants to return this book");
                        int u_id = input.nextInt();

                        if (Database.returnBook(Integer.toString(b_id), Integer.toString(u_id))) {
                            System.out.println("The user with id:" + u_id);
                            System.out.println("is returing the book with id: " + b_id);
                        }
                        else
                            System.out.println("Failed to return the Book with id: "+b_id);
                    }
                    else if(action==5){
                        System.out.println("Search for Books");
                        System.out.println("1. By Title");
                        System.out.println("2. By Author");
                        System.out.println("3. By Id");
input=new Scanner(System.in);
                        String searchAction=input.nextLine();

                        if(searchAction.equals("1")) {
                            System.out.println("Enter the title of the Book");
                            String title=input.nextLine();
                            ArrayList<Book> books=new ArrayList<>();

                            books=Database.searchForBooksByTitle(title);
                            if(books.isEmpty())
                                System.out.println("There are no books");
                            else {
                                for (Book book : books) {
                                    System.out.println("The id of the Book is: "+book.bookId);
                                    System.out.println("The title of the Book is: "+ book.title);
                                    System.out.println("The author of the Book is: "+book.author);
                                    System.out.println("The Genre of the Book is: "+book.genre);
                                    System.out.println("The availability of the Book is: "+(book.availability));
                                    System.out.println();
                                }
                            }
                        }
                        else if(searchAction.equals("2")) {
                            System.out.println("Enter the author of the Book");
                            String author=input.nextLine();
                            ArrayList<Book> books=new ArrayList<>();
                            books=Database.searchForBooksByAuthor(author);
                            if(books.isEmpty())
                                System.out.println("There are no books");
                            else {
                                for (Book book : books) {
                                    System.out.println("The id of the Book is: "+book.bookId);
                                    System.out.println("The title of the Book is: "+ book.title);
                                    System.out.println("The author of the Book is: "+book.author);
                                    System.out.println("The Genre of the Book is: "+book.genre);
                                    System.out.println("The availability of the Book is: "+(book.availability));
                                    System.out.println();
                                }
                            }
                        }
                        else if(searchAction.equals("3")) {
                            System.out.println("Enter the Id of the Book");
                            String id=input.nextLine();
                            ArrayList<Book> books=new ArrayList<>();
                            books=Database.searchForBooksById(id);
                            if(books.isEmpty())
                                System.out.println("There are no books");
                            else {
                                for (Book book : books) {
                                    System.out.println("The id of the Book is: "+book.bookId);
                                    System.out.println("The title of the Book is: "+ book.title);
                                    System.out.println("The author of the Book is: "+book.author);
                                    System.out.println("The Genre of the Book is: "+book.genre);
                                    System.out.println();
                                }
                            }
                        }
                        else
                            System.out.println("Please enter a valid input");

                    }


                }
                else
                    System.out.println("Please enter the correct username: `admin`");
            }
            else{
                System.out.println("Please Try again and give a valid input");
            }
        }while(!libOrUser.equals("3"));

    }
}

// Database.java

package org.example;

import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    public static Connection connect(){
        Connection conn=null;
        try{
            Class.forName("org.sqlite.JDBC");
            conn=DriverManager.getConnection("jdbc:sqlite:forJava.db");
            System.out.println("Connected");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e+"");
        }
        return conn;
    }

    public static boolean userLogin(String id){
        Connection con =Database.connect();
        PreparedStatement pss=null;
        ResultSet rss=null;
        try{
            String sql="SELECT * From Users where id=?";
            pss=con.prepareStatement(sql);
            pss.setString(1,id);
            rss = pss.executeQuery();
            String name="";
            while(rss.next()){
                 name=rss.getString("Name");
            }
            if(name==""){
                return false;
            }
            else
                return true;
        }catch (SQLException e){
            System.out.println(e+"");
            return false;
        }finally {
            try {
                if (rss != null) rss.close();
                if (pss != null) pss.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

    }

    public static boolean borrowBook(String id, String uId){
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try{
            con= Database.connect();
            String sql="SELECT * FROM Books where id=? and availability is null";
            ps=con.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(id));
            rs=ps.executeQuery();

            if(!rs.next()){
                return false;
            }
            ps.close();
            rs.close();

            con=Database.connect();

            sql = "update Books set availability=? where id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, uId);
            ps.setInt(2, Integer.parseInt(id));
            ps.executeUpdate();

            ps.close();
            con.close();
            con=Database.connect();
            sql="update Users set Borrowed_Books=? where id=?";
            ps=con.prepareStatement(sql);
            ps.setString(1,id);
            ps.setInt(2,Integer.parseInt(uId));
            ps.executeUpdate();

            return true;
        }catch (SQLException e){
            System.out.println(e+"");
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e);
                return false;
            }
        }
    }

    public static boolean returnBook(String id,String uId){
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try{
            con=Database.connect();
            String sql="SELECT * from Books where id=? and availability=?";
            ps=con.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(id));
            ps.setString(2,uId);
            rs=ps.executeQuery();

            if(!rs.next())
                return false;

            sql="update Books set availability=null where id=?";
            ps=con.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(id));
            ps.executeUpdate();

            return true;
        }catch (SQLException e){
            System.out.println(e+"");
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e);
                return false;
            }
        }
    }

    public static ArrayList<Book> getBookCollection(){
        ArrayList<Book> books=new ArrayList<>();
        Connection con= Database.connect();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            String sql="SELECT * FROM Books";
            ps=con.prepareStatement(sql);
            rs= ps.executeQuery();
            String title="";
            String author="";
            String genre="";
            String availability="";
            int id=0;
            while(rs.next()){
                title=rs.getString("title");
                author=rs.getString("author");
                genre=rs.getString("genre");
                availability=rs.getString("availability");
                id=rs.getInt("id");
                books.add(new Book(title,author,genre,availability,id));
            }
        }catch (SQLException e){
            System.out.println(e+"");
        }finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return books;
    }

    public static ArrayList<Book> searchForBooksByTitle(String title){
        ArrayList<Book> books=new ArrayList<>();
        Connection con =Database.connect();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            String sql="select * from Books where title =?";
            ps=con.prepareStatement(sql);
            ps.setString(1,title);
            rs=ps.executeQuery();
            String _title="";
            String author="";
            String genre="";
            String availability="";
            int id=0;
            while(rs.next()){
                _title=rs.getString("title");
                author=rs.getString("author");
                genre=rs.getString("genre");
                availability=rs.getString("availability");
                id=rs.getInt("id");
                books.add(new Book(_title,author,genre,availability,id));
            }

        }catch (SQLException e){
            System.out.println(e+"");
        }finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return books;
    }

    public static ArrayList<Book> searchForBooksByAuthor(String _author){
        ArrayList<Book> books=new ArrayList<>();
        Connection con =Database.connect();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            String sql="select * from Books where author =?";
            ps=con.prepareStatement(sql);
            ps.setString(1,_author);
            rs=ps.executeQuery();
            String _title="";
            String author="";
            String genre="";
            String availability="";
            int id=0;
            while(rs.next()){
                _title=rs.getString("title");
                author=rs.getString("author");
                genre=rs.getString("genre");
                availability=rs.getString("availability");
                id=rs.getInt("id");
                books.add(new Book(_title,author,genre,availability,id));
            }

        }catch (SQLException e){
            System.out.println(e+"");
        }finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return books;
    }

    public static ArrayList<Book> searchForBooksById(String _id){
        ArrayList<Book> books=new ArrayList<>();
        Connection con =Database.connect();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            String sql="select * from Books where id =?";
            ps=con.prepareStatement(sql);
            ps.setString(1,_id);
            rs=ps.executeQuery();
            String _title="";
            String author="";
            String genre="";
            String availability="";
            int id=0;
            while(rs.next()){
                _title=rs.getString("title");
                author=rs.getString("author");
                genre=rs.getString("genre");
                availability=rs.getString("availability");
                id=rs.getInt("id");
                books.add(new Book(_title,author,genre,availability,id));
            }

        }catch (SQLException e){
            System.out.println(e+"");
        }finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return books;
    }

    public static boolean bookAddition(String title,String author,String genre){
        Connection con=Database.connect();
        PreparedStatement ps=null;
        try{
            String sql="insert into Books(title,author,genre) values(?,?,?)";
            ps=con.prepareStatement(sql);
            ps.setString(1,title);
            ps.setString(2, author);
            ps.setString(3,genre);
            ps.execute();
            return true;
        }catch (SQLException e){
            System.out.println(e+"");
            return false;
        }finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }


    public static boolean userAddition(String name,String contactInfo){
        Connection con =Database.connect();
        PreparedStatement ps=null;
        try{
            String sql="insert into Users(Name,Contact_Info) values(?,?)";
            ps=con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,contactInfo);
            ps.execute();
            return true;

        }catch (SQLException e){
            System.out.println(e+"");
            return false;
        }finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }




}

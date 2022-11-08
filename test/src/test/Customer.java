package test;

import java.sql.*;

import java.util.ArrayList;
//SQL DataBase와 연결

public class Customer {
    public static void main(String[] args) {
        createTable();
    }
    //사용을 위해서 해당프로젝트에 mysqldbconnetor생성 필요
     //SQL문법에 맞추어 입력되는 값들을 DB로 전송한다
    public static String [][] getCustomers(){
        try{
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("Select name, author, booknumber, publisher, category, introduction FROM customer");
            ResultSet results = statement.executeQuery();
            ArrayList<String[]> list = new ArrayList<String[]>();
            while(results.next()){
                list.add(new String[] {
                        results.getString("name"),
                        results.getString("author"),
                        results.getString("booknumber"),
                        results.getString("publisher"),
                        results.getString("category"),
                        results.getString("introduction"),
                });
            }
            System.out.println("The data has been fetched");
            String[][] arr = new String[list.size()][5];
            return list.toArray(arr);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    // 테이블에 Header추가
    public static void createCustomer(String name, String author, String booknumber, String publisher, String category, String introduction){
        try{
            Connection con = getConnection();
            PreparedStatement insert = con.prepareStatement(""
                    + "INSERT INTO customer"
                    + "(name, author, booknumber, publisher, category, introduction) "
                    + "VALUE "
                    + "('"+name+"','"+author+"','"+booknumber+"','"+publisher+"','"+category+"','"+introduction+"')");
            insert.executeUpdate();
            System.out.println("The data has been saved!");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Table 생성
    public static void createTable(){
        try{
            Connection con = getConnection();
            Statement stmt = con.createStatement();        
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            System.out.println("Table successfully created");
        }
    }
    //MYSQL 연결
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "0366");
            System.out.println("DB연결 성공");
            return con;
        }catch(ClassNotFoundException e){
        	System.out.println("JDBC 드라이버 로드 에러");
        	return null;
        	
        }catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("SQL 실행 에러");
            return null;
        }
    } 
}
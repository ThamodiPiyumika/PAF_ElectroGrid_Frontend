package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment_management","root", "admin123");
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	
	public String insertPayment(String CustomerName, String Address, String Expiry, String BillNo, String Amount) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into payment (`PaymentID`,`CustomerName`,`Address`,`AccountNo`,`BillNo`, `Amount`)"
	 + " values (?, ?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, CustomerName); 
	 preparedStmt.setString(3, Address); 
	 preparedStmt.setString(4, Expiry); 
	 preparedStmt.setInt(5, Integer.parseInt(BillNo));
	 preparedStmt.setInt(6, Integer.parseInt(Amount));
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newPay = readPayment();
		output = "{\"status\":\"success\", \"data\": \"" +newPay+ "\"}";
	 //output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the Payment details"; 
	 output = "{\"status\":\"error\", \"data\":\"Error while Inserting the Payment Details.\"}";
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	public String readPayment() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table class='table' border='1'>" +
	 "<thead>" +
	 "<tr>"  +
	 "<th>Customer Name</th>" +
	 "<th>Address</th>" + 
	 "<th>Account No</th>" +
	 "<th>BillNo</th>"+
	 "<th>Amount</th>"+
	 "<th>Update</th>"+
	 "<th>Remove</th></tr></thead>"; 
	 
	 String query = "select * from payment"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String PaymentID = Integer.toString(rs.getInt("PaymentID")); 
	 String CustomerName = rs.getString("CustomerName"); 
	 String Address = rs.getString("Address"); 
	 String AccountNo = rs.getString("AccountNo"); 
	 String BillNo = Integer.toString(rs.getInt("BillNo"));
	 String Amount = Integer.toString(rs.getInt("Amount"));
	 
	 // Add into the html table
	 
	 output += "<tr><td> <input id='hididUpdate' name='hididUpdate' type='hidden' value=" + PaymentID + ">" + CustomerName + "</td>"; 
	 output += "<td>" + Address + "</td>"; 
	 output += "<td>" + AccountNo + "</td>";
	 output += "<td>" + BillNo + "</td>";
	 output += "<td>" + Amount + "</td>";
	
	 
	// buttons
		output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
				+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-PaymentID='"
				+ PaymentID + "'>" + "</td></tr>";
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the Payment Details"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	public String updatePayment(String PaymentID, String CustomerName, String Address, String AccountNo, String BillNo, String Amount) 
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE payment  SET CustomerName=?,Address=?,AccountNo=?,BillNo=?,Amount=? WHERE PaymentID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		
		 preparedStmt.setString(1, CustomerName); 
		 preparedStmt.setString(2, Address); 
		 preparedStmt.setString(3, AccountNo); 
		 preparedStmt.setInt(4,Integer.parseInt(BillNo)); 
		 preparedStmt.setInt(5, Integer.parseInt(Amount)); 
		 preparedStmt.setInt(6, Integer.parseInt(PaymentID)); 

		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newPay = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" +newPay+ "\"}";
		 //output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the payment details.\"}";
		// output = "Error while updating the payment details."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	public String deletePayment(String PaymentID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from  payment where PaymentID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(PaymentID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newPay = readPayment();
		output = "{\"status\":\"success\", \"data\": \"" +  newPay + "\"}";
	// output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "{\"status\":\"error\", \"data\":\"Error while Deleting the Payment Details.\"}";
	// output = "Error while deleting the Payment Details"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 


}



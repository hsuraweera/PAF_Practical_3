import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {
	
public static void insertItem(int itemId, String code, String name, Double price, String desc) throws SQLException {
		
	java.sql.Connection conn = Connection.getConnection();
		
		String output = "";
		
		try {
			
			String query = " insert into items (itemID,itemCode,itemName,itemPrice,itemDesc) values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = conn.prepareStatement(query);
				
			preparedStmt.setInt(1, itemId);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4, price);
			preparedStmt.setString(5, desc);
			
			//execute the statement
			preparedStmt.execute();
			conn.close();
			output = "Inserted successfully";
			
		}catch(Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		
		
		
}



public static String readItems()
{
	String output = "";
	try
	{
		java.sql.Connection con = Connection.getConnection();
		if (con == null)
	{
		return "Error while connecting to the database for reading.";
	}
	// Prepare the html table to be displayed
	output = "<table border='1'><tr><th>Item Code</th>"
	+"<th>Item Name</th><th>Item Price</th>"
	+ "<th>Item Description</th>"
	+ "<th>Update</th><th>Remove</th></tr>";
	
	String query = "select * from items";
	
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	
	// iterate through the rows in the result set
	while (rs.next())
	{
		String itemID = Integer.toString(rs.getInt("itemID"));
		String itemCode = rs.getString("itemCode");
		String itemName = rs.getString("itemName");
		String itemPrice = Double.toString(rs.getDouble("itemPrice"));
		String itemDesc = rs.getString("itemDesc");
		// Add a row into the html table
		output += "<tr><td>" + itemCode + "</td>";
		output += "<td>" + itemName + "</td>";
		output += "<td>" + itemPrice + "</td>";
		output += "<td>" + itemDesc + "</td>";
		// buttons
		output += "<td><input name='btnUpdate' "
		+ " type='button' value='Update'></td>"
		+ "<td><form method='post' action='items.jsp'>"
		+ "<input name='btnRemove' "
		+ " type='submit' value='Remove'>"
		+ "<input name='itemID' type='hidden' "
		+ " value='" + itemID + "'>" + "</form></td></tr>";
	}
	con.close();
	// Complete the html table
	output += "</table>";
	}
	catch (Exception e)
	{
		output = "Error while reading the items.";
		System.err.println(e.getMessage());
	}
	return output;
}
	

}
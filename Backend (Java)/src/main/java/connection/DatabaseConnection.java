package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import model.Invoice;


public class DatabaseConnection {
	//Here I am creating an ArrayList of Invoices to store values of invoices from the sql file named 

    public static Connection DbConnection() throws ClassNotFoundException  {
        String url = "jdbc:mysql://localhost:3306/oap";
		String username = "root";
		String password = "rohit123";
		Connection con = null; 
        try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
								
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return con;
    }
    
    //Following are the methods to get and add invoices into the sql file 
    public List<Invoice> getInvoices(){//Method used to get invoices data from sql file
    	List<Invoice> invoices;
    	invoices = new ArrayList<>();
    	//Invoice invoice = null;
        String url = "jdbc:mysql://localhost:3306/oap";
		String username = "root";
		String password = "rohit123";
		Connection con = null; 
        try {
			Class.forName("com.mysql.jdbc.Driver");
						
            con = DriverManager.getConnection(url, username, password);
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT sl_No, customer_Order_Id, sales_Org, distribution_Channel, company_Code, order_Creation_Date, order_Currency, customer_Number, amount_In_USD FROM H2H_OAP LIMIT 100000");
			
			while(rs.next()) {
				Invoice invoice = new Invoice(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9)); // Using parameterized constructor
				invoices.add(invoice);
				// Here I am adding all the rows of the sql file into invoice objects and then storing them to array of invoices so that I can access the invoices if required in getInvoice method
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
    	System.out.println("Returning Invoice");//Using the list of invoices created while constructor is called
    	for(Invoice invoice : invoices) {
    		System.out.println(invoice);
    		System.out.println("Sl_No-"+invoice.getSlNo()+" Customer_Order_Id-"+invoice.getCustomerOrderId()+" Sales_Org-"+invoice.getSalesOrg()+" Distribution_Channel-"+invoice.getDistributionChannel()+" Company_Code-"+invoice.getCompanyCode()+" Oder_Creation_Date-"+invoice.getOrderCreationDate()+" Order_Currency-"+invoice.getOrderCurrency()+" Customer_Number"+invoice.getCustomerNumber()+" Amount_In_USD-"+invoice.getAmountInUSD());
    	}
        return invoices;
    }

    public boolean addInvoice(Invoice invoice) throws ClassNotFoundException {
    	int var = 0;
    	String url = "jdbc:mysql://localhost:3306/oap";
		String username = "root";
		String password = "rohit123";
		Connection con = null; 
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		con = DriverManager.getConnection(url, username, password);
            //sql querry to add data to sql table
            String query = "INSERT INTO H2H_OAP (customer_Order_Id, sales_Org, distribution_Channel, company_Code, order_Creation_Date, order_Currency, customer_Number, amount_In_USD) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                        
            //Passing values into querry by using getters of Invoice class to get different sttributes of the invoice to be added
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, invoice.getCustomerOrderId());
            ps.setInt(2, invoice.getSalesOrg());
            ps.setString(3, invoice.getDistributionChannel());
            ps.setInt(4, invoice.getCompanyCode());
            ps.setString(5, invoice.getOrderCreationDate());
            ps.setString(6, invoice.getOrderCurrency());
            ps.setInt(7, invoice.getCustomerNumber());
            ps.setDouble(8, invoice.getAmountInUSD());
            
            //  Getting Invoice Class properties using corresponding setter methods

            var = ps.executeUpdate();

            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if(var == 0) {
        	return false;
        }
        System.out.println("Invoice Added to the sql table!");
        return true;
        
    }
}

package implementation;
import connection.DatabaseConnection;
import model.Invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;


public class InvoiceDaoImpl implements InvoiceDao {
	
	private DatabaseConnection databaseConnection;
	
	public InvoiceDaoImpl(){
        databaseConnection = new DatabaseConnection(); //Creating an object of databaseConnection class to establish a connection to sql file
    }

    @Override
    public List<Invoice> getInvoice(){
        return databaseConnection.getInvoices(); // Here I am calling method getInvoice of databaseConnection Class
    }

    @Override
    public boolean insertInvoice(Invoice invoice) {
        try {
			return databaseConnection.addInvoice(invoice);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Here I am calling method addInvoice of databaseConnection Class
		return false;
    }

    @Override
    public boolean updateInvoice(int id, Invoice invoice) {
    	int rowsUpdated = 0;
    	try (Connection connection = DatabaseConnection.DbConnection()) {
            // Preparing the SQL update statement
            String sql = "UPDATE H2H_OAP SET slNo=?, salesOrg=?, distributionChannel=?, " +
                    "companyCode=?, orderCreationDate=?, orderCurrency=?, customerNumber=?, amountInUSD=? " +
                    "WHERE customerOrderId=?";
            
            // Passing the parameters into the sql statement from getters of the Invoice object
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, invoice.getSlNo());
            statement.setInt(2, invoice.getSalesOrg());
            statement.setString(3, invoice.getDistributionChannel());
            statement.setInt(4, invoice.getCompanyCode());
            statement.setString(5, invoice.getOrderCreationDate());
            statement.setString(6, invoice.getOrderCurrency());
            statement.setInt(7, invoice.getCustomerNumber());
            statement.setDouble(8, invoice.getAmountInUSD());
            statement.setInt(9, id);

            // Executing the update statement
            rowsUpdated = statement.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    	if (rowsUpdated > 0) {
            System.out.println("Invoice updated successfully.");
            return true;
        } else {
            System.out.println("No invoice found with customerOrderId: " + id);
            return false;
        }
   }

    @Override
    public boolean deleteInvoice(int id) {
        int rowsDeleted = 0;
    	try(Connection connection = DatabaseConnection.DbConnection()){
        	String sql = "DELETE FROM invoices WHERE customerOrderId=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            // Executing the delete statement
            rowsDeleted = statement.executeUpdate();
            
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (rowsDeleted > 0) {
            System.out.println("Invoice deleted successfully!");
            return true;
        } else {
            System.out.println("No invoice found with customerOrderId: " + id);
            return false;
        }
    }

}

package servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import implementation.InvoiceDao;
import implementation.InvoiceDaoImpl;
import model.Invoice;

import com.google.gson.Gson;


@WebServlet("/Read-Invoice")

public class ReadInvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// doGet method to take care of client get request
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InvoiceDao invoicedao = new InvoiceDaoImpl();
		List <Invoice> invoices = new ArrayList<Invoice>(); // creating an array list of objects of Invoice class to get data from sql file  
		
		try {
			invoices = invoicedao.getInvoice();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Here we are converting our normal array list into json format so that it can be displayed over the webpage
		Gson gson = new Gson();
		String jsonResponse = new String();
		
		jsonResponse = gson.toJson(invoices);
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(jsonResponse);
	}
	
}

package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import implementation.InvoiceDao;
import implementation.InvoiceDaoImpl;
import model.Invoice;


@WebServlet("/AddInvoice")
public class AddInvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private InvoiceDao invoicedao = new InvoiceDaoImpl();
    
   
    public AddInvoiceServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	response.getWriter().append("Served at: ").append(request.getContextPath());
    	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/AddInvoice.jsp");
    	dispatcher.forward(request, response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		//request.getRequestDispatcher("/AddInvoice.jsp").include(request, response);
		System.out.println(request.getParameter("customerOrderId"));
		System.out.println(request.getParameter("SalesOrg"));
		System.out.println(request.getParameter("distributionChannel"));
		System.out.println(request.getParameter("companyCode"));
		
		int customerOrderId = Integer.parseInt(request.getParameter("customerOrderId"));
	    int salesOrg = Integer.parseInt(request.getParameter("SalesOrg"));
	    String distributionChannel = request.getParameter("distributionChannel");
	    int companyCode = Integer.parseInt(request.getParameter("companyCode"));
	    String orderCreationDate = request.getParameter("orderCreationDate");
	    String orderCurrency = request.getParameter("orderCurrency");
	    int customerNumber = Integer.parseInt(request.getParameter("customerNumber"));
	    double amountInUSD = Double.parseDouble(request.getParameter("amountInUSD"));
	    Invoice invoice = new Invoice(customerOrderId, salesOrg, distributionChannel, companyCode, orderCreationDate, orderCurrency, customerNumber, amountInUSD);
	    
	    System.out.println(request.getParameter("customerOrderId"));
		System.out.println(request.getParameter("SalesOrg"));
		System.out.println(request.getParameter("distributionChannel"));
		System.out.println(request.getParameter("companyCode"));
		
	    try {
	    	boolean inserted =  invoicedao.insertInvoice(invoice);
	    	if (inserted) {
	    		response.getWriter().println("Invoice Added Successfully!");
	    	}
	    	else {
	    		response.getWriter().println("Failed to add Invoice!");
	    	}
	    	
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	}
}

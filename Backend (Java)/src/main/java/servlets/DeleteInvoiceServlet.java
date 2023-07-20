package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import implementation.InvoiceDao;
import implementation.InvoiceDaoImpl;

 
@WebServlet("/Delete-Invoice")
public class DeleteInvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
private InvoiceDao invoicedao;
    
    public void init() {
    	invoicedao = new InvoiceDaoImpl();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Getting the customerOrderId parameter from the responce
        int customerOrderId = Integer.parseInt(request.getParameter("customerOrderId"));
        
        try {
        	boolean deleted = invoicedao.deleteInvoice(customerOrderId);
	    	if (deleted) {
	    		response.getWriter().println("Invoice Updated Successfully!");
	    	}
	    	else {
	    		response.getWriter().println("Failed to Update Invoice!");
	    	}
	    	
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
        
	}


}

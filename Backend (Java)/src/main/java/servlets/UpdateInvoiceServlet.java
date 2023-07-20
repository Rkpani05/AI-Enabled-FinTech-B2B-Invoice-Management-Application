package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import implementation.InvoiceDao;
import implementation.InvoiceDaoImpl;
import model.Invoice;

@WebServlet("/Update-Invoice")

public class UpdateInvoiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private InvoiceDao invoicedao;
    
    public void init() {
    	invoicedao = new InvoiceDaoImpl();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Getting the customerOrderId parameter from the response
        int customerOrderId = Integer.parseInt(request.getParameter("customerOrderId"));

        // Creating an instance of Invoice and set its attributes
        Invoice invoice = new Invoice();
        invoice.setSlNo(Integer.parseInt(request.getParameter("slNo")));
        invoice.setSalesOrg(Integer.parseInt(request.getParameter("salesOrg")));
        invoice.setDistributionChannel(request.getParameter("distributionChannel"));
        invoice.setCompanyCode(Integer.parseInt(request.getParameter("companyCode")));
        invoice.setOrderCreationDate(request.getParameter("orderCreationDate"));
        invoice.setOrderCurrency(request.getParameter("orderCurrency"));
        invoice.setCustomerNumber(Integer.parseInt(request.getParameter("customerNumber")));
        invoice.setAmountInUSD(Double.parseDouble(request.getParameter("amountInUSD")));

        

        try {
        	boolean updated = invoicedao.updateInvoice(customerOrderId, invoice); // Calling the updateInvoice function to update the invoice
	    	if (updated) {
	    		response.getWriter().println("Invoice Updated Successfully!");
	    	}
	    	else {
	    		response.getWriter().println("Failed to Update Invoice!");
	    	}
	    	
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
    }
   };
package implementation;
import java.util.*;

import model.Invoice;

public interface InvoiceDao {
	List<Invoice> getInvoice() throws ClassNotFoundException;
    boolean insertInvoice(Invoice invoice);
    boolean updateInvoice(int id, Invoice invoice);
    boolean deleteInvoice(int id);
}

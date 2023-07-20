<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Customer Order Form</h1>
    <form action = "<%= request.getContextPath() %>/AddInvoice" method="post">
        <label for="customerOrderId">Customer Order ID:</label>
        <input type="number" id="customerOrderId" name="customerOrderId" required><br><br>
        
        <label for="salesOrg">Sales Organization:</label>
        <input type="number" id="SalesOrg" name="SalesOrg" required><br><br>
        
        <label for="distributionChannel">Distribution Channel:</label>
        <input type="text" id="distributionChannel" name="distributionChannel" required><br><br>
        
        <label for="companyCode">Company Code:</label>
        <input type="number" id="companyCode" name="companyCode" required><br><br>
        
        <label for="orderCreationDate">Order Creation Date:</label>
        <input type="text" id="orderCreationDate" name="orderCreationDate" required><br><br>
        
        <label for="orderCurrency">Order Currency:</label>
        <input type="text" id="orderCurrency" name="orderCurrency" required><br><br>
        
        <label for="customerNumber">Customer Number:</label>
        <input type="number" id="customerNumber" name="customerNumber" required><br><br>
        
        <label for="amountInUSD">Amount in USD:</label>
        <input type="number" id="amountInUSD" name="amountInUSD" required><br><br>
        
        <input type="submit" value="Submit">
    </form>
</body>
</html>
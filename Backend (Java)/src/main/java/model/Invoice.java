package model;

public class Invoice {
	// The class Invoice contains the following features
	private int slNo;
	private int customerOrderId;
    private int salesOrg;
    private String distributionChannel;
    private int companyCode;
    private String orderCreationDate;
    private String orderCurrency;
    private int customerNumber;
    private double amountInUSD;
    
    public Invoice() {
		super();
	}

	//Here it is a parameterized constructor
    public Invoice(int customerOrderId, int salesOrg, String distributionChannel, int companyCode,
			String orderCreationDate, String orderCurrency, int customerNumber, double amountInUSD) {
		super();
		this.customerOrderId = customerOrderId;
		this.salesOrg = salesOrg;
		this.distributionChannel = distributionChannel;
		this.companyCode = companyCode;
		this.orderCreationDate = orderCreationDate;
		this.orderCurrency = orderCurrency;
		this.customerNumber = customerNumber;
		this.amountInUSD = amountInUSD;
	}
    
    public Invoice(int slNo, int customerOrderId, int salesOrg, String distributionChannel,
    		int companyCode, String orderCreationDate, String orderCurrency,
    		int customerNumber, double amountInUSD) {
    	super();	
    	this.slNo = slNo;
    	this.customerOrderId = customerOrderId;
    	this.salesOrg = salesOrg;
    	this.distributionChannel = distributionChannel;
    	this.companyCode = companyCode;
    	this.orderCreationDate = orderCreationDate;
    	this.orderCurrency = orderCurrency;
    	this.customerNumber = customerNumber;
    	this.amountInUSD = amountInUSD;
    }
    

    // Getters and setters for all the required fields
    

	public int getSlNo() {
		return slNo;
	}

	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}

	public int getCustomerOrderId() {
		return customerOrderId;
	}

	public void setCustomerOrderId(int customerOrderId) {
		this.customerOrderId = customerOrderId;
	}

	public int getSalesOrg() {
		return salesOrg;
	}

	public void setSalesOrg(int salesOrg) {
		this.salesOrg = salesOrg;
	}

	public String getDistributionChannel() {
		return distributionChannel;
	}

	public void setDistributionChannel(String distributionChannel) {
		this.distributionChannel = distributionChannel;
	}

	public int getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(int companyCode) {
		this.companyCode = companyCode;
	}

	public String getOrderCreationDate() {
		return orderCreationDate;
	}

	public void setOrderCreationDate(String orderCreationDate) {
		this.orderCreationDate = orderCreationDate;
	}

	public String getOrderCurrency() {
		return orderCurrency;
	}

	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public double getAmountInUSD() {
		return amountInUSD;
	}

	public void setAmountInUSD(double amountInUSD) {
		this.amountInUSD = amountInUSD;
	}
    
    
}

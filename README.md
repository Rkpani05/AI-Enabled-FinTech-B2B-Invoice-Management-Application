# AI-Enabled FinTech B2B Invoice Management Application

This project is a Full-stack Invoice Management Application developed using Java, ReactJS, JDBC, and Java Servlets. The application aims to provide an AI-powered solution for managing B2B invoices, allowing businesses to track their accounts receivable efficiently and predict order amounts. The application includes a responsive Receivables Dashboard with various features to manage invoices effectively.

## Table of Contents
* Introduction
* Business Overview
* Tech Stack
* Features
* Getting Started
* Setup Instructions
* Running the Application
* API Endpoints
* Contributing
* License

## Introduction
The B2B Invoice Management Application is designed to cater to the specific needs of businesses dealing with other businesses on credit. It streamlines the process of managing accounts receivable, credit checks, and invoice processing. The application includes an AI-based predictive model that can help estimate order amounts based on historical data.

## Business Overview
In the B2B world, businesses often work on credit terms and issue invoices for goods or services sold to other businesses. Managing these accounts receivable efficiently is crucial for maintaining a healthy cash flow. This application provides tools for credit check departments to validate customers, verify credit limits, and handle the entire order inflow process.

## Tech Stack
The application is built using the following technologies:

* Frontend:
  * ReactJS
  * Material UI
  * Backend:

* Java Servlets
  * JDBC for database connectivity
  * Database:

* MySQL
  * AI Integration:

* Flask (Python)
  
## Features
The application offers the following key features:

**Receivables Dashboard:** A responsive dashboard to view and manage invoice data.
Grid Data Operations: Perform CRUD operations (Create, Read, Update, Delete) on invoice data.
**Pagination:** Allow users to control the number of rows displayed per page.
**AI-Predictions:** Implement a predictive model to estimate order amounts.
**Advanced Search:** Provide advanced search functionality based on different parameters.
**Analytics View:** Visualize data using bar graphs and pie charts.
**Data Loading in DB:** Import provided SQL file to set up the initial database.
**API Support:** Implement APIs for various functionalities.

## Getting Started
Before running the application, ensure you have the following prerequisites installed:

* Node.js
* Java Development Kit (JDK)
* MySQL Server
* Apache Tomcat
* Python (for AI integration with Flask)

## Setup Instructions

Clone the repository to your local machine:

```git clone https://github.com/your-username/fintech-invoice-management.git```
```cd fintech-invoice-management```

Set up the backend:

* Import the provided SQL file [Invoice_management.sql](https://drive.google.com/file/d/18oPFSeJlNkhYbnJRTx0dSOPyWcwP-v1q/view?usp=sharing) into your MySQL Server to create the necessary database and tables.
* Open Eclipse IDE and import the Java backend project into your workspace.
* Configure Apache Tomcat in Eclipse Runtime Environments.
* Resolve project dependencies and ensure all required libraries are in the build path.

## Running the Application
-Start the Java backend in Eclipse:
  * Locate the main Java class for the backend application.
  * Right-click on the main class and select "Run As" > "Java Application".
  * Eclipse will compile the Java code and run the backend application on the Apache Tomcat server.

-Start the React frontend:
 * Open a terminal in the frontend directory.
 * Install dependencies:
   ```npm install```
 * Run the frontend:
   ```npm start```
The frontend will be accessible at http://localhost:3000/.

## API Endpoints
The following API endpoints are available:

* GET /api/invoices: Get all invoice data.
* POST /api/invoices: Add a new invoice.
* PUT /api/invoices/:id: Update an existing invoice.
* DELETE /api/invoices/:id: Delete an invoice by ID.
* POST /api/invoices/predict/:id: Predict order amount for an invoice.

## Contributing
I welcome contributions to improve the application. To contribute, follow these steps and send a mail to rk.pani2002@gmail.com:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make changes and commit them.
4. Push your changes to your fork.
5. Create a pull request to merge your changes into the main repository.
   
## License
This project is licensed under the MIT License. Feel free to use, modify, and distribute the code as per the terms of the license.

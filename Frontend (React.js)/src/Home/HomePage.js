// Importing React and necessary dependencies
import React, { useEffect, useState, useRef} from 'react';
import { DataGrid } from '@mui/x-data-grid'; // Import the DataGrid component from the '@mui/x-data-grid' library
import axios from 'axios'; // Import the axios library for making HTTP requests
import './HomePage.css'; // Import the CSS file for the HomePage component
import ABCLogo from '../Logo/abclogo.svg'; // Import the SVG logo file for ABC
import { toast, ToastContainer } from 'react-toastify'; // Import the toast and ToastContainer components from the 'react-toastify' library
import 'react-toastify/dist/ReactToastify.css'; // Import the CSS file for styling the toast messages


const Datagrid = () => {
  const [rows, setRows] = useState([]); // Holds the data for the grid
  const [pageSize, setPageSize] = useState(10); // Determines the number of rows to display per page
  const [selectedSection, setSelectedSection] = useState(''); // Keeps track of the selected section
  const gridRef = useRef(null); // Reference to the DataGrid component


  useEffect(() => {
    fetchInvoiceData();
  }, []);
  
  const fetchInvoiceData = () => {
    // Function to fetch invoice data from the server
  
    const url = `http://localhost:8100/Milestone-3/Read-Invoice`; // URL for fetching the invoice data
  
    axios
      .get(url) // Make a GET request to the specified URL
      .then((response) => {
        // Handle the successful response
  
        const data = response.data; // Extract the data from the response
        const rowsWithId = data.map((row, index) => ({ id: index + 1, ...row })); // Add unique IDs to each row
  
        setRows(rowsWithId); // Update the state with the fetched rows
  
        if (gridRef.current) {
          gridRef.current.scrollTo(0); // Scroll to the top of the grid
        }
      })
      .catch((error) => {
        // Handle any errors that occur during the request
        console.log('Please connect to Database:', error); // Log the error message to the console
      });
  };
  
  const columns = [
    // Define the columns for the DataGrid
    { field: 'slNo', headerName: 'Sl No', width: 100 }, // Column for Sl No
    { field: 'customerOrderId', headerName: 'Customer Order Id', width: 200 }, // Column for Customer Order Id
    { field: 'salesOrg', headerName: 'Sales Org', width: 200 }, // Column for Sales Org
    { field: 'distributionChannel', headerName: 'Distribution Channel', width: 250 }, // Column for Distribution Channel
    { field: 'companyCode', headerName: 'Company Code', width: 200 }, // Column for Company Code
    { field: 'orderCreationDate', headerName: 'Order Creation Date', width: 200 }, // Column for Order Creation Date
    { field: 'orderCurrency', headerName: 'Order Currency', width: 200 }, // Column for Order Currency
    { field: 'customerNumber', headerName: 'Customer Number', width: 200 }, // Column for Customer Number
    { field: 'amountInUSD', headerName: 'Amount in USD', width: 200 }, // Column for Amount in USD
  ];
  
  const [selectedRows] = useState([]); // Holds the currently selected rows
  const [searchValue, setSearchValue] = useState(''); // Holds the value entered in the search input
  const [setSearchResults] = useState([]); // Holds the search results

  const handleSectionClick = (sectionName) => {
    // Function to handle section click

    setSelectedSection(sectionName === 'Home Page' ? '' : sectionName); // Update the selected section based on the clicked section name
  };
  
const handleSubmit = () => {
  // Function to handle form submission
  // Get the values from the input fields
  const customerOrderId = document.getElementById('customerOrderId').value;
  const salesOrg = document.getElementById('salesOrg').value;
  const distributionChannel = document.getElementById('distributionChannel').value;
  const customerNumber = document.getElementById('customerNumber').value;
  const companyCode = document.getElementById('companyCode').value;
  const orderCurrency = document.getElementById('orderCurrency').value;
  const amountInUSD = document.getElementById('amountInUSD').value;
  const orderCreationDate = document.getElementById('orderCreationDate').value;

  // Create an object with the form data
  const data = {
    customerOrderId,
    salesOrg,
    distributionChannel,
    customerNumber,
    companyCode,
    orderCurrency,
    amountInUSD,
    orderCreationDate,
  };

  axios
  .post('http://localhost:8100/Milestone-3/AddInvoice', data) // Make a POST request to the specified URL with the form data
  .then((response) => {
    // Handle the successful response

    console.log('Data saved:', response.data); // Log the response data to the console
    fetchInvoiceData(); // Fetch the updated invoice data
    handleSectionClick(''); // Reset the selected section
    toast.success('Data saved successfully'); // Show success notification
  })
  .catch((error) => {
    // Handle any errors that occur during the request
    console.log('Error saving data:', error); // Log the error message to the console
    toast.error('Error saving data'); // Show error notification
  });
};

const handleRefreshData = () => {
  // Function to handle data refresh
  fetchInvoiceData(); // Fetch the updated invoice data
  toast.success('Grid data refreshed!', { autoClose: 2000 }); // Show success notification with auto-close after 2000 milliseconds
};


const handleEditSubmit = () => {
  // Function to handle edit form submission

  // Get the values from the edit form fields
  const orderCurrency = document.getElementById('orderCurrency').value;
  const companyCode = document.getElementById('companyCode').value;
  const distributionChannel = document.getElementById('distributionChannel').value;

  // Get the selected row's ID
  const selectedRowId = selectedRows[0].id;

  // Create the payload with the updated data
  const payload = {
    id: selectedRowId,
    orderCurrency,
    companyCode,
    distributionChannel
  };

  // Make a PUT request to update the data
  axios
    .put('http://localhost:8100/Milestone-3/UpdateInvoice', payload) // Make a PUT request to the specified URL with the payload
    .then((response) => {
      // Handle the successful response

      console.log('Data updated:', response.data); // Log the response data to the console
      fetchInvoiceData(); // Fetch the updated invoice data
      toast.success('Data updated successfully'); // Show success notification
    })
    .catch((error) => {
      // Handle any errors that occur during the request
      console.log('Error updating data:', error); // Log the error message to the console
      toast.error('Error updating data'); // Show error notification
    });

  // Reset the edit form fields
  document.getElementById('orderCurrency').value = '';
  document.getElementById('companyCode').value = '';
  document.getElementById('distributionChannel').value = '';

  // Close the edit popup window
  handleSectionClick('Home Page');
};


const handleDelete = () => {
  // Function to handle delete action
  setSelectedSection('Delete Data'); // Set the selected section to 'Delete Data'
};

const confirmDelete = () => {
  // Function to confirm delete action

  // Get the selected row's ID
  const selectedRowId = selectedRows[0].id;

  // Make a DELETE request to delete the data
  axios
    .delete(`http://localhost:8100/Milestone-3/Delete-Invoice/${selectedRowId}`) // Make a DELETE request to the specified URL with the selected row's ID
    .then((response) => {
      // Handle the successful response

      console.log('Data deleted:', response.data); // Log the response data to the console
      fetchInvoiceData(); // Fetch the updated invoice data
      toast.success('Data deleted successfully'); // Show success notification
    })
    .catch((error) => {
      // Handle any errors that occur during the request
      console.log('Error deleting data:', error); // Log the error message to the console
      toast.error('Error deleting data'); // Show error notification
    });

  // Reset the state
  setSelectedSection(''); // Reset the selected section
};


const handleSearch = () => {
  // Function to handle search logic

  // Perform the search logic here
  // You can filter the rows based on the search value and update the search results state
  const filteredRows = rows.filter((row) =>
    row.customerOrderId.toLowerCase().includes(searchValue.toLowerCase())
  );
  setSearchResults(filteredRows); // Update the search results state with the filtered rows
};


const handlePredict = () => {
  // Function to handle predict logic

  // Handle predict logic here
  // Add code to perform the desired prediction functionality
};

// JSX rendering
return (
  <div className="custom-table-container">
    
    <header className="header">
      <div className="abc-logo">
        <img src={ABCLogo} alt="ABC Product" />
      </div>
      <div className="invoice-list">
        <span className="invoice-list-text">Invoice List</span>
      </div>
      </header>
      <div className="custom-data-table">
        <div className="table-section">
          <div
            className={`table-section-item ${selectedSection === 'Home Page' ? 'active' : ''}`}
            onClick={() => handleSectionClick('Home Page')}
          >
            Home Page
          </div>
          <div
            className={`table-section-item ${selectedSection === 'Add Data' ? 'active' : ''}`}
            onClick={() => handleSectionClick('Add Data')}
          >
            Add Data
          </div>
          <div
            className={`table-section-item ${selectedSection === 'Analytics View' ? 'active' : ''}`}
            onClick={() => handleSectionClick('Analytics View')}
          >
            Analytics View
          </div>
          <div className="search-bar">
          <input
            type="text"
            placeholder="Search Customer Order ID"
            value={searchValue}
            onChange={(e) => setSearchValue(e.target.value)}
          />
          <button onClick={handleSearch}>Search</button>
        </div>
        </div>
        <ToastContainer />
        <DataGrid
          ref={gridRef}
          rows={rows}
          columns={columns}
          checkboxSelection
          disableColumnMenu
          pageSize={pageSize}
          onPageSizeChange={(newPageSize) => setPageSize(newPageSize)}
          rowsPerPageOptions={[10, 25, 50]}
          pagination
        />
        <div className="table-actions">
          <div 
          className="action-button"
          onClick= {handleRefreshData}
        >
          Refresh Data
        </div>
          <div 
          className={`action-button ${selectedSection === 'Edit Data' ? 'active' : ''}`}
          onClick={() => handleSectionClick('Edit Data')}
        >
          Edit
        </div>
        <div
        className={`action-button ${selectedSection === 'Delete Data' ? 'active' : ''}`}
        onClick={() => handleDelete('Delete Data')}
      >
        Delete
      </div>
        <div 
          className={`action-button ${selectedSection === ' ' ? 'active' : ''}`}
          onClick={() => handlePredict('')} 
        >
          Predict
        </div>
        </div>
      </div>
      <footer className="footer">
        <p>
          <span>Privacy Policy</span> | © 2023 ABC Corporation. All rights reserved.
        </p>
      </footer>

      {selectedSection === 'Delete Data' && (
        <div className="popup-window2">
          <h2>Delete Data</h2>
          <p>Are you sure you want to delete this data?</p>
          <button onClick={confirmDelete}>Delete</button>
          <button onClick={() => setSelectedSection('')}>Cancel</button>
        </div>
      )}

      {selectedSection === 'Edit Data' && (
  <div className="popup-window1">
    <h2>Edit Data</h2>
    <label>Order Currency:</label>
    <input type="text" id="orderCurrency" />

    <label>Company Code:</label>
    <input type="text" id="companyCode" />

    <label>Distribution Channel:</label>
    <input type="text" id="distributionChannel" />

    <button onClick={handleEditSubmit}>Edit</button>
    <button onClick={() => handleSectionClick('')}>Cancel</button>
  </div>
)}


      {selectedSection === 'Add Data' && (
        <div className="popup-window">
          <div className="popup-content">
            <div className="popup-header">Add Data</div>
            <div className="popup-fields">
              <button className="close-button" onClick={() => handleSectionClick('')}>Close</button>
            <div className="popup-field">
                <label htmlFor="customerOrderId">Customer Order ID:</label>
                <input type="text" id="customerOrderId" />
              </div>
              <div className="popup-field">
                <label htmlFor="salesOrg">Sales Org:</label>
                <input type="text" id="salesOrg" />
              </div>
              <div className="popup-field">
                <label htmlFor="distributionChannel">Distribution Channel:</label>
                <input type="text" id="distributionChannel" />
              </div>
              <div className="popup-field">
                <label htmlFor="customerNumber">Customer Number:</label>
                <input type="text" id="customerNumber" />
              </div>
              <div className="popup-field">
                <label htmlFor="companyCode">Company Code:</label>
                <input type="text" id="companyCode" />
              </div>
              <div className="popup-field">
                <label htmlFor="orderCurrency">Order Currency:</label>
                <input type="text" id="orderCurrency" />
              </div>
              <div className="popup-field">
                <label htmlFor="amountInUSD">Amount in USD:</label>
                <input type="text" id="amountInUSD" />
              </div>
              <div className="popup-field">
                <label htmlFor="orderCreationDate">Order Creation Date:</label>
                <input type="date" id="orderCreationDate" />
              </div>
            </div>
            <div className="popup-actions">
            <button onClick={handleSubmit}>Submit</button>
            </div>
          </div>
        </div>
      )}
      {selectedSection === 'Analytics View' && (
        <div className="popup-window">
          <div className="popup-content">
            <div className="popup-header">Analytics View</div>
            <div className="popup-fields">
            <button className="close-button" onClick={() => handleSectionClick('')}>Close</button>
              <div className="popup-field">
                <label htmlFor="distributionChannel">Distribution Channel:</label>
                <input type="text" id="distributionChannel" />
              </div>
              <div className="popup-field">
                <label htmlFor="customerNumber">Customer Number:</label>
                <input type="text" id="customerNumber" />
              </div>
            </div>
            <div className="popup-charts">
              <div className="popup-chart">
                {/* Bar graph */}
              </div>
              <div className="popup-chart">
                {/* Pie Chart */}
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Datagrid;

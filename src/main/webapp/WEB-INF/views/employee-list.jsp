<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    
<title>Employee List</title>
    
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
   
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        
        .pagination {
            margin-top: 10px;
            text-align: center;
        }

        .pagination a {
            display: inline-block;
            padding: 5px 10px;
            margin: 0 2px;
            border: 1px solid #ddd;
            color: #333;
            text-decoration: none;
            background-color: #f7f7f7;
            cursor: pointer;
        }

        .pagination.current {
            font-weight: bold;
            background-color: #4CAF50;
            color: white;
        }

        .pagination.disabled {
            color: #aaa;
            pointer-events: none;
            cursor: not-allowed;
        } 
        </style>
        
    
</head>
<body>
    <h1>Employee List</h1>
    <form class="filter-form" action="/employees" method="GET">
		
			<input type="text" name="search" placeholder="Search" id="search"
                value="${param.search}" autocomplete="off" list="suggestions" />
                
            <input type="datetime-local" name="fromDateTime" placeholder="From Date and Time" value="${param.fromDateTime}" />
    <input type="datetime-local" name="toDateTime" placeholder="To Date and Time" value="${param.toDateTime}" /> 
            <datalist id="suggestions"></datalist>
            
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function() {
  $('#search').keyup(function() {
    var search = $(this).val();
    
    $.ajax({
      url: '/suggestions',
      type: 'GET',
      data: { search: search },
      success: function(response) {
        // Clear previous suggestions
        $('#suggestions').empty();
        
        // Add new suggestions
        response.forEach(function(suggestion) {
          $('#suggestions').append('<option value="' + suggestion + '">');
        });
      }
    });
  });
});
</script>
            
			
		<button type="submit">Search</button>
	</form>

	<a href="/employees"><button>Clear</button></a>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Designation</th>
            <th>File</th>
            <th>DateandTime</th>
             <th>Actions</th>
        </tr>
        <c:forEach items="${employees}" var="employee">
            <tr>
                <td>${employee.id}</td>
                <td>${employee.name}</td>
                <td>${employee.designation}</td>
                <td>
                <a href="/downloadFile?id=${employee.id}"><i class="fa fa-download"></i></a>
                </td>
                <td>${employee.datetimeField}</td>
                <td>
                    <a href="/edit?id=${employee.id}">Edit</a>
                    <a href="/delete?id=${employee.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        
        
      
    </table>
 <!-- <form action="/addColumn" method="post">
 <button id="addColumnButton">Add Column</button>
    <script>
$(document).ready(function() {
  $('#addColumnButton').click(function() {
    // Prompt the user for the column name
    var columnName = prompt("Enter the column name:");
    
    if (columnName) {
      // Add the column header to the table
      $('table tr:first').append('<th>' + columnName + '</th>');
      
      // Add the corresponding cell in each row
      $('table tr').each(function() {
        $(this).append('<td></td>');
      });
    }
  });
});
</script> 
</form> -->

<form action="/addColumn" method="post">
    Column Name: <input type="text" name="columnName" autocomplete="off"><br>
    Data Type: <input type="text" name="dataType" autocomplete="off"><br>
    <input type="submit" value="Add Column">
</form>
 


  <!-- Pagination -->
  
  
    <c:if test="${totalPages > 1}">
        <div class="pagination">
            <c:choose>
                <c:when test="${currentPage > 1}">
                    <a href="/employee-list?page=${currentPage - 1}">Previous</a>
                </c:when>
                <c:otherwise>
                    <span class="disabled">Previous</span>
                </c:otherwise>
            </c:choose>

            <c:forEach begin="1" end="${totalPages}" varStatus="status">
                <c:choose>
                    <c:when test="${status.index + 1 == currentPage}">
                        <span class="current">${status.index + 1}</span>
                    </c:when>
                    <c:otherwise>
                        <a href="/employee-list?page=${status.index + 1}">${status.index + 1}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:choose>
                <c:when test="${currentPage < totalPages}">
                    <a href="/employee-list?page=${currentPage + 1}">Next</a>
                </c:when>
                <c:otherwise>
                    <span class="disabled">Next</span>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>

   
  
</body>
</html>
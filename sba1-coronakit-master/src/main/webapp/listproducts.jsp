<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<%-- Required View Template --%>

<h3>List of Products</h3>
	
	<c:choose>
		<c:when test="${products==null || products.isEmpty() }">
			<p>No products Found</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>ID</th>
					<th>Product Name</th>
					<th>Cost</th>
					<th>Product Description</th>
				</tr>
				<c:forEach items ="${products}" var="product">
					<tr>
					<td>${product.id }</td>
					<td>${product.productname }</td>
					<td>${product.cost }</td>
					<td>${product.productdescription }</td>
					<td>
						<a href="deleteproduct?id=${product.id}">DELETE</a> <span>|</span>
						<a href="editproduct?id=${product.id}">EDIT</a> <span>|</span>
					</td>
				</tr>				
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	
	<h4>
	<a href="newproduct.jsp">ADD NEW PRODUCT</a> <span>|</span>
	</h4>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Add New Product(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<%-- Required View Template --%>

<h2>New Product Details</h2>
	<form action="admin?action=newproduct" method="post">
		<div>
			<div><label for="id">Enter Id</label> </div>
			<div><input type="text" id="id" name="id"> </div>
		</div>
		<div>
			<div><label for="productName">Enter product name</label> </div>
			<div><input type="text" id="productName" name="productName"> </div>
		</div>
		<div>
			<div><label for="cost">Enter cost</label> </div>
			<div><input type="text" id="cost" name="cost"> </div>
		</div>
		<div>
			<div><label for="productDescription">Enter product description</label> </div>
			<div><input type="text" id="productDescription" name="productDescription"> </div>
		</div>
		<div>
			<div><input type="submit" value="ADD NEW PRODUCT"> </div>
		</div>
	</form>
</div>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>
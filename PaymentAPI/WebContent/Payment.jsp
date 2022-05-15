<%@page import="model.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
<style type="text/css">



}

*{
margin:0 ;
padding: 0;
box-sizing: border-box;
}
.body{
background-color: white;
font-family: sans-serif;
}
.table-container{
padding: 0 10%;
margin: 40px auto 0;
}
.heading{
font-size: 40px;
text-align: center;
color: #f1f1f1;
margin-bottom: 40px;
}
.table{
width: 100%;
border-collapse: collapse;
margin-top: 50px;
}
.table thead{
background-color: black;
}
.table thead tr th{
font-size: 14px;
font-weight: 600;
letter-spacing: 0.35px;
color: #ffff;
opacity: 1;
padding: 12px;
vertical-align: top;
border: 1px solid #dee2e685;



}
.table tbody tr td{
font-size: 14px;
font-weight: normal;
letter-spacing: 0.35px;

background-color: silver;
padding: 8px;
text-align: center;
border:1px solid #dee2e685;
}



#btn-record{
margin-left: 85%;
}
</style>


<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payment.js"></script>

</head>
<body>


<div class="container">
<br>
<center><h2>Payment Management</h2></center>
<div class="row">
	<div class="col-lg-12">
	<div class="card m-b-32">
	<div class="card-body">
	<form id="formPayment" name="formPayment" method="post" action="Payment.jsp">
	
		
			
			<label>Customer Name</label> <input id="CustomerName"
			name="CustomerName" type="text"
		    class="form-control form-control-sm">
			<br>
			
		    <label>Address</label> <input id="Address"
			name="Address" type="number"
		    class="form-control form-control-sm">
			<br>
			
			<label>Account No</label> <input id="AccountNo"
			name="AccountNo" type="text"
		    class="form-control form-control-sm">
			<br>
			
			<label>BillNo</label> <input id="BillNo"
			name="BillNo" type="number"
		    class="form-control form-control-sm">
			<br>
			
			<label>Amount</label> <input id="Amount"
			name="Amount" type="number"
		    class="form-control form-control-sm">
			<br>

	
			
			
			<input id="btnSave" name="btnSave" type="button" value="Save"
			class="btn btn-primary"> <input type="hidden"
				id="PaymentID" name="PaymentID" value="">		
	</form>
	<br>
						<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br> <br>
										<div id="divUserGrid">
						<%
						Payment paymentObj = new Payment();
						out.print(paymentObj.readPayment());
						%>
					</div>
	</div>
	</div>
	</div>
</div>
<br>
</div>

</body>
</html>
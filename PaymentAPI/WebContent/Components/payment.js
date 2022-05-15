$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});


//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
 // Form validation-------------------
var status = validatePaymentform();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }

 var type = ($("#PaymentID").val() == "") ? "POST" : "PUT";


$.ajax(
		{
		 url : "PaymentAPI",
		 type : type,
		data: $("#formPayment").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onPaymentSaveComplete(response.responseText, status);
		 }
		});

});

function onPaymentSaveComplete(response, status)
{
if (status == "success")
 {
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
		$("#alertSuccess").text("Successfully saved.");
		$("#alertSuccess").show();
		
		$("#divUserGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
	}
 	} else if (status == "error")
 	{
 		$("#alertError").text("Error while saving.");
 		$("#alertError").show();
 	} else
 	{
 		$("#alertError").text("Unknown error while saving..");
 		$("#alertError").show();
 	}
		$("#PaymentID").val("");
		$("#formPayment")[0].reset();
}


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#PaymentID").val($(this).closest("tr").find('#hididUpdate').val()); 
 $("#CustomerName").val($(this).closest("tr").find('td:eq(0)').text());
 $("#Address").val($(this).closest("tr").find('td:eq(1)').text());
 $("#AccountNo").val($(this).closest("tr").find('td:eq(2)').text());
 $("#BillNo").val($(this).closest("tr").find('td:eq(3)').text());
 $("#Amount").val($(this).closest("tr").find('td:eq(4)').text());
 
});

$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "PaymentAPI",
		 type : "DELETE",
		 data : "PaymentID=" + $(this).data("PaymentID"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 ondeletePaymentComplete(response.responseText, status);
		 }
		 });
		});

function ondeletePaymentComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divUserGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}

//CLIENTMODEL=========================================================================
function validatePaymentform()
{


//acc_no
if ($("#CustomerName").val().trim() == "")
{
return "Insert Account Number.";
}

//card_num
if ($("#Address").val().trim() == "")
{
return "Insert Card Number.";
}

//cus_nic
if ($("#AccountNo").val().trim() == "")
{
return "Insert Expire Date.";
}

//BillNo
if ($("#BillNo").val().trim() == "")
{
return "Insert BillNo.";
}

//Amount
if ($("#Amount").val().trim() == "")
{
return "Insert Telephone Number.";
}




return true;
}
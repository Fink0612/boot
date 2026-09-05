<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style type="text/css">
body {
	background-color: #f5f5f5;
	color: #006e6e;
	font-size: 32px;
	text-align: center;
}
.c_ctrlMsg {
    position: absolute;
    top: 50%;
    left: 50%;
    margin-right: -50%;
    transform: translate(-50%, -50%);
}
</style>
<script type="text/javascript" src="../js/funcoesCtrl.js"></script>
</head>
<body>
<div class="c_ctrlMsg" id="i_ctrlMsg">
</div>
<form id="ctrlForm" action="#" method="post">
<%@ include file="camposControle.jsp" %>
</form>
<script type="text/javascript">
setTarget();
</script>
</body>
</html>
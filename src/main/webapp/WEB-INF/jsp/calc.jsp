<%--@elvariable id="message" type="java.lang.String"--%>
<%--@elvariable id="time" type="java.util.Date"--%>
<!DOCTYPE html>

<html lang="en">
<title>Hello world</title>
<link rel="stylesheet" type="text/css"
      href="webjars/bootstrap/3.3.5/css/bootstrap.min.css"/>
<body>

<form action = "/ultra-calculator" method="post">
    <label for="value1">Value 1:</label><br>
    <input type="text" id="value1" name="value1"><br>
    <label for="value2">Value 2:</label><br>
    <input type="text" id="value2" name="value2">
    <input type="submit" value="Calcular" >
</form>

<kbd>${values}<span class="glyphicon glyphicon-console"></span>${message}</kbd>
<script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
</body>

</html>
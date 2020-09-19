<%--@elvariable id="message" type="java.lang.String"--%>
<%--@elvariable id="time" type="java.util.Date"--%>
<!DOCTYPE html>

<html lang="en">
<title>Hello world</title>
<link rel="stylesheet" type="text/css"
      href="webjars/bootstrap/3.3.5/css/bootstrap.min.css"/>
<body>
<kbd>${time}  <span class="glyphicon glyphicon-console"></span>  ${message}</kbd><br/>
<kbd>Today's lucky colour is ${luckyColor}</kbd><br/>
<kbd>${joke_const}</kbd><br/>
<kbd>+</kbd><kbd>${joke_plus}</kbd><br/>
<kbd>-</kbd><kbd>${joke_mins}</kbd><br/>
<script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
</body>

</html>

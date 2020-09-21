<%--@elvariable id="message" type="java.lang.String"--%>
<%--@elvariable id="time" type="java.util.Date"--%>
<!DOCTYPE html>

<html lang="en">
  <title>Roll the dice</title>
  <link rel="stylesheet" type="text/css"
        href="webjars/bootstrap/3.3.5/css/bootstrap.min.css"/>
  <body>
    <h2>${message} &#8594; ${dice}</h2>
    <kbd>${time}  <span class="glyphicon glyphicon-console"></span>  ${message}</kbd><br/>
    <p>Your IP is <kbd>${ip}</kbd></p>
    <p>Your Host is <kbd>${host}</kbd></p>
    <p>The JAVA version used is <kbd>${java}</kbd></p>
    <br>
    <kbd>Today's lucky colour is ${luckyColor}</kbd><br/><br/>
    <kbd>${joke_const}</kbd><br/>
    <kbd>+</kbd><kbd>${joke_plus}</kbd><br/>
    <kbd>-</kbd><kbd>${joke_minus}</kbd><br/><br/>
    <kbd>${extra_message}</kbd>
    <a href ="https://www.youtube.com/watch?v=dQw4w9WgXcQ">${link}</a>
    <p><img src="images/Pilar.jpg"/></p>
    <kbd><a href="/password" style="color: white">Access to the password generator</a></kbd>
    <script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"/>
  </body>
</html>

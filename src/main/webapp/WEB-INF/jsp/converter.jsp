<%--@elvariable id="message" type="java.lang.String"--%>
<%--@elvariable id="time" type="java.util.Date"--%>
<!DOCTYPE html>

<html lang="en">
<title>Base converter</title>
<body>
<h1>Base converter</h1>
<h2>Convert a number between differents number systems.</h2>
    <form action="/base-converter" method="post">
        <label>Number:</label>
        <input type="text" name="original_number" >
        <label>in base </label>
        <select name="original_base">
            <option value ="hex">Hex</option>
            <option value ="dec">Dec</option>
            <option value ="oct">Oct</option>
            <option value ="bin">Bin</option>
        </select>
        <label> to base </label>

        <select name="target_base">
            <option value ="hex">Hex</option>
            <option value ="dec">Dec</option>
            <option value ="oct">Oct</option>
            <option value ="bin">Bin</option>
        </select>
  <input type="submit" value="Convert">
</form>
    <kbd>${values}</span>${error_message}</kbd>
    <script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
</body>

</html>
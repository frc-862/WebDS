<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>WebDS: Team Number Settings</title>
    </head>
    <body>
        <h2>Configured Team Number: ${teamNum}</h2>
        <form method="GET" action="run">
            <h4>Start WebDS for Team ${teamNum}</h4>
            <input type="submit" id="launch-button" value="Launch Web Driver Station" />
        </form>
    </body>
</html>

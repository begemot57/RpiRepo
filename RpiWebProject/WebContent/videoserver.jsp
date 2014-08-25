<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Video Server Control Page</title>
</head>
<body>
<div align="center">
	<form action="${pageContext.request.contextPath}/VideoServerServlet" method="get">
		<table>		    
			<tr>
				<td align="center">  
					<input type="submit" name="start" value="START" style="height:150px; width:150px" />    
				</td>
				<td>
				</td>
				<td align="center">  
				    <input type="submit" name="stop" value="STOP" style="height:150px; width:150px" />
				</td>   
			</tr>
			<tr>
				<td>
				</td>		
				<td align="center">
					<input type="button" value="View Stream" onclick="window.open('http://begemot57.ddns.net:8090/stream_simple.html')">
				</td>
				<td>
				</td>			    
			</tr>	    
		</table>    
	</form>
</div>
</body>
</html>
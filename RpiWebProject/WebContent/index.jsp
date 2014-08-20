<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP with the current date</title>
</head>
<body>
<div align="center">
	<form action="${pageContext.request.contextPath}/Servlet" method="get">
		<table>
			<tr>
				<td>
				</td>
				<td align="center">  
				    <input type="submit" name="forward" value="FORWARD" style="height:150px; width:150px" />
				</td>
				<td>
				</td>    
			</tr>			    
			<tr>
				<td align="center">  
					<input type="submit" name="left" value="LEFT" style="height:150px; width:150px" />    
				</td>
				<td align="center">  
					<input type="submit" name="stop" value="STOP" style="height:150px; width:150px" />    
				</td>  
				<td align="center">  
				    <input type="submit" name="right" value="RIGHT" style="height:150px; width:150px" />
				</td>   
			</tr>
			<tr>
				<td>
				</td>		
				<td align="center">
					<input type="submit" name="back" value="BACK" style="height:150px; width:150px" />  
				</td>
				<td>
				</td>			    
			</tr>	    
		</table>    
	</form>
</div>
</body>
</html>
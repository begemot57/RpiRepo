<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Remote control</title>
</head>
<body>
<div align="center">
	<form action="${pageContext.request.contextPath}/CarServlet" method="get">
		<table>
			<tr>
				<td>
				</td>
				<td align="center">  
				    <input type="submit" name="forward" value="FORWARD" style="height:300px; width:300px" />
				</td>
				<td>
				</td>    
			</tr>			    
			<tr>
				<td align="center">  
					<input type="submit" name="left" value="LEFT" style="height:300px; width:300px" />    
				</td>
				<td align="center">  
					<input type="submit" name="stop" value="STOP" style="height:300px; width:300px" />    
				</td>  
				<td align="center">  
				    <input type="submit" name="right" value="RIGHT" style="height:300px; width:300px" />
				</td>   
			</tr>
			<tr>
				<td>
				</td>		
				<td align="center">
					<input type="submit" name="back" value="BACK" style="height:300px; width:300px" />  
				</td>
				<td>
				</td>			    
			</tr>
		</table>
		<br/>
		<br/>
		<br/>
		<br/>
		<table>
			<tr>
				<td align="center">
					<input type="submit" name="speed_minus" value="SPEED-" style="height:300px; width:300px" />
				</td>		
				<td>
				</td>
				<td align="center">
					 <input type="submit" name="speed_plus" value="SPEED+" style="height:300px; width:300px" />  
				</td>			    
			</tr>
			<tr>
				<td>
				</td>		
				<td align="center">
					<input type="submit" name="off" value="OFF" style="height:300px; width:300px" />  
				</td>
				<td>
				</td>			    
			</tr>
		</table>    
	</form>
</div>
</body>
</html>
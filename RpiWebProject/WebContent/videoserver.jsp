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
				<td valign="top">  
					<input type="submit" name="start" value="START" style="height:150px; width:150px; background-color: #00FF00;" />    
				</td>
				<td align="center">
					<table>
						<tr>
							<td align="center">
								<input type="button" value="View Stream" onclick="window.open('http://begemot57.ddns.net:8090/stream_simple.html')" style="height:60px; width:150px; background-color: #FFFF00;" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="submit" name="checkstate" value="Check Status" style="height:30px; width:150px;" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="text" value="${videoStreamAppState}" style="height:30px; width:150px; text-align:center" />
							</td>
						</tr>
					</table>
				</td>
				<td align="center">  
				    <input type="submit" name="stop" value="STOP" style="height:150px; width:150px; background-color: #FF0000;" />
				</td>   
			</tr>
		</table>    
	</form>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page to control various processes on RPI</title>
</head>
<body  onload="onloadFct()">
<div align="center">
	<h1 align="center">Video Stream Controller</h1>
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
<div align="center">
	<h1 align="center">House Monitoring Controller</h1>
	<form action="${pageContext.request.contextPath}/HouseMonitoringServlet" method="get">
		<table>		    
			<tr>
				<td align="center">  
					<input type="submit" name="start" value="START" style="height:150px; width:150px; background-color: #00FF00;" />    
				</td>
				<td align="center">
					<table>
						<tr>
							<td align="center">
								<input type="submit" name="checkstate" value="Check Status" style="height:30px; width:150px;" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="text" value="${houseMonitorAppState}" style="height:30px; width:150px; text-align:center" />
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
<div align="center">
	<h1 align="center">DoneDeal.ie Monitoring Controller</h1>
	<form action="${pageContext.request.contextPath}/DoneDealMonitoringServlet" method="get">
		<table>		    
			<tr>
				<td align="center">  
					<input type="submit" name="start" value="START" style="height:150px; width:150px; background-color: #00FF00;" />    
				</td>
				<td align="center">
					<table>
						<tr>
							<td align="center">
								<input type="submit" name="checkstate" value="Check Status" style="height:30px; width:150px;" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="text" value="${doneDealMonitorAppState}" style="height:30px; width:150px; text-align:center" />
							</td>
						</tr>
					</table>
				</td>
				<td align="center">  
				    <input type="submit" name="stop" value="STOP" style="height:150px; width:150px; background-color: #FF0000;" />
				</td>   
			</tr>
			<tr>
				<td colspan="3" align="center">
					<textarea NAME="ddurl" style="height:30px; width:450px; text-align:center"></textarea>
				</td>
			</tr>    
		</table>    
	</form>
</div>

<script>
	function onloadFct() {
		console.log("hi there");
	}
</script>

</body>
</html>
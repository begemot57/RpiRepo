<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Accelerometer Javascript Test</title>
<meta name="viewport" content="width=device-width,user-scalable=yes" />
<style>
body {
	font-family: helvetica, arial, sans serif;
}
</style>
</head>

<body>
<div id="content">
	<ul>
		<li>acceleration x: <span id="accelerationX"></span>g</li>
		<li>acceleration y: <span id="accelerationY"></span>g</li>
	</ul>
</div>

<div align="center">
	<form action="${pageContext.request.contextPath}/CarServlet" method="post">
		<table>
			<tr>
				<td>
				</td>
				<td align="center">  
				    <input type="submit" name="forward" value="FORWARD" style="height:150px; width:150px"  id="forward_button"/>
				</td>
				<td>
				</td>    
			</tr>			    
			<tr>
				<td align="center">  
					<input type="submit" name="left" value="LEFT" style="height:150px; width:150px"  id="left_button"/>    
				</td>
				<td align="center">  
					<input type="submit" name="stop" value="STOP" style="height:150px; width:150px"  id="stop_button"/>    
				</td>  
				<td align="center">  
				    <input type="submit" name="right" value="RIGHT" style="height:150px; width:150px"  id="right_button"/>
				</td>   
			</tr>
			<tr>
				<td>
				</td>		
				<td align="center">
					<input type="submit" name="back" value="BACK" style="height:150px; width:150px"  id="back_button"/>  
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
				<td>
				</td>		
				<td align="center">
					<input type="submit" name="off" value="OFF" style="height:300px; width:300px" id="off_button"/>  
				</td>
				<td>
				</td>			    
			</tr>
		</table>    
	</form>
</div>

<script type="text/javascript">
var x, y;
var forward = false;
var back = false;
var right = false;
var left = false;
var stop = false;
//need to stop time to avoid calling "stop" each time we pass "stop" position
//only staying in "stop" positoin for 0.2 sec will "stop" the car
var time;
if (window.DeviceMotionEvent != undefined) {
	window.ondevicemotion = function(e) {
		x = e.accelerationIncludingGravity.x;
		y = e.accelerationIncludingGravity.y;
		document.getElementById("accelerationX").innerHTML = x;
		document.getElementById("accelerationY").innerHTML = y;
		if(forward == false && y > 5){
			forward = true;
			console.log("go forward");
			back = false;
			right = false;
			left = false;
			stop = false;
			time = null;
			document.getElementById("forward_button").click();
		}
		else if(back == false && y < -5){
			back = true;
			console.log("go back");
			forward = false;
			right = false;
			left = false;
			stop = false;
			time = null;
			document.getElementById("back_button").click();
		}
		//forward and back positions dominate over left and right
		else if(right == false && x > 5 && y <= 5 && y >= -5){
			right = true;
			console.log("go right");
			forward = false;
			back = false;
			left = false;
			stop = false;
			time = null;
			document.getElementById("right_button").click();
		}
		else if(left == false && x < -5 && y <= 5 && y >= -5){
			left = true;
			console.log("go left");
			forward = false;
			back = false;
			right = false;
			stop = false;
			time = null;
			document.getElementById("left_button").click();
		}else if(y >= -5 && y <= 5 && x >= -5 && x <= 5 && stop == false){
			if(time == null){
				time = Date.now();
				//console.log("time: "+time);
			}else{
				currTime = Date.now();
				interval = currTime - time;
				//console.log("interval: "+interval);
				if(interval > 200){
					stop = true;
					console.log("stop");
					forward = false;
					back = false;
					right = false;
					left = false;
					time = null;
					document.getElementById("stop_button").click();
				}
			}
		}
	}
} 

</script>

</body>
</html>

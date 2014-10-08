<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css">
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js"></script>
<script>
$(document).on("pagecreate",function(event){
  $(window).on("orientationchange",function(event){
    alert("Orientation changed to: " + event.orientation);
  });                     
});
</script>
</head>
<body>

<div data-role="page">
  <div data-role="header">
    <h1>The orientationchange Event</h1>
  </div>

  <div data-role="main" class="ui-content">
    <p>Try to rotate your device!</p>
    <p><b>Note:</b> You must use a mobile device, or a mobile emulator to see the effect of this event.</p>
  </div>

  <div data-role="footer">
    <h1>Footer Text</h1>
  </div>
</div> 

</body>
</html>
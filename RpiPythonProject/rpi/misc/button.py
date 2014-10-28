#import modules and libraries
import RPi.GPIO as GPIO

#set up GPIO using Board numbering
GPIO.setmode(GPIO.BOARD)

#set up the pin as an input
GPIO.setup(11, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)

try:
	whild True:
		#read voltage level translated to logic
		if(GPIO.input(11) == 1)
			#Do something
		else:
			#Do something else
except KeybaordInterrupt:
	#clean up the ports
	GPIO.cleanup()
#import modules and libraries
import RPi.GPIO as GPIO
import time

#set up GPIO using Board numbering
GPIO.setmode(GPIO.BOARD)

#set up the pin that the LED connects to on the RPi as an output
led = 15
GPIO.setup(led, GPIO.OUT)

#turn the LED on
GPIO.output(led, 1)
#wait 5 seconds
time.sleep(5)
#turn the LED off
GPIO.output(led, 0)
#clean up the ports
GPIO.cleanup()
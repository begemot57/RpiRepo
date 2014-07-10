import RPi.GPIO as gpio
import time
gpio.setmode(gpio.BOARD)
gpio.setup(7, gpio.OUT)
gpio.setup(11, gpio.OUT)
gpio.setup(13, gpio.OUT)
gpio.setup(15, gpio.OUT)
try:
	while (True):
		request=raw_input("direction: ")
		if(request == "e"):
			print("go forward")
			gpio.output(7, True)
			gpio.output(11, False)
			gpio.output(13, False)
			gpio.output(15, True)
		elif(request == "d"):
			print("go back")
			gpio.output(7, False)
			gpio.output(11, True)
			gpio.output(13, True)
			gpio.output(15, False)
		elif(request == "s"):
			print("go left")
			gpio.output(7, True)
			gpio.output(11, False)
			gpio.output(13, False)
			gpio.output(15, False)
		elif(request == "f"):
			print("go right")
			gpio.output(7, False)
			gpio.output(11, False)
			gpio.output(13, False)
			gpio.output(15, True)
		else:
			print("stop")
			gpio.output(7, False)
			gpio.output(11, False)
			gpio.output(13, False)
			gpio.output(15, False)
except KeyboardInterrupt:
	gpio.cleanup()

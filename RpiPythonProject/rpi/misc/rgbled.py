import RPi.GPIO as gpio
import time
gpio.setmode(gpio.BOARD)
r = 15
g = 13
b = 12
gpio.setup(r, gpio.OUT)
gpio.output(r, 1)
gpio.setup(g, gpio.OUT)
gpio.output(g, 1)
gpio.setup(b, gpio.OUT)
gpio.output(b, 1)
try:
        while (True):
		request = raw_input("RGB-->")
		if(len(request)==3):
			gpio.output(r, int(request[0]))
			gpio.output(g, int(request[1]))
			gpio.output(b, int(request[2]))
except KeyboardInterrupt:
        gpio.cleanup()

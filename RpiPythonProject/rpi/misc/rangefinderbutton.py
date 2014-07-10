import RPi.GPIO as gpio
import time
gpio.setmode(gpio.BOARD)
trig = 11
echo = 16
bin = 22
gpio.setup(trig, gpio.OUT)
gpio.output(trig, 0)
gpio.setup(echo, gpio.IN)
gpio.setup(bin, gpio.IN, pull_up_down=gpio.PUD_DOWN)
try:
        while (True):
		if(gpio.input(bin)==1):
			time.sleep(0.1)
			print "Starting Measurement..."
			gpio.output(trig, 1)
			time.sleep(0.00001)
			gpio.output(trig, 0)
			while gpio.input(echo) == 0:
				pass
			start = time.time()
			while gpio.input(echo) == 1:
				pass
			stop = time.time()
			print (stop - start)*17000
except KeyboardInterrupt:
        gpio.cleanup()

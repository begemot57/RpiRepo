import RPi.GPIO as gpio
import time
gpio.setmode(gpio.BOARD)
trig = 11
echo = 16
bin = 22
r = 15
g = 13
b = 12
gpio.setup(trig, gpio.OUT)
gpio.output(trig, 0)
gpio.setup(echo, gpio.IN)
gpio.setup(bin, gpio.IN, pull_up_down=gpio.PUD_DOWN)
gpio.setup(r, gpio.OUT)
gpio.output(r, 1)
gpio.setup(g, gpio.OUT)
gpio.output(g, 1)
gpio.setup(b, gpio.OUT)
gpio.output(b, 1)
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
			dist = (stop - start)*17000 
			print dist
			if(dist<5):
				gpio.output(r, 1)
                        	gpio.output(g, 0)
                        	gpio.output(b, 0)
			elif(dist<20):
                                gpio.output(r, 0)
                                gpio.output(g, 1)
                                gpio.output(b, 0)
                        else:
                                gpio.output(r, 0)
                                gpio.output(g, 0)
                                gpio.output(b, 1)
		else:
			gpio.output(r, 0)
                        gpio.output(g, 0)
                        gpio.output(b, 0)			
except KeyboardInterrupt:
        gpio.cleanup()

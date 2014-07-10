import RPi.GPIO as gpio
import threading
import time
gpio.setmode(gpio.BOARD)
rp1 = 19
rp2 = 21
lp1 = 23
lp2 = 24
gpio.setup(rp1, gpio.OUT)
gpio.setup(rp2, gpio.OUT)
gpio.setup(lp1, gpio.OUT)
gpio.setup(lp2, gpio.OUT)
r1 = gpio.PWM(rp1, 50)
r2 = gpio.PWM(rp2, 50)
l1 = gpio.PWM(lp1, 50)
l2 = gpio.PWM(lp2, 50)
r1.start(0)
r2.start(0)
l1.start(0)
l2.start(0)
trig = 11
echo = 16
r = 15
g = 13
b = 12
gpio.setup(trig, gpio.OUT)
gpio.output(trig, 0)
gpio.setup(echo, gpio.IN)
gpio.setup(r, gpio.OUT)
gpio.output(r, 0)
gpio.setup(g, gpio.OUT)
gpio.output(g, 0)
gpio.setup(b, gpio.OUT)
gpio.output(b, 0)

reddist = 30
greendist = 60

def movefun(v1, v2, v3, v4):
	r1.ChangeDutyCycle(v1)
        r2.ChangeDutyCycle(v2)
        l1.ChangeDutyCycle(v3)
        l2.ChangeDutyCycle(v4)

def rgbfun(v1, v2, v3):
	gpio.output(r, v1)
        gpio.output(g, v2)
        gpio.output(b, v3)

class DistThread(threading.Thread):
	def run(self):
		while (True):
			time.sleep(0.1)
        		#print "Starting Measurement..."
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
       			#print dist
        		if(dist<reddist):
				rgbfun(1, 0, 0)
				time.sleep(0.1)
				movefun(0,0,0,0)
				time.sleep(0.5)
				movefun(0,100,88,0)
				time.sleep(1)
				movefun(0,0,0,0)
        		elif(dist<greendist):
				rgbfun(0, 1, 0)
        		else:
				rgbfun(0, 0, 1)
				movefun(80, 0, 0, 100)

try:
	distThread = DistThread()
	distThread.start()
	while (True):
		request=raw_input("direction: ")
		if(request == "e"):
			print("go forward")
			movefun(80, 0, 0, 100)
		elif(request == "d"):
			print("go back")
                        movefun(0, 100, 88, 0)
		elif(request == "s"):
			print("go left")
                        movefun(100, 0, 0, 50)
		elif(request == "f"):
			print("go right")
                        movefun(30, 0, 0, 100)
		else:
			print("stop")
                        movefun(0, 0, 0, 0)
except KeyboardInterrupt:
	distThread._Thread__stop()
	gpio.cleanup()

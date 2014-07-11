import RPi.GPIO as gpio
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

def fun(v1, v2, v3, v4):
	r1.ChangeDutyCycle(v1)
        r2.ChangeDutyCycle(v2)
        l1.ChangeDutyCycle(v3)
        l2.ChangeDutyCycle(v4)

try:
	while (True):
		request=raw_input("direction: ")
		if(request == "e"):
			print("go forward")
			fun(80, 0, 0, 100)
		elif(request == "d"):
			print("go back")
                        fun(0, 100, 88, 0)
		elif(request == "s"):
			print("go left")
                        fun(100, 0, 0, 50)
		elif(request == "f"):
			print("go right")
                        fun(30, 0, 0, 100)
		else:
			print("stop")
                        fun(0, 0, 0, 0)
except KeyboardInterrupt:
	gpio.cleanup()

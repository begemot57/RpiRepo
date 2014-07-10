import RPi.GPIO as gpio
import time
gpio.setmode(gpio.BOARD)
rp1 = 7
rp2 = 11
lp1 = 13
lp2 = 15
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
try:
	while (True):
		request=raw_input("direction: ")
		if(request == "e"):
			print("go forward")
			r1.ChangeDutyCycle(80)
			r2.ChangeDutyCycle(0)
			l1.ChangeDutyCycle(0)
			l2.ChangeDutyCycle(100)
		elif(request == "d"):
			print("go back")
                        r1.ChangeDutyCycle(0)
                        r2.ChangeDutyCycle(100)
                        l1.ChangeDutyCycle(88)
                        l2.ChangeDutyCycle(0)
		elif(request == "s"):
			print("go left")
                        r1.ChangeDutyCycle(100)
                        r2.ChangeDutyCycle(0)
                        l1.ChangeDutyCycle(0)
                        l2.ChangeDutyCycle(50)
		elif(request == "f"):
			print("go right")
                        r1.ChangeDutyCycle(30)
                        r2.ChangeDutyCycle(0)
                        l1.ChangeDutyCycle(0)
                        l2.ChangeDutyCycle(100)
		else:
			print("stop")
                        r1.ChangeDutyCycle(0)
                        r2.ChangeDutyCycle(0)
                        l1.ChangeDutyCycle(0)
                        l2.ChangeDutyCycle(0)
except KeyboardInterrupt:
	gpio.cleanup()

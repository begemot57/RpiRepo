import RPi.GPIO as gpio
import time
gpio.setmode(gpio.BOARD)
s = 7
gpio.setup(s, gpio.OUT)
p = gpio.PWM(7,50)
p.start(7.5)
try:
        while (True):
		p.ChangeDutyCycle(7.5)
		time.sleep(1)
		p.ChangeDutyCycle(12.5)
		time.sleep(1)
		p.ChangeDutyCycle(2.5)
                time.sleep(1)
except KeyboardInterrupt:
	p.stop()
        gpio.cleanup()

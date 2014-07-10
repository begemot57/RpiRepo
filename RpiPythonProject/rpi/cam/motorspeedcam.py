import RPi.GPIO as gpio
import threading
import time
import sys

from PIL import Image
from numpy import array

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
speedvar = 1
currdir = ""

red_lb = 100
red_ub = 255
green_lb = 0
green_ub = 50
blue_lb = 0
blue_ub = 50
item_size_threashold = 20
fine_step_size_threashold = 10

def movefun(v1, v2, v3, v4):
	r1.ChangeDutyCycle(speedvar*v1)
        r2.ChangeDutyCycle(v2)
        l1.ChangeDutyCycle(v3)
        l2.ChangeDutyCycle(speedvar*v4)

def stopmovefun():
        r1.stop()
        r2.stop()
        l1.stop()
        l2.stop()

def rgbfun(v1, v2, v3):
	gpio.output(r, v1)
	gpio.output(g, v2)
	gpio.output(b, v3)
	
def check_colors(x, y, arr, width, height):
    if(x < 0 or y < 0 or x > width or y > height):
        return False
    r = arr[y][x][0]
    g = arr[y][x][1]
    b = arr[y][x][2]
    check = red_lb <= r <= red_ub
#     check = red_lb <= r <= red_ub and green_lb <= g <= green_ub and blue_lb <= b <= blue_ub
    if check:
        print (x, y, r, g, b)
#     print (y, x, r, g, b)
    return check
   
def find_ball(y, x, arr, width, height, fine_step_size):
    counter_slash = 0
    counter_back_slash = 0   
    for i in range(1, fine_step_size_threashold):  
        if(check_colors(x + fine_step_size*i, y - fine_step_size*i, arr, width, height)):
            counter_slash += 1
        if(check_colors(x - fine_step_size*i, y + fine_step_size*i, arr, width, height)):
            counter_slash += 1
        if(check_colors(x + fine_step_size*i, y + fine_step_size*i, arr, width, height)):
            counter_back_slash += 1
        if(check_colors(x - fine_step_size*i, y - fine_step_size*i, arr, width, height)):
            counter_back_slash += 1
    print counter_slash, counter_back_slash
    return counter_slash >=fine_step_size_threashold or counter_back_slash >= fine_step_size_threashold			          	

class FindBallThread(threading.Thread):
	def run(self):
		while (True):
			time.sleep(2)
			img = Image.open("../../images/balls/red_ball_1.jpg")
			
			width, height = img.size
			item_size = min(width, height)/item_size_threashold
			fine_step_size = item_size/fine_step_size_threashold
			arr = array(img)
			
			print("width:", width)
			print("height:", height)
			print("item_size:", item_size)
			
			ball_found = False            
			for y in range(0, height, item_size):
			    for x in range(0, width, item_size):        
			        if check_colors(x, y, arr, width, height):
			            print "found candidate pixel - starting fine search"
			            if find_ball(x, y, arr, width, height, fine_step_size):
			                print "found the ball"
			                ball_found = True
			                break
			    if ball_found:
			        break
			       

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

try:
	distThread = DistThread()
	distThread.start()
	findBallThread = FindBallThread()
	findBallThread.start()
	while (True):
		params=raw_input("direction: ").split()
		if(len(params)==0):
			request = ""
		else:
			request=params[0]
		if(request != "speed"):
			currdir = request
		if(len(params)>1):
			try:
				speedvar=float(params[1])
			except ValueError:
            			pass
		if(currdir == "e"):
			print("go forward")
			movefun(90, 0, 0, 100)
		elif(currdir == "d"):
			print("go back")
                        movefun(0, 100, 88, 0)
		elif(currdir == "s"):
			print("go left")
                        movefun(100, 0, 0, 50)
		elif(currdir == "f"):
			print("go right")
                        movefun(30, 0, 0, 100)
		elif(currdir == "off"):
			stopmovefun()
        		distThread._Thread__stop()
        		findBallThread._Thread__stop()
        		gpio.cleanup()
			sys.exit()
		else:
			print("stop")
                        movefun(0, 0, 0, 0)
except KeyboardInterrupt:
	stopmovefun()
	distThread._Thread__stop()
	findBallThread._Thread__stop()
	gpio.cleanup()


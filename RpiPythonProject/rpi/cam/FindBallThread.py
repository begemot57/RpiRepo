'''
Created on Jul 10, 2014

@author: Leo
'''
import threading
import time
from PIL import Image
from numpy import array

findBallThread = None

class FindBallThread(threading.Thread):

    def run(self):
        self.red_lb = 100
        self.red_ub = 255
        self.green_lb = 0
        self.green_ub = 50
        self.blue_lb = 0
        self.blue_ub = 50
        self.item_size_threashold = 20
        self.fine_step_size_threashold = 10
        self.width = None
        self.height = None
        self.arr = None
        
        while (True):
            time.sleep(2)
            img = Image.open("../../images/balls/red_ball_1.jpg")
            
            self.width, self.height = img.size
            item_size = min(self.width, self.height)/self.item_size_threashold
            self.fine_step_size = item_size/self.fine_step_size_threashold
            self.arr = array(img)
            
            print("width:", self.width)
            print("height:", self.height)
            print("item_size:", item_size)
            
            ball_found = False            
            for y in range(0, self.height, item_size):
                for x in range(0, self.width, item_size):        
                    if self.check_colors(x, y):
                        print "found candidate pixel - starting fine search"
                        if self.find_ball(x, y):
                            print "found the ball"
                            ball_found = True
                            break
                if ball_found:
                    break
                
    def check_colors(self, x, y):
        if(x < 0 or y < 0 or x > self.width or y > self.height):
            return False
        r = self.arr[y][x][0]
        g = self.arr[y][x][1]
        b = self.arr[y][x][2]
        check = self.red_lb <= r <= self.red_ub
    #     check = red_lb <= r <= red_ub and green_lb <= g <= green_ub and blue_lb <= b <= blue_ub
        if check:
            print (x, y, r, g, b)
    #     print (y, x, r, g, b)
        return check
   
    def find_ball(self, y, x):
        counter_slash = 0
        counter_back_slash = 0   
        for i in range(1, self.fine_step_size_threashold):  
            if(self.check_colors(x + self.fine_step_size*i, y - self.fine_step_size*i)):
                counter_slash += 1
            if(self.check_colors(x - self.fine_step_size*i, y + self.fine_step_size*i)):
                counter_slash += 1
            if(self.check_colors(x + self.fine_step_size*i, y + self.fine_step_size*i)):
                counter_back_slash += 1
            if(self.check_colors(x - self.fine_step_size*i, y - self.fine_step_size*i)):
                counter_back_slash += 1
        print counter_slash, counter_back_slash
        return counter_slash >=self.fine_step_size_threashold or counter_back_slash >= self.fine_step_size_threashold  
    
if __name__ == '__main__':
    findBallThread = FindBallThread()
    findBallThread.start()
        
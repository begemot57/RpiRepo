'''
Created on 10 Apr 2014

@author: leonid
'''
import threading
import time

class MyThread(threading.Thread):
    def run(self):
        print("About to sleep")
        time.sleep(5)
        print("Finished sleeping")
        
m = MyThread()
m.start()

time.sleep(1)
print("I'm still running")
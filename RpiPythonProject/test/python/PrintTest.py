'''
Created on 8 May 2014

@author: leonid
'''
# import sys

class MyError(Exception):
    def __init__(self, value):
        self.value = value
    def __str__(self):
        return repr(self.value)
    
# if(sys.argv[1] == "start"):
#     print "start python test"
    
try:
    while True:
        var = raw_input("Please enter something: ").strip()
        if (var == "f"):
            print("python forward")
            open("files/forward.txt", 'a')
        elif(var == "b"):
            print("python back")
            open("files/back.txt", 'a')
        elif(var == "r"):
            print("python right")
            open("files/right.txt", 'a')
        elif(var == "l"):
            print("python left")
            open("files/left.txt", 'a')
        elif(var == "s"):
            print("python stop")
            open("files/stop.txt", 'a')
        else:
            print("didn't get it", var)
            open("files/didntgetit.txt", 'a')
except Exception,e:
    print str(e)
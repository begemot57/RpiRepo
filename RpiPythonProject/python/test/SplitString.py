'''
Created on May 19, 2014

@author: Leo
'''
if __name__ == '__main__':
    params=raw_input("input string: ").split()
    first = params[0]
    print("first: "+first)
    if(len(params)>1):
        second = params[1]
        print("second: "+second)
        try:
            number = float(second)
            number += 1
            print("number: "+str(number))
        except ValueError:
            print("error thrown")
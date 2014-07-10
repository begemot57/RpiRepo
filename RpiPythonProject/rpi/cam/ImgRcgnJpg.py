from PIL import Image
from numpy import array

red_lb = 200
red_ub = 255
green_lb = 0
green_ub = 50
blue_lb = 0
blue_ub = 50
item_size_threashold = 20
fine_step_size_threashold = 10

img = Image.open("../../images/balls/red_ball_1.jpg")

width, height = img.size
item_size = min(width, height)/item_size_threashold
fine_step_size = item_size/fine_step_size_threashold
array = array(img)

print("width:", width)
print("height:", height)
print("item_size:", item_size)

def check_colors(x, y):
    if(x < 0 or y < 0 or x > width or y > height):
        return False
    r = array[y][x][0]
    g = array[y][x][1]
    b = array[y][x][2]
    check = red_lb <= r <= red_ub and green_lb <= g <= green_ub and blue_lb <= b <= blue_ub
    if check:
        print (x, y, r, g, b)
    return check

def find_ball(x, y):
    counter_slash = 0
    counter_back_slash = 0   
    for i in range(1, fine_step_size_threashold):  
        if(check_colors(x + fine_step_size*i, y - fine_step_size*i)):
            counter_slash += 1
        if(check_colors(x - fine_step_size*i, y + fine_step_size*i)):
            counter_slash += 1
        if(check_colors(x + fine_step_size*i, y + fine_step_size*i)):
            counter_back_slash += 1
        if(check_colors(x - fine_step_size*i, y - fine_step_size*i)):
            counter_back_slash += 1
    print counter_slash, counter_back_slash
    return counter_slash >=fine_step_size_threashold or counter_back_slash >= fine_step_size_threashold

ball_found = False            
for y in range(0, height, item_size):
    for x in range(0, width, item_size):        
        if check_colors(x, y):
            print "found candidate pixel - starting fine search"
            if find_ball(x, y):
                print "found the ball"
                ball_found = True
                break
    if ball_found:
        break

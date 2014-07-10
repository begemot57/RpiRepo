import png

red_lb = 200
red_ub = 255
green_lb = 0
green_ub = 50
blue_lb = 0
blue_ub = 50
item_size_threashold = 10
fine_step_size_threashold = 10

reader = png.Reader(filename='../cam/pics/red_ball_1.png')

pic_info = reader.read_flat()
pixel_byte_width = 4 if pic_info[3]['alpha'] else 3
width = pic_info[0]
height = pic_info[1]
item_size = min(width, height)/item_size_threashold
fine_step_size = item_size/fine_step_size_threashold
array = pic_info[2]

print("width:", width)
print("height:", height)
print("item_size:", item_size)
print("pixel_byte_width:", pixel_byte_width)
# print(pic_info)

def check_colors(x, y):
    if(x < 0 or y < 0 or x > width or y > height):
        return False
    num = pixel_byte_width*(y*width+x)
    r = array[num]
    g = array[num+1]
    b = array[num+2]
    check = red_lb <= r <= red_ub and green_lb <= g <= green_ub and blue_lb <= b <= blue_ub
    if check:
        print (x, y, num, r, g, b)
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

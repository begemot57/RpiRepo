import png

red_lb = 235
red_ub = 255
green_lb = 125
green_up = 170
blue_lb = 125
blue_up = 185

reader = png.Reader(filename='../images/mypic_1.png')

pic_info = reader.read_flat()
pixel_byte_width = 4 if pic_info[3]['alpha'] else 3
width = pic_info[0]
height = pic_info[1]
item_size = 1
array = pic_info[2]

print("width:", width)
print("height:", height)
print("item_size:", item_size)
print("pixel_byte_width:", pixel_byte_width)
# print(pic_info)

for y in range(0, height, item_size):
    for x in range(0, width, item_size):
            n = pixel_byte_width*(y*width+x)
            if array[n] == 254 and array[n+1] == 240 and  array[n+2] == 231:
                print (n, x, y, array[n], array[n+1], array[n+2])

# print(array.__getitem__(2))
# print(array.__getitem__(2)[1])

# print("reader.asRGBA8()")
# print(reader.asRGBA8())

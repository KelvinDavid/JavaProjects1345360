import serial
import tkinter   # The python tcl/tk ui construction api

top = tkinter.Tk()     # The tcl/tk object
canvas = tkinter.Canvas(top,bg="bisque",height=800,width=800)
canvas.pack()
top.update()

ser = serial.Serial()
ser.baudrate = 115200
ser.bytesize = 8
ser.stopbits = 1
ser.parity = 'N'
ser.timeout = 2000
ser.port = "com6"
ser.xonxoff = 0
ser.rtscts = 0
ser.open()

count = 1
t = 0
lastX = 0
lastY = 0
lastZ = 0

while (True):
  srlIn = ser.read(4)
  if (srlIn.__len__() == 4 and count == 0):
    xi = srlIn[0] + 256 * (srlIn[1] + 256 * (srlIn[2] + 256 * srlIn[3]))
    if srlIn[3] >= 128:      # 2's complement
      xi =  xi - 65536 * 65536;
    print(xi, end=" ", flush=True)
    x = xi / 5
    canvas.create_line(t, 400 + lastX, t + 1, 400 + x, fill="red")
    lastX = x

  if (srlIn.__len__() == 4 and count == 1):
    yi = srlIn[0] + 256 * (srlIn[1] + 256 * (srlIn[2] + 256 * srlIn[3]))
    if srlIn[3] >= 128:      # 2's complement
      yi =  yi - 65536 * 65536;
    print(yi, end=" ", flush=True)    
    y = yi / 5
    canvas.create_line(t, 400 + lastY, t + 1, 400 + y, fill="green")
    lastY = y
  if(srlIn.__len__() == 4 and count == 2):
    zi = srlIn[0] + 256 * (srlIn[1] + 256 * (srlIn[2] + 256 * srlIn[3]))
    if srlIn[3] >= 128:      # 2's complemen
        zi =  zi - 65536 * 65536;
    print(zi, end=" ", flush=True)
    z = zi / 5    
    canvas.create_line(t, 400 + lastZ, t + 1, 400 + z, fill="blue")
    lastZ = z
    
  t += 0.2
  top.update()
  if(count == 0):
    count = 1
  elif(count == 1):
    count = 2
  else:
    count = 0
  if t > 800:
    t = 0
    canvas.delete("all")
    
    
    

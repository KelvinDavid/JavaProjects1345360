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
ser.timeout = 5
ser.port = "com6"
ser.xonxoff = 0
ser.rtscts = 0
ser.open()


mark = 0
mark2 = 0
reading = False

t = 0

last1 = 0
last2 = 0
last3 = 0

while (True):
  if(reading == False):
      x = ser.read(1)
      mark = x[0]
      print("mark: ", mark, " mark2: ", mark2)
      if(mark == 128 and mark2 == 128):
        reading = True
      mark2 = mark
  else:
    x = ser.read(4)
    if (x.__len__() == 4):
        print(x[0], x[1], x[2], x[3])
        xi = x[0] + 256 * (x[1] + 256 * (x[2] + 256 * x[3]))
    if x[3] >= 128:      # 2's complement
      xi =  xi - 65536 * 65536;
    print(xi, end=" ", flush=True)
    x = xi / 5
    canvas.create_line(t, 400 - last1, t + 1, 400 - x, fill="red")
    last1 = x
    
    print("")
    
    x = ser.read(4)
    if (x.__len__() == 4):
        print(x[0], x[1], x[2], x[3])
        xi = x[0] + 256 * (x[1] + 256 * (x[2] + 256 * x[3]))
    if x[3] >= 128:      # 2's complement
      xi =  xi - 65536 * 65536;
    print(xi, end=" ", flush=True)
    x = xi / 5
    canvas.create_line(t, 400 - last2, t + 1, 400 - x, fill="blue")
    last2 = x
    
    print("")
    
    x = ser.read(5)
    if (x.__len__() == 5):
        print(x[0], x[1], x[2], x[3])
        xi = x[0] + 256 * (x[1] + 256 * (x[2] + 256 * x[3]))
    if x[3] >= 128:      # 2's complement
      xi =  xi - 65536 * 65536;
    print(xi, end=" ", flush=True)
    x = xi / 5
    canvas.create_line(t, 400 - last3, t + 1, 400 - x, fill="green")
    last3 = x
    
    print("\n")
    reading = False
  
  t += 0.2
  top.update()
  if t > 800:
    t = 0
    canvas.delete("all")  
    
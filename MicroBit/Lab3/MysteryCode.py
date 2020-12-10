import serial
import tkinter   # The python tcl/tk ui construction api

#tkinter properties
top = tkinter.Tk()     # The tcl/tk object
canvas = tkinter.Canvas(top,bg="bisque",height=800,width=800)
canvas.pack()
top.update()

#Serial Properties
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

t = 0
count = 0
lastVar1 = 0
lastVar2 = 0
lastVar3 = 0

#NOTES:
#Var1 = AccelX
#Var2 = AccelY?

while (True):
  #Reading incoming serial values
  x = ser.read()
  print(x[0], end=" ", flush=True)
  if(count == 3):
    var1O = x[0]
  if(count == 4):
    var1 = x[0]
  if(count == 7):
    var2O = x[0]
  if(count == 8):
    var2 = x[0]
  if(count == 14):
    var3 = x[0]
  if(count == 14):
    if(var1O == 255):
        var1 = var1 * -1
    if(var2O == 255):
        var2 = var2 * -1
    var1g = var1 / 5
    var2g = var2 / 5
    var3g = var3 / 5
    print("\n")
    print("var1: ", var1)
    print("var2", var2)
    print("var3", var3)
    print("\n")
    canvas.create_line(t, 400 - lastVar1, t + 1, 400 - var1g, fill="red")
    lastVar1 = var1g
    canvas.create_line(t, 400 - lastVar2, t + 1, 400 - var2g, fill="blue")
    lastVar2 = var2g
    #canvas.create_line(t, 400 - lastVar3, t + 1, 400 - var3g, fill="green")
    lastVar3 = var3g
    count = 0
  count += 1;
  
  t += 0.1
  top.update()
  if t > 800:
    t = 0
    canvas.delete("all")
  
  
  

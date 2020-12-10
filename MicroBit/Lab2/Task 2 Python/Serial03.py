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
ser.port = "com4"
ser.xonxoff = 0
ser.rtscts = 0
ser.open()

#This method reads the lines of th inputs coming in
def readLine():
  line = ""
  vType = ""
  while True:
    key = ser.read(1)
    if key.__len__() > 0:
      if chr(key[0]) == '\r':
        return vType, int(line)
      elif chr(key[0]) == 'x':
        vType += '1'
      elif chr(key[0]) == 'y':
        vType += '2'
      elif chr(key[0]) == 'z':
        vType += '3'
      else:
        line += chr(key[0])
        
t = 0
lastX = 0
lastY = 0
lastZ = 0
while True:
  tupleI = readLine()
  vType = tupleI[0]
  i = tupleI[1]
  print("TupleI: ", vType)
  print("i: ", i)
  if vType == '1':
    x = i
    #print("Accel X: ", x)
    x = x / 5
    canvas.create_line(t, 400 + lastX, t + 1, 400 + x, fill="red")
    lastX = x
  elif vType == '2':
    y = i
    #print("Accel Y: ",y)
    y = y / 5
    canvas.create_line(t, 400 + lastY, t + 1, 400 + y, fill="blue")
    lastY = y
  else:
    z = i
    #print("Accel Z: ",z)
    z = z / 5
    canvas.create_line(t, 400 + lastZ, t + 1, 400 + z, fill="green")
    lastZ = z
  t += 0.5
  top.update()
  if t > 800:
    t = 0
    canvas.delete("all")

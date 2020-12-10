import serial
ser = serial.Serial()

ser.baudrate = 115200
ser.bytesize = 8
ser.stopbits = 1
ser.parity = 'N'
ser.timeout = 5
ser.port = "com3"
ser.xonxoff = 0
ser.rtscts = 0
ser.open()

while True:
  x = ser.read(1)
  if x.__len__() > 0:
      print(chr(x[0]), end="", flush=True)
  y = ser.read(2)
  if x.__len__() > 0:
      print(chr(y[0]), end="", flush=True)
  z = ser.read(3)
  if x.__len__() > 0:
      print(chr(z[0]), end="", flush=True)

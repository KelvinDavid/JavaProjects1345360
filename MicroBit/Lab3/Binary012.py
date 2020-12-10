import serial
import time
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

count = 0
while (True):
  x = ser.read()
  print(x[0], end=" ", flush=True)
  if(count == 14):
    print("\n")
    count = 0
  count += 1;

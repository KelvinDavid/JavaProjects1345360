import serial
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

while (True):
  x = ser.read(1)
  print(x[0], end=" ", flush=True)

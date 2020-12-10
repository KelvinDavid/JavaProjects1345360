import serial

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

while (True):
  x = ser.read(4)
  if (x.__len__() == 4):
    xi = x[0] + 256 * (x[1] + 256 * (x[2] + 256 * x[3]))
    if x[3] >= 128:      # 2's complement
      xi =  xi - 65536 * 65536;
    print(xi, end=" ", flush=True)

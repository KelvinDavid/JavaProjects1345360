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

def readLine():
  line = ""
  while True:
    x = ser.read(1)
    if x.__len__() > 0:
      if chr(x[0]) == '\r':
        return line
      else:
        line += chr(x[0])
        
sum = 0
while (True):
  input = readLine()
  value = int(input)
  sum += value
  print(sum)

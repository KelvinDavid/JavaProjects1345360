#include "MicroBit.h"

MicroBit uBit;

int main() {
  uBit.init();
  for (;;) {
	int x = uBit.accelerometer.getX();
	int y = uBit.accelerometer.getY();
	int z = uBit.accelerometer.getZ();
	uBit.serial.send("x"+ManagedString(x)+"\r\n");
	uBit.serial.send("y"+ManagedString(y)+"\r\n");
	uBit.serial.send("z"+ManagedString(z)+"\r\n");
  }
}

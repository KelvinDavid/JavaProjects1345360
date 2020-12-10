#include "MicroBit.h"

MicroBit uBit;

int main() {
  uBit.init();

  uint8_t bufferx[10];  // Bigger than needed
  uint8_t buffery[10];  // Bigger than needed
  uint8_t bufferz[10];  // Bigger than needed

  for (;;) {
	int x = uBit.accelerometer.getX();
	int y = uBit.accelerometer.getY();
	int z = uBit.accelerometer.getZ();
     *((int *)bufferx) = x;
	 *((int*)buffery) = y;
	 *((int*)bufferz) = z;
     uBit.serial.send(bufferx, 4);
	 uBit.serial.send(buffery, 4);
	 uBit.serial.send(bufferz, 4);
	}
}

#include "MicroBit.h"

MicroBit uBit;

int main()
{
    uBit.init();

    while (true) {
        
        uBit.display.image.setPixelValue(2, 2, 255);

        uBit.sleep (500);
        
        uBit.display.image.setPixelValue(2, 2, 0);

        uBit.sleep (500);
    }
}

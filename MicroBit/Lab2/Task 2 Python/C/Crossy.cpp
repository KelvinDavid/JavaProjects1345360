#include "MicroBit.h"

MicroBit uBit;

int main()
{
    uBit.init();

    for (int r = 0;  r < 5;  r++)
        for (int c = 0;  c < 5;  c++)
            if (r == c || r == 4 - c)
                uBit.display.image.setPixelValue(r, c, 255);
            else            
                uBit.display.image.setPixelValue(r, c, 0);
    
    release_fiber();
}

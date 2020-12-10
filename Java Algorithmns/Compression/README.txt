We managed to implement LZW to work with 4 bit nibbles and 8 bit bytes with a 16 symbols being used to represent all possible
 Nibblecombinations and 256 symbols being used to represent all possible Byte combinations. The results we found were quite intesting with
 the Nibble implementaion consitantly ending up with more phrase numbers than the Byte implemenation after being encoded, however after
using our bit packer implementaion the file ended up smaller from the Nibble output than was seen from
the Byte encoded output. 

Our code worked well and quick for encoding / decoding small files (Around 100kb in size) however it took a significantly long time to decode
 MobyDick.txt possible because of the implementation of symbols or the use of a linked list instead of a BST(which we failed to implement as 
we couldnt code a working balance method). (Would not fully load in one go in either case in linux notepad, Not sure if it was due to hardware 
power required as we both tried running it on our laptops for around 2 hours to get it to finish decoding (Which it did do successfully)

We were not able to implement a bitUnpacker to be able to test out our bitPacker due to the trouble we were having with decoding large files.

The Encoder program takes any readable file it is called using and outputs a text file named <input file name.extention>.txt and the
 Decoder is called using the output file and will recreate the file that was encoded and will output it as being <input file name>Decoded<.extention>

The LZWpacker is called using the same <input file name.extention>.txt output by either the Nibble or Byte encoder and the file created once it has been bitpacked is 
output to be <input file name.extention>_Packed.LZW

We feel that if 

The Nibble folder contains all the .java files setup with the 16 symbol trie and the Byte folder contains all the .java files setup with
 the 256 symbol trie 

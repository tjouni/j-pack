# Testing document

Project testing is currently done by unit tests and manual compression tests of different files. The I/O functionality is not tested as it uses standard Java libraries.

### Unit testing

  * LZ77 compression and decompression tests
    * Test compression of different types of files (small and large with lots of repetition) to see actual compression works
    * Test decompression of different types of files by asserting that the decompressed file matches the original
  * Data structures tests
    * ByteList tests
      * Test all functions of the dynamically sized Byte array
    * HuffmanNode tests
      * Test all functions of the HuffmanNode class
    * HuffmanTree tests
      * Test that different types of trees built are of the correct form for a few different types of trees
  
  
### Manual testing

  * Application gives compression ratio and elapsed time figures when used. These numbers are used to test which optimizations give better time/compression performance.
    * File currently used for testing is Aleksis Kivi's Seitsemän veljestä copied five times in a row (3,2MB)
      * Current performance figures (with only LZ77 implemented):
        * Compression ratio 1.62
        * Compression time 7030ms
        * Decompression time 26ms

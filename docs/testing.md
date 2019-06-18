# Testing document

Project testing is currently done by unit tests and manual compression tests of different files. The I/O functionality is not tested as it uses standard Java libraries.

### Unit testing

  * LZ77 compression and decompression tests
    * Test compression of different types of arrays (small and large with lots of repetition) to see actual compression works
    * Test decompression of different types of arrays by asserting that the decompressed file matches the original
  * Huffman compression and decompression tests
    * Test compression of different types of arrays (varying degrees of repetition) to see actual compression works
    * Test decompression by asserting that the decompressed file matches the original
  * Deflate compression and decompression test
    * Test a combination of LZ77+Huffman
  * Data structures tests
    * ByteList tests
      * Test all functions of the dynamically sized byte array
    * BitList tests
      * Test all functions of the dynamically sized bit array
    * HuffmanNode tests
      * Test all functions of the HuffmanNode class
    * HuffmanTree tests
      * Test that different types of trees built are of the correct form for a few different types of trees
  
  
### Manual testing

  * Application gives compression ratio and elapsed time figures when used. These numbers are used to test which optimizations give better time/compression performance.

#### The Canterbury Corpus

##### Huffman coding only performance figures

| File         | Size(B)  | Compressed Size(B) | Compression ratio | Space savings | Compression time(ms) | Decompression time(ms) |
| ------------ | -------- | ------------------ | ------------------| ------------- | -------------------- | ---------------------- |
| alice29.txt  | 152089   | 87782              | 1.73              | 42.3%         | 22                   | 16                     |
| asyoulik.txt | 125179   | 75892              | 1.65              | 39.4%         | 22                   | 19                     |
| cp.html      | 24603    | 16307              | 1.51              | 33.7%         | 8                    | 9                      |
| fields.c     | 11150    | 7140               | 1.56              | 36.0%         | 11                   | 9                      |
| grammar.lsp  | 3721     | 2266               | 1.64              | 39.1%         | 5                    | 5                      |
| kennedy.xls  | 1029744  | 462853             | 2.22              | 55.1%         | 36                   | 35                     |
| lcet10.txt   | 426754   | 250670             | 1.70              | 41.3%         | 29                   | 24                     |
| plrabn12.txt | 481861   | 275687             | 1.75              | 42.8%         | 34                   | 26                     |
| ptt5         | 513216   | 106751             | 4.81              | 79.2%         | 25                   | 20                     |
| sum          | 38240    | 25965              | 1.47              | 32.1%         | 13                   | 10                     |
| xargs.1      | 4227     | 2695               | 1.57              | 36.2%         | 5                    | 4                      |

##### LZ77+Huffman coding performance figures

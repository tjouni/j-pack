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
    * BitList tests
      * Test all functions of the dynamically sized bit array
    * ByteList tests
      * Test all functions of the dynamically sized byte array
    * HuffmanNode tests
      * Test all functions of the HuffmanNode class
    * HuffmanTree tests
      * Test that different types of trees built are of the correct form for a few different types of trees
    * MinimumHeap tests
      * Test all functions of the heap priority queue implementation
    * PrefixHashTable tests
      * Unit tests for asserting that inserted objects can be found in the table
      * Prefix searching is tested by integration tests through LZ77CompressTest and DeflateTest classes
    * Prefix tests
      * Test prefix 16b values for different combinations of two bytes
    
  
  
### Manual testing

  * Application gives compression ratio and elapsed time figures when used. These numbers are used to test which optimizations give better time/compression performance.

#### The Canterbury Corpus

Compression and decompression times are averages of 10 measurements. Testing done on a 2017 MacBook Pro with 2,3 GHz Intel Core i5 and 8GB memory.

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

| File         | Size(B)  | Compressed Size(B) | Compression ratio | Space savings | Compression time(ms) | Decompression time(ms) |
| ------------ | -------- | ------------------ | ------------------| ------------- | -------------------- | ---------------------- |
| alice29.txt  | 152089   | 75566              | 2.01              | 50.3%         | 59                   | 32                     |
| asyoulik.txt | 125179   | 67665              | 1.85              | 45.9%         | 52                   | 29                     |
| cp.html      | 24603    | 12745              | 1.93              | 48.2%         | 20                   | 13                     |
| fields.c     | 11150    | 5328               | 2.09              | 52.2%         | 13                   | 9                      |
| grammar.lsp  | 3721     | 2276               | 1.63              | 38.8%         | 8                    | 6                      |
| kennedy.xls  | 1029744  | 269631             | 3.82              | 73.8%         | 152                  | 49                     |
| lcet10.txt   | 426754   | 203692             | 2.10              | 52.2%         | 106                  | 52                     |
| plrabn12.txt | 481861   | 268093             | 1.80              | 44.4%         | 152                  | 57                     |
| ptt5         | 513216   | 93629              | 5.48              | 81.8%         | 183                  | 40                     |
| sum          | 38240    | 18159              | 2.11              | 52.5%         | 36                   | 14                     |
| xargs.1      | 4227     | 2794               | 1.51              | 33.9%         | 8                    | 7                      |

<img src="https://raw.githubusercontent.com/tjouni/j-pack/master/docs/sizechart1.png">

<img src="https://raw.githubusercontent.com/tjouni/j-pack/master/docs/sizechart2.png">

As can be seen from the results, LZ77+Huffman is a better choice if the longer compression-time running time is not an issue. All files except grammar.lsp and xargs.1 were compressed more efficiently and in both these cases the difference in file size was under 4%. Most of the files compressed with LZ77+Huffman showed significant space savings compared to Huffman only compression. Decompression times are "fast enough" in both cases, so this should not be an issue.


### Sources

  * [The Canterbury Corpus](http://corpus.canterbury.ac.nz/descriptions/#cantrbry)

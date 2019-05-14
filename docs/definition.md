# Project definition

The goal of this project is to create a java application that implements Huffman codes and the LZ77 algorithm to compress uncompressed files with 20-70% efficiency. In the end this should result in a compression method similar to the DEFLATE algorithm. All the data structures needed will be implemented by hand.


### Data structures

  * Huffman code
    * Tree structure for the code map
    * Minimum heap for building the tree
  * LZ77
    * Something for string matching possibly?
    
    
### Problem definition

  * Compression of files
  * Algorithms and data structures chosen for their simplicity and efficiency
  

### IO

  * Input: a file to be compressed or decompressed
  * Output: compressed/decompressed file
  
  
### Goal time and space complexity (N = file length)

  * Huffman codes implemented in O(N log N) time
  * LZ77 probably depends on string matching? or O(N) with large constant coefficients?
    * Searching for matches in window is probably the bottleneck
    
  * Decompression in O(N) time
  
  
### Sources

  * Huffman codes & minimum heap :
    * Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2009). Introduction to algorithms. MIT press.
  * LZ77:
    * [Wikipedia](https://en.wikipedia.org/wiki/LZ77_and_LZ78)
  * DEFLATE:
    * [RFC 1951](https://www.ietf.org/rfc/rfc1951.txt)


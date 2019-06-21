# Implementation document

### LZ77

LZ77 is currently implemented with a lookback window of size 4096 and a lookahead buffer of size 15. First 4100 bytes of compressed file contains the original file size and 4096 bytes of uncompressed data for the lookback window. Every block of compressed data starts with a a single bit indicating if what follows is a literal or a pointer. 1, indicating a block, is followed by two bytes consisting of 12b offset and 4b block length. 0, indicating a literal, is followed by a single byte literal. A hash table is used for searching for matches.

#### Prefix hash table

A hash table is used to search for prefixes. Each bucket of the table contains a singly-linked list of prefixes and new prefixes are added to the front of the list. If linear search for a prefix finds any prefix outside the lookback window, the list is cut to minimize memory usage.

### Huffman codes

Huffman codes are implemented through HuffmanNode, HuffmanTree and MinimumHeap classes. First byte of the compressed file consists of 1b indicating if LZ77 compression was used, and following 7b for the number of bits used in the last byte of the file. The Huffman code tree is built recursively and written to file after the first byte. 0-bit is used to indicate a non-leaf node and 1-bit to indicate a leaf node. The leaf node bit is followed by the 8bit byte value of that node.

#### Huffman tree

The Huffman tree is implemented as a binary tree structure of HuffmanNode objects. The HuffmanTree class contains a method to write a bit representation explained previously on a BitList object.

#### Minimum heap

Minimum heap is implemented as an array of fixed size of 4095 starting at index 1 that maintains the minimum heap property at every insertion and minimum object removal. 

### BitList and ByteList

BitList and ByteList are dynamically sized arrays of bits and bytes that allow writing and reading of bits and bytes. Both can be converted to a byte array that is the same size as the original object.

### IO

All IO operations use Java standard library functionality. All compression and and decompression operations are done in memory and the whole file is read and written to memory at once.

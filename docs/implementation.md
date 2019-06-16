# Implementation document

### LZ77

LZ77 is currently implemented with a lookback window of size 4096 and a lookahead buffer of size 15. Every block starts with a a single bit indicating if what follows is a literal or a pointer. 1, indicating a block, is followed by two bytes consisting of 12b offset and 4b block length. 0, indicating a literal, is followed by a single byte literal. A hash table is used for searching for matches.

#### Prefix hash table

A hash table is used to search for prefixes. Each bucket of the table contains a singly-linked list of prefixes and new prefixes are added to the front of the list. If linear search for a prefix finds a prefix outside the lookback window, the list is cut to minimize memory usage.

### Huffman codes

Huffman codes are implemented through HuffmanNode, HuffmanTree and MinimumHeap classes. First byte of the compressed file consists of 1b indicating if LZ77 compression was used, and following 7b for the number of bits used in the last byte of the file. The Huffman code tree is built recursively and written to file after the first byte. 0-bit is used to indicate a non-leaf node and 1-bit to indicate a leaf node. The leaf node bit is followed by the 8bit byte value of that node.

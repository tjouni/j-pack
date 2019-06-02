# Implementation document

### LZ77

LZ77 is currently implemented with a lookback window of size 4096 and a lookahead buffer of size 15. Each LZ77 block is three bytes. It is made up of 12b offset, 4b length and 8b next character. LZ77 uses a dynamically sized array of bytes to do compression and decompression. The compression algorithm searches for a long enough prefix-match for the lookahead buffer (long enough is currently 2/3 of lookahead buffer). Prefix-searching is currently the slowest part of the program so some optimizations are probably coming.

### Huffman codes

Huffman code implementation is a work in progress. Currently implemented parts are the HuffmannTree and HuffmanNode classes for building Huffman code trees, and basic methods for compression with Huffman Codes.


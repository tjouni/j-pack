# j-pack


### Documentation

  * [Project definition](https://github.com/tjouni/j-pack/blob/master/docs/definition.md)
  * [Implementation document](https://github.com/tjouni/j-pack/blob/master/docs/implementation.md)
  * [Testing document](https://github.com/tjouni/j-pack/blob/master/docs/testing.md)
  * [Javadoc](http://htmlpreview.github.com/?https://github.com/tjouni/j-pack/blob/master/docs/javadoc/index.html)


### Weekly reports (in Finnish)

  * [Viikko 1](https://github.com/tjouni/j-pack/blob/master/docs/viikkoraportti1.md)
  * [Viikko 2](https://github.com/tjouni/j-pack/blob/master/docs/viikkoraportti2.md)
  * [Viikko 3](https://github.com/tjouni/j-pack/blob/master/docs/viikkoraportti3.md)
  * [Viikko 4](https://github.com/tjouni/j-pack/blob/master/docs/viikkoraportti4.md)
  * [Viikko 5](https://github.com/tjouni/j-pack/blob/master/docs/viikkoraportti5.md)
  * [Viikko 6](https://github.com/tjouni/j-pack/blob/master/docs/viikkoraportti6.md)


### Usage

 ```
 ./gradlew run --args='arguments'
 ```
 
arguments: [-hu *filename*] [-df *filename*] [-de *inputfile outputfile*]

* hu       compress file using Huffman only
* df       compress using LZ77 + Huffman
* de       decompress a jpack compressed file

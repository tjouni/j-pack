# j-pack

A Java application for compressing files with LZ77 and Huffman algorithms. A school project for an Algorithms and Data Structures course.


### Documentation

  * [Project definition](https://github.com/tjouni/j-pack/blob/master/docs/definition.md)
  * [Implementation document](https://github.com/tjouni/j-pack/blob/master/docs/implementation.md)
  * [Testing document](https://github.com/tjouni/j-pack/blob/master/docs/testing.md)
  * [Javadoc](https://tjouni.github.io/j-pack-javadoc/)


### Weekly reports (in Finnish)

  * [Viikko 1](https://github.com/tjouni/j-pack/blob/master/docs/viikkoraportti1.md)
  * [Viikko 2](https://github.com/tjouni/j-pack/blob/master/docs/viikkoraportti2.md)
  * [Viikko 3](https://github.com/tjouni/j-pack/blob/master/docs/viikkoraportti3.md)
  * [Viikko 4](https://github.com/tjouni/j-pack/blob/master/docs/viikkoraportti4.md)
  * [Viikko 5](https://github.com/tjouni/j-pack/blob/master/docs/viikkoraportti5.md)
  * [Viikko 6](https://github.com/tjouni/j-pack/blob/master/docs/viikkoraportti6.md)


### Usage

Run from source code
 ```
 ./gradlew run --args='arguments'
 ```
 
Run .jar
 ```
 java -jar jpack.jar [arguments]
 ```
 
Arguments: [-hu *filename*] [-df *filename*] [-de *inputfile outputfile*]

* hu       compress file using Huffman only
* df       compress using LZ77 + Huffman
* de       decompress a jpack compressed file


#### Build project
 ```
 ./gradlew build
 ```


#### Generate jar from source code
 ```
 ./gradlew fatJar
 ```
 
#### Generate Javadoc
 ```
 ./gradlew javadoc
 ```
 
#### Generate Jacoco test report
 ```
 ./gradlew jacocoTestReport
 ```


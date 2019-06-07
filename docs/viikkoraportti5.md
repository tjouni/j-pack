# Viikko 5

[koodikatselmointi](https://github.com/Darake/zip-zop/issues/1)


Aikaa käytetty 26h.

### Mitä olen tehnyt tällä viikolla?

  * Tehty Huffman-koodaus loppuun saakka, toteutettu testit pakkaukselle ja purkamiselle
  * Mietitty miten päätetään sopiva pakkaustapa. Manuaalisessa testauksessa selvisi, että joskus kannattaisi tehdä pelkä Huffman ilman LZ77.
  
### Miten ohjelma on edistynyt?

Sain viime viikon laiskottelut otettua kiinni. Ohjelma on hyvällä mallilla.

### Mitä opin tällä viikolla / tänään?

Testauksen tärkeys. En ollut ollenkaan ajatellut tilannetta, jossa pakattavassa tiedostossa on vain yhtä ja samaa tavua koko tiedosto. Tällaisen tiedoston kohdalla Huffman epäonnistuu. Tällä viikolla aika ei riittänyt sitä korjaamaan.

### Mikä jäi epäselväksi tai tuottanut vaikeuksia?

Tuo yhtä tavua sisältävä tiedosto on sellainen bugi joka pitää korjata ensi viikolla.

### Mitä teen seuraavaksi?

Korjaan bugin Huffmanissa ja toteutan minimikeolle oman tietorakenteen. Alan katselemaan LZ77 varten KMP:tä tai hajautustaulun käyttöä.


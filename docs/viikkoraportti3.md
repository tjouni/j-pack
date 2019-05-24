# Viikko 3

Aikaa käytetty 11h.

### Mitä olen tehnyt tällä viikolla?

  * Toteutettu oma dynaaminen tavutaulukko ByteList
  * LZ77 pikku parannuksia, nopeutui hieman. Tämä on vielä pullonkaula
  * Aloitettu Huffman-koodien koodausta kirjoittamalla Huffman-koodipuun luokkia hieman
  * Kirjoitettu ja javadocit testit kaikille uusille metodeille
  * Tutustuttu taas lisää bittien värkkäämiseen Huffman-koodeja varten. Lueskelin eilen melkein koko päivän vinkkejä siihen, miten tuon toteuttaisi.

### Miten ohjelma on edistynyt?

Ohjelma on edistynyt. Kokeiltu LZ77 prefixin hakuun Z-algoritmia, mutta se oli erittäin hidas. Lueskeltu vähän KMP:stä, jos sillä sais toteutettua nopeamman haun. Huffman-koodin toteutus on pääkopassa selvillä nyt bittien lukemista ja kirjoittamista lukuunottamatta.

### Mitä opin tällä viikolla / tänään?

Opeteltu perusteet Z-algoritmista, KMP:stä ja Boyer-Mooresta.

### Mikä jäi epäselväksi tai tuottanut vaikeuksia?

Matchaus on vähän nopeampaa, mutta edelleen hidasta. Jos KMP:n saa sovellettua tähän ongelmaan, ja sillä saadaan tarpeeksi suorituskykyä niin hyvä. Muuten alkaa näyttää siltä että pitää alkaa rakentelemaan jokin hajautusta hyödyntävä tietorakenne.

### Mitä teen seuraavaksi?

Koodaan Huffman-koodauksen (toivottavasti) valmiiksi. Ainoa asia mikä epäilyttää on tuo bittien lukeminen/kirjoittaminen. Tähän toteutaan varmaan jonkin oman bitset-tyyppisen tietorakenteen.

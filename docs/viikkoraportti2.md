# Viikko 2

Aikaa käytetty 13h.

### Mitä olen tehnyt tällä viikolla?

  * Toteutettu ohjelmaan IO-luokka tiedostojen lukemista ja kirjoittamista varten
  * Toteutettu alustava LZ77-algoritmi, joka on tällä hetkellä vielä aika hidas
    * Pakkaa testitiedoston 1.62 ratiolla (n. 38%)
  * Aloitettu myös testien kirjoittaminen sitä mukaa kun luokkia tulee lisää
  * Paikkailtu puutteita määrittelydokumentista

### Miten ohjelma on edistynyt?

Ihan hyvin mielestäni. Sain tehtyä sen mitä ajattelinkin tällä viikolla tehdä. Huomasin, että ohjelma tarvii myös dynaamisen taulukon tavuja varten. Lisäsin sen määrittelydokumenttiin tietorakenteiden kohdalle. Tällä hetkellä käytän Javan ArrayListiä.

Ohjelman voi nyt suorittaa, ja se pakkaa ja purkaa projektin juuressa olevan test-input.txt -tiedoston.

### Mitä opin tällä viikolla / tänään?

Javalla tavujen kans värkkäilyä. Ei ollut aikaisemmin tullut tehtyä mitään noilla bitwise-operaattoreilla, joten siinä oli harjoittelemista. Loppujen lopuksi sain kuitenkin asiat toimimaan.

### Mikä jäi epäselväksi tai tuottanut vaikeuksia?

Stringin matchaus on hidasta. Tähän pitää jossakin vaiheessa keksiä jotakin järkevämpää. Suunnitelmissa olisi viikonloppuna lueskella vähän aiheesta ja miettiä siihen jokin parempi ratkaisu.

### Mitä teen seuraavaksi?

Aloitan Huffman-koodien toteutuksen ja/tai stringin matchauksen optimointia.

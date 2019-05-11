# Viikko 1

Aikaa käytetty 10h.

### Mitä olen tehnyt tällä viikolla?

Tutustuttu Huffman-koodeihin ja Lempel Ziv -algoritmin eri versioihin. Työn aiheeksi valikoitui LZ77 ja Huffman, jotka tullaan lopulta yhdistämään hieman DEFLATE:a muistuttavaksi pakkausalgoritmiksi. Suurin osa ajasta meni aiemmin tuntemattoman Lempel Zivin opiskeluun. Tutustuttu myös Javan mahdollisuuksiin lukea ja kirjoittaa tiedostoja, ja tähän liittyvien rajoituksien johdosta päätetty (mahdollisesti) luopua täysverisen DEFLATEn toteuttamisesta. Tällä hetkellä tarkoitus olisi ajaa ensin LZ77 tiedostolle ja käyttää 24 bittiä yhden blokin koodaamiseen (12b offset, 4b pituus, 8b seuraava merkki). Tämän jälkeen tiedosto pakataan Huffmann-koodeilla.


### Miten ohjelma on edistynyt?

Projekti luotu. Tutustuttu hieman gradleen.


### Mitä opin tällä viikolla / tänään?

Opin miten erilaiset LZ-variaatiot toimii ja käytin aika paljon aikaa miettiessäni miten tällaisen toteuttaisi tehokkaasti. Kiinnostus tiedon pakkaamista kohtaan kasvoi huomattavasti.

### Mikä jäi epäselväksi tai tuottanut vaikeuksia?

Mietityttää, että onko tuo idea DEFLATEn yksinkertaistamisesta järkevä. Pelottaa, että ohjelmasta tulee erittäin vaikea toteuttaa jos alan käyttämään noita 9 bitin mittaisia "aakkosia". Toinen mietityttänyt asia on LZ77:n stringin matchaus. Jos ikkunan koko on 4096 tavua ja matchattava stringi 3-16 tavua niin tähän pitäisi keksiä joku tehokas tapa.

### Mitä teen seuraavaksi?

Aloitan ohjelmoimalla yksinkertaisen tiedoston luvun byte-taulukkoon ja LZ77 koodauksen naiivilla stringin matchauksella.

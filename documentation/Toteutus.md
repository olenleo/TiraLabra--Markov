# Projektin toteutusdokumentti

## Ohjelman yleisrakenne

Ohjelman päätoiminnallisuus on jaetttu muutamaan pakkaukseen:
- **Käyttöliittymä**  kutsuu muita luokkia tarvittaessa
- **Sovelluslogiikka** sisältää tietorakenteet ja markovketju-toiminnallisuuden
- **NoteReader** ja **NotePrinter** sisältävät toiminnallisuuden .csv-tiedoston lukuun ja vastaavasti lopputuloksen sisältävän .rb-tiedoston formatointiin ja kirjoittamiseen.

## Saavutetut aika- ja tilavaativuudet (m.m. O-analyysit pseudokoodista)
Testaukseni perusteella Trie-tietorakenne toimii tehokkaasti. En ole testannut nuottien lukemisen tehokkuutta sen tarkemmin - yksi for-loop olisi varmaankin mahdollisesti poistettavissa - mutta koska opetusdatan sisältävät tiedostot ovat melko pieniä en usko että tämä on varsinainen pullonkaula. 

## Työn mahdolliset puutteet ja parannusehdotukset
Käytettävyys paranisi suuresti joko toteuttamalla koko sovellus Sonic Pi:ssä tai vaihtoehtoisesti lukemalla .mid-tiedostot suoraan tässä java-sovelluksessa. Näin voisi välttää  MidiCSV-ohjelman käytön. 

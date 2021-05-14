# Projektin toteutusdokumentti

## Ohjelman yleisrakenne

Ohjelman päätoiminnallisuus on jaetttu muutamaan pakkaukseen:
- **Käyttöliittymä**  kutsuu muita luokkia tarvittaessa
- **Sovelluslogiikka** sisältää tietorakenteet ja markovketju-toiminnallisuuden
- **NoteReader** ja **NotePrinter** sisältävät toiminnallisuuden .csv-tiedoston lukuun ja vastaavasti lopputuloksen sisältävän .rb-tiedoston formatointiin ja kirjoittamiseen.

## Saavutetut aika- ja tilavaativuudet (m.m. O-analyysit pseudokoodista)
Testaukseni perusteella Trie-tietorakenne toimii tehokkaasti. En ole testannut nuottien lukemisen tehokkuutta sen tarkemmin - yksi for-loop olisi varmaankin mahdollisesti poistettavissa - mutta koska opetusdatan sisältävät tiedostot ovat melko pieniä en usko että tämä on varsinainen pullonkaula. 

## Parannusehdotukset
Käytettävyys paranisi suuresti joko toteuttamalla koko sovellus Sonic Pi:ssä tai vaihtoehtoisesti lukemalla .mid-tiedostot suoraan tässä java-sovelluksessa. Näin voisi välttää  MidiCSV-ohjelman käytön. 

## Puutteita
Nuottien kestot saattavat olla negatiivisia. Pikaisena korjauksena Sonic Pi asettaa negatiivisten nuottien kestojen arvoksi 2. 

Toinen harvinaisempi ongelma: Sointu, jossa on enemmän nuotteja kuin trie:n haluttu syvyys, saattaa aiheuttaa nuottisarjan ilman taukoa. Tämä keskeyttää Sonic Pi:n live_loopin - jokaisessa loopissa täytyy nimittäin olla joko synkronointikäsky (sync :foo) tai tauko (sleep). Tämä on korjattavissa asettamalla hyvin pieni sleep-arvo (esimerkiksi 0.0001) looppiin ja halutessa kompensoitava tästä aiheutuva minimaalinen aikavääristymä tarvittaessa. 

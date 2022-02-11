# Markov Music Generator
Tietorakenteet ja Algoritmit-harjoitustyö: Proseduraalista musiikkia Markovin ketjuilla ja trie-tietorakenteella.

## Kuvaus
Tämä java-sovellus ottaa syötteekseen esiformatoidun .csv-pohjaisen tiedoston joka sisältää raakaa midi-informaatiota. Sovellus tallettaa halutun pitkiä nuottisarjoja ja niiden esiintymistiheyksiä trie-tietorakenteeseen. Näiden pohjalta generoidaan haluttu määrä nuottisarjoja.

Tällä hetkellä lopputulos tallentuu [Sonic Pi](https://sonic-pi.net/)-syntetisaattorin syntaksia hyödyntävään .rb-tiedostoon. Kyseessä on valmis, soitettava sävellys. 

## Käyttöohjeet

Sovellus avaa komentorivipohjaisen käyttöliittymän. Lopputuloksena saatava ruby-tiedosto on suoraan ajettavissa Sonic Pi:n avulla. 

Käynnistys: <code>$  java -jar musicGenerator.jar </code>

Repositoriossa löytyvät .csv-tiedostot lueteltuina [resources-kansiossa](https://github.com/olenleo/TiraLabra--Markov/tree/main/src/main/resources)


Checkstyle: <code>$ mvn jxr:jxr checkstyle:checkstyle</code>

Testiraportti: <code>$ mvn test jacoco:report</code>

## Vaatimukset
Repositorio sisältää .csv-tiedostoja testausta varten. Omien tiedostojen importtaminen vaatii ulkoisen [MidiCSV](https://www.fourmilab.ch/webtools/midicsv/)-työkalun asennuksen. Musiikin toisto puolestaan edellyttää [Sonic Pi:n](https://sonic-pi.net/) asentamisen.

## Requirements
- Install openjdk:11
- Run ``` $ java -jar musicgenerator.jar ```
- The docker image has issues with the default target folder: I suggest using ```/generatedMusic/```

# Viikkoraportit ja muu dokumentaatio:

[Viikko 1](https://github.com/olenleo/TiraLabra--Markov/blob/main/documentation/MAARITTELY.md)
[Viikko 2](https://github.com/olenleo/TiraLabra--Markov/blob/main/documentation/Viikkoraportti2.md)
[Viikko 3](https://github.com/olenleo/TiraLabra--Markov/blob/main/documentation/Viikkoraportti3.md)
[Viikko 4](https://github.com/olenleo/TiraLabra--Markov/blob/main/documentation/Viikkoraportti4.md)
[Viikko 5](https://github.com/olenleo/TiraLabra--Markov/blob/main/documentation/Viikkoraportti5.md)
[Viikko 6](https://github.com/olenleo/TiraLabra--Markov/blob/main/documentation/Viikkoraportti6.md)

[Testaus](https://github.com/olenleo/TiraLabra--Markov/blob/main/documentation/Testaus.md)

[Toteutus](https://github.com/olenleo/TiraLabra--Markov/blob/main/documentation/Toteutus.md)

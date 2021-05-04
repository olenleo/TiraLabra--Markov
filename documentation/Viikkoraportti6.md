# Viikko 6

Viikkoraportti ja vertaisarviointi myöhässä flunssan myötä - ensin lapset kipeinä, sitten minä itse.

# Mitä olen tehnyt tällä viikolla?
 
Viikolla 5 toteutin ensiversion nuottien keston tallettavasta ohjelmasta. Päivittelin myös Sonic Pi-formaattiin kirjoittavaa luokkaa. 

# Miten ohjelma on edistynyt?
Kiitettävästi olosuhteet huomioon ottaen. mutta nuottien kestojen talletus on haastavaa. Ei kestot itsessään, mutta tauot ja polyfonia huomioon ottaen ongelmia on useita.
Päätin että keskityn nyt Sonic Pi-yhteensopivien ruby-tiedostojen luontiin. Näin saan musiikkia helposti ja nopeasti kuuluville. Haittapuolena on tietenkin että käyttäjä joutuu asentamaan lisää ohjelmia saadakseen hyödyn irti ohjelmastani. 


# Mitä opin tällä viikolla / tänään?
Midi-formaatissa on kaksi tapaa ilmaista nuotin loppuminen - joko tekstimuotoinen 'note off'-tyyppinen komento tai uusi 'note on'-viesti joka saa velocity-parametrin arvoksi 0. Kuvaava sitaatti internetin syövereistä: "You never know what you'll find in a MIDI file". Eli toisin sanoen olen hyvin iloinen siitä, että testausmateriaalini on monipuolista, sekä itse luotua että ladattua materiaalia.


# Mikä jäi epäselväksi tai tuottanut vaikeuksia? Vastaa tähän kohtaan rehellisesti, koska saat tarvittaessa apua tämän kohdan perusteella.
Tällä hetkellä yritän toteuttaa rytmiikkaa seuraavasti:

Pidän kirjaa nuottien alku- sekä päätepisteistä. Täten myös informaatio siitä paljonko taukoa nuotin lopun ja uuden alun välillä on saatavilla. Jos kukin nuotti-olio tietää kestonsa (sustain) sekä pitää kirjaa siitä kauanko taukoa pitää jatkaa (paremman puutteessa: rest) ennen seuraavaa nuottia, on rytmiikka toteutettavissa. Sonic Pi-puolella jokainen nuotti olisi taulukko mallia {korkeus, kesto, aika seuraavaan nuottiin}. Näin esimerkiksi soinnut olisivat mahdollisia - jos aika seuraavaan nuottiin on pienempi kuin nykyisen nuotin kesto saan useat sävelet kuuluviin samaan aikaan: 
```
[[60,4,0], [64,4,0], [67, 4,0]], # C-duuri
[[60,4,0], [64,4,0], [67, 4,0]] # C-molli
```
Tätä pitää vielä viilata. Ruby-koodia luova luokka on vielä raakile.

Alustava ajatukseni on että en pidä kirjaa tahtilajista, tahdeista tai muista vastaavista konstruktioista. Tämä olisi varmasti toteutettavissa trie-rakenteen avulla suhteellisen yksinkertaisesti laskemalla yhteen nuottien ja taukojen kestoja haluttuun määrään asti (esim 16 kokonuottia) ja merkitsemällä tämän pisteen lehdeksi.

# Mitä teen seuraavaksi?
Jatkan polyfonian ja rytmiikan toteutusta. Jos tämä vaikuttaa liian haastavalta siirryn suoraan viimeistelyn ja dokumentaation pariin.

Tuntikirjanpito: ~10 h tällä viikolla.

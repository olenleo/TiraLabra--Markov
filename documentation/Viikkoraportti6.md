# Viikko 6

Viikkoraportti ja vertaisarviointi myöhässä flunssan myötä - ensin lapset kipeinä, sitten minä itse.

# Mitä olen tehnyt tällä viikolla?
 
Viikolla 5 toteutin ensiversion nuottien keston tallettavasta ohjelmasta. Päivittelin myös Sonic Pi-formaattiin kirjoittavaa luokkaa. 

# Miten ohjelma on edistynyt?
Kiitettävästi olosuhteet huomioon ottaen. mutta nuottien kestojen talletus on haastavaa. Ei kestot itsessään, mutta tauot ja polyfonia huomioon ottaen ongelmia on useita. 


# Mitä opin tällä viikolla / tänään?
Midi-formaatissa on kaksi tapaa ilmaista nuotin loppuminen - joko tekstimuotoinen 'note off'-tyyppinen komento tai uusi 'note on'-viesti joka saa velocity-parametrin arvoksi 0. Kuvaava sitaatti internetin syövereistä: "You never know what you'll find in a MIDI file". Eli toisin sanoen olen hyvin iloinen siitä, että testausmateriaalini on monipuolista, sekä itse luotua että ladattua materiaalia.


# Mikä jäi epäselväksi tai tuottanut vaikeuksia? Vastaa tähän kohtaan rehellisesti, koska saat tarvittaessa apua tämän kohdan perusteella.
Tällä hetkellä yritän toteuttaa rytmiikkaa seuraavasti:

Pidän kirjaa nuottien alku- sekä päätepisteistä. Täten myös informaatio siitä paljonko taukoa nuotin lopun ja uuden alun välillä on saatavilla. Jos kukin nuotti-olio tietää kestonsa (sustain) sekä pitää kirjaa siitä kauanko taukoa pitää jatkaa (paremman puutteessa: rest) ennen seuraavaa nuottia, on rytmiikka toteutettavissa. Alustava ajatukseni on että en pidä kirjaa tahtilajista, tahdeista tai muista vastaavista konstruktioista. Tämä olisi varmasti toteutettavissa trie-rakenteen avulla suhteellisen yksinkertaisesti laskemalla yhteen nuottien ja taukojen kestoja haluttuun määrään asti (esim 16 kokonuottia) ja merkitsemällä tämän pisteen lehdeksi.

# Mitä teen seuraavaksi?
Jatkan polyfonian ja rytmiikan toteutusta. Jos tämä vaikuttaa liian haastavalta siirryn suoraan viimeistelyn ja dokumentaation pariin.

Tuntikirjanpito: ~10 h tällä viikolla.

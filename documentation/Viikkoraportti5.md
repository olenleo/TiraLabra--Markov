# Viikko 5

# Mitä olen tehnyt tällä viikolla?
 Olen toteuttanut tekstikäyttöliittymän, trie-rakenteen korjauksen, satunnaisia liukulukuja arpovan luokan ja refaktoroinut sovelluksen siten että käyttäjä voi valita arvottujen (ja tallennettujen) nuottisarjojen pituuksia.

Olen lisännyt examples-kansioon hyvin yksinkertaisen [Sonic Pi:ssä](https://sonic-pi.net/) toimivan [ohjelman](https://github.com/olenleo/TiraLabra--Markov/blob/main/examples/bach_esimerkki.rb) ja sen tuottaman audion [.mp3-muodossa](https://github.com/olenleo/TiraLabra--Markov/blob/main/examples/bach_example.mp3). 


# Miten ohjelma on edistynyt?
Ohjelma on edistynyt melko paljon. Kompastuskysymyksiä on nyt kaksi: nuottien kestot / tauot sekä polyfonia, niistä enemmän alla.


# Mitä opin tällä viikolla / tänään?
[Satunnaislukujen generointi](https://en.wikipedia.org/wiki/Linear_congruential_generator) on näköjään optimoitavissa käyttämällä sopivia [muuttujia](https://arxiv.org/pdf/2001.05304.pdf). En syventynyt tähän sen tarkemmin vielä, mutta mielenkiintoinen ajatus - tämä oli minulle uutta.


# Mikä jäi epäselväksi tai tuottanut vaikeuksia? Vastaa tähän kohtaan rehellisesti, koska saat tarvittaessa apua tämän kohdan perusteella.

Haluaisin totetuttaa ohjelmasta version joka tallettaa trieen nuotti-olioita jotka sisältävät informaatiota nuotin kestosta ja voimakkuudesta. Tämä olisi tehtävissä tehokkaasti hajautustaulujen avulla. Myös taukojen käsittely onnistuisi. 

Projekti tuottaisi tällöin paljon mielenkiintoisempia lopputuloksia. Tällä hetkellä rytmiikka on toteutettava erikseen, eikä se siten viittaa luettuun materiaaliin millään tavalla.

Työmäärä kasvaisi kuitenkin melko paljon midi-informaation käsittelyn myötä. Onko oman hajautustaulun toteutus välttämätöntä ottaen huomioon että olen jo totetuttanut trien? En usko että se olisi mahdotonta, mutta keskittyisin mieluummin käytettävyyteen ja musikaalisempien lopputulosten tuotokseen.

# Mitä teen seuraavaksi?
Dokumentaationi on hyvin puutteellista. Testit ovat myös erittäni minimaalisella tasolla.
Suunnittelin testaamani tehokkuutta luomalla hyvin pitkiä midi-tiedostoja ja testaamalla niiden tallennusta ja -hakua. Vaikuttaako tämä järkevältä? 

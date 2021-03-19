Opinto-ohjelma: TKT, projektin kieli: Suomi

# Kuvaus: 

Toteutetaan proseduraalisesti musiikkia generoiva algoritmi. 

Algoritmi generoi sointukiertoja (koulutettu pop-rock-pohjalta, alustavasti kovakoodattu - toisin sanoen toteutettava ohjelma ei anna mahdollisuutta syöttää uutta koulutusmateriaalia) ja tuottaa melodiaa soittavan merkkijonon joka koostuu midi-nuoteista (kokonaislukuja 0-127) ja nuottien kestosta.

Sovellus vastaanottaa midi-tiedostoja koulutusta varten. Näistä luetaan informaatio erillisessä moduulissa.

Sovellus palauttaa sointukierron ja / tai melodian merkkijonomuodossa jatkokäyttöä varten – esim lisäys haluttuun Digital Audio Workstationiin (DAW) [Reaper](https://www.reaper.fm/sdk/reascript/reascripthelp.html#MIDI_InsertNote)
tai esimerkiksi [Sonic Pi](https://sonic-pi.net/):hin [OSC-protokollan](https://en.wikipedia.org/wiki/Open_Sound_Control) avustuksella

Käyttäjä voi valita haluaako hän pelkkiä sointuja, melodian vai molempia.


# Aikavaativuksista ja algoritmeista
**Kysymys** Telegram-keskustelussa puhuttiin trie-tietorakenteesta markov-ketjujen käsittelyssä. Tämä vaikuttaa lupaavalta, mutta en ole vielä keksinit miten trie-rakennetta sovellettaisiin markov-ketjuista haussa. Nähdäkseni yleinen käytäntö on kaksiulotteisesta matriisista etsiminen. Päivitän siis tätä osuutta tutkittuani asiaa tarkemmin.

Markov-matriisin luonti on aikavaativuudeltaan O(n) – koko merkkijono on luettava kertaalleen läpi.

Tavoiteltu aikavaativuus on O(n); en ainakaan tässä vaiheessa ole keksinyt nopeampaa vaihtoehtoa.

# Suunnitelma: 
*Esiaste* 

Muodostetaan hooktheoryn applikaation kautta sointujen todennäköisyyksiä tallentavia markov-ketjumatriiseja. Tämä vaatii hieman näpertelyä, mutta matriisien ei tarvitse olla kovin laajoja – lähinnä testaamista ja vauhtiin pääsemistä varten. Vaihtoehtona olisi analysoida jotain dataset:iä mutta tätä en osaa tehdä, ja haluan keskittyä ensisijaisesti algoritmin toteutukseen ja valmiin informaation exporttaamiseen. Sointuja sisältävät matriisit tallennetaan sävellajeittain.

*Toinen aste*

Ohjelma on koulutettavissa lukemalla .midi-tiedostoja jFugue-kirjaston metodilla. Tämän pitäisi olla melko helposti toteutettavissa; jFugue tarjoaa metodin joka muuntaa midikäskyn merkkijonoksi. Nämä tallennetaan matriisiin.

*Kolmas aste* 

Ohjelma lukee 2 edellistä nuottia ja valitsee niiden pohjalta seuraavan.Tässä tulee jo haasteita algoritmin toiminnan hahmottamisen suhteen – taulukko lienee kuitenkin järkevin ratkaisu, sillä tarvitsemme nopeat haku- ja talletusominaisuudet, eikä opeteltu materiaali tule kasvamaan jolloin taulukon maksimikoko pysyy staattisena. 

*Neljäs aste* 

Rytmiikan lisääminen. Tämä lisää haasteita midi-tiedoston lukuun; tarvitsemme algoritmin joka laskee kappaleen temmon saadaksemme nuottien kestot.
Tämä lisää uuden ulottuvuuden Markov-matriisiin.


	
# Lähteitä
Päivitän lähdeluetteloa projektin edetessä.


Web-applikaatio joka näyttää seuraavan soinnun todennäköisyyden: https://www.hooktheory.com/trends# 

Markov-ketjujen visualisaatio: https://setosa.io/ev/markov-chains/

Mm. trie-algoritmia hyödyntävä sovellus: https://devpost.com/software/procedural-music-generator

Loistava visualisaatio generatiivisesta musiikista: https://teropa.info/loop/#/additiverhythmgrammar

Improvisaatiota markov-ketjuilla: https://dke.maastrichtuniversity.nl/gm.schoenmakers/wp-content/uploads/2015/09/Linskens-Final-Draft.pdf




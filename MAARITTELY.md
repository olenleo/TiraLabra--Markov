# Kuvaus: 

Toteutetaan proseduraalisesti musiikkia generoiva algoritmi. 
Käyttäjä ilmoittaa sävellajin ja antaa midi-tiedoston tuossa samassa sävellajissa (halutessaan harmonista musiikkia siis).

Algoritmi generoi sointukierron (koulutettu pop-rock-pohjalta, alustavasti kovakoodattu) ja tuottaa merkkijonon joka koostuu midi-nuoteista (kokonaislukuja 0-127) ja mahdollisesti myös nuottien keston. 

Palautus merkkijonomuodossa jatkokäyttöä varten – esim lisäys haluttuun Digital Audio Workstationiin (DAW) [Reaper](https://www.reaper.fm/sdk/reascript/reascripthelp.html#MIDI_InsertNote)
tai esimerkiksi [Sonic Pi](https://sonic-pi.net/):hin [OSC-protokollan](https://en.wikipedia.org/wiki/Open_Sound_Control) avustuksella

Käyttäjä voi valita haluaako hän pelkkiä sointuja, melodian vai molempia.

# Aikavaativuudet 
Palaan näihin mietittyäni tarkemmin

Markov-matriisin luonti on aikavaativuudeltaan O(n) – koko merkkijono on luettava kertaalleen läpi
Haku matriisista (seuraavan nuotin arpominen) O(1) + O(n) jossa n on matriisin koko + satunnaisluvun arpominen

Tavoiteltu aikavaativuus on O(n); en ainakaan tässä vaiheessa ole keksinyt nopeampaa vaihtoehtoa.

# Suunnitelma: 
*Esiaste* 

Muodostetaan hooktheoryn applikaation kautta sointujen todennäköisyyksiä tallentavia markov-ketjumatriiseja.Tämä vaatii hieman näpertelyä, mutta matriisien ei tarvitse olla kovin laajoja – lähinnä testaamista ja vauhtiin pääsemistä varten. Vaihtoehtona olisi analysoida jotain dataset:iä mutta tätä en osaa tehdä, ja haluan keskittyä ensisijaisesti algoritmin toteutukseen ja valmiin informaation exporttaamiseen. Matriisit tallennetaan sävellajeittain.

*Toinen aste*

Ohjelma on koulutettavissa lukemalla .midi-tiedosto jFugue-kirjaston metodilla. Tämän pitäisi olla melko helposti toteutettavissa; jFugue tarjoaa metodin joka muuntaa midikäskyn merkkijonoksi.

*Kolmas aste* 

Ohjelma lukee 2 edellistä nuottia ja valitsee niiden pohjalta seuraavan.Tässä tulee jo haasteita algoritmin toiminnan hahmottamisen suhteen – taulukko lienee kuitenkin järkevin ratkaisu, sillä tarvitsemme nopeat haku- ja talletusominaisuudet, eikä opeteltu materiaali tule kasvamaan jolloin taulukon maksimikoko pysyy staattisena. 

*Neljäs aste* 

Rytmiikan lisääminen. Tämä lisää haasteita midi-tiedoston lukuun; tarvitsemme algoritmin joka laskee kappaleen temmon saadaksemme nuottien kestot.
Tämä lisää uuden ulottuvuuden Markov-matriisiin.

	
# Lähteitä
Päivitän lähdeluetteloa projektin edetessä.
Web-applikaatio joka näyttää seuraavan soinnun todennäköisyyden: https://www.hooktheory.com/trends# 
https://setosa.io/ev/markov-chains/
https://devpost.com/software/procedural-music-generator
Loistava visualisaatio generatiivisesta musiikista: https://teropa.info/loop/#/additiverhythmgrammar


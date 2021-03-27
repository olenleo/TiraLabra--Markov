Käytetty aika: ~12 h

**Mitä olen tehnyt tällä viikolla?**

Olen tutkinut trie-tietorakenteen toiminnallisuutta ja ryhtynyt toteuttamaan yhtä versiota. 

Olen myös tutkinut midi-tiedostojen lukua jFugue-kirjaston avulla. Tämä ei olekkaan aivan yksinkertaista; kirjaston tulostusmetodin formaatti ei kelpaa suoranaiseti ja vaikka jFugue salli esimerkiksi oman parseri-olion luomisen vaikuttaa siltä että tästä osuudesta tulisi turhan työläs. [MidiCSV](https://www.fourmilab.ch/webtools/midicsv/) vaikuttaa lupaavalta vaihtoehdolta; suunnittelen nyt aluksi lataavani opetusmateriaalia tämän avulla.

Pääosin käyttämäni aika on ollut ohjelman yleisen rakenteen suunnittelua ja pähkäilyä trien sovittamisesta siihen. Olen kieltämättä vähän jäljessä aikataulussa tenttilukemisen takia, mutta nyt loppuperiodissa ei onneksi ole turhia kiireitä joten pääsen varmasti jo ensi viikolla mukaan aikatauluun!


# Miten ohjelma on edistynyt?

Turhan vähän edellä mainitun tentin takia. Olen tehnyt trie-tietorakenteen [tämän kirjoituksen](https://dev.to/mlarocca/tries-3ddb) ja siihen liittyvää [esimerkkirepositoriota](https://github.com/mlarocca/AlgorithmsAndDataStructuresInAction/blob/master/Java/src/org/mlarocca/containers/strings/trie/Trie.java) hyödyntäen. Lopullisessa palautuksessa tulen kirjoittamaan tietorakenteen uusiksi ilman muistiinpanoja. Jos löytyy vinkkiä hyvään pseudokoodikuvaukseen algoritmista otan sen kiitollisena vastaan!

Automaattinen testaus jäi ikävä kyllä suorittamatta mutta olen tehnyt manuaalista testausta. Nähdäkseni trieen lisääminen ja haku toimivat yksinkertaisilla syötteillä.

# Mitä opin tällä viikolla / tänään?

Olen ryhtynyt hahmottamaan trien etuja tämänlaisessa ohjelmassa, ja ylesisetikkin.

# Mikä jäi epäselväksi tai tuottanut vaikeuksia? Vastaa tähän kohtaan rehellisesti, koska saat tarvittaessa apua tämän kohdan perusteella.

Trien käyttö esimerkiksi sanoja luodessa tuntuu selkeältä. Musikaalisesta näkökulmasta en ole selvittänyt itselleni vielä miten sanan loppu - trie-algoritmissa vaikkapa <code>boolean muodostaaSanan</code> - olisi hyödynnettävissä, tai josko se on edes välttämätön.

Järkeilen näin:

Oletetaan että koulutusmateriaali koostuu 50 nuotin sarjasta.
Trie-puuhun lisätään nyt ns. polku nuotista 1 -> 2 -> ... -> 50.
Algoritmiin liittyy kuitenkin askel, jossa jokainen materiaalissa esiintyvä siirtymä jostain nuotista *c* johonkin nuottiin *c, c#, d, ... ,b* kirjataan muistiin. Tälle valinnalle lasketaan jokin todennäköisyys.

Jos halutaan loputonta improvisaatiota voisi kehittää toiminnon joka asettaa nyt vuorossa olevan nuotin juureksi ja aloittaa uuden nuottisarjan generöimisen.

Esimerkisi blues-kitaristin 'lick':ien muodostamiseen jonkinlainen sanan / lickin päättävä merkki olisi kuitenkin varmasti hyödyllinen.

# Mitä teen seuraavaksi?

- Aloitan automaattisen testauksen ja toteutan jacoco-rivikattavuuden testauksen. Myös javadoc:it kuntoon! (rästitöitä tältä viikolta)
- Toteutan midi-tiedoston lukemisen jollain tavalla. Tällä hetkellä, jos MidiCSV-ohjelma vaikuttaa tarpeeksi ketterältä vaihtoehdolta, toteutan luokan joka formatoi sen palauttaman .csv-tiedoston järkevään muotoon.
- Muuten jatkan viikon 1 suunnitelmani mukaan, eli teen markov-ketjuja hyödyntävän sointukiertoja generöivan luokan.

# Viikko 4

Huom! Keskeneräinen palautus - päivittelen tätä dokumenttia ja toivottavasti myös ohjelmakoodia illemmalla.

# Mitä olen tehnyt tällä viikolla?

Keskusteltuani Hannun kanssa olen päättänyt yksinkertaistaa tietorakennettani. 

Tietorakenteen periaate on nyt seuraava:
Lisätessä nuottisarja trie ryhtyy tallentamaan taulukoihin TrieNode-olioita. Taulukon indeksi ilmaisee nuotin korkeuden (kokonaisluku 0-127).
Nämä trieNodet pitävät kirjaa lapsistaan, eli mahdollisista seuraavista nuoteista, ja niiden frekvensseistä.

Näin ollen voin tallettaa triee:n paljon helpommin ja tehokkaammin, mutta nuottien kestoja ja mahdollisia taukoja ei saa sisällytettyä tähän rakenteeseen suoraan. 

# Miten ohjelma on edistynyt?

Olen toivottavasti päässyt lähemmäksi ohjelman perustoiminnallisuuden toteutusta.

# Mitä opin tällä viikolla / tänään?


# Mikä jäi epäselväksi tai tuottanut vaikeuksia? Vastaa tähän kohtaan rehellisesti, koska saat tarvittaessa apua tämän kohdan perusteella.

Musiikin generointi tuntuu vielä haastavalta. Joudun sulattelemaan asiaa ja otan yhteyttä ensi viikolla ellen keksi ratkaisua.

# Mitä teen seuraavaksi?

Olen hyvin jäljessä aikataulusta; ensi viikolla käyn läpi tarkasti viikottaisten palautusten vaatimukset ja ryhdyn kuromaan kiinni.
Aloitan käyttöliittymän toteutuksen.

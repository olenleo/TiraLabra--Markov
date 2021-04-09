# Mitä olen tehnyt tällä viikolla?
Trie:n muutos hyväksymään nuotti-olioitani oli ongelmallista.
Joko kyseessä oli yksinkertaisesti painovirhe (esimerkiksi <code><=</code> kun pitäisi olla <code><</code>) tai Note-olioitten ekvivalenssi. 
Tein varmuuden vuoksi oman equals()-metodin, ja ohjelma toimii. Tähän meni kuitenkin luvattoman paljon aikaa. Toisaalta tämän bugin metsästys pakotti minut käymään kaiken koodin läpi hyvin tarkasti ajatuksen kanssa, niin ei se ajan hukkaa ole.
  
# Miten ohjelma on edistynyt?

Ohjelma ei ole ihan edennyt odotusten mukaan. Midi-tiedostojen käsittely ei ollutkaan niin yksinkertaista kun ajattelin. 
Tämä ei kuitenkaan minua haittaa, olen jo jonkin aikaa halunnut tutkia kyseistä protokollaa.

Luulisin että Trie on valmis perusominaisuuksiltaan (lisäys, haku).


# Mitä opin tällä viikolla / tänään?

Nyt trie:n toiminta ja sen hyödyt tuntuvat melko selviltä. Midi-protokollan suhteen opin että ajan ilmaisu onkin yllättävän vaikeaselkoista. 

# Mikä jäi epäselväksi tai tuottanut vaikeuksia? Vastaa tähän kohtaan rehellisesti, koska saat tarvittaessa apua tämän kohdan perusteella.

Uuden nuottisarjan arpominen on vielä itselleni epäselvää. Luulen että tutustun tekstigeneroimiseen ja sovellan löytämiäni metodeja tähän.

Kysymys:
Haku-metodini toteuttaa rekursiivisen haun. Jäin miettimään Arrays.copyOfRange()-metodin järkevyyttä tässä:
```
       public Trie search(Note[] noteArray) {
        if (noteArray.length == 0) {
            return storesKey ? this : null;
        } else { 
            Note note = noteArray[0];
            System.out.println("Etsitään " + note);
            if (children.containsKey(note)) {
                return children.get(note).search(Arrays.copyOfRange(noteArray, 1, noteArray.length));
            } else {
                return null;
            }
        }
    }

```

Ajatukseni on siis että aloittamalla rekursiivisen haun aina seuraavasta askeleesta, eli lyhentämällä nuottisarjaa, säästän aina jonkin verran laskentaa. Haettavan nuotin indeksin antamien haun parametrina lienee myös toinen vaihtoehto.
Tämä on kuitenkin mielestäni helpompi hahmottaa, ja kopioitavat taulut ovat melko pieniä.

Tulisiko minun toteuttaa itse tämä kopiointi vai voinko käyttää Javan tarjoamia metodeja?
Pikaisen google-haun perusteella System.arrayCopy() olisi marginaalisesti tehokkaampi.


# Mitä teen seuraavaksi?

Trie vaatii vielä nuottisarjojen esiintymistiheyden.

Trie sisältää seuraavat valmiit tietorakenteet:
- HashMap
- ArrayDeque

MidiToCsv-ohjelma on toimiva, mutta ulkoisen ohjelman ajo käyttöliittymässä ei ole minulle tuttua. 
Jätän tämän myöhemmäksi; keskityn nyt ydintoiminnallisuuteen.

Koodissa on refaktoroitavaa; notereader-luokka tekee osan trie-toiminnallisuudesta joka mm. vaikeuttaa suhteettomasti testausta. Vaihdan myös muuttujien ja luokkien nimet englanninkielisiksi jotta kaikki olisi yhtenäistä - vai onko suomenkieliset muuttujat käytäntö?

Työmäärä: ~11 tuntia.

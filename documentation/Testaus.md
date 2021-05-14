# Testausdokumentti

## jUnit-yksikkötestaus

Automaattiset yksikkötestit testaavat Trien toiminnallisuutta, nuottien lukua ja markov-ketjutoiminnallisuutta. Testit voisivat kieltämättä olla laajempia (esimerkiksi nuottien kesto ei koskaan saisi olla negatiivinen) mutta nämä kattavat perustoiminnallisuuden. Olen lisäksi tehnyt paljon manuaalista testaamista.


## Tehokkuus
Tehokkuutta mittaava luokka luo 10, 50 ja 100 solmun mittaisia trie-rakenteita. Alla ajat (ms) miljoonan nuottisarjan generointiin kussakin.

Trie lengths
| 10 |50  | 100|
--- | --- | ---
|5894.7185|	30503.1369	|59698.7509|
|5921.5139	|29589.2135|	61957.8063|
|6628.0132|	30689.8894|	58456.3703|
|5955.0493|	28484.9575|58301.3372|
|6010.0534	|29855.8556|	61541.7785|
|6347.1057|29751.2193	|60781.8996|
|6178.8084	|31017.5671|	63284.396|
|6213.8754|	29484.2828|	59046.7838|
|6473.225611|	31571.685421|	61997.625711|
|5963.918228|	28754.710394|	62742.111246|
|6093.878537	|30426.193585|	61696.286738|
|5917.971696|	28507.25745|	62167.533381|
|6449.818047	|31539.361798	|59077.535936|
|5939.521671|	30036.941786	|61984.616529|
|6066.982389	|28897.148596	|59890.882963|


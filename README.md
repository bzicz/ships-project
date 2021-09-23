# ships-project

Obszar symulacji to płaska powierzchnia, po której poruszają się statki, przewożące ładunek między portami. Podróż odbywa się po linii prostej do portu docelowego. Ponadto można całkowicie pominąć problem wykrywania kolizji – statki nie muszą się omijać, ponieważ są traktowane jako punkt na płaszczyźnie. Sposób sterowania symulacją następuje automatycznie.

Każdy statek ma określone następujące właściwości:

aktualną pozycję lub port, jeśli jest zadokowany, ładowność, wyrażoną w ilości kontenerów, jakie może przewieźć, aktualną i maksymalną ilość paliwa, stopień zużycia, wskazujący stan techniczny statku.

Statek zadokowany w porcie może pobierać i wyładowywać kontenery, a także tankować. Podczas podróży statek z każdą iteracją ulega stopniowemu zużyciu, przy czym jeśli wartość reprezentująca stan techniczny spadnie do zera, statek ulega zniszczeniu i tonie razem z ładunkiem. Napraw dokonuje się w stoczni.

Port
Statki zawijają do portów i z nich wyruszają, zdając lub zabierając ładunek. Port posiada:

Jeden lub więcej doków, magazyn kontenerów do załadowania na statek, magazyn kontenerów odbieranych ze statków, magazyn paliwa dla statków.

Stocznia
Jest to miejsce, gdzie dokonuje się napraw istniejących statków oraz zamówień nowych. Oczywiście koszty naprawy są znacznie niższe od zakupu nowej jednostki, nawet w przypadku prawie całkowitego zużycia. Podobnie jak port, stocznia posiada magazyn paliwa oraz określoną ilość doków, w których konstruuje się lub naprawia statki. Okręt przybywający do stoczni musi poczekać na wolny dok, jeśli wszystkie są zajęte (naprawa / budowa).

Armator
W symulacji trzeba uwzględnić posiadaczy floty. Każdy statek należy do floty jednego właściciela. Armator ma określony na początku symulacji kapitał, który może powiększać, zarabiając na przewozie kontenerów. Do kompetencji armatora zalicza się:

wybór kontenerów z portu do przewiezienia danym statkiem, wybór portu docelowego, konserwację statku, zakup paliwa dla statku, zamówienie okrętu o pożądanej specyfikacji, oddanie jednostki do rozbiórki.

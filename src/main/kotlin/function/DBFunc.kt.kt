package function

import db
import entity.DBlaptop
import entity.Laptop
import komunikat
import laptopy
import org.jetbrains.exposed.sql.transactions.transaction
import tornadofx.asObservable

//funkcja przekazująca dane z bazy do widoku aplikacji
//dla każdego obiektu bazy danych tworzy się obiekt widoku
fun czytajBD() {
    var liczbaRekordow = 0
    val stanListy = laptopy.size
    var duplikaty = 0

    //połączenie z bazą
    transaction(db) {
        DBlaptop.all().forEach {

            //sprawdzenie, czy lista w widoku aplikacji nie jest pusta
            if(stanListy > 0) {

                //sprawdzanie czy laptop już jest na liście
                //jeśli nie ma laptopu na liście, dodaje go
                if (!laptopy.any { l -> l.takiSam(it) })
                    laptopy.add(Laptop(listOf(
                        it.producent,
                        it.przekatna,
                        it.rozdzielczosc,
                        it.powierzchnia,
                        if (it.dotykowy) "tak" else "nie",
                        it.procesor,
                        it.rdzenie.toString(),
                        it.taktowanie,
                        it.ram,
                        it.pojdysk,
                        it.rodzdysk,
                        it.grafika,
                        it.pamgraf,
                        it.sysop,
                        it.naped
                    )))

                //gdy jakiś laptop już jest na liście, zaznacza wszystkie identyczne instancje jako duplikat
                else {
                    laptopy.filter { l -> l.takiSam(it) }.forEach { f -> f.duplikat = true }
                    duplikaty++
                }
            }

            //jeśli widok aplikacji jest pusty to nie sprawdza duplikatów przy dodawaniu
            else laptopy.add(Laptop(listOf(
                it.producent,
                it.przekatna,
                it.rozdzielczosc,
                it.powierzchnia,
                if (it.dotykowy) "tak" else "nie",
                it.procesor,
                it.rdzenie.toString(),
                it.taktowanie,
                it.ram,
                it.pojdysk,
                it.rodzdysk,
                it.grafika,
                it.pamgraf,
                it.sysop,
                it.naped
            )))
        }

        //pobranie liczby rekordów z połączenia musi byc w bloku transaction(){}
        liczbaRekordow = DBlaptop.all().count().toInt()
    }
    komunikat.set("Wczytano bazę danych. " +
            "Znaleziono ${liczbaRekordow-duplikaty} unikatowe rekordy i $duplikaty duplikaty")
}

//funkcja przekazująca dane z widoku aplikacji do bazy
//dla każdego obiektu widoku tworzy się obiekt bazy danych
fun zapiszBD() {

    //połączenie z bazą
    transaction(db) {
        laptopy.forEach {
            DBlaptop.new {
                producent = it.producent
                przekatna = it.przekatna
                rozdzielczosc = it.rozdzielczosc
                powierzchnia = it.powierzchnia
                dotykowy = it.dotykowy == "tak"
                procesor = it.procesor
                rdzenie = if(it.rdzenie != "") it.rdzenie.toInt() else 0
                taktowanie = it.taktowanie
                ram = it.ram
                pojdysk = it.pojdysk
                rodzdysk = it.rodzdysk
                grafika = it.grafika
                pamgraf = it.pamgraf
                sysop = it.sysop
                naped = it.naped
            }
        }
    }
    komunikat.set("Zapisano do bazy danych")
}
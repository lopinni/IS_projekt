import entity.Laptop
import tornadofx.asObservable
import java.io.File

//funkcja pobierająca dane z pliku po liniach
//najpierw czyści listę żeby nie duplikować danych
fun czytajPlik() {
    val temp = mutableListOf<Laptop>().asObservable()
    val stanListy = laptopy.size
    var duplikaty = 0

    //czytanie z pliku do listy tymczasowej
    if(!File("katalog.txt").exists()) createTXT() //FileCreate.kt
    File("katalog.txt").bufferedReader().useLines { linie ->
        linie.forEach { ciag -> temp.add(Laptop(ciag.split(";"))) }
    }

    temp.forEach { l ->

        //sprawdzenie, czy lista w widoku aplikacji nie jest pusta
        if(stanListy > 0) {

            //sprawdzanie czy laptop już jest na liście
            if(!laptopy.any { it.takiSam(l) } ) laptopy.add(l)

            //gdy jakiś laptop już jest na liście, zaznacza wszystkie identyczne instancje jako duplikat
            else {
                laptopy.filter { it.takiSam(l) }.forEach { it.duplikat = true }
                duplikaty++
            }
        }

        //jeśli widok aplikacji jest pusty to nie sprawdza duplikatów przy dodawaniu
        else laptopy.add(l)
    }
    komunikat.set("Wczytano plik TXT. Znaleziono ${temp.size-duplikaty} unikatowe rekordy i $duplikaty duplikaty")
}

//funkcja zapisu do pliku
//kasuje plik o tej nazwie jak istnieje, potem zapisuje dane po liniach
fun zapiszPlik(){
    File("katalog.txt").delete()
    File("katalog.txt").bufferedWriter().use { wyjscie ->
        laptopy.forEach { l ->
            wyjscie.write(l.producent + ";" + l.przekatna + ";" + l.rozdzielczosc + ";" +
                            l.powierzchnia + ";" + l.dotykowy + ";" + l.procesor + ";" +
                            l.rdzenie + ";" + l.taktowanie + ";" + l.ram + ";" +
                            l.pojdysk + ";" + l.rodzdysk + ";" + l.grafika + ";" +
                            l.pamgraf + ";" + l.sysop + ";" + l.naped + ";" + "\n")
        }
    }
    komunikat.set("Zapisano do pliku TXT")
}
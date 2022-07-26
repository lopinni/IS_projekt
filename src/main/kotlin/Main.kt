import entity.*
import function.*
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import tornadofx.*

//lista laptopów, asObservable() daje kolekcjom możliwość interakcji z obiektami interfejsu
var laptopy = mutableListOf<Laptop>().asObservable()

//połączenie z bazą danych
var db = Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")

//komunikat z liczbą duplikatów
var komunikat = SimpleStringProperty("Tu będzie tekst o duplikatach")

//klasa okna, zawiera wszystkie obiekty interfejsu
class MyView: View("Integracja Systemów Maciej Sójka") {

    //tabela z danymi, definiowana wcześniej jako zmienna żeby mogła się aktualizować
    //elementy listy laptopów są rekordami w tabeli
    //obiekty kolumn pobierają dane używając getProperty()
    //po uruchomieniu programu od razu zczytywane są dane z pliku
    var tabela = tableview<Laptop> {
        isEditable = true
        czytajPlik() //TXTFunc.kt
        items = laptopy
        column("Producent", Laptop::producentProperty)
            .makeEditable()
        column("Przekątna", Laptop::przekatnaProperty)
            .makeEditable()
        column("Rozdzielczość", Laptop::rozdzielczoscProperty)
            .makeEditable()
        column("Powierzchnia", Laptop::powierzchniaProperty)
            .makeEditable().useComboBox(listOf("matowa", "blyszczaca").asObservable())
        column("Dotykowy", Laptop::dotykowyProperty)
            .makeEditable().useComboBox(listOf("tak", "nie").asObservable())
        column("Procesor", Laptop::procesorProperty)
            .makeEditable()
        column("Liczba rdzeni", Laptop::rdzenieProperty)
            .makeEditable()
        column("Taktowanie", Laptop::taktowanieProperty)
            .makeEditable()
        column("RAM", Laptop::ramProperty)
            .makeEditable()
        column("Dysk", Laptop::pojdyskProperty)
            .makeEditable()
        column("Typ dysku", Laptop::rodzdyskProperty)
            .makeEditable().useComboBox(listOf("HDD", "SSD").asObservable())
        column("Grafika", Laptop::grafikaProperty)
            .makeEditable()
        column("Pamięć grafiki", Laptop::pamgrafProperty)
            .makeEditable()
        column("System operacyjny", Laptop::sysopProperty)
            .makeEditable()
        column("Napęd", Laptop::napedProperty)
            .makeEditable().useComboBox(listOf("DVD", "Blu-Ray", "brak").asObservable())
        column("Braki", Laptop::brakdanychProperty).cellFormat {
            text = it.toString()
            style {
                if (it != "brak") {
                    backgroundColor += c("#8b7777")
                    textFill = Color.WHITE
                }
            }
        }
        column("Błędy formatu", Laptop::bladdanychProperty).cellFormat {
            text = it.toString()
            style {
                if (it != "brak") {
                    backgroundColor += c("#8b8b77")
                    textFill = Color.WHITE
                }
            }
        }
        column("Duplikat", Laptop::duplikatProperty).cellFormat {
            text = if(it == true) "tak" else "nie"
            style {
                if (it == true) {
                    backgroundColor += c("#ff6969")
                    textFill = Color.WHITE
                }
            }
        }
    }

    //stała "root" jest obiektem bazowym interfejsu
    override val root = borderpane {

        //panel z przyciskami
        top = hbox {
            button("Wyczyść tabelę") { action {
                laptopy.clear()
                komunikat.set("Wyczyszczono tabelę")
            } }
            button("Pobierz z pliku") { action {
                czytajPlik() //TXTFunc.kt
            } }
            button("Eksportuj do pliku") { action {
                zapiszPlik() //TXTFunc.kt
            } }
            button("Pobierz z XML") { action {
                czytajXML() //XMLFunc.kt
            } }
            button("Eksportuj do XML") { action {
                zapiszXML() //XMLFunc.kt
            } }
            button("Pobierz z bazy danych") { action {
                czytajBD() //DBFunc.kt
            } }
            button("Eksportuj do bazy danych") { action {
                zapiszBD() //DBFunc.kt
            } }

            //tekst o duplikatach
            label(komunikat){
                style {
                    fontWeight = FontWeight.EXTRA_BOLD
                    borderColor += box(
                        top = Color.RED,
                        right = Color.DARKGREEN,
                        left = Color.ORANGE,
                        bottom = Color.PURPLE
                    )
                    paddingTop = 3
                    paddingBottom = 3
                    paddingLeft = 9
                    paddingRight = 9
                }
            }
        }

        //wstawienie tabeli
        center = tabela
    }
}

//przypisanie klasy interfejsu do aplikacji
class MyApp: App(MyView::class)

fun main(args: Array<String>) {

    //stworzenie tabeli bazy danych
    transaction(db) { SchemaUtils.create (Laptops) }

    //uruchomienie aplikacji
    launch<MyApp>(args)
}
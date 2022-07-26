import entity.Laptop
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import tornadofx.asObservable
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.stream.XMLOutputFactory
import javax.xml.stream.XMLStreamWriter

//funkcja pobiera dane z XML-a i dodaje do listy laptopów
fun czytajXML(){
    val temp = mutableListOf<Laptop>().asObservable()
    val stanListy = laptopy.size
    var duplikaty = 0

    //inicjalizacja pliku XML
    if(!File("katalog.xml").exists()) createXML() //FileCreate.kt
    val xmlDoc: Document = DocumentBuilderFactory
                            .newInstance()
                            .newDocumentBuilder()
                            .parse(File("katalog.xml"))
    xmlDoc.documentElement.normalize()

    //pobieranie danych
    val laptopNodes: NodeList = xmlDoc.getElementsByTagName("laptop")
    for (i in 0 until laptopNodes.length) {
        val elem = laptopNodes.item(i) as Element

        //niektóre elementy są zagnieżdżone lub zawierają parametry
        val ekran = elem.getElementsByTagName("screen").item(0) as Element
        val proc = elem.getElementsByTagName("processor").item(0) as Element
        val rdysk = elem.getElementsByTagName("disc").item(0) as Element
        val graf = elem.getElementsByTagName("graphic_card").item(0) as Element

        //zapis nowego laptopa do listy tymczasowej
        temp.add(Laptop(listOf(
            elem.getElementsByTagName("manufacturer").item(0).textContent,
            elem.getElementsByTagName("size").item(0).textContent,
            elem.getElementsByTagName("resolution").item(0).textContent,
            waliduj(elem.getElementsByTagName("type").item(0).textContent),
            waliduj(sprawdzParametr(ekran)),
            proc.getElementsByTagName("name").item(0).textContent,
            proc.getElementsByTagName("physical_cores").item(0).textContent,
            proc.getElementsByTagName("clock_speed").item(0).textContent,
            elem.getElementsByTagName("ram").item(0).textContent,
            elem.getElementsByTagName("storage").item(0).textContent,
            sprawdzParametr(rdysk),
            graf.getElementsByTagName("name").item(0).textContent,
            graf.getElementsByTagName("memory").item(0).textContent,
            elem.getElementsByTagName("os").item(0).textContent,
            elem.getElementsByTagName("disc_reader").item(0).textContent
        )))
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
    komunikat.set("Wczytano plik XML. Znaleziono ${temp.size-duplikaty} unikatowe rekordy i $duplikaty duplikaty")
}

//funkcja poprawia dane z XML-a
fun waliduj(tekst: String): String {
    return when (tekst) {
        "yes" -> "tak"
        "no" -> "nie"
        "matowy" -> "matowa"
        else -> tekst
    }
}

//funkcja sprawdzająca czy element ma pusty parametr
//jeśli tak, wyrzuca pusty tekst
fun sprawdzParametr(el: Element): String {
    return if(el.attributes.item(0) == null ) ""
    else el.attributes.item(0).textContent
}

//funkcja zapisuje laptopy do XML
fun zapiszXML(){
    var i = 0

    //inicjalizacja pliku
    File("katalog.xml").delete()
    val outputStream: OutputStream = FileOutputStream(File("katalog.xml"))

    //pobranie czasu zapisu
    val dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val dtf2 = DateTimeFormatter.ofPattern("HH:mm")
    val now = LocalDateTime.now()

    //tworzenie obiektu wyjścia
    //obiekt out automatycznie "napisze się" do pliku
    val out = XMLOutputFactory.newInstance().createXMLStreamWriter(
                OutputStreamWriter(outputStream, "utf-8")).XML {
        element("laptops") { atrybut("moddate", dtf.format(now) + " T " + dtf2.format(now))
            laptopy.forEach {
                element("laptop") { atrybut("id", i.toString())
                    element("manufacturer", it.producent)
                    element("screen") { atrybut("touch", it.dotykowy)
                        element("size", it.przekatna)
                        element("resolution", it.rozdzielczosc)
                        element("type", it.powierzchnia)
                    }
                    element("processor") {
                        element("name", it.procesor)
                        element("physical_cores", it.rdzenie)
                        element("clock_speed", it.taktowanie)
                    }
                    element("ram", it.ram)
                    element("disc") { atrybut("type", it.rodzdysk)
                        element("storage", it.pojdysk)
                    }
                    element("graphic_card") {
                        element("name", it.grafika)
                        element("memory", it.pamgraf)
                    }
                    element("os", it.sysop)
                    element("disc_reader", it.naped)
                }
                i++
            }
        }
    }

    //zamknięcie pliku
    out.close()
    komunikat.set("Zapisano do pliku XML")
}

//poniższe funkcje są rozszerzeniem obiektu XMLStreamWriter
//sprawiają, że kod w zapiszXML() jest bardziej czytelny

//funkcja inicjalizująca plik XML
fun XMLStreamWriter.XML(init: XMLStreamWriter.() -> Unit): XMLStreamWriter {
    this.writeStartDocument()
    this.init()
    this.writeEndDocument()
    return this
}

//funkcja tworząca element XML, bez danych w środku
fun XMLStreamWriter.element(nazwa: String, init: XMLStreamWriter.() -> Unit): XMLStreamWriter {
    this.writeCharacters(System.getProperty("line.separator"))
    this.writeStartElement(nazwa)
    this.init()
    this.writeEndElement()
    return this
}

//funkcja tworząca element XML razem z danymi w środku
fun XMLStreamWriter.element(nazwa: String, parametr: String) {
    element(nazwa) { writeCharacters(parametr) }
}

//funkcja dodająca atrybut do elementu
fun XMLStreamWriter.atrybut(name: String, value: String) = writeAttribute(name, value)
package entity

import tornadofx.getProperty
import tornadofx.property

class Laptop(argumenty: List<String>, dup: Boolean = false) {

    var producent: String by property(argumenty[0])
    var przekatna: String by property(argumenty[1])
    var rozdzielczosc: String by property(argumenty[2])
    var powierzchnia: String by property(argumenty[3])
    var dotykowy: String by property(argumenty[4])
    var procesor: String by property(argumenty[5])
    var rdzenie: String by property(argumenty[6])
    var taktowanie: String by property(argumenty[7])
    var ram: String by property(argumenty[8])
    var pojdysk: String by property(argumenty[9])
    var rodzdysk: String by property(argumenty[10])
    var grafika: String by property(argumenty[11])
    var pamgraf: String by property(argumenty[12])
    var sysop: String by property(argumenty[13])
    var naped: String by property(argumenty[14])
    var brakdanych: String by property(argumenty[0])
    var bladdanych: String by property(argumenty[1])
    var duplikat: Boolean by property(dup)

    fun producentProperty() = getProperty(Laptop::producent)
    fun przekatnaProperty() = getProperty(Laptop::przekatna)
    fun rozdzielczoscProperty() = getProperty(Laptop::rozdzielczosc)
    fun powierzchniaProperty() = getProperty(Laptop::powierzchnia)
    fun dotykowyProperty() = getProperty(Laptop::dotykowy)
    fun procesorProperty() = getProperty(Laptop::procesor)
    fun rdzenieProperty() = getProperty(Laptop::rdzenie)
    fun taktowanieProperty() = getProperty(Laptop::taktowanie)
    fun ramProperty() = getProperty(Laptop::ram)
    fun pojdyskProperty() = getProperty(Laptop::pojdysk)
    fun rodzdyskProperty() = getProperty(Laptop::rodzdysk)
    fun grafikaProperty() = getProperty(Laptop::grafika)
    fun pamgrafProperty() = getProperty(Laptop::pamgraf)
    fun sysopProperty() = getProperty(Laptop::sysop)
    fun napedProperty() = getProperty(Laptop::naped)
    fun brakdanychProperty() = getProperty(Laptop::brakdanych)
    fun bladdanychProperty() = getProperty(Laptop::bladdanych)
    fun duplikatProperty() = getProperty(Laptop::duplikat)

    init {
        this.producent = argumenty[0]
        this.przekatna = argumenty[1]
        this.rozdzielczosc = argumenty[2]
        this.powierzchnia = argumenty[3]
        this.dotykowy = argumenty[4]
        this.procesor = argumenty[5]
        this.rdzenie = if(argumenty[6] != "") argumenty[6] else "0"
        this.taktowanie = argumenty[7]
        this.ram = argumenty[8]
        this.pojdysk = argumenty[9]
        this.rodzdysk = argumenty[10]
        this.grafika = argumenty[11]
        this.pamgraf = argumenty[12]
        this.sysop = argumenty[13]
        this.naped = argumenty[14]
        this.brakdanych = zliczBraki()
        this.bladdanych = zliczBledy()
    }

    //funkcja zwracająca komunikaty o pustych polach
    //#BD to "Brak Danych"
    fun zliczBraki(): String {
        var tekst = ""
        if(this.producent == "") tekst += "#Zły typ przy [producent]\n"
        if(this.przekatna == "") tekst += "#BD przy [przekątna]\n"
        if(this.rozdzielczosc == "") tekst += "#BD przy [rozdzielczość]\n"
        if(this.powierzchnia == "") tekst += "#BD przy [powierzchnia]\n"
        if(this.dotykowy == "") tekst += "#BD przy [dotykowy]\n"
        if(this.procesor == "") tekst += "#BD przy [procesor]\n"
        if(this.rdzenie == "") tekst += "#BD przy [l. rdzeni]\n"
        if(this.taktowanie == "") tekst += "#BD przy [taktowanie]\n"
        if(this.ram == "") tekst += "#BD przy [ram]\n"
        if(this.pojdysk == "") tekst += "#BD przy [dysk]\n"
        if(this.rodzdysk == "") tekst += "#BD przy [typ dysku]\n"
        if(this.grafika == "") tekst += "#BD przy [grafika]\n"
        if(this.pamgraf == "") tekst += "#BD przy [pam. grafiki]\n"
        if(this.sysop == "") tekst += "#BD przy [sys. op.]\n"
        if(this.naped == "") tekst += "#BD przy [napęd]\n"
        return if(tekst == "") "brak" else tekst
    }

    //funkcja zwracająca komunikaty o błędnie sformatowanych polach
    //#BFD to "Błąd Formatu Danych"
    fun zliczBledy(): String {
        var tekst = ""
        if(!this.przekatna.matches(Regex("\\d+\""))) tekst += "#BFD przy [przekątna]\n"
        if(!this.rozdzielczosc.matches(Regex("\\d+x\\d+"))) tekst += "#BFD przy [rozdzielczość]\n"
        if(!this.rdzenie.matches(Regex("\\d+"))) tekst += "#BFD przy [l. rdzeni]\n"
        if(!this.taktowanie.matches(Regex("\\d+"))) tekst += "#BFD przy [taktowanie]\n"
        if(!this.ram.matches(Regex("\\d+GB"))) tekst += "#BFD przy [ram]\n"
        if(!this.pojdysk.matches(Regex("\\d+GB"))) tekst += "#BFD przy [dysk]\n"
        if(!this.pamgraf.matches(Regex("\\d+GB"))) tekst += "#BFD przy [pam. grafiki]\n"
        return if(tekst == "") "brak" else tekst
    }

    //funkcja sprawdzająca, czy dwa laptopy są takie same
    fun takiSam(l: Laptop): Boolean {
        return l.producent == this.producent &&
                l.przekatna == this.przekatna &&
                l.rozdzielczosc == this.rozdzielczosc &&
                l.powierzchnia == this.powierzchnia &&
                l.dotykowy == this.dotykowy &&
                l.procesor == this.procesor &&
                l.rdzenie == this.rdzenie &&
                l.taktowanie == this.taktowanie &&
                l.ram == this.ram &&
                l.pojdysk == this.pojdysk &&
                l.rodzdysk == this.rodzdysk &&
                l.grafika == this.grafika &&
                l.pamgraf == this.pamgraf &&
                l.sysop == this.sysop &&
                l.naped == this.naped
    }

    //funkcja sprawdzająca, czy laptop z listy jest taki sam co pobrany z bazy
    fun takiSam(l: DBlaptop): Boolean {
        val dotykowy = if(l.dotykowy) "tak" else "nie"
        return l.producent == this.producent &&
                l.przekatna == this.przekatna &&
                l.rozdzielczosc == this.rozdzielczosc &&
                l.powierzchnia == this.powierzchnia &&
                dotykowy == this.dotykowy &&
                l.procesor == this.procesor &&
                l.rdzenie.toString() == this.rdzenie &&
                l.taktowanie == this.taktowanie &&
                l.ram == this.ram &&
                l.pojdysk == this.pojdysk &&
                l.rodzdysk == this.rodzdysk &&
                l.grafika == this.grafika &&
                l.pamgraf == this.pamgraf &&
                l.sysop == this.sysop &&
                l.naped == this.naped
    }
}


package entity

import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.*
import tornadofx.property

object Laptops: IntIdTable() {
    var producent = varchar("producent", 50)
    var przekatna = varchar("przekatna", 50)
    var rozdzielczosc = varchar("rozdzielczosc", 50)
    var powierzchnia = varchar("powierzchnia", 50)
    var dotykowy = bool("dotykowy")
    var procesor = varchar("procesor", 50)
    var rdzenie = integer("liczba_rdzeni")
    var taktowanie = varchar("taktowanie", 50)
    var ram = varchar("ram", 50)
    var pojdysk = varchar("pojemnosc_dysku", 50)
    var rodzdysk = varchar("rodzaj_dysku", 50)
    var grafika = varchar("grafika", 50)
    var pamgraf = varchar("pamiec grafiki", 50)
    var sysop = varchar("system operacyjny", 50)
    var naped = varchar("naped", 50)
}

class DBlaptop(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<DBlaptop>(Laptops)

    var producent by Laptops.producent
    var przekatna by Laptops.przekatna
    var rozdzielczosc by Laptops.rozdzielczosc
    var powierzchnia by Laptops.powierzchnia
    var dotykowy by Laptops.dotykowy
    var procesor by Laptops.procesor
    var rdzenie by Laptops.rdzenie
    var taktowanie by Laptops.taktowanie
    var ram by Laptops.ram
    var pojdysk by Laptops.pojdysk
    var rodzdysk by Laptops.rodzdysk
    var grafika by Laptops.grafika
    var pamgraf by Laptops.pamgraf
    var sysop by Laptops.sysop
    var naped by Laptops.naped
}
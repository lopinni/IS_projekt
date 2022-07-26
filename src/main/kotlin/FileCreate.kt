import java.io.File

//funkcja tworzy plik katalog.txt, razem z domyślną zawartością
fun createTXT() {
    val tekst = listOf(
        "Dell;12\";;matowa;nie;intel i7;4;2800;8GB;240GB;SSD;intel HD Graphics 4000;1GB;Windows 7 Home;;",
        "Asus;14\";1600x900;matowa;nie;intel i5;4;;16GB;120GB;SSD;intel HD Graphics 5000;1GB;;brak;",
        "Fujitsu;14\";1920x1080;blyszczaca;tak;intel i7;8;1900;24GB;500GB;HDD;intel HD Graphics 520;1GB;brak systemu;Blu-Ray;",
        "Huawei;13\";;matowa;nie;intel i7;4;2400;12GB;24GB;HDD;NVIDIA GeForce GTX 1050;;;brak;",
        "MSI;17\";1600x900;blyszczaca;tak;intel i7;4;3300;8GB;60GB;SSD;AMD Radeon Pro 455;1GB;Windows 8.1 Profesional;DVD;",
        "Dell;;1280x800;matowa;nie;intel i7;4;2800;8GB;240GB;SSD;;;Windows 7 Home;brak;",
        "Asus;14\";1600x900;matowa;nie;intel i5;4;2800;;120GB;SSD;intel HD Graphics 5000;1GB;Windows 10 Home;;",
        "Fujitsu;15\";1920x1080;blyszczaca;tak;intel i7;8;2800;24GB;500GB;HDD;intel HD Graphics 520;;brak systemu;Blu-Ray;",
        "Samsung;13\";1366x768;matowa;nie;intel i7;4;2800;12GB;24GB;HDD;NVIDIA GeForce GTX 1050;1GB;Windows 10 Home;brak;",
        "Sony;16\";;blyszczaca;tak;intel i7;4;2800;8GB;;;AMD Radeon Pro 455;1GB;Windows 7 Profesional;DVD;",
        "Samsung;12\";1280x800;matowa;nie;intel i7;;2120;;;;intel HD Graphics 4000;1GB;;brak;",
        "Samsung;14\";1600x900;matowa;nie;intel i5;;;;;SSD;intel HD Graphics 5000;1GB;Windows 10 Home;brak;",
        "Fujitsu;15\";1920x1080;blyszczaca;tak;intel i7;8;2800;24GB;500GB;HDD;intel HD Graphics 520;;brak systemu;Blu-Ray;",
        "Huawei;13\";1366x768;matowa;nie;intel i7;4;3000;;24GB;HDD;NVIDIA GeForce GTX 1050;;Windows 10 Home;brak;",
        "MSI;17\";1600x900;blyszczaca;tak;intel i7;4;9999;8GB;60GB;SSD;AMD Radeon Pro 455;1GB;Windows 7 Profesional;;",
        "Huawei;14\";;matowa;nie;intel i7;4;2200;8GB;16GB;HDD;NVIDIA GeForce GTX 1080;;;brak;",
        "MSI;17\";1600x900;blyszczaca;tak;intel i7;4;3300;8GB;60GB;SSD;AMD Radeon Pro 455;1GB;;;",
        "Asus;;1600x900;blyszczaca;tak;intel i5;2;3200;16GB;320GB;HDD;;;Windows 7 Home;brak;",
        "Asus;14\";1600x900;matowa;nie;intel i5;4;2800;;120GB;SSD;intel HD Graphics 5000;1GB;Windows 10 Profesional;;",
        "Fujitsu;14\";1280x800;blyszczaca;tak;intel i7;8;2800;24GB;500GB;HDD;intel HD Graphics 520;;brak systemu;Blu-Ray;",
        "Samsung;12\";1600x900;;nie;intel i5;4;2800;12GB;24GB;HDD;NVIDIA GeForce GTX 1050;1GB;Windows 8.1 Home;brak;",
        "Sony;11\";;blyszczaca;tak;intel i7;4;2800;8GB;;;AMD Radeon Pro 455;1GB;Windows 7 Profesional;brak;",
        "Samsung;13\";1366x768;;nie;intel i5;;2120;;;;intel HD Graphics 4000;2GB;;DVD;",
        "Samsung;15\";1920x1080;matowa;nie;intel i9;;;;;SSD;intel HD Graphics 4000;2GB;Windows 10 Profesional;Blu-Ray;"
    )
    File("katalog.txt").bufferedWriter().use { wyjscie ->
        tekst.forEach { wyjscie.write(it + "\n") }
    }
}

//funkcja tworzy plik katalog.xml, razem z domyślną zawartością
fun createXML() {
    val tekst = listOf(
        "<laptops moddate=\"2015-10-30 T 10:15\">", "<laptop id=\"1\">", "<manufacturer>Asus</manufacturer>",
        "<screen touch=\"no\">", "<size>12\"</size>", "<resolution>1600x900</resolution>", "<type>matowy</type>",
        "</screen>", "<processor>", "<name>i7</name>", "<physical_cores>8</physical_cores>",
        "<clock_speed>3200</clock_speed>", "</processor>", "<ram>8GB</ram>", "<disc type=\"SSD\">",
        "<storage>240GB</storage>", "</disc>", "<graphic_card>", "<name>intel HD Graphics 4000</name>",
        "<memory>1GB</memory>", "</graphic_card>", "<os>Windows 7 Home</os>", "<disc_reader>Blu-Ray</disc_reader>",
        "</laptop>", "<laptop id=\"2\">", "<manufacturer>Dell</manufacturer>", "<screen touch=\"yes\">",
        "<size>16\"</size>", "<resolution>1376x768</resolution>", "<type/>", "</screen>", "<processor>",
        "<name>i5</name>", "<physical_cores>4</physical_cores>", "<clock_speed/>", "</processor>", "<ram>16GB</ram>",
        "<disc>", "<storage>120GB</storage>", "</disc>", "<graphic_card>", "<name>intel HD Graphics 5000</name>",
        "<memory>2GB</memory>", "</graphic_card>", "<os>Windows Vista</os>", "<disc_reader>brak</disc_reader>",
        "</laptop>", "</laptops>",
    )
    File("katalog.xml").bufferedWriter().use { wyjscie ->
        tekst.forEach { wyjscie.write(it + "\n") }
    }
}

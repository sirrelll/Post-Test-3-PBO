# Darel Prasetya Fawwaz | 2409116064 | Sistem Informasi B 24
================================================================
# Post Test 5 PBO
Di Post Test 5 ini saya membuat class tambahan yaitu Database sebagia penghubung ke SQL Database,
Dan saya mengembangkan CRUD & ORM sesuai dengan ketentuan di Goggle CLassroom

**Pembuatan Database**


<img width="237" height="73" alt="Screenshot 2025-10-10 143056" src="https://github.com/user-attachments/assets/6230bd09-1c1f-4612-af7b-eec8226eaf5a" />


Data yang sudah ada di database.
<img width="1019" height="334" alt="Screenshot 2025-10-09 234159" src="https://github.com/user-attachments/assets/9c5ff352-c5d4-44b7-a7b8-866110b2839b" />

```java
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final static String DB_HOST = "localhost";
    private final static String DB_NAME = "konserdb";
    private final static String DB_USERNAME = "root";
    private final static String DB_PASSWORD = ""; 

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load driver: " + e.getMessage());
        }
    }

    public static Connection connect() throws SQLException {
        String url = "jdbc:mysql://" + DB_HOST + "/" + DB_NAME;
        return DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
    }

}
 ```



# Post Test 4 PBO

Di Post Test 4 ini saya melakukan beberapa tambahan sesuai dengan ketentuan di Goggle Classroom:
- Tambahan Class yaitu Reservasi yang saya jadikan sebagai interface
- Class Event dijadikan Abstract Class
- KonserEvent dan PrivateKonser ditambahkan implements Reservasi

```java
  package models;

public interface Reservasi {
    void reservasi();  
}
```

Saya membuat class baru yaitu reservasi yang dijadikan sebagai interface.

Class KonserEvent
```java
  package models;

  public class KonserEvent extends Event implements Reservasi {
      private String artis;

    public KonserEvent(String nama, String tanggal, String venue, String artis) {
        super(nama, tanggal, venue);
        this.artis = artis;
    }

    // Getter & Setter
    
    public String getArtis() { 
        return artis; 
    }
    
    public void setArtis(String artis) { 
        this.artis = artis; 
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Nama   : " + getNama());
        System.out.println("Tanggal: " + getTanggal());
        System.out.println("Venue  : " + getVenue());
        System.out.println("Artis  : " + artis);
    }

    @Override
    public void reservasi() {
        System.out.println("Reservasi tiket konser artis " + artis + " berhasil!");
    }
```
Class PrivateKonser
```java
package models;

public class PrivateKonser extends Event implements Reservasi {
    private int kapasitas;

    public PrivateKonser(String nama, String tanggal, String venue, int kapasitas) {
        super(nama, tanggal, venue);
        this.kapasitas = kapasitas;
    }


  
    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Nama     : " + getNama());
        System.out.println("Tanggal  : " + getTanggal());
        System.out.println("Venue    : " + getVenue());
        System.out.println("Kapasitas: " + kapasitas);
    }

    @Override
    public void reservasi() {
        System.out.println("Reservasi private konser (kapasitas " + kapasitas + " orang) berhasil!");
    }
}
```
Disini saya membuat class KonserEvent dan PrivateKonser menjadi implementasi dari Reservasi, dan di kedua class saya melakukan override dari class Event yang saya jadikan abstract.

Class EventService
```java
            public void reservasiEvent() {
        lihatEvent();
        if (daftarEvent.isEmpty()) return;

        System.out.print("Pilih nomor konser untuk reservasi: ");
        int index = input.nextInt(); input.nextLine();

        if (index < 1 || index > daftarEvent.size()) {
            System.out.println("Nomor tidak valid.");
            return;
        }

        Event e = daftarEvent.get(index - 1);
        if (e instanceof Reservasi r) {
            r.reservasi();   
        } else {
            System.out.println("Event ini tidak bisa direservasi.");
        }
    }
}
```
Di class ini saya menambahkan Fitur reservasi, dengan menggunakan public void reservasiEvent(), saya bisa memanggil interface yang ada di class Reservasi.

Class Konser
```java
package com.mycompany.konser;

import service.EventService;
import java.util.Scanner;

public class Konser {
    public static void main(String[] args) {
        EventService eventService = new EventService();
        Scanner input = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== Manajemen Konser ===");
            System.out.println("1. Tambah Konser Biasa");
            System.out.println("2. Tambah Private Konser");
            System.out.println("3. Lihat Konser");
            System.out.println("4. Ubah Konser");
            System.out.println("5. Hapus Konser");
            System.out.println("6. Cari Konser (huruf)");
            System.out.println("7. Cari Konser (tanggal)");
            System.out.println("8. Reservasi Konser");
            System.out.println("9. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt(); input.nextLine();

            switch (pilihan) {
                case 1 -> eventService.tambahEvent();
                case 2 -> eventService.tambahPrivateKonser();
                case 3 -> eventService.lihatEvent();
                case 4 -> eventService.ubahEvent();
                case 5 -> eventService.hapusEvent();
                case 6 -> eventService.cariEvent(); // cari pakai huruf
                case 7 -> {
                    System.out.print("Masukkan tanggal (dd-mm-yyyy): ");
                    String tgl = input.nextLine();
                    eventService.cariEvent(tgl);     // cari pakai tanggal
                }
                case 8 -> eventService.reservasiEvent();
                case 9 -> System.out.println("Terima kasih!");
                default -> System.out.println("Pilihan salah.");
            }
        } while (pilihan != 9);
    }
}
```
Terakhir di class Konser atau class Main ini, saya menambahkan beberapa fitur seperti Cari Konser dari tanggal, lalu fitur Reservasi Konser, jadi dengan menggunakan Overloading fitur mencari event bisa dibagi 2 yaitu cari dengan huruf atau dengan tanggal.

Demonstrasi Program


<img width="385" height="303" alt="Screenshot 2025-09-29 062226" src="https://github.com/user-attachments/assets/896a587e-38a1-4c09-b6ed-8334450340e1" />


<img width="385" height="236" alt="Screenshot 2025-09-29 062241" src="https://github.com/user-attachments/assets/0a46a95b-0588-4d1f-a7eb-d6da643e22bb" />





================================================================
# Post-Test-3-PBO

Di Post Test 3 ini ada beberapa tambahan yaitu 

- Tambahaan 2 Class yaitu KonserEvent dan PrivateKonser

  Class Event adalah kelas induk atau superclass, yang menjadi pondasi untuk semua tipe event yang ada di program ini.
```java
  package models;

public class Event {
    private String nama;
    private String tanggal;
    private String venue;

    public Event(String nama, String tanggal, String venue) {
        this.nama = nama;
        this.tanggal = tanggal;
        this.venue = venue;
    }

    // Getter & Setter 
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

  
    public void tampilkanInfo() {
        System.out.println("Nama   : " + nama);
        System.out.println("Tanggal: " + tanggal);
        System.out.println("Venue  : " + venue);
    }
}
```
- Semua atribut nama, tanggal, dan venue bersifat private.
- Untuk mengakses atau mengubah nilai, digunakan getter dan setter.
- Keuntungan: data terlindungi dan kontrol penuh terhadap akses atribut.

Disini ada Class baru yang berfungsi sebagai SubClass yaitu KonserEvent adalah subclass pertama dari Event, mewakili konser biasa.
```java
package models;

public class KonserEvent extends Event {
    private String artis;

    public KonserEvent(String nama, String tanggal, String venue, String artis) {
        super(nama, tanggal, venue);
        this.artis = artis;
    }

    // Getter & Setter
    
    public String getArtis() { 
        return artis; 
    }
    
    public void setArtis(String artis) { 
        this.artis = artis; 
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Artis  : " + artis);
    }

    @Override
    public String toString() {
        return super.toString() + " | Artis: " + artis;
    }
}
```
- KonserEvent otomatis mewarisi atribut nama, tanggal, dan venue dari Event.
- tampilkanInfo() diubah untuk menampilkan artis, tetapi tetap memanggil
- super.tampilkanInfo() supaya nama, tanggal, dan venue tetap ditampilkan.
- Getter & Setter: Digunakan untuk mengakses atau mengubah atribut artis secara aman.

Ketiga ada SubClass PrivateKonser yang berfungsi sebagai subclass kedua dari Event, mewakili konser privat/VIP.
  ```java
package models;

public class PrivateKonser extends Event {
    private int kapasitas;

    public PrivateKonser(String nama, String tanggal, String venue, int kapasitas) {
        super(nama, tanggal, venue);
        this.kapasitas = kapasitas;
    }


  
    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Kapasitas: " + kapasitas);
    }

    @Override
    public String toString() {
        return super.toString() + " | Kapasitas: " + kapasitas;
    }
}
```
- PrivateKonser mewarisi semua atribut dan method Event.
- Tidak perlu menulis ulang nama, tanggal, dan venue.
- kapasitas bersifat private, diakses lewat getter dan setter.
- Method tampilkanInfo() menambahkan informasi kapasitas ke output.

Dokumentasi Program



<img width="318" height="249" alt="Screenshot 2025-09-21 200631" src="https://github.com/user-attachments/assets/62b0fb1b-c328-4b07-b2b7-eef71ba37099" />



<img width="303" height="246" alt="Screenshot 2025-09-21 200635" src="https://github.com/user-attachments/assets/e9a6dac2-4465-4a18-9f7f-51a68e9478f1" />



<img width="239" height="195" alt="Screenshot 2025-09-21 200641" src="https://github.com/user-attachments/assets/ea7d8947-f8d9-40e1-8953-de60f34544d8" />


=====================================================================================

# Post Test 2

Deskripsi Program

Program ini dibuat dengan menggunakan bahasa pemrograman Java di NetBeans yang memiliki 3 Packages dan masing masing memiliki fungsinya masing masing, dan disini juga menerapkan fitur searching atau cari event. Program ini dibuat untuk bisa mengelola informasi event atau konser, sehingga pengguna bisa menyusun daftar acara secara lebih teratur karena disetiap event memiliki nama dan tanggal pelaksanaan.


Penjelasan Program

Package Models

<img width="808" height="784" alt="Screenshot 2025-09-14 225127" src="https://github.com/user-attachments/assets/f4528c6c-bcd7-48be-a78e-ecde15549de6" />

Didalam package ini berisi class Event yang menjadi tempat untuk data data Event yang tersedia, didalamnya ada nama, tanggal, dan venue. dengan menggunakan access modifier private agar tidak bisa diakses langsung, dan data hanya bisa diubah melalui code yang ada di class Event, dan disini juga memakai tampikanInfo() untuk menampilkan detail event ke layar.


Package Service

<img width="766" height="879" alt="Screenshot 2025-09-14 225514" src="https://github.com/user-attachments/assets/c028c025-ec1f-4498-be71-ed2ee6878d42" />


<img width="807" height="881" alt="Screenshot 2025-09-14 225536" src="https://github.com/user-attachments/assets/0ae795aa-8270-49da-ba9f-3c5b337c532a" />

Didalam package ini terdapat class EventService yang dimana berfungsi sebagai pengelola CRUD, jadi data data yang ada di class ini disimpan didalam ArrayList, sehingga pengguna bisa menambahkan, mengupdate, menghapus, membaca data yang tersedia.



Package Main

<img width="692" height="639" alt="Screenshot 2025-09-14 225715" src="https://github.com/user-attachments/assets/78a28698-d86b-4c4d-85f3-8379400a08ac" />

Disini adalah package main nya yang berfungsi sebagai titik masuk program, dan program berjalan dengan perulangan agar menu selalu muncul sampai user memilih keluar.


Demonstrasi Program


<img width="405" height="206" alt="Screenshot 2025-09-14 230156" src="https://github.com/user-attachments/assets/dd30f930-2226-45c0-94e7-73489112eac1" />

Membuat sebuah event.

<img width="468" height="194" alt="Screenshot 2025-09-14 230208" src="https://github.com/user-attachments/assets/eb514790-3e36-466f-b966-ff3f3b9cb329" />

Melihat Event yang tersedia.

<img width="559" height="332" alt="Screenshot 2025-09-14 230312" src="https://github.com/user-attachments/assets/a2436d16-00e3-4480-aaed-53476961801c" />

Mengupdate atau mengubah event yang ada.

<img width="646" height="306" alt="Screenshot 2025-09-14 230429" src="https://github.com/user-attachments/assets/ac6bd468-afc5-4eda-80e5-18828e425159" />

Menghapus event yang ada.

<img width="540" height="260" alt="Screenshot 2025-09-14 230415" src="https://github.com/user-attachments/assets/780f8f6f-6c0c-49c9-9dcd-7d05b33c8222" />

Melakukan searching dengan kata kunci.

===========================================================================================

# Post Test 1

**Deskripsi Program**

Program ini dibuat dengan menggunakan bahasa pemrograman Java di NetBeans dengan menerapkan konsep CRUD (Create, Read, Update, Delete), Data data event disimpan dalam sebuah ArrayList agar dapat menampung data secara dinamis. Program ini dibuat untuk bisa mengelola informasi event atau konser, sehingga pengguna bisa menyusun daftar acara secara lebih teratur karena disetiap event memiliki nama dan tanggal pelaksanaan
==============================================================================
**Penjelasan Code**
==============================================================================
<img width="247" height="80" alt="Screenshot 2025-09-09 194102" src="https://github.com/user-attachments/assets/220827dc-d9ef-4a07-a09b-f571b064cc16" />


Bagian ini adalah tempat package & import yang dimana package untuk menandakan folder tempat filenya, dan ArrayList untuk menyimpan suatu data, dan Scanner untuk membaca input dari keyboard.
=============================================================================

<img width="501" height="96" alt="Screenshot 2025-09-09 194355" src="https://github.com/user-attachments/assets/2f9d6928-8b5d-43db-9097-f299297fb48b" />

Bagian ini adalah kelas utamanyam jadi Main berfungsi sebagai titik masuk program atau semua progrma yang akan di run dimulai dari Main, dan ada Variabel Utama yaitu daftarEvent bertipe data String yang akan berfungsi untuk menyimpan data setiap event, Dan ada input yang membaca masukan user, dan Pilihan untuk menyimpan nomor menu yang akan dipilih.

==============================================================================

<img width="573" height="236" alt="Screenshot 2025-09-09 194820" src="https://github.com/user-attachments/assets/01182806-45de-4f9a-b8ab-e0244967e4c0" />

Disini diawali dengan do yaitu untuk memulai sesuatu perulangan, dan seterusnya ada code yang menampilkan menu awal yang ada pada program ini contohnya seperti ini

<img width="300" height="103" alt="Screenshot 2025-09-09 195029" src="https://github.com/user-attachments/assets/6e8b3518-2b73-4bf1-89d6-4e73c678adb1" />

Dan diakhir ada switch yaitu berfungsi sebagai struktur menu untuk memilih dari berbagai case yang ada.

=============================================================================

<img width="570" height="165" alt="Screenshot 2025-09-09 195215" src="https://github.com/user-attachments/assets/0accddec-f27f-47a8-9b28-0aca4c8cb8b1" />

Di case pertama ini ada Create, kita bisa memasukkan event baru dan tanggal dilaksanakannya event tersebut, dengan membuat string gabunganevent maka nama dan tanggal akan digabungkan menjadi satu string.

Contohnya seperti ini:

<img width="297" height="67" alt="Screenshot 2025-09-09 195613" src="https://github.com/user-attachments/assets/8783ee3f-f478-43ba-a2c2-e9cc8d88b122" />

=============================================================================

<img width="564" height="183" alt="Screenshot 2025-09-09 195357" src="https://github.com/user-attachments/assets/397e753a-023e-437f-88dc-bc7ab241bbe6" />

Di case kedua ini ada Read, yang dimana kita bisa meminta program menampilkan event event yang sudah ada, jika event belum ada satupun maka akan menampilkan pesan "Belum ada Event".

Contohnya seperti ini:

<img width="196" height="40" alt="Screenshot 2025-09-09 195621" src="https://github.com/user-attachments/assets/178f583d-c9ce-478c-b7a6-122f65fc639a" />

JIka belum ada event:

<img width="183" height="43" alt="Screenshot 2025-09-09 195715" src="https://github.com/user-attachments/assets/e5035ea9-48ad-4b93-9af6-f682274ae846" />

=============================================================================

<img width="655" height="495" alt="Screenshot 2025-09-09 195855" src="https://github.com/user-attachments/assets/b948a598-7559-480f-98a3-7fad52ff8d66" />

Pada case 3 ini yaitu Update, memungkinkan pengguna untuk mengubah event yang sudah ada, dan jika daftar masih kosong maka program akan langsung memberi pesan tidak ada event untuk diubah.

Contohnya seperti ini mengupdate sebuah event:

<img width="383" height="112" alt="Screenshot 2025-09-09 195651" src="https://github.com/user-attachments/assets/af5d1bbe-78a7-40cc-a41e-05cfecf6b97b" />

Dan akan menghasilkan:

<img width="197" height="45" alt="Screenshot 2025-09-09 195657" src="https://github.com/user-attachments/assets/c2ad2d09-5cf7-4c15-8d62-91115a73329d" />

==============================================================================

<img width="612" height="375" alt="Screenshot 2025-09-09 200330" src="https://github.com/user-attachments/assets/b3db93b4-e844-4f99-b57e-28e59429a645" />

Pada case 4 ini yaitu Delete yang berfungsi untuk menghapus event yang sudah ada.

Contohnya seperti ini:

<img width="283" height="73" alt="Screenshot 2025-09-09 195709" src="https://github.com/user-attachments/assets/418aa623-0a44-4974-8878-0c0e3c5651b6" />

Dan akan menghasilkan tampilan:

<img width="183" height="43" alt="Screenshot 2025-09-09 195715" src="https://github.com/user-attachments/assets/431ba049-e234-4bc7-ba1d-97a86f4c7f35" />

==============================================================================

<img width="515" height="74" alt="Screenshot 2025-09-09 200533" src="https://github.com/user-attachments/assets/a7467031-7edf-4c68-9f1a-ca6915535736" />

Di case 5 ini adalah untuk Keluar dari loop serta programnya, Jadi dengan pengguna menginput angka 5 maka program akan selesai, dan jika input pilihan tidak valid maka akan menampilkan pesan "Pilihan tidak ada, Coba lagi".

<img width="291" height="143" alt="Screenshot 2025-09-09 200730" src="https://github.com/user-attachments/assets/9f0599b5-e3f7-4eb8-930d-6812e667f338" />

==============================================================================

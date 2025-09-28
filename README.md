# Darel Prasetya Fawwaz | 2409116064 | Sistem Informasi B 24
================================================================
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

# Post-Test-3-PBO
Darel Prasetya Fawwaz 2409116064 Sistem Informasi B 24

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

# Post-Test-3-PBO
Darel Prasetya Fawwaz 2409116064 Sistem Informasi B 24

Di Post Test 3 ini ada beberapa tambahan yaitu 

- Tambahaan 2 Class yaitu KonserEvent dan PrivateKonser

  Class Event berfungsi sebagai SuperClass
  package models;

public class Event {
    private String nama;
    private String tanggal;
    private String venue;
```java
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

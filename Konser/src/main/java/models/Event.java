package models;

public abstract class Event {
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

  
    public abstract void tampilkanInfo() ;
}

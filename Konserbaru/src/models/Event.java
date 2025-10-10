package models;

public abstract class Event {
    private int id;
    private String nama;
    private String tanggal;
    private String venue;

    public Event(int id, String nama, String tanggal, String venue) {
        this.id = id;
        this.nama = nama;
        this.tanggal = tanggal;
        this.venue = venue;
    }

    // Getter & Setter 
    
    public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

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

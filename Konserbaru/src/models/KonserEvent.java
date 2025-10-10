package models;

public class KonserEvent extends Event implements Reservasi {
    private String artis;

    public KonserEvent(int id, String nama, String tanggal, String venue, String artis) {
        super(id, nama, tanggal, venue);
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
        System.out.println("ID Konser  : " + getId());
        System.out.println("Nama   : " + getNama());
        System.out.println("Tanggal: " + getTanggal());
        System.out.println("Venue  : " + getVenue());
        System.out.println("Artis  : " + artis);
    }

    @Override
    public void reservasi() {
        System.out.println("Reservasi tiket konser artis " + artis + " berhasil!");
    }
}

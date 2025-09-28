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

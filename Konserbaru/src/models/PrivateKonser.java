package models;

public class PrivateKonser extends Event implements Reservasi {
    private int kapasitas;

    public PrivateKonser(int id, String nama, String tanggal, String venue, int kapasitas) {
        super(id, nama, tanggal, venue);
        this.kapasitas = kapasitas;
    }

    // Getter & Setter
    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("ID Konser  : " + getId());
        System.out.println("Nama       : " + getNama());
        System.out.println("Tanggal    : " + getTanggal());
        System.out.println("Venue      : " + getVenue());
        System.out.println("Kapasitas  : " + kapasitas + " orang");
    }

    @Override
    public void reservasi() {
        System.out.println("Reservasi private konser (kapasitas " + kapasitas + " orang) berhasil!");
    }
}

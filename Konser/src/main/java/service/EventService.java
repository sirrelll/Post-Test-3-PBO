package service;

import models.Event;
import models.KonserEvent;
import java.util.ArrayList;
import java.util.Scanner;
import models.PrivateKonser;
import models.Reservasi;

public class EventService {
    private ArrayList<Event> daftarEvent = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    // Tambah Konser
    public void tambahEvent() {
        System.out.print("Masukkan nama konser: ");
        String nama = input.nextLine();
        System.out.print("Masukkan tanggal (dd-mm-yyyy): ");
        String tanggal = input.nextLine();
        System.out.print("Masukkan venue: ");
        String venue = input.nextLine();
        System.out.print("Masukkan artis: ");
        String artis = input.nextLine();

        daftarEvent.add(new KonserEvent(nama, tanggal, venue, artis));
        System.out.println("Konser berhasil ditambahkan!");
    }
    
    public void tambahPrivateKonser() {
        System.out.print("Nama private konser: ");
        String nama = input.nextLine();
        System.out.print("Tanggal: ");
        String tanggal = input.nextLine();
        System.out.print("Venue: ");
        String venue = input.nextLine();
        System.out.print("Kapasitas: ");
        int kapasitas = input.nextInt(); input.nextLine();

        daftarEvent.add(new PrivateKonser(nama, tanggal, venue, kapasitas));
        System.out.println("Private konser berhasil ditambahkan!");
        }

    // Lihat Konser
    public void lihatEvent() {
        if (daftarEvent.isEmpty()) {
            System.out.println("Belum ada konser.");
        } else {
            System.out.println("\n=== Daftar Konser ===");
            for (int i = 0; i < daftarEvent.size(); i++) {
                System.out.print((i + 1) + ". ");
                daftarEvent.get(i).tampilkanInfo();
                System.out.println("----------------");
            }
        }
    }

    // Ubah Konser
    public void ubahEvent() {
        lihatEvent();
        if (daftarEvent.isEmpty()) return;

        System.out.print("Pilih nomor konser yang ingin diubah: ");
        int index = input.nextInt(); input.nextLine();

        if (index < 1 || index > daftarEvent.size()) {
            System.out.println("Nomor tidak valid.");
            return;
        }

        System.out.print("Nama baru: ");
        String nama = input.nextLine();
        System.out.print("Tanggal baru: ");
        String tanggal = input.nextLine();
        System.out.print("Venue baru: ");
        String venue = input.nextLine();
        System.out.print("Artis baru: ");
        String artis = input.nextLine();

        daftarEvent.set(index - 1, new KonserEvent(nama, tanggal, venue, artis));
        System.out.println("Konser berhasil diubah!");
    }

    // Hapus Konser
    public void hapusEvent() {
        lihatEvent();
        if (daftarEvent.isEmpty()) return;

        System.out.print("Pilih nomor konser yang ingin dihapus: ");
        int index = input.nextInt(); input.nextLine();

        if (index < 1 || index > daftarEvent.size()) {
            System.out.println("Nomor tidak valid.");
            return;
        }

        daftarEvent.remove(index - 1);
        System.out.println("Konser berhasil dihapus!");
    }

    // Cari Konser
    public void cariEvent() {
        System.out.print("Masukkan kata kunci: ");
        String keyword = input.nextLine().toLowerCase();
        boolean ditemukan = false;

    for (Event e : daftarEvent) {
        if (e.getNama().toLowerCase().contains(keyword) ||
            e.getTanggal().toLowerCase().contains(keyword) ||
            e.getVenue().toLowerCase().contains(keyword)) {
            e.tampilkanInfo(); 
            System.out.println("----------------");
            ditemukan = true;
        }
    }


        if (!ditemukan) {
            System.out.println("Konser tidak ditemukan.");
        }
    }
    
        public void cariEvent(String tanggal) {
        boolean ditemukan = false;
        for (Event e : daftarEvent) {
            if (e.getTanggal().equalsIgnoreCase(tanggal)) {
                e.tampilkanInfo();
                System.out.println("----------------");
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Tidak ada konser pada tanggal " + tanggal);
        }
    }
        
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

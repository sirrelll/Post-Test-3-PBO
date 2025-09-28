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

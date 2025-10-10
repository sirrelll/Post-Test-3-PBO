package com.mycompany.konser;

import service.EventService;
import java.util.Scanner;

public class Konser {
    public static void main(String[] args) {
        EventService eventService = new EventService();
        Scanner input = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== Manajemen Konser (Database Terhubung) ===");
            System.out.println("1. Tambah Konser Biasa");
            System.out.println("2. Tambah Private Konser");
            System.out.println("3. Lihat Semua Konser");
            System.out.println("4. Ubah Konser");
            System.out.println("5. Hapus Konser");
            System.out.println("6. Cari Konser (kata kunci)");
            System.out.println("7. Reservasi Konser");
            System.out.println("8. Keluar");
            System.out.print("Pilih menu: ");
            while (!input.hasNextInt()) {
                System.out.print("Masukkan angka yang valid: ");
                input.next();
            }
            pilihan = input.nextInt(); input.nextLine();

            switch (pilihan) {
                case 1 -> eventService.tambahEvent();
                case 2 -> eventService.tambahPrivateKonser();
                case 3 -> eventService.lihatEvent();
                case 4 -> eventService.ubahEvent();
                case 5 -> eventService.hapusEvent();
                case 6 -> eventService.cariEvent();
                case 7 -> eventService.reservasiEvent();
                case 8 -> System.out.println("Terima kasih! Program selesai.");
                default -> System.out.println("Pilihan tidak valid. Coba lagi.");
            }
        } while (pilihan != 8);
    }
}

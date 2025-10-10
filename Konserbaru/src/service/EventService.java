package service;

import models.Event;
import models.KonserEvent;
import models.PrivateKonser;
import models.Reservasi;
import models.Database;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class EventService {
    private ArrayList<Event> daftarEvent = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public void loadEventDariDatabase() {
        daftarEvent.clear();
        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM konser";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama_konser");
                String tanggal = rs.getString("tanggal");
                String venue = rs.getString("venue");

                String jenis = null;
                try { jenis = rs.getString("jenis"); } catch (SQLException ex) { jenis = "konser"; }

                if (jenis != null && jenis.equalsIgnoreCase("private")) {
                    int kapasitas = rs.getInt("kapasitas");
                    if (rs.wasNull()) kapasitas = 0;
                    PrivateKonser pk = new PrivateKonser(id, nama, tanggal, venue, kapasitas);
                    daftarEvent.add(pk);
                } else {
                    String artis = rs.getString("artis");
                    KonserEvent k = new KonserEvent(id, nama, tanggal, venue, artis);
                    daftarEvent.add(k);
                }
            }

        } catch (SQLException e) {
            System.out.println("Gagal memuat data dari database: " + e.getMessage());
        }
    }

    // Tambah konser biasa (artis)
    public void tambahEvent() {
        System.out.print("Masukkan nama konser: ");
        String nama = input.nextLine();
        System.out.print("Masukkan tanggal (yyyy-mm-dd): ");
        String tanggal = input.nextLine();
        System.out.print("Masukkan venue: ");
        String venue = input.nextLine();
        System.out.print("Masukkan artis: ");
        String artis = input.nextLine();

        String sql = "INSERT INTO konser (nama_konser, tanggal, venue, artis, jenis, kapasitas) VALUES (?, ?, ?, ?, 'konser', NULL)";
        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nama);
            ps.setString(2, tanggal);
            ps.setString(3, venue);
            ps.setString(4, artis);
            ps.executeUpdate();
            System.out.println("Konser berhasil ditambahkan ke database!");
        } catch (SQLException e) {
            System.out.println("Gagal menambah konser: " + e.getMessage());
        }
    }

    // Tambah private konser (kapasitas)
    public void tambahPrivateKonser() {
        System.out.print("Nama private konser: ");
        String nama = input.nextLine();
        System.out.print("Tanggal (yyyy-mm-dd): ");
        String tanggal = input.nextLine();
        System.out.print("Venue: ");
        String venue = input.nextLine();
        System.out.print("Kapasitas: ");
        int kapasitas = 0;
        try {
            kapasitas = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("Input kapasitas tidak valid. Dibatalakan.");
            return;
        }

        String sql = "INSERT INTO konser (nama_konser, tanggal, venue, artis, jenis, kapasitas) VALUES (?, ?, ?, NULL, 'private', ?)";
        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nama);
            ps.setString(2, tanggal);
            ps.setString(3, venue);
            ps.setInt(4, kapasitas);
            ps.executeUpdate();
            System.out.println("Private konser berhasil ditambahkan ke database!");
        } catch (SQLException e) {
            System.out.println("Gagal menambah private konser: " + e.getMessage());
        }
    }


    public void ubahEvent() {
        loadEventDariDatabase();
        if (daftarEvent.isEmpty()) {
            System.out.println("Belum ada konser.");
            return;
        }

        System.out.print("Masukkan ID konser yang ingin diubah: ");
        int id;
        try {
            id = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("ID tidak valid.");
            return;
        }

        // cek jenis dari database
        String jenis = null;
        try (Connection conn = Database.connect();
             PreparedStatement check = conn.prepareStatement("SELECT jenis FROM konser WHERE id = ?")) {

            check.setInt(1, id);
            ResultSet rs = check.executeQuery();
            if (!rs.next()) {
                System.out.println("Konser dengan ID tersebut tidak ditemukan.");
                return;
            }
            jenis = rs.getString("jenis");
        } catch (SQLException e) {
            System.out.println("Gagal membaca jenis event: " + e.getMessage());
            return;
        }

        System.out.print("Nama baru: ");
        String nama = input.nextLine();
        System.out.print("Tanggal baru (yyyy-mm-dd): ");
        String tanggal = input.nextLine();
        System.out.print("Venue baru: ");
        String venue = input.nextLine();

        try (Connection conn = Database.connect()) {
            if ("private".equalsIgnoreCase(jenis)) {
                System.out.print("Kapasitas baru: ");
                int kapasitas;
                try {
                    kapasitas = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException ex) {
                    System.out.println("Input kapasitas tidak valid. Batal.");
                    return;
                }
                String sql = "UPDATE konser SET nama_konser=?, tanggal=?, venue=?, kapasitas=? WHERE id=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, nama);
                ps.setString(2, tanggal);
                ps.setString(3, venue);
                ps.setInt(4, kapasitas);
                ps.setInt(5, id);
                int rows = ps.executeUpdate();
                if (rows > 0) System.out.println("Private konser berhasil diubah di database!");
                else System.out.println("ID konser tidak ditemukan.");
            } else {
                System.out.print("Artis baru: ");
                String artis = input.nextLine();
                String sql = "UPDATE konser SET nama_konser=?, tanggal=?, venue=?, artis=? WHERE id=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, nama);
                ps.setString(2, tanggal);
                ps.setString(3, venue);
                ps.setString(4, artis);
                ps.setInt(5, id);
                int rows = ps.executeUpdate();
                if (rows > 0) System.out.println("Konser berhasil diubah di database!");
                else System.out.println("ID konser tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengubah konser: " + e.getMessage());
        }
    }



    public void hapusEvent() {
        loadEventDariDatabase();
        if (daftarEvent.isEmpty()) {
            System.out.println("Belum ada konser.");
            return;
        }

        System.out.print("Masukkan ID konser yang ingin dihapus: ");
        int id;
        try {
            id = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("ID tidak valid.");
            return;
        }

        String sql = "DELETE FROM konser WHERE id = ?";
        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Konser berhasil dihapus dari database!");
            else System.out.println("ID konser tidak ditemukan.");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus konser: " + e.getMessage());
        }
    }



    public void lihatEvent() {
        loadEventDariDatabase();
        if (daftarEvent.isEmpty()) {
            System.out.println("Belum ada konser di database.");
        } else {
            System.out.println("\n=== Daftar Konser dari Database ===");
            for (Event e : daftarEvent) {
                e.tampilkanInfo();
                System.out.println("----------------");
            }
        }
    }

    public void cariEvent() {
        System.out.print("Masukkan kata kunci: ");
        String keyword = input.nextLine().toLowerCase();
        boolean ditemukan = false;

        String sql = "SELECT * FROM konser WHERE LOWER(nama_konser) LIKE ? OR LOWER(venue) LIKE ? OR LOWER(COALESCE(artis, '')) LIKE ?";
        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String pattern = "%" + keyword + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);
            ps.setString(3, pattern);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama_konser");
                String tanggal = rs.getString("tanggal");
                String venue = rs.getString("venue");
                String jenis = rs.getString("jenis");

                if ("private".equalsIgnoreCase(jenis)) {
                    int kapasitas = rs.getInt("kapasitas");
                    if (rs.wasNull()) kapasitas = 0;
                    PrivateKonser pk = new PrivateKonser(id, nama, tanggal, venue, kapasitas);
                    pk.tampilkanInfo();
                } else {
                    String artis = rs.getString("artis");
                    KonserEvent ke = new KonserEvent(id, nama, tanggal, venue, artis);
                    ke.tampilkanInfo();
                }
                System.out.println("----------------");
                ditemukan = true;
            }

            if (!ditemukan) System.out.println("Konser tidak ditemukan.");
        } catch (SQLException e) {
            System.out.println("Gagal mencari konser: " + e.getMessage());
        }
    }


    public void reservasiEvent() {
        loadEventDariDatabase();
        if (daftarEvent.isEmpty()) {
            System.out.println("Belum ada konser.");
            return;
        }

        System.out.print("Masukkan ID konser untuk reservasi: ");
        int id;
        try {
            id = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("ID tidak valid.");
            return;
        }

        for (Event e : daftarEvent) {
            if (e.getId() == id) {
                if (e instanceof Reservasi r) {
                    r.reservasi();
                } else {
                    System.out.println("Event ini tidak dapat direservasi.");
                }
                return;
            }
        }
        System.out.println("ID konser tidak ditemukan.");
    }
}

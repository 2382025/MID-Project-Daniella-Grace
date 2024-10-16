import java.util.Scanner;

public class firmahukum {
    public static Klien[] daftarKlien = new Klien[3]; // Awal kapasitas array 3, bisa diperbesar
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMainMenu();
    }

    static class Klien {
        String nama;
        String jenisKasus;
        String tanggalRegistrasi;
        String status;
        String pengacara;
        String jumlahTagihan;

        Klien(String nama, String jenisKasus, String tanggalRegistrasi, String status, String pengacara, String jumlahTagihan) {
            this.nama = nama;
            this.jenisKasus = jenisKasus;
            this.tanggalRegistrasi = tanggalRegistrasi;
            this.status = status;
            this.pengacara = pengacara;
            this.jumlahTagihan = jumlahTagihan;
        }
    }



    // Menambah klien baru
    public static void addKlien(String nama, String jenisKasus, String tanggalRegistrasi, String status, String pengacara, String jumlahTagihan) {
        resizeArrayIfFull();
        for (int i = 0; i < daftarKlien.length; i++) {
            if (daftarKlien[i] == null) {
                daftarKlien[i] = new Klien(nama, jenisKasus, tanggalRegistrasi, status, pengacara, jumlahTagihan);
                break;
            }
        }
    }

    // Mengecek apakah array penuh
    public static boolean isArrayFull() {
        for (Klien klien : daftarKlien) {
            if (klien == null) {
                return false;
            }
        }
        return true;
    }

    // Menambah kapasitas array
    public static void resizeArrayToTwoTimesBigger() {
        Klien[] temp = daftarKlien;
        daftarKlien = new Klien[daftarKlien.length * 2];
        System.arraycopy(temp, 0, daftarKlien, 0, temp.length);
    }

    // Mengecek dan memperbesar array jika penuh
    public static void resizeArrayIfFull() {
        if (isArrayFull()) {
            resizeArrayToTwoTimesBigger();
        }
    }


    // Input untuk memudahkan pengambilan data dari pengguna
    public static String input(String info) {
        System.out.print(info + " : ");
        return scanner.nextLine();

    // Menampilkan menu utama
    public static void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("MENU:");
            System.out.println("1. Tampilkan Daftar Klien");
            System.out.println("2. Tambah Klien Baru");
            System.out.println("3. Edit Informasi Klien");
            System.out.println("4. Hapus Klien");
            System.out.println("5. Cari Klien");
            System.out.println("6. Tampilkan Statistik Kasus");
            System.out.println("7. Tampilkan Daftar Tagihan");
            System.out.println("8. Ubah Status Kasus");
            System.out.println("9. Keluar");
            String selectedMenu = input("Pilih");

            switch (selectedMenu) {
                case "1":
                    //showDaftarKlien();
                    break;
                case "2":
                    showMenuAddKlien();
                    break;
                case "3":
                    showMenuEditKlien();
                    break;
                case "4":
                   // showMenuRemoveKlien();
                    break;
                case "5":
                    showMenuSearchKlien();
                    break;
                case "6":
                    //showStatistikKasus();
                    break;
                case "7":
                    //showDaftarTagihan();
                    break;
                case "8":
                    //showMenuUbahStatusKasus();
                    break;
                case "9":
                    isRunning = false;
                    break;
            }
        }
    }
    // Menampilkan menu untuk menambah klien
    public static void showMenuAddKlien() {
        System.out.println("MENAMBAH KLIEN:");
        String nama = input("Nama Klien (x jika batal)");
        if (!nama.equals("x")) {
            String jenisKasus = input("Jenis Kasus");
            String tanggalRegistrasi = input("Tanggal Registrasi (dd-MM-yyyy)");
            String status = input("Status Kasus");
            String pengacara = input("Pengacara yang Ditugaskan");
            String jumlahTagihan = input("Jumlah Tagihan");
            addKlien(nama, jenisKasus, tanggalRegistrasi, status, pengacara, jumlahTagihan);
        }
    }

    // Menampilkan menu untuk mencari klien
    public static void showMenuSearchKlien() {
        System.out.println("MENCARI KLIEN:");
        String nama = input("Masukkan nama klien");
        searchKlien(nama);
    }

    // Menampilkan menu untuk mengedit klien
    public static void showMenuEditKlien() {
        System.out.println("EDIT KLIEN:");
        showDaftarKlien(); // Menampilkan daftar klien sebelum mengedit
        String selectedKlien = input("Nomor klien yang akan diedit (x jika batal)");
        System.out.println("UPDATE INFORMASI KLIEN");
        if (!selectedKlien.equals("x")) {
            String namaBaru = input("Nama");
            String jenisKasusBaru = input("Jenis Kasus");
            String tanggalBaru = input("Tanggal Registrasi");
            String pengacaraBaru = input("Pengacara");
            String tagihanBaru = input("Jumlah Tagihan");
            boolean isEditKlienSuccess = editKlien(Integer.parseInt(selectedKlien), namaBaru, jenisKasusBaru, tanggalBaru, pengacaraBaru, tagihanBaru);
            if (isEditKlienSuccess) {
                System.out.println("Berhasil Mengedit Klien");
            } else {
                System.out.println("Gagal Mengedit Klien");
            }
        }
    }

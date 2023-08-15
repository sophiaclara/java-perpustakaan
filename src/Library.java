import java.text.SimpleDateFormat;
import java.util.*;

public class Library {
    //Enkapsulasi: Atribut bookList, memberList, dan transactionHistory dienkapsulasi dengan deklarasi private,
    private ArrayList<Book> bookList;
    private ArrayList<Member> memberList;
    private ArrayList<History> transactionHistory;
    private String member_id; // Tambahkan atribut untuk melacak ID anggota saat ini

    public void run() {
        Scanner scanner = new Scanner(System.in);

        int menu;
        System.out.println("\nSelamat Datang !");
        do {
            System.out.println("\n********** Menu **********");
            System.out.println("0. Keluar");
            System.out.println("1. Buku");
            System.out.println("2. Anggota");
            System.out.println("3. Transaksi");
            System.out.print("\nPilih menu: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Pilihan tidak valid. Silakan pilih menu yang sesuai !");
                System.out.print("\nPilih menu: ");
                scanner.next();
            }
            menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1:
                    menuBuku(scanner);
                    break;
                case 2:
                    menuAnggota(scanner);
                    break;
                case 3:
                    menuTransaksi(scanner);
                    break;
                case 0:
                    System.out.println("\nTerima kasih. Sampai jumpa!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih menu yang sesuai.");
                    break;
            }
        } while (menu != 0);
    }

    private void menuBuku(Scanner scanner) {
        int submenu;
        do {
            System.out.println("\n********** Menu Buku **********");
            System.out.println("1. Daftar Buku");
            System.out.println("2. Detail Buku");
            System.out.println("3. Tambah Buku");
            System.out.println("4. Hapus Buku");
            System.out.println("0. Kembali");
            System.out.print("\nPilih submenu: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Pilihan tidak valid. Silakan pilih submenu yang sesuai !");
                System.out.print("\nPilih submenu: ");
                scanner.next();
            }
            submenu = scanner.nextInt();
            scanner.nextLine();

            switch (submenu) {
                case 1:
                    daftarBuku();
                    break;
                case 2:
                    detailBuku();
                    break;
                case 3:
                    tambahBuku(scanner);
                    break;
                case 4:
                    hapusBuku(scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih submenu yang sesuai.");
                    break;
            }
        } while (submenu != 0);
    }

    private void menuAnggota(Scanner scanner) {
        int submenu;
        do {
            System.out.println("\n********** Menu Anggota **********");
            System.out.println("1. Tambah Anggota");
            System.out.println("2. Hapus Anggota");
            System.out.println("0. Kembali");
            System.out.print("\nPilih submenu: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Pilihan tidak valid. Silakan pilih submenu yang sesuai !");
                System.out.print("\nPilih submenu: ");
                scanner.next();
            }
            submenu = scanner.nextInt();
            scanner.nextLine();

            switch (submenu) {
                case 1:
                    tambahAnggota(scanner);
                    break;
                case 2:
                    hapusAnggota(scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih submenu yang sesuai.");
                    break;
            }
        } while (submenu != 0);
    }

    private void menuTransaksi(Scanner scanner) {
        int submenu;
        do {
            System.out.println("\n********** Menu Transaksi **********");
            System.out.println("1. Pinjam Buku");
            System.out.println("2. Kembali Buku");
            System.out.println("3. History");
            System.out.println("0. Kembali");
            System.out.print("\nPilih submenu: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Pilihan tidak valid. Silakan pilih submenu yang sesuai !");
                System.out.print("\nPilih submenu: ");
                scanner.next();
            }
            submenu = scanner.nextInt();
            scanner.nextLine();

            switch (submenu) {
                case 1:
                    pinjamBuku(scanner);
                    break;
                case 2:
                    kembaliBuku(scanner);
                    break;
                case 3:
                    lihatTransaksi();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih submenu yang sesuai.");
                    break;
            }
        } while (submenu != 0);
    }

    private void daftarBuku() {
        if (bookList.isEmpty()) {
            System.out.println("Tidak ada buku yang tersedia.");
        } else {
            System.out.println("Daftar Buku:");
            System.out.println("----------------------------------------");
            System.out.printf("| %-30s | %-15s |\n", "Judul", "Jumlah");
            System.out.println("----------------------------------------");

            Map<String, Integer> judulToJumlah = new HashMap<>(); // Map untuk menyimpan jumlah buku berdasarkan judul

            for (Book book : bookList) {
                String judul = book.getTitle();
                int jumlah = judulToJumlah.getOrDefault(judul, 0) + book.getQuantity();
                judulToJumlah.put(judul, jumlah);
            }

            for (Map.Entry<String, Integer> entry : judulToJumlah.entrySet()) {
                String judul = entry.getKey();
                int jumlah = entry.getValue();

                System.out.printf("| %-30s | %-15s |\n", judul, jumlah);
            }

            System.out.println("----------------------------------------");
        }
        // Enkapsulasi: Data diambil dari bookList yang merupakan atribut private dari kelas Library.
    }

    private void tambahBuku(Scanner scanner) {
        System.out.print("Masukkan judul buku: ");
        String title = scanner.nextLine();
        System.out.print("Masukkan penulis buku: ");
        String author = scanner.nextLine();

        String isbn;
        while (true) {
            System.out.print("Masukkan ISBN buku : ");
            isbn = scanner.nextLine();
            if (isbn.matches("\\d{12}")) { // Memeriksa apakah ISBN memiliki 12 digit angka
                if (isIsbnUnique(isbn)) { // Memeriksa apakah ISBN sudah ada
                    break;
                } else {
                    System.out.println("ISBN sudah digunakan");
                }
            } else {
                System.out.println("ISBN harus terdiri dari 12 digit angka.");
            }
        }

        int quantity = 1; // Selalu set jumlah buku menjadi 1

        // Enkapsulasi: Pembuatan objek Book dengan parameter yang diberikan, atribut internal diatur dengan metode yang sesuai.
        Book book = new Book(title, author, isbn, quantity);
        addBook(book); // Polimorfisme addBook bisa menerima objek Book yang merupakan instance dari kelas turunan.
        System.out.println("Buku berhasil ditambahkan.");
    }

    private boolean isIsbnUnique(String isbn) {
        for (Book book : bookList) {
            if (book.getIsbn().equals(isbn)) {
                return false;
            }
        }
        return true;
        //Enkapsulasi: Akses ke atribut buku dilakukan melalui metode getter (book.getIsbn()).
        //Polimorfisme: Metode ini bekerja dengan objek dari kelas Book (atau turunannya) tanpa memperhatikan jenis spesifik objeknya.
    }

    private void hapusBuku(Scanner scanner) {
        System.out.print("Masukkan ISBN buku yang ingin dihapus: ");
        String isbn = scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getIsbn().equals(isbn)) {
                bookList.remove(i); // Enkapsulasi: Menghapus objek dari bookList menggunakan metode internal remove.
                found = true;
                System.out.println("Buku dengan ISBN " + isbn + " berhasil dihapus.");
                break;
            }
        }

        if (!found) {
            System.out.println("Buku dengan ISBN " + isbn + " tidak ditemukan.");
        }
    }

    private void detailBuku() {
        System.out.println("Detail Buku :");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("| %-30s | %-15s | %-10s |\n", "Judul", "ISBN", "Status");
        System.out.println("---------------------------------------------------------------");

        for (Book book : bookList) {
            String status = book.isBorrowed() ? "Dipinjam" : "Tersedia";
            // Enkapsulasi: Menggunakan metode get yang telah disediakan oleh kelas Book untuk mengakses atribut internal.
            System.out.printf("| %-30s | %-15s | %-10s |\n", book.getTitle(), book.getIsbn(), status);
        }

        System.out.println("---------------------------------------------------------------");
    }

    private int nextMemberId = 1; // Inisialisasi ID anggota awal

    private void tambahAnggota(Scanner scanner) {
        String id = generateNextMemberId(); // Enkapsulasi: Menggunakan metode untuk menghasilkan ID anggota baru.
        System.out.print("Masukkan nama anggota: ");
        String name = scanner.nextLine();
        System.out.print("Masukkan alamat anggota: ");
        String address = scanner.nextLine();
        // Enkapsulasi: Pembuatan objek Member dengan parameter yang diberikan, atribut internal diatur dengan metode yang sesuai.
        Member member = new Member(id, name, address);
        addMember(member);// Polimorfisme: addMember bisa menerima objek Member yang merupakan instance dari kelas turunan.
        System.out.println("Anggota berhasil ditambahkan dengan ID: " + id);
    }

    private String generateNextMemberId() {
        String id = "A" + String.format("%04d", nextMemberId); // Format ID dengan awalan "A" dan padding 4 digit
        nextMemberId++;
        return id;
    }

    private Book findBookByIsbn(String isbn) {
        // Enkapsulasi: Menggunakan getter untuk mengakses atribut internal bookList.
        for (Book book : bookList) {
            // Enkapsulasi: Menggunakan getter untuk mengakses atribut internal isbn di dalam objek Book.
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        return null;
    }

    private void hapusAnggota(Scanner scanner) {
        System.out.print("Masukkan ID anggota yang ingin dihapus: ");
        String id = scanner.nextLine();

        boolean found = false;
        // Enkapsulasi: Menggunakan metode getter untuk mendapatkan memberId dari objek Member.
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getMemberId().equalsIgnoreCase(id)) {
                memberList.remove(i); // Polimorfisme : remove bisa menerima objek turunan dari kelas List.
                found = true;
                System.out.println("Anggota dengan ID " + id + " berhasil dihapus.");
                break;
            }
        }

        if (!found) {
            System.out.println("Anggota dengan ID " + id + " tidak ditemukan.");
        }
    }

    private boolean isValidMember(String memberId) {
        for (Member member : memberList) {
            if (member.getMemberId().equalsIgnoreCase(memberId)) {
                return true;
            }
        }
        return false;
    }

    private void pinjamBuku(Scanner scanner) {
        System.out.print("Masukkan ID anggota: ");
        String memberId = scanner.nextLine(); // Meminta ID anggota

        // Enkapsulasi : Cek apakah anggota dengan ID yang dimasukkan ada
        if (!isValidMember(memberId)) {
            System.out.println("Anggota dengan ID " + memberId + " tidak ditemukan.");
            return;
        }

        System.out.print("Masukkan ISBN buku: ");
        String isbn = scanner.nextLine(); // Meminta ISBN buku

        // Enkapsulasi : Cek apakah buku dengan ISBN yang dimasukkan ada
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("Buku dengan ISBN " + isbn + " tidak ditemukan.");
            return;
        }

        if (book.isBorrowed()) {
            System.out.println("Buku sudah dipinjam.");
        } else if (book.getQuantity() <= 0) {
            System.out.println("Stok buku habis.");
        } else {
            // Polimorfisme: Melakukan peminjaman dengan memanggil metode setBorrowed() dan setQuantity() pada objek Book.
            book.setBorrowed(true);
            book.setQuantity(book.getQuantity() - 1);

            Date borrowDate = new Date();
            Date returnDate = null; // Ini akan diatur saat pengembalian
            // Polimorfisme : Membuat objek History dengan parameter yang sesuai.
            History transaction = new History(memberId, book.getTitle(), isbn, borrowDate, returnDate);
            transactionHistory.add(transaction);// Polimorfisme : Menambahkan objek History ke dalam transactionHistory.

            System.out.println("Buku berhasil dipinjam oleh anggota dengan ID " + memberId + ".");
        }
    }

    private void kembaliBuku(Scanner scanner) {
        System.out.print("Masukkan ISBN buku: ");
        String isbn = scanner.nextLine(); // Meminta ISBN buku
        // Enkapsulasi: Mencari buku berdasarkan ISBN menggunakan metode internal.
        Book book = findBookByIsbn(isbn);
        if (book != null && book.isBorrowed()) {
            // Lakukan pengembalian
            book.setBorrowed(false);
            book.setQuantity(book.getQuantity() + 1);

            Date returnDate = new Date();

            // Polimorfisme: Iterasi melalui transactionHistory dan mencari objek History yang sesuai.
            for (History transaction : transactionHistory) {
                if (transaction.getBookTitle().equalsIgnoreCase(book.getTitle()) && transaction.getReturnDate() == null) {
                    // Enkapsulasi: Mengatur tanggal pengembalian dalam objek History menggunakan metode internal.
                    transaction.setReturnDate(returnDate);
                    System.out.println("Buku berhasil dikembalikan.");
                    return;
                }
            }

            System.out.println("Buku tidak tercatat sebagai dipinjam.");
        } else {
            System.out.println("Buku tidak tercatat sebagai dipinjam.");
        }
    }

    public void lihatTransaksi() {
        // Enkapsulasi: Mengakses data transaksi melalui objek History tanpa langsung mengakses atributnya secara langsung.
        System.out.println("Riwayat Transaksi:");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-12s | %-30s | %-15s | %-15s | %-15s \n", "ID Anggota", "Judul Buku", "ISBN", "Tanggal Pinjam", "Tanggal Kembali");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");

        for (History transaction : transactionHistory) {
            // Polimorfisme : Menggunakan metode dari objek History tanpa perlu tahu jenis spesifik objek yang sedang diiterasi.
            String borrowDate = formatDate(transaction.getBorrowDate());
            String returnDate = transaction.getReturnDate() != null ? formatDate(transaction.getReturnDate()) : " ";
            System.out.printf("| %-12s | %-30s | %-15s | %-15s | %-15s \n", transaction.getMemberId().toUpperCase(), transaction.getBookTitle(), transaction.getIsbn(), borrowDate, returnDate);
        }

        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
    }

    private String formatDate(Date date) {
        // Enkapsulasi: Pembentukan objek SimpleDateFormat dan format tanggal dilakukan secara internal.
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    public Library() {
        bookList = new ArrayList<>(); // Enkapsulasi: Inisialisasi daftar buku sebagai atribut privat dengan ArrayList.
        memberList = new ArrayList<>(); // Enkapsulasi: Inisialisasi daftar anggota sebagai atribut privat dengan ArrayList.
        transactionHistory = new ArrayList<>(); // Enkapsulasi: Inisialisasi riwayat transaksi sebagai atribut privat dengan ArrayList.
    }

    public void addBook(Book book) {
        bookList.add(book); // Enkapsulasi: Menambahkan objek book ke dalam daftar buku (bookList).
    }

    public void addMember(Member member) {
        memberList.add(member); // Enkapsulasi: Menambahkan objek member ke dalam daftar anggota (memberList).
    }

}

public class Book {
    private String title;
    private String author;
    private String isbn;
    private int quantity;
    private boolean isBorrowed;
    private String id_member;

    public Book(String title, String author, String isbn, int quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.quantity = quantity;
        this.isBorrowed = false;
        this.id_member = null;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public String getBorrowedMemberId() {
        return id_member;
    }

    public void setBorrowedMemberId(String memberId) {
        this.id_member = memberId;
    }
}

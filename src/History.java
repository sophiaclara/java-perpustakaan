import java.util.Date;

public class History {
    private String memberId;
    private String bookTitle;
    private String isbn;
    private Date borrowDate;
    private Date returnDate;

    public History(String memberId, String bookTitle, String isbn, Date borrowDate, Date returnDate) {
        this.memberId = memberId;
        this.bookTitle = bookTitle;
        this.isbn = isbn;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getIsbn() {
        return isbn;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}

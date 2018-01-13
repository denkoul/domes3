public class BookInfo {
    int ISBN;
    int copies;

    public BookInfo(int ISBN, int copies) {
        this.ISBN = ISBN;
        this.copies = copies;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
    public int compareTo(BookInfo book2) {
        return Integer.compare(this.ISBN, book2.ISBN);
    }


}

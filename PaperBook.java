public class PaperBook extends Book {
    private int quantity;

    public PaperBook(String isbn, String title, String author, int year, double price, int quantity) {
        super(isbn, title, author, year, price);
        this.quantity = quantity;
    }

    @Override
    public boolean isForSale() {
        return true;
    }

    public boolean isAvailable(int qty) {
        return quantity >= qty;
    }

    public void reduceQuantity(int qty) {
        quantity -= qty;
    }
}
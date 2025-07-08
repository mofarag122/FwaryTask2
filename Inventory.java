import java.util.*;

public class Inventory {
    private Map<String, Book> books = new LinkedHashMap<>();
    private ShippingService shippingService;
    private MailService mailService;

    public Inventory(ShippingService shippingService, MailService mailService) {
        this.shippingService = shippingService;
        this.mailService = mailService;
    }

    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
    }

    public void removeOutdated(int cutoffYear) {
        Iterator<Map.Entry<String, Book>> iterator = books.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Book> entry = iterator.next();
            Book book = entry.getValue();
            if (!(book instanceof DemoBook) && book.getYear() < cutoffYear) {
                System.out.println("Quantum book store: '" + book.getTitle() + "' is outdated and was removed from inventory");
                iterator.remove();
            }
        }
    }

    public double buyBook(String isbn, int qty, String email, String address) {
        Book book = books.get(isbn);
        if (book == null) {
            throw new IllegalArgumentException("Quantum book store: Book not found in inventory");
        }

        if (!book.isForSale()) {
            throw new IllegalArgumentException("Quantum book store: (" + book.getTitle() + ") is a demo book not for sale");
        }

        if (book instanceof PaperBook paperBook) {
            if (!paperBook.isAvailable(qty))
                throw new IllegalArgumentException("Quantum book store: (" + paperBook.getTitle() + ") out of stock");
            paperBook.reduceQuantity(qty);
            shippingService.ship(address, paperBook.getTitle());
        } else if (book instanceof EBook eBook) {
            mailService.send(email, eBook.getTitle(), eBook.getFileType());
        }

        return book.getPrice() * qty;
    }
}

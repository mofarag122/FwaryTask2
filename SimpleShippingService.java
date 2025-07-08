public class SimpleShippingService implements ShippingService {
    @Override
    public void ship(String address, String bookTitle) {
        System.out.println("Quantum book store: shipping (" + bookTitle + ") to " + address);
    }
}


public class QuantumBookstoreTest{

    public static void main(String[] args){
        ShippingService shippingService= new SimpleShippingService();
        MailService mailService= new SimpleMailService();
        Inventory inventory= new Inventory(shippingService, mailService);

        Book paper= new PaperBook("ISBN11", "Java Tips", "Mark", 2010, 300, 2);
        Book ebook= new EBook("ISBN22", "Database", "Ramy", 2010, 200, ".pdf");
        Book demo= new DemoBook("ISBN33", "C++ Basics", "Samuel", 2009);

        inventory.addBook(paper);
        inventory.addBook(ebook);
        inventory.addBook(demo);

        inventory.removeOutdated(2010);

        try {
            double amountPaid = inventory.buyBook("ISBN11", 2, "user@example.com", "Cairo, Egypt");
            System.out.println("Quantum book store: paid amount: " + amountPaid);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            double amountPaid = inventory.buyBook("ISBN22", 1, "user@example.com", "N/A");
            System.out.println("Quantum book store: paid amount: " + amountPaid);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            inventory.buyBook("ISBN33", 1, "user@example.com", "N/A");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

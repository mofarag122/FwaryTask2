public class SimpleMailService implements MailService {
    @Override
    public void send(String email, String bookTitle, String fileType) {
        System.out.println("Quantum book store: sending (" + bookTitle + ") as " + fileType + " to " + email);
    }
}
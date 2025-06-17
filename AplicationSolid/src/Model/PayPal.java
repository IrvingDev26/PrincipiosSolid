package Model;


public class PayPal implements IPaymentMethod {
    private String email;
    private String password;

    public PayPal(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean processPayment(double amount) {

        if (amount > 0 && email != null && email.contains("@") && password != null && !password.isEmpty()) {
            System.out.println("Procesando pago con PayPal por $" + amount);
            System.out.println("Cuenta de PayPal: " + email);
            System.out.println("Pago con PayPal procesado exitosamente.");
            return true;
        }
        System.out.println("Error: Datos de PayPal inválidos o monto <= 0.");
        return false;
    }

    @Override
    public void pay(double amount) {
        processPayment(amount);
    }

    @Override
    public String getName() {
        return "PayPal";
    }
}
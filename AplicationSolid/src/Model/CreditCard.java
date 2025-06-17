

package Model;


public class CreditCard implements IPaymentMethod {
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;

    public CreditCard(String cardNumber, String cardHolderName, String expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    @Override
    public boolean processPayment(double amount) {
        if (amount > 0 && cardNumber != null && !cardNumber.isEmpty() && cvv != null && cvv.length() == 3) {
            System.out.println("Procesando pago con Tarjeta de Crédito por $" + amount);
            System.out.println("Número de tarjeta: " + cardNumber);
            System.out.println("Pago con tarjeta procesado exitosamente."); // Mensaje de confirmación
            return true; // Siempre exitoso si los datos básicos son válidos
        }
        System.out.println("Error: Datos de tarjeta inválidos o monto <= 0.");
        return false;
    }

    @Override
    public void pay(double amount) {
        processPayment(amount);
    }

    @Override
    public String getName() {
        return "Tarjeta de Crédito";
    }
}
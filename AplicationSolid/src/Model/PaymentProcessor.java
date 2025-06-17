package Model;

public class PaymentProcessor implements IPaymentService { // Implementa IPaymentService

    @Override
    public boolean process(IPaymentMethod method, double amount) {
        if (method == null) {
            System.out.println("Error: No se ha seleccionado un método de pago.");
            return false;
        }
        System.out.println("Iniciando procesamiento con " + method.getName() + "...");
        return method.processPayment(amount);
    }
}
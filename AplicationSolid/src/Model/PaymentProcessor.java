package Model;

/**
 * Clase que se encarga de procesar pagos utilizando cualquier método de pago.
 * Implementa IPaymentService para permitir la Inversión de Dependencias.
 * Cumple con el Principio de Responsabilidad Única (S) al enfocarse solo en el procesamiento.
 * Cumple con el Principio de Inversión de Dependencias (D) al depender de IPaymentMethod (abstracción).
 */
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
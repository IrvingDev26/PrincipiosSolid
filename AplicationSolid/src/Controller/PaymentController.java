
package Controller;

import Model.CreditCard;
import Model.IPaymentMethod;
import Model.PayPal;
import Model.IPaymentService;
import View.IPaymentView;
import View.UIFactory;

import java.awt.Color;


public class PaymentController {
    private IPaymentView view; // Ahora el controlador depende de la interfaz
    private IPaymentService model; // Ahora el controlador depende de la interfaz

    // El constructor recibe las abstracciones
    public PaymentController(IPaymentView view, IPaymentService model) {
        this.view = view;
        this.model = model;
        this.view.addPayButtonListener(e -> processPayment());
    }

    private void processPayment() {
        try {
            double amount = Double.parseDouble(view.getAmount());
            String selectedMethod = view.getSelectedPaymentMethod();
            IPaymentMethod paymentMethod = null;

            if ("Tarjeta de Crédito".equals(selectedMethod)) {
                String cardNumber = view.getCardNumber();
                String cardHolderName = view.getCardHolderName();
                String expirationDate = view.getExpirationDate();
                String cvv = view.getCvv();
                paymentMethod = new CreditCard(cardNumber, cardHolderName, expirationDate, cvv);
            } else if ("PayPal".equals(selectedMethod)) {
                String email = view.getPayPalEmail();
                String password = new String(view.getPayPalPassword());
                paymentMethod = new PayPal(email, password);
            }

            if (paymentMethod != null) {
                boolean success = model.process(paymentMethod, amount);
                if (success) {
                    view.setStatus("¡Pago Exitoso! con " + paymentMethod.getName(), UIFactory.getSuccessColor());
                } else {
                    view.setStatus("¡Pago Fallido! con " + paymentMethod.getName() + ". Intente de nuevo.", UIFactory.getErrorColor());
                }
            } else {
                // Si la lógica de selección de método falla o no se reconoce
                view.setStatus("Error: Método de pago no seleccionado o inválido.", Color.ORANGE);
            }

        } catch (NumberFormatException e) {
            view.setStatus("Error: Monto inválido. Ingrese un número válido.", UIFactory.getErrorColor());
        } catch (Exception e) {
            view.setStatus("Ocurrió un error inesperado: " + e.getMessage(), UIFactory.getErrorColor());
            e.printStackTrace();
        }
    }
}
// controller/PaymentController.java
package Controller;

import Model.CreditCard;
import Model.IPaymentMethod;
import Model.PayPal;
import Model.IPaymentService; // Importar la nueva interfaz
import View.IPaymentView;     // Importar la nueva interfaz
import View.UIFactory;

import java.awt.Color;

/**
 * El Controlador de la aplicación, encargado de manejar la lógica de la interacción
 * entre la Vista y el Modelo.
 * Ahora depende de abstracciones (IPaymentView, IPaymentService) para cumplir mejor
 * con el Principio de Inversión de Dependencias (D).
 * Cumple con el Principio de Responsabilidad Única (S) al coordinar la aplicación.
 */
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
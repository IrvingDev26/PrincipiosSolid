package View;

import java.awt.Color;
import java.awt.event.ActionListener;

/**
 * Interfaz que define el contrato para la Vista del sistema de pagos.
 * Permite que el Controlador dependa de una abstracción de la Vista, no de su implementación concreta.
 * Esto ayuda a cumplir con el Principio de Inversión de Dependencias (D).
 */
public interface IPaymentView {
    String getAmount();
    String getSelectedPaymentMethod();
    String getCardNumber();
    String getCardHolderName();
    String getExpirationDate();
    String getCvv();
    String getPayPalEmail();
    char[] getPayPalPassword();
    void setStatus(String status, Color color);
    void addPayButtonListener(ActionListener listener);
    void setVisible(boolean b); // Necesario para que el Main pueda mostrarla
}
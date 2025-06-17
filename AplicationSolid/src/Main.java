// Main.java
import Controller.PaymentController;
import Model.IPaymentService; // Importar la interfaz del servicio
import Model.PaymentProcessor; // Importar la implementación concreta del servicio
import View.IPaymentView;     // Importar la interfaz de la vista
import View.PaymentView;       // Importar la implementación concreta de la vista

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Instanciar las implementaciones concretas
            IPaymentService model = new PaymentProcessor(); // Se crea la implementación concreta
            IPaymentView view = new PaymentView();         // Se crea la implementación concreta

            // Crear el Controlador y pasarle las interfaces (abstracciones)
            PaymentController controller = new PaymentController(view, model);

            // Hacer visible la ventana de la aplicación
            view.setVisible(true);
        });
    }
}
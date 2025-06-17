
import Controller.PaymentController;
import Model.IPaymentService; // Importar la interfaz del servicio
import Model.PaymentProcessor; // Importar la implementación concreta del servicio
import View.IPaymentView;     // Importar la interfaz de la vista
import View.PaymentView;       // Importar la implementación concreta de la vista

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            IPaymentService model = new PaymentProcessor();
            IPaymentView view = new PaymentView();

            // Crear el Controlador y pasarle las interfaces (abstracciones)
            PaymentController controller = new PaymentController(view, model);


            view.setVisible(true);
        });
    }
}
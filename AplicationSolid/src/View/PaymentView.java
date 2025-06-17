package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


public class PaymentView extends JFrame implements IPaymentView { // Implementa IPaymentView

    private JTextField amountField;
    private JComboBox<String> paymentMethodComboBox;
    private JPanel cardPanel;
    private JPanel payPalPanel;
    private JTextField cardNumberField;
    private JTextField cardHolderNameField;
    private JTextField expirationDateField;
    private JTextField cvvField;
    private JTextField payPalEmailField;
    private JPasswordField payPalPasswordField;
    private JButton payButton;
    private JLabel statusLabel;

    public PaymentView() {
        setTitle("Sistema de Procesamiento de Pagos");
        setSize(450, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(UIFactory.getSecondaryColor());

        // Panel superior para el monto y selección de método
        JPanel topPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 15));
        topPanel.setBackground(UIFactory.getSecondaryColor());

        topPanel.add(UIFactory.createLabel("Monto a Pagar:"));
        amountField = UIFactory.createTextField();
        amountField.setText("100.00");
        amountField.setDocument(new DecimalInputDocument());
        topPanel.add(amountField);

        topPanel.add(UIFactory.createLabel("Método de Pago:"));
        String[] paymentMethods = {"Tarjeta de Crédito", "PayPal"};
        paymentMethodComboBox = UIFactory.createComboBox(paymentMethods);
        topPanel.add(paymentMethodComboBox);
        add(topPanel, BorderLayout.NORTH);

        // Paneles para los detalles de cada método de pago
        cardPanel = UIFactory.createTitledPanel("Detalles de Tarjeta de Crédito", new GridLayout(4, 2, 10, 10));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 15, 5, 15),
                cardPanel.getBorder()
        ));
        cardPanel.add(UIFactory.createLabel("Número de Tarjeta:"));
        cardNumberField = UIFactory.createTextField();
        cardPanel.add(cardNumberField);
        cardPanel.add(UIFactory.createLabel("Nombre del Titular:"));
        cardHolderNameField = UIFactory.createTextField();
        cardPanel.add(cardHolderNameField);
        cardPanel.add(UIFactory.createLabel("Fecha de Vencimiento (MM/AA):"));
        expirationDateField = UIFactory.createTextField();
        cardPanel.add(expirationDateField);
        cardPanel.add(UIFactory.createLabel("CVV:"));
        cvvField = UIFactory.createTextField();
        cardPanel.add(cvvField);

        payPalPanel = UIFactory.createTitledPanel("Detalles de PayPal", new GridLayout(2, 2, 10, 10));
        payPalPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 15, 5, 15),
                payPalPanel.getBorder()
        ));
        payPalPanel.add(UIFactory.createLabel("Email:"));
        payPalEmailField = UIFactory.createTextField();
        payPalPanel.add(payPalEmailField);
        payPalPanel.add(UIFactory.createLabel("Contraseña:"));
        payPalPasswordField = UIFactory.createPasswordField();
        payPalPanel.add(payPalPasswordField);

        // Panel principal para alternar entre los métodos de pago
        JPanel centerPanel = new JPanel(new CardLayout());
        centerPanel.add(cardPanel, "Tarjeta de Crédito");
        centerPanel.add(payPalPanel, "PayPal");
        add(centerPanel, BorderLayout.CENTER);

        // Listener para el ComboBox para cambiar el panel visible
        paymentMethodComboBox.addActionListener(e -> {
            CardLayout cl = (CardLayout)(centerPanel.getLayout());
            cl.show(centerPanel, (String)paymentMethodComboBox.getSelectedItem());
        });

        // Panel inferior para el botón de pago y estado
        JPanel bottomPanel = new JPanel(new BorderLayout(0, 10));
        bottomPanel.setBackground(UIFactory.getSecondaryColor());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));

        payButton = UIFactory.createButton("Pagar");
        bottomPanel.add(payButton, BorderLayout.CENTER);

        statusLabel = UIFactory.createStatusLabel();
        bottomPanel.add(statusLabel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // Asegurarse de que el panel de tarjeta esté visible al inicio
        CardLayout cl = (CardLayout)(centerPanel.getLayout());
        cl.show(centerPanel, "Tarjeta de Crédito");
    }

    @Override
    public String getAmount() {
        return amountField.getText();
    }

    @Override
    public String getSelectedPaymentMethod() {
        return (String) paymentMethodComboBox.getSelectedItem();
    }

    @Override
    public String getCardNumber() {
        return cardNumberField.getText();
    }

    @Override
    public String getCardHolderName() {
        return cardHolderNameField.getText();
    }

    @Override
    public String getExpirationDate() {
        return expirationDateField.getText();
    }

    @Override
    public String getCvv() {
        return cvvField.getText();
    }

    @Override
    public String getPayPalEmail() {
        return payPalEmailField.getText();
    }

    @Override
    public char[] getPayPalPassword() {
        return payPalPasswordField.getPassword();
    }

    @Override
    public void setStatus(String status, Color color) {
        statusLabel.setText(status);
        statusLabel.setForeground(color);
    }

    @Override
    public void addPayButtonListener(ActionListener listener) {
        payButton.addActionListener(listener);
    }

    private static class DecimalInputDocument extends javax.swing.text.PlainDocument {
        @Override
        public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws javax.swing.text.BadLocationException {
            if (str == null) {
                return;
            }
            String currentText = getText(0, getLength());
            String newText = currentText.substring(0, offs) + str + currentText.substring(offs);

            if (newText.matches("\\d*\\.?\\d*")) {
                super.insertString(offs, str, a);
            }
        }
    }
}
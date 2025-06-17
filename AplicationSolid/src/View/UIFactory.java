package View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class UIFactory {

    // Colores y fuentes base para una estética más agradable
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219); // Azul brillante
    private static final Color SECONDARY_COLOR = new Color(236, 240, 241); // Gris claro casi blanco
    private static final Color ACCENT_COLOR = new Color(46, 204, 113); // Verde esmeralda (para éxito)
    private static final Color ERROR_COLOR = new Color(231, 76, 60); // Rojo brillante (para errores)
    private static final Color TEXT_COLOR = new Color(44, 62, 80); // Azul oscuro para texto
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font FIELD_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 16);
    private static final Border FIELD_BORDER = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Borde con padding

    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(LABEL_FONT);
        label.setForeground(TEXT_COLOR);
        return label;
    }

    public static JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(FIELD_FONT);
        textField.setBorder(FIELD_BORDER);
        textField.setBackground(SECONDARY_COLOR);
        textField.setForeground(TEXT_COLOR);
        textField.setCaretColor(PRIMARY_COLOR); // Color del cursor
        return textField;
    }


    public static JPasswordField createPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(FIELD_FONT);
        passwordField.setBorder(FIELD_BORDER);
        passwordField.setBackground(SECONDARY_COLOR);
        passwordField.setForeground(TEXT_COLOR);
        passwordField.setCaretColor(PRIMARY_COLOR);
        return passwordField;
    }


    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false); // Quitar el borde de foco
        button.setBorder(new EmptyBorder(10, 20, 10, 20)); // Padding interno
        return button;
    }


    public static JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(FIELD_FONT);
        comboBox.setBackground(SECONDARY_COLOR);
        comboBox.setForeground(TEXT_COLOR);
        ((JLabel)comboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER); // Centrar texto
        return comboBox;
    }


    public static JPanel createTitledPanel(String title, LayoutManager layout) {
        JPanel panel = new JPanel(layout);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR),
                title,
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                LABEL_FONT.deriveFont(Font.BOLD, 16), // Fuente más grande para el título
                PRIMARY_COLOR
        ));
        panel.setBackground(Color.WHITE); // Fondo blanco para paneles
        return panel;
    }


    public static JLabel createStatusLabel() {
        JLabel label = new JLabel("Listo para procesar pago...", SwingConstants.CENTER);
        label.setFont(LABEL_FONT.deriveFont(Font.ITALIC));
        label.setForeground(TEXT_COLOR);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        return label;
    }


    public static Color getSuccessColor() {
        return ACCENT_COLOR;
    }


    public static Color getErrorColor() {
        return ERROR_COLOR;
    }


    public static Color getNormalTextColor() {
        return TEXT_COLOR;
    }


    public static Color getPrimaryColor() {
        return PRIMARY_COLOR;
    }


    public static Color getSecondaryColor() {
        return SECONDARY_COLOR;
    }
}
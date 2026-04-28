import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistanceCalculator extends JFrame {

    private JTextField txtLat1, txtLon1, txtLat2, txtLon2, txtR, txtResult;
    private JButton btnSolve, btnClear;

    public DistanceCalculator() {
        setTitle("Калькулятор відстані");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 5, 5));

        // Ініціалізація компонентів
        txtLat1 = new JTextField();
        txtLon1 = new JTextField();
        txtLat2 = new JTextField();
        txtLon2 = new JTextField();
        txtR = new JTextField("6371000"); // Радіус Землі за замовчуванням в метрах
        txtResult = new JTextField();
        txtResult.setEditable(false);

        btnSolve = new JButton("Обчислити");
        btnClear = new JButton("Очистити");

        // Додавання компонентів на панель
        add(new JLabel("Широта 1 (град):"));
        add(txtLat1);
        add(new JLabel("Довгота 1 (град):"));
        add(txtLon1);
        add(new JLabel("Широта 2 (град):"));
        add(txtLat2);
        add(new JLabel("Довгота 2 (град):"));
        add(txtLon2);
        add(new JLabel("Радіус R (м):"));
        add(txtR);
        add(new JLabel("Відстань D (м):"));
        add(txtResult);
        add(btnSolve);
        add(btnClear);

        // Обробник для кнопки Solve
        btnSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateDistance();
            }
        });

        // Обробник для кнопки Clear
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtLat1.setText("");
                txtLon1.setText("");
                txtLat2.setText("");
                txtLon2.setText("");
                txtR.setText("6371000");
                txtResult.setText("");
            }
        });
    }

    private void calculateDistance() {
        try {
            double lat1 = Double.parseDouble(txtLat1.getText());
            double lon1 = Double.parseDouble(txtLon1.getText());
            double lat2 = Double.parseDouble(txtLat2.getText());
            double lon2 = Double.parseDouble(txtLon2.getText());
            double r = Double.parseDouble(txtR.getText());

            // Переведення в радіани
            double phi1 = Math.toRadians(lat1);
            double phi2 = Math.toRadians(lat2);
            double deltaPhi = Math.toRadians(lat2 - lat1);
            double deltaLambda = Math.toRadians(lon2 - lon1);

            // Формула гаверсинуса
            double a = Math.pow(Math.sin(deltaPhi / 2), 2) +
                    Math.cos(phi1) * Math.cos(phi2) *
                            Math.pow(Math.sin(deltaLambda / 2), 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double d = r * c;

            txtResult.setText(String.format("%.2f", d));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Некоректні дані. Будь ласка, введіть числа.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DistanceCalculator().setVisible(true);
            }
        });
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame {

    private JTextField display;
    private double numeroIngresado;
    private String operacionPendiente;

    private boolean inicioDeEntrada = true;

    public Calculadora() {
        // Configuración de la ventana
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Configuración del campo de texto
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        // Configuración de los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 4));

        String[] nombresBotones = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "sin", "cos", "tan", "√",
                "^2", "^3", "log", "ln"
        };

        for (String nombreBoton : nombresBotones) {
            JButton boton = new JButton(nombreBoton);
            boton.addActionListener(new BotonClickListener());
            panelBotones.add(boton);
        }

        add(panelBotones, BorderLayout.CENTER);
    }

    private class BotonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String textoBoton = source.getText();

            if (inicioDeEntrada) {
                display.setText("");
                inicioDeEntrada = false;
            }

            if (textoBoton.equals("=")) {
                calcularResultado();
            } else {
                display.setText(display.getText() + textoBoton);
            }
        }
    }

    private void calcularResultado() {
        String entrada = display.getText();

        try {
            double numeroActual = Double.parseDouble(entrada);

            switch (operacionPendiente) {
                case "+":
                    numeroIngresado += numeroActual;
                    break;
                case "-":
                    numeroIngresado -= numeroActual;
                    break;
                case "*":
                    numeroIngresado *= numeroActual;
                    break;
                case "/":
                    numeroIngresado /= numeroActual;
                    break;
                case "sin":
                    numeroIngresado = Math.sin(Math.toRadians(numeroActual));
                    break;
                case "cos":
                    numeroIngresado = Math.cos(Math.toRadians(numeroActual));
                    break;
                case "tan":
                    numeroIngresado = Math.tan(Math.toRadians(numeroActual));
                    break;
                case "√":
                    numeroIngresado = Math.sqrt(numeroActual);
                    break;
                case "^2":
                    numeroIngresado = Math.pow(numeroActual, 2);
                    break;
                case "^3":
                    numeroIngresado = Math.pow(numeroActual, 3);
                    break;
                case "log":
                    numeroIngresado = Math.log10(numeroActual);
                    break;
                case "ln":
                    numeroIngresado = Math.log(numeroActual);
                    break;
                default:
                    numeroIngresado = numeroActual;
                    break;
            }

            display.setText(Double.toString(numeroIngresado));
            inicioDeEntrada = true;

        } catch (NumberFormatException ex) {
            display.setText("Error");
            inicioDeEntrada = true;
        }
    }
    }


package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BaseCalculator extends JPanel {
    // Input box
    JTextField inputNum;
    //Output box
    JTextField output;

   //Dropdowns for source and destination base
    JComboBox<String> sourceBase;
    JComboBox<String> destBase;

    public BaseCalculator() {
        createComponents();
        addButtons();
    }

    private void createComponents() {
        inputNum = new JTextField();
        JLabel inputLabel = new JLabel("Enter a number: ");

        output = new JTextField();
        JLabel outputLabel = new JLabel("Converted number: ");

        //Create array of options for combo box
        String[] bases = {"Binary", "Octal", "Decimal", "Hexadecimal"};
        sourceBase = new JComboBox<>(bases);
        destBase = new JComboBox<>(bases);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(inputLabel);
        inputPanel.add(inputNum);
        inputPanel.add(new JLabel("From: "));
        inputPanel.add(sourceBase);
        inputPanel.add(new JLabel("To: "));
        inputPanel.add(destBase);

        this.setLayout(new BorderLayout());
        this.add(inputPanel, BorderLayout.NORTH);
    }

    private void addButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4, 5, 5));

        ActionListener command = new ButtonListener();

        JButton convert = new JButton("Convert");
        convert.addActionListener(command);
        buttonPanel.add(convert);

        JButton clear = new JButton("C");
        clear.addActionListener(command);
        buttonPanel.add(clear);

        JButton backspace = new JButton("<-");
        backspace.addActionListener(command);
        buttonPanel.add(backspace);

        this.add(buttonPanel, BorderLayout.CENTER);

        output.setEditable(false);
        this.add(output, BorderLayout.SOUTH);
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String op = e.getActionCommand();

            if (op.equals("Convert")) {
                try {
                    String inputText = inputNum.getText().trim();
                    int fromBase = getBase((String) sourceBase.getSelectedItem());
                    int toBase = getBase((String) destBase.getSelectedItem());

                    int number = Integer.parseInt(inputText, fromBase);

                    String result = convertNumber(number, toBase);
                    output.setText(result);

                } catch (NumberFormatException err) {
                    output.setText("Invalid input for the selected source base.");
                }
            } else if (op.equals("C")) {
                inputNum.setText("");
                output.setText("");
            } else if (op.equals("<-")) {
                String text = inputNum.getText();
                if (text.length() > 0) {
                    inputNum.setText(text.substring(0, text.length() - 1));
                }
            }
        }

        private int getBase(String baseName) {
            switch (baseName) {
                case "Binary": return 2;
                case "Octal": return 8;
                case "Decimal": return 10;
                case "Hexadecimal": return 16;
                default: return 10;
            }
        }

        private String convertNumber(int number, int base) {
            switch (base) {
                case 2: return Integer.toBinaryString(number);
                case 8: return Integer.toOctalString(number);
                case 10: return Integer.toString(number);
                case 16: return Integer.toHexString(number).toUpperCase();
                default: return Integer.toString(number);
            }
        }
    }
    public static void main(String[] args) {
		JFrame myFrame = new JFrame();
		myFrame.setSize(400, 400);
		myFrame.setLocationRelativeTo(null);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setTitle("Base calculator");
		myFrame.add(new BaseCalculator());
		myFrame.setVisible(true);

	}
}


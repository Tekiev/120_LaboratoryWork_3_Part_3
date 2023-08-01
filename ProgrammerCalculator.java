package DEV120_3_3_Tekiev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ProgrammerCalculator {

    private JFrame frame;
    private JTextField field;

    ArrayList<JButton> jButtons;

    String typeCalc = "";
    private String number1 = "", number2 = "", operation = "";

    public ProgrammerCalculator() {
        frame = new JFrame();
    }

    public void init() {

        frame.setTitle("Programmer Calculator");
        frame.setSize(850, 500);
        frame.add(getTextField(), BorderLayout.NORTH);
        frame.add(getButtonsGroup(), BorderLayout.CENTER);
        frame.add(getResult(), BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private Container getTextField() {

        JPanel panelTextField = new JPanel();
        GridLayout layout = new GridLayout();
        panelTextField.setLayout(layout);
        field = new JTextField();
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setFont(new Font("CalcFontTextField", Font.PLAIN, 45));
        field.setEditable(false);
        panelTextField.add(field);
        field.setText("0");
        return panelTextField;
    }

    private Container getButtonsGroup() {

        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(4, 8);
        panel.setLayout(layout);
        jButtons = new ArrayList<>() {{
            add(new JButton("C"));
            add(new JButton("D"));
            add(new JButton("E"));
            add(new JButton("F"));
            add(new JButton("Lsh"));
            add(new JButton("Xor"));
            add(new JButton("/"));
            add(new JButton("HEX"));
            add(new JButton("8"));
            add(new JButton("9"));
            add(new JButton("A"));
            add(new JButton("B"));
            add(new JButton("Rsh"));
            add(new JButton("And"));
            add(new JButton("*"));
            add(new JButton("DEC"));
            add(new JButton("4"));
            add(new JButton("5"));
            add(new JButton("6"));
            add(new JButton("7"));
            add(new JButton("CE"));
            add(new JButton("Or"));
            add(new JButton("-"));
            add(new JButton("OCT"));
            add(new JButton("0"));
            add(new JButton("1"));
            add(new JButton("2"));
            add(new JButton("3"));
            add(new JButton("+/-"));
            add(new JButton("Not"));
            add(new JButton("+"));
            add(new JButton("BIN"));
        }};
        for (JButton jButton : jButtons) {
            jButton.setFont(new Font("CalcFontButton", Font.PLAIN, 30));
            if (!jButton.getText().equals("HEX") && !jButton.getText().equals("DEC") && !jButton.getText().equals("OCT") && !jButton.getText().equals("BIN")) {
                jButton.setEnabled(false);
            }
            jButton.addActionListener(new ProgrammerCalculator.Listener());
        }
        jButtons.forEach(panel::add);
        return panel;
    }

    private Container getResult() {
        JPanel panelResult = new JPanel();
        GridLayout layout = new GridLayout();
        panelResult.setLayout(layout);
        JButton result = new JButton("=");
        result.setFont(new Font("CalcFontResult", Font.PLAIN, 45));
        result.addActionListener(new ProgrammerCalculator.Listener());
        panelResult.add(result);
        return panelResult;
    }

    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            if (s.equals("HEX")) {
                for (JButton jButton : jButtons) {
                    jButton.setEnabled(true);
                }
                if (typeCalc.equals("DEC")) {
                    field.setText(decToHex(Integer.parseInt(field.getText())).toUpperCase());
                } else if (typeCalc.equals("OCT")) {
                    field.setText(decToHex(octToDec(field.getText())).toUpperCase());
                } else if (typeCalc.equals("BIN")) {
                    field.setText(binToHex(field.getText()).toUpperCase());
                }
                typeCalc = s;
            } else if (s.equals("DEC")) {
                for (JButton jButton : jButtons) {
                    jButton.setEnabled(!jButton.getText().equals("A") && !jButton.getText().equals("B") && !jButton.getText().equals("C") && !jButton.getText().equals("D") && !jButton.getText().equals("E") && !jButton.getText().equals("F"));
                }

                if (typeCalc.equals("HEX")) {
                    field.setText(hexToDecimal(field.getText()) + "");
                } else if (typeCalc.equals("OCT")) {
                    field.setText(octToDec(field.getText()) + "");
                } else if (typeCalc.equals("BIN")) {
                    field.setText(binToDec(field.getText()) + "");
                }
                typeCalc = s;
            } else if (s.equals("OCT")) {
                for (JButton jButton : jButtons) {
                    jButton.setEnabled(!jButton.getText().equals("A") && !jButton.getText().equals("B") && !jButton.getText().equals("C") && !jButton.getText().equals("D") && !jButton.getText().equals("E") && !jButton.getText().equals("F") && !jButton.getText().equals("8") && !jButton.getText().equals("9"));
                }
                if (typeCalc.equals("HEX")) {
                    field.setText(decToOct(hexToDecimal(field.getText())));
                } else if (typeCalc.equals("DEC")) {
                    field.setText(decToOct(Integer.parseInt(field.getText())));
                } else if (typeCalc.equals("BIN")) {
                    field.setText(decToOct(binToDec(field.getText())));
                }
                typeCalc = s;
            } else if (s.equals("BIN")) {
                for (JButton jButton : jButtons) {
                    jButton.setEnabled(!jButton.getText().equals("A") && !jButton.getText().equals("B") && !jButton.getText().equals("C") && !jButton.getText().equals("D") && !jButton.getText().equals("E") && !jButton.getText().equals("F") && !jButton.getText().equals("8") && !jButton.getText().equals("9") && !jButton.getText().equals("4") && !jButton.getText().equals("5") && !jButton.getText().equals("6") && !jButton.getText().equals("7") && !jButton.getText().equals("2") && !jButton.getText().equals("3"));
                }
                if (typeCalc.equals("HEX")) {
                    field.setText(hexToBin(field.getText()));
                } else if (typeCalc.equals("DEC")) {
                    field.setText(decToBin(Integer.parseInt(field.getText())));
                } else if (typeCalc.equals("OCT")) {

                    field.setText(decToBin(octToDec(field.getText())));
                }
                typeCalc = s;
            } else if ((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.equals("A") || s.equals("B") || s.equals("C") || s.equals("D") || s.equals("E") || s.equals("F")) {
                if (operation.equals("")) {
                    number1 = number1 + s;
                } else {
                    number2 = number2 + s;
                }
                field.setText(number1 + operation + number2);
            } else if (s.equals("CE")) {
                number1 = number2 = operation = "";
                field.setText("0");
            } else if (s.equals("+/-")) {

                if (typeCalc.equals("HEX")) {
                    field.setText(Integer.toHexString(-Integer.parseInt(field.getText(), 16)).toUpperCase());
                    number1 = number2 = operation = "";
                } else if (typeCalc.equals("DEC")) {
                    field.setText(-Integer.parseInt(field.getText()) + "");
                    number1 = number2 = operation = "";
                } else if (typeCalc.equals("OCT")) {
                    field.setText(Integer.toOctalString(-Integer.parseInt(field.getText(), 8)));
                    number1 = number2 = operation = "";
                } else if (typeCalc.equals("BIN")) {
                    field.setText(Integer.toBinaryString(-Integer.parseInt(field.getText(), 2)));
                    number1 = number2 = operation = "";
                }
            } else if (s.equals("Not")) {

                if (typeCalc.equals("HEX")) {
                    field.setText(Integer.toHexString(~Integer.parseInt(field.getText(), 16)).toUpperCase());
                    number1 = number2 = operation = "";
                } else if (typeCalc.equals("DEC")) {
                    field.setText(~Integer.parseInt(field.getText()) + "");
                    number1 = number2 = operation = "";
                } else if (typeCalc.equals("OCT")) {
                    field.setText(Integer.toOctalString(~Integer.parseInt(field.getText(), 8)));
                    number1 = number2 = operation = "";
                } else if (typeCalc.equals("BIN")) {
                    field.setText(Integer.toBinaryString(~Integer.parseInt(field.getText(), 2)));
                    number1 = number2 = operation = "";
                }
            } else if (s.charAt(0) == '=') {
                if (!(number1.isEmpty() || number2.isEmpty())) {
                    if (typeCalc.equals("DEC")) {
                        int res = 0;
                        switch (operation) {
                            case "+":
                                res = Integer.parseInt(number1) + Integer.parseInt(number2);
                                break;
                            case "-":
                                res = Integer.parseInt(number1) - Integer.parseInt(number2);
                                break;
                            case "/":
                                res = Integer.parseInt(number1) / Integer.parseInt(number2);
                                break;
                            case "*":
                                res = Integer.parseInt(number1) * Integer.parseInt(number2);
                                break;
                            case "Or":
                                res = Integer.parseInt(number1) | Integer.parseInt(number2);
                                break;
                            case "And":
                                res = Integer.parseInt(number1) & Integer.parseInt(number2);
                                break;
                            case "Xor":
                                res = Integer.parseInt(number1) ^ Integer.parseInt(number2);
                                break;
                            case "Lsh":
                                res = Integer.parseInt(number1) << Integer.parseInt(number2);
                                break;
                            case "Rsh":
                                res = Integer.parseInt(number1) >> Integer.parseInt(number2);
                                break;
                            default:
                                break;
                        }
                        field.setText(String.valueOf(res));
                        operation = number2 = "";
                    } else if (typeCalc.equals("OCT")) {
                        int res = 0;
                        switch (operation) {
                            case "+":
                                res = octToDec(number1) + octToDec(number2);
                                break;
                            case "-":
                                res = octToDec(number1) - octToDec(number2);
                                break;
                            case "/":
                                res = octToDec(number1) / octToDec(number2);
                                break;
                            case "*":
                                res = octToDec(number1) * octToDec(number2);
                                break;
                            case "Or":
                                res = octToDec(number1) | octToDec(number2);
                                break;
                            case "And":
                                res = octToDec(number1) & octToDec(number2);
                                break;
                            case "Xor":
                                res = octToDec(number1) ^ octToDec(number2);
                                break;
                            case "Lsh":
                                res = octToDec(number1) << octToDec(number2);
                                break;
                            case "Rsh":
                                res = octToDec(number1) >> octToDec(number2);
                                break;
                            default:
                                break;
                        }
                        field.setText(decToOct(res));
                        operation = number2 = "";
                    } else if (typeCalc.equals("BIN")) {
                        int res = 0;
                        switch (operation) {
                            case "+":
                                res = binToDec(number1) + binToDec(number2);
                                break;
                            case "-":
                                res = binToDec(number1) - binToDec(number2);
                                break;
                            case "/":
                                res = binToDec(number1) / binToDec(number2);
                                break;
                            case "*":
                                res = binToDec(number1) * binToDec(number2);
                                break;
                            case "Or":
                                res = binToDec(number1) | binToDec(number2);
                                break;
                            case "And":
                                res = binToDec(number1) & binToDec(number2);
                                break;
                            case "Xor":
                                res = binToDec(number1) ^ binToDec(number2);
                                break;
                            case "Lsh":
                                res = binToDec(number1) << binToDec(number2);
                                break;
                            case "Rsh":
                                res = binToDec(number1) >> binToDec(number2);
                                break;
                            default:
                                break;
                        }
                        field.setText(decToBin(res));
                        operation = number2 = "";
                    } else if (typeCalc.equals("HEX")) {
                        int res = 0;
                        switch (operation) {
                            case "+":
                                res = hexToDecimal(number1) + hexToDecimal(number2);
                                break;
                            case "-":
                                res = hexToDecimal(number1) - hexToDecimal(number2);
                                break;
                            case "/":
                                res = hexToDecimal(number1) / hexToDecimal(number2);
                                break;
                            case "*":
                                res = hexToDecimal(number1) * hexToDecimal(number2);
                                break;
                            case "Or":
                                res = hexToDecimal(number1) | hexToDecimal(number2);
                                break;
                            case "And":
                                res = hexToDecimal(number1) & hexToDecimal(number2);
                                break;
                            case "Xor":
                                res = hexToDecimal(number1) ^ hexToDecimal(number2);
                                break;
                            case "Lsh":
                                res = hexToDecimal(number1) << hexToDecimal(number2);
                                break;
                            case "Rsh":
                                res = hexToDecimal(number1) >> hexToDecimal(number2);
                                break;
                            default:
                                break;
                        }
                        field.setText(decToHex(res).toUpperCase());
                        operation = number2 = "";
                    }
                }
            } else {
                if (operation.equals("")) {
                    operation = s;
                }
                field.setText(number1 + operation + number2);
            }
        }
    }

    private String decToBin(int decimalNumber) {
        if (decimalNumber > 0) {
            String s = "";
            while (decimalNumber >= 1) {
                s += "" + decimalNumber % 2;
                decimalNumber = (decimalNumber - decimalNumber % 2) / 2;
            }
            return "" + new StringBuilder(s).reverse();
        } else
            return "0";
    }

    private int binToDec(String binaryNumber) {
        int t = 0;
        if (binaryNumber == null || binaryNumber.length() == 0) {
            return 0;
        } else {
            for (int i = 0; i < binaryNumber.length(); i++) {
                char b = binaryNumber.charAt(i);
                int h = Character.getNumericValue(b);
                t += h * Math.pow(2, (binaryNumber.length() - 1 - i));
            }
            return t;
        }
    }

    public int octToDec(String octalN) {

        int octalNumber = Integer.parseInt(octalN);
        if (octalNumber > 0) {
            int t = 0;
            for (int i = 0; octalNumber != 0; i++) {
                t += (octalNumber % 10) * (int) (Math.pow(8, i));
                octalNumber = octalNumber / 10;
            }
            return t;
        } else
            return 0;
    }

    public String decToOct(int decimalNumber) {
        if (decimalNumber > 0) {
            int t = 0;
            for (int i = 0; decimalNumber != 0; i++) {
                t += (decimalNumber % 8) * (int) (Math.pow(10, i));
                decimalNumber = decimalNumber / 8;
            }
            return String.valueOf(t);
        } else
            return "0";
    }

    public int hexToDecimal(String hexNumber) {
        int t = 0;
        if (hexNumber == null || hexNumber.length() == 0) {
            return 0;
        } else {
            for (int i = 0; i < hexNumber.length(); i++) {
                char b = hexNumber.charAt(i);
                int h = Character.getNumericValue(b);
                t += h * Math.pow(16, (hexNumber.length() - 1 - i));
            }
            return t;
        }
    }

    public String decToHex(int decimalNumber) {
        if (decimalNumber > 0) {
            int s1 = 0;
            int decimalNumber1 = decimalNumber;
            int decimalNumber2 = decimalNumber;
            for (int i = 0; decimalNumber1 >= 1; i++) {
                decimalNumber1 = (decimalNumber1 - decimalNumber1 % 16) / 16;
                s1 = i;
            }
            String[] array = new String[s1 + 1];
            for (int j = 0; j < array.length; j++) {
                array[array.length - 1 - j] = "" + decimalNumber2 % 16;
                decimalNumber2 = (decimalNumber2 - decimalNumber2 % 16) / 16;
            }
            String str = "";
            for (int m = 0; m < array.length; m++) {
                if (array[m].equals("10")) {
                    array[m] = "a";
                } else if (array[m].equals("11")) {
                    array[m] = "b";
                } else if (array[m].equals("12")) {
                    array[m] = "c";
                } else if (array[m].equals("13")) {
                    array[m] = "d";
                } else if (array[m].equals("14")) {
                    array[m] = "e";
                } else if (array[m].equals("15")) {
                    array[m] = "f";
                }
                str += array[m];
            }
            return str;
        } else {
            return "0";
        }
    }

    public String hexToBin(String hexNumber) {
        int t = 0;
        for (int i = 0; i < hexNumber.length(); i++) {
            char b = hexNumber.charAt(i);
            int h = Character.getNumericValue(b);
            t += h * Math.pow(16, (hexNumber.length() - 1 - i));
        }
        String s = "";
        while (t >= 1) {
            s += "" + t % 2;
            t = (t - t % 2) / 2;
        }
        return "" + new StringBuffer(s).reverse();
    }

    public String binToHex(String binaryNumber) {
        int t = 0;
        for (int i = 0; i < binaryNumber.length(); i++) {
            char b = binaryNumber.charAt(i);
            int h = Character.getNumericValue(b);
            t += h * Math.pow(2, (binaryNumber.length() - 1 - i));
        }
        String s = "";
        System.out.println(t);
        int i;
        int t1 = t;
        for (i = 0; t >= 1; i++) {
            s += "" + t % 16;
            t = (t - t % 16) / 16;
        }
        String[] array = new String[i];
        for (int j = 0; j < array.length; j++) {
            array[array.length - 1 - j] = "" + t1 % 16;
            t1 = (t1 - t1 % 16) / 16;
        }
        String str = "";
        System.out.println(Arrays.toString(array));
        for (int m = 0; m < array.length; m++) {
            if (array[m].equals("10")) {
                array[m] = "a";
            } else if (array[m].equals("11")) {
                array[m] = "b";
            } else if (array[m].equals("12")) {
                array[m] = "c";
            } else if (array[m].equals("13")) {
                array[m] = "d";
            } else if (array[m].equals("14")) {
                array[m] = "e";
            } else if (array[m].equals("15")) {
                array[m] = "f";
            }
            str += array[m];
        }
        return str;
    }

}

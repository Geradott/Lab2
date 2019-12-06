package bsu.rfe.java.group8.lab2.Chernysh.varC3;

import java.io.File;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JPanel;

public class CMainFrame extends JFrame {
    private static final int iFWidth = 1080;
    private static final int iFHeight = 720;
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    private ButtonGroup radioButtons = new ButtonGroup();
    private int iFormulaId = 1;
    private JTextField iMemory1;
    private JTextField iMemory2;
    private JTextField iMemory3;
    
    public static void main (String[] args) {
        CMainFrame frame = new CMainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public Double memoryPlus(Double mPrevious, Double mPlus) {
        return mPrevious + mPlus;
    }
    
    public Double calculate1(Double x, Double y, Double z) {
        return (Math.sin(Math.PI * y * y) + Math.log(y * y)) / (Math.sin(Math.PI * z * z) + Math.sin(x) + Math.log(z * z) + x * x + Math.pow(Math.E, Math.cos(z* x)));
    }
    
    public Double calculate2(Double x, Double y, Double z) {
        return (Math.pow(Math.cos(Math.pow(Math.E, y)) + Math.sqrt(1 / x), 1 / 4) / (Math.cos(Math.PI * z * z * z)));
    }
    
    private void addRadioButton(String sButtonName, final int iFormulaId, Box hbox) {
        JRadioButton button = new JRadioButton(sButtonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                CMainFrame.this.iFormulaId = iFormulaId;
            }
        });
        radioButtons.add(button);
        hbox.add(button);
    }

    public CMainFrame() {
        super("Mathmatics");
        setSize(iFWidth, iFHeight);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - iFWidth) / 2, (kit.getScreenSize().height - iFHeight) / 2);
        
        Box hboxMemory = Box.createHorizontalBox();
        hboxMemory.add(Box.createGlue());
        addRadioButton("Variable 1", 3, hboxMemory);
        addRadioButton("Variable 2", 4, hboxMemory);
        addRadioButton("Variable 3", 5, hboxMemory);
        JButton buttonClean = new JButton("MC");
        buttonClean.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                switch (iFormulaId) {
                    case 3:
                        iMemory1.setText("0");
                        break;
                    case 4:
                        iMemory2.setText("0");
                        break;
                    case 5:
                        iMemory3.setText("0");
                        break;
                }
            }
        });
        JButton buttonAdd = new JButton("M+");
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Double result;
                switch (iFormulaId) {
                    case 3: {
                        result = Double.parseDouble(textFieldResult.getText()) + Double.parseDouble(iMemory1.getText());
                        iMemory1.setText(result.toString());
                        break;
                    }
                    case 4: {
                        result = Double.parseDouble(textFieldResult.getText()) + Double.parseDouble(iMemory2.getText());
                        iMemory2.setText(result.toString());
                        break;
                    }
                    case 5: {
                        result = Double.parseDouble(textFieldResult.getText()) + Double.parseDouble(iMemory3.getText());
                        iMemory3.setText(result.toString());
                        break;
                    }
                }
            }
        });
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hboxMemory.add(Box.createHorizontalGlue());
        hboxMemory.add(buttonClean);
        hboxMemory.add(Box.createHorizontalStrut(10));
        hboxMemory.add(buttonAdd);
        hboxMemory.add(Box.createHorizontalGlue());
        hboxMemory.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        
        Box hboxMemoryPanel = Box.createHorizontalBox();
        hboxMemoryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel labelForM1 = new JLabel("M1:");
        iMemory1 = new JTextField("0", 10);
        iMemory1.setMaximumSize(iMemory1.getPreferredSize());        
        JLabel labelForM2 = new JLabel("M2:");
        iMemory2 = new JTextField("0", 10);
        iMemory2.setMaximumSize(iMemory2.getPreferredSize());
        JLabel labelForM3 = new JLabel("M3:");
        iMemory3 = new JTextField("0", 10);
        iMemory3.setMaximumSize(iMemory3.getPreferredSize());
        hboxMemoryPanel.add(Box.createHorizontalGlue());
        hboxMemoryPanel.add(labelForM1);
        hboxMemoryPanel.add(Box.createHorizontalStrut(10));
        hboxMemoryPanel.add(iMemory1);
        hboxMemoryPanel.add(Box.createHorizontalStrut(100));
        hboxMemoryPanel.add(labelForM2);
        hboxMemoryPanel.add(Box.createHorizontalStrut(10));
        hboxMemoryPanel.add(iMemory2);
        hboxMemoryPanel.add(Box.createHorizontalStrut(100));
        hboxMemoryPanel.add(labelForM3);
        hboxMemoryPanel.add(Box.createHorizontalStrut(10));
        hboxMemoryPanel.add(iMemory3);
        hboxMemoryPanel.add(Box.createHorizontalGlue());
        
        Box hBoxFormulaType = Box.createHorizontalBox();
        String imagePath = "C:\\Users\\ASUS-PC\\Documents\\Proga\\Java\\Problems\\2\\Formula1.bmp";
        BufferedImage image1;
        String imagePath2 = "C:\\Users\\ASUS-PC\\Documents\\Proga\\Java\\Problems\\2\\Formula2.bmp";
        BufferedImage image2;
        hBoxFormulaType.add(Box.createHorizontalStrut(100));
        try {
            image1 = ImageIO.read(new File(imagePath));
            image2 = ImageIO.read(new File(imagePath2));
            JLabel imageFormula1 = new JLabel(new ImageIcon(image1));
            JLabel imageFormula2 = new JLabel(new ImageIcon(image2));
            JRadioButton button1 = new JRadioButton("Formula 1");
            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    iFormulaId = 1;
                    hBoxFormulaType.remove(imageFormula2);
                    hBoxFormulaType.add(imageFormula1);
                    hBoxFormulaType.repaint();
                    hBoxFormulaType.revalidate();
                }
            });
            radioButtons.add(button1);
            hBoxFormulaType.add(button1);
            JRadioButton button2 = new JRadioButton("Formula 2");
            button2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    iFormulaId = 2;
                    hBoxFormulaType.remove(imageFormula1);
                    hBoxFormulaType.add(imageFormula2);
                    hBoxFormulaType.repaint();
                    hBoxFormulaType.revalidate();
                }
            });
            radioButtons.add(button2);
            hBoxFormulaType.add(button2);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(CMainFrame.this, "No image at this path", "Wrong image path", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hBoxFormulaType.add(Box.createHorizontalGlue());
        hBoxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));  
        
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());        
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());
        
        JLabel labelForResult = new JLabel("Result:");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        
        JButton buttonCalc = new JButton("Calculate:");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (iFormulaId == 1) {
                        result = calculate1(x, y, z);
                        textFieldResult.setText(result.toString());
                    }
                    else if (iFormulaId == 2) {
                        result = calculate2(x, y, z);
                        textFieldResult.setText(result.toString());
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CMainFrame.this, "Error in floating-point mathmatics", "Wrong number format", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Clean");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
            textFieldX.setText("0");
            textFieldY.setText("0");
            textFieldZ.setText("0");
            textFieldResult.setText("0");
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxMemory);
        contentBox.add(hboxMemoryPanel);
        contentBox.add(hBoxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
}
import MemoryAllocation.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainClass extends JFrame {
    private final JEditorPane editorPane = new JEditorPane();
    private final String helpText = "";
    private boolean canOpenFile = true;
    private final SegmentationClass segmentationClass = new SegmentationClass();
    private final PagingClass pagingClass = new PagingClass();
    private final FileManagementClass fileManagementClass = new FileManagementClass();
    private ArrayList<ProgramInfoClass> listOfItems = new ArrayList<>();

    private void frame() {
        JPanel panel = new JPanel();
        JButton buttonRun = new JButton("Start");
        JButton buttonHelp = new JButton("Help");
        JLabel JLSizeOfMemory = new JLabel(" Size of memory: ");
        JTextField JTFSizeOfMemory = new JTextField();
        JMenuBar menuBar = new JMenuBar();

        String[] schedulingMethods = {"Paging", "Segmentation"};
        JComboBox<String> comboBox = new JComboBox<>(schedulingMethods);

        buttonRun.addActionListener(e -> {
            if (fileManagementClass.ifFileExist(this, listOfItems) && fileManagementClass.isInt(JTFSizeOfMemory.getText())) {
                fileManagementClass.readWriteSortArray(listOfItems, this);
                if (canOpenFile) {
                    openFile();
                    canOpenFile = false;
                }
                switch (comboBox.getItemAt(comboBox.getSelectedIndex())) {
                    case "Paging":
                        //editorPane.setText(fifoClass.findAvgTime(listOfItems, JTFSizeOfMemory.getText()));
                        break;
                    case "Segmentation":
                        //editorPane.setText(fifoClass.findAvgTime(listOfItems, JTFSizeOfMemory.getText()));
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Choose one of Paging or Segmentation"
                                , "Error", JOptionPane.ERROR_MESSAGE);
                }
                updateJFrame();
            }
            updateJFrame();
        });

        buttonHelp.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, helpText, "Help", JOptionPane.INFORMATION_MESSAGE);
        });

        menuBar.add(comboBox);
        menuBar.add(JLSizeOfMemory);
        menuBar.add(JTFSizeOfMemory);
        menuBar.add(buttonRun);
        menuBar.add(buttonHelp);

        editorPane.setText(helpText);
        setTitle("Memory Allocation");
        setJMenuBar(menuBar);
        panel.add(editorPane);
        panel.setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTFSizeOfMemory.setBounds(10, 10, 0, 0);
        setBounds(0, 0, 600, 400);
        editorPane.setSize(getWidth() - 50, getHeight() - 50);
        editorPane.setEditable(false);
        add(panel);
        setVisible(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/Files/LOGO.png"));
        JScrollPane jScrollPane = new JScrollPane(panel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(jScrollPane);
        setLocationRelativeTo(getOwner());
        pack();
    }

    private void openFile() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("Notepad.exe", "ProgramInfo.BGH");
            processBuilder.start();
        } catch (IOException ignored) {

        }
    }

    private void updateJFrame() {
        SwingUtilities.updateComponentTreeUI(this);
        invalidate();
        validate();
        repaint();
        pack();
    }

    public static void main(String[] args) {
        try {
            MainClass mainClass = new MainClass();
            mainClass.frame();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error has occurred!\nPlease Run program again!\n" +
                    "Error Message: " + e, "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

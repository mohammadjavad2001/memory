import MemoryAllocation.ProgramInfoClass;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FileManagementClass {
    private final Random random = new Random();
    private final String filePath = "ProgramInfo.BGH";

    public boolean fileExist() {
        File file = new File(filePath);
        return (file.exists() && !file.isDirectory());
    }

    public boolean ifFileExist(JFrame frame, ArrayList<ProgramInfoClass> listOfItems) {
        if (fileExist())
            return true;

        createFile(frame,listOfItems);
        return true;
    }

    public void createFile(JFrame jFrame, ArrayList<ProgramInfoClass> listOfItems) {
        try {
            new File(filePath).createNewFile();
            writeFile(jFrame, listOfItems);
        } catch (IOException e) {
            createFile(jFrame,listOfItems);
        }
    }

    public void writeFile(JFrame jFrame, ArrayList<ProgramInfoClass> listOfItems) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < 100000; i++) {
                fileWriter.write(processInfo(i));
            }
            JOptionPane.showMessageDialog(jFrame, "The database was set!", "Succeed!", JOptionPane.INFORMATION_MESSAGE);
            fileWriter.close();
        } catch (IOException e) {
            createFile(jFrame,listOfItems);
        }
    }

    public void readWriteSortArray(ArrayList<ProgramInfoClass> listOfItems, JFrame jFrame) {
        readWriteFile(listOfItems, jFrame);
    }

    private void readWriteFile(ArrayList<ProgramInfoClass> listOfItems, JFrame jFrame) {
        int tempID, tempArrivalTime, tempPriority, tempBurstTime;
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] splitData = decode(myReader.nextLine()).split(" ");
                tempID = Integer.parseInt(splitData[0]);
                tempArrivalTime = Integer.parseInt(splitData[1]);
                tempPriority = Integer.parseInt(splitData[2]);
                tempBurstTime = Integer.parseInt(splitData[3]);
                //ProcessClass temp = new ProcessClass(tempID, tempArrivalTime, tempPriority, tempBurstTime);
                //listOfItems.add(temp);
            }
            myReader.close();
        } catch (Exception e) {
            createFile(jFrame,listOfItems);
        }
    }

    public boolean isInt(String input) {
        try {
            if (Integer.parseInt(input) <= 0) {
                throw new NumberFormatException();
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please write a number greater than 1 in text field to set size of memory " +
                            "number\nCause maybe:\n->You don't write number into the text field!\n->You wrote number with character or space\n" +
                            "->You wrote just character\n->You wrote number less than 1\nError message: " + e.getMessage()
                    , "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public String processInfo(int i) {
        String str = "";
        //ID
        str += i + " ";
        //ArrivalTime
        str += (i / 4) + " ";
        //Priority
        str += random.nextInt(6) + 1 + " ";
        //BurstTime
        str += random.nextInt(5) + 1 + "\n";
        return code(str);
    }

    private String code(String input) {
        /*String[] splitString = input.split("");
        String result = "";
        for (int i = 0; i < splitString.length; i++) {
            splitString[i] = Character.toString((char) (((int) splitString[i].charAt(0)) + 46));
            result += splitString[i];
        }
        return result + "\n";*/
        return input;
    }

    private String decode(String input) {
        /*String[] splitString = input.split("");
        String result = "";
        for (int i = 0; i < splitString.length; i++) {
            splitString[i] = Character.toString((char) (((int) splitString[i].charAt(0)) - 46));
            result += splitString[i];
        }
        return result + "\n";*/
        return input;
    }
}

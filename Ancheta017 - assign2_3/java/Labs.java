package Ancheta017.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * This class holds an arrays of existing Labs.
 */

public class Labs {

    public Lab[] labs;                                   // a collection of labs of type array
    public int numberOfLabs;                             // number of labs in collection

    /**
     * Constructor
     * @param numberOfLabs
     */
    public Labs(int numberOfLabs) {
        this.numberOfLabs = numberOfLabs;
        this.labs = new Lab[numberOfLabs];
    }

    /**
     * This method creates a lab object from a file.
     * @param labName
     * @param labFileName
     * @return Lab object
     */
    public Lab addDevicesToLab(String labName, String labFileName) {

        Lab lab = buildLabFromFile(labName, labFileName);
        System.out.println("Lab = " + labName + "\n[\n" + lab + "]");
        return lab;
    }

    /**
     * Build a lab object from a file
     * @param labName
     * @param fileName
     * @return Lab object
     */
    public Lab buildLabFromFile(String labName, String fileName) {

        Lab lab = new Lab(labName);
        MobileDevice md = null;
        String s;
        String filePath = new File("").getAbsolutePath() + "\\src\\Ancheta017\\java\\";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath + fileName))) {

            while ((s = br.readLine()) != null) {
                String[] tokens = s.split(",");
                md = new MobileDevice(tokens[0],Integer.parseInt(tokens[1]));
                md.setLab(lab);
                lab.addDevice(md);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lab;
    }

    /**
     * Check a device if available in all of the labs.
     * @param md
     * @return Lab object or null
     */
    public Lab isThereDeviceInLabs(MobileDevice md) {
        for (Lab lab : labs){
            if(lab.isThereDevice(md)){
                return lab;
            }
        }
        return null;
    }

    /**
     * Check a device in all of the labs and return the lab that has the device available to rent
     * @param md
     * @param requestDate
     * @param dueDate
     * @return
     */
    public Lab rentDeviceAvailable(MobileDevice md, String requestDate, String dueDate) {
        Lab foundLab = null;
        for (Lab lab : labs){
            if(lab.rentRequest(md,requestDate,dueDate)){
                return lab;
            }
        }
        return foundLab;
    }

    //Setters & Getters
    public Lab[] getLabs() {
        return labs;
    }

    public void setLabs(Lab[] labs) {
        this.labs = labs;
    }

    public int getNumberOfLabs() {
        return numberOfLabs;
    }

    public void setNumberOfLabs(int numberOfLabs) {
        this.numberOfLabs = numberOfLabs;
    }


    @Override
    public String toString() {
        String r = "";
        if (numberOfLabs > 0 ){
            for (Lab l : labs){
                r += "Lab = " + l.labName + "\n[\n";
                r +=  l.toString();
                r += "]\n";
            }
        }
        else{
            System.out.println("No labs created!");
        }
        return r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Labs)) return false;
        Labs test = (Labs) o;
        if (numberOfLabs != test.numberOfLabs) return false;
        if (!(Arrays.deepEquals(labs, test.labs))) return false;
        return true;
    }
}

/**
 * Name: ANCHETA, Jesus Jr
 * Course: JAC444SAA
 * Project: Assignment2_3
 * Date: 11/07/2016
 */
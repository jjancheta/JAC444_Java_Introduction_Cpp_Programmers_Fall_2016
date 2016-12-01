package Ancheta017.java;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;


/**
 * This class holds the test data.
 */
public class Main {

    public static void main(String[] args) {

	   /**
        *  TASK 1 - build labs from files - at least two labs
        */

        System.out.println("\n\n *" + " TASK 1 " + "*");
        Labs labs = new Labs(2);
        labs.labs[0] = labs.addDevicesToLab("Seneca@York", "YorkLab.txt");
        labs.labs[1] = labs.addDevicesToLab("Newham", "NewhamLab.txt");


        /**
         * TASK 2 - ask for a device that is not in any lab inventory
         */

        System.out.println("\n\n *" + " TASK 2 " + "*");

        MobileDevice unknown = new MobileDevice("Unknown", 20);
        Lab foundTask2 = labs.isThereDeviceInLabs(unknown);
        if (foundTask2 == null){
            System.out.println(Helper.printNonexistent(unknown));
        }


        /**
         * TASK 3 - ask for a device that is in a lab inventory
         *  issue a rent request and print the device
         *  issue the same rent request and print the device
         *  return the device
         *  issue the rent request with new dates and print the device
         */
        System.out.println("\n\n *" + " TASK 3 " + "*");

        MobileDevice wantedDevice = new MobileDevice("Android8",65);
        String rentDate = "10/30/2016";
        String dueDate = "11/10/2016";

        //issue a rent request and print the device object : 3 times
        for(int i = 0 ; i < 3 ; i++) {
            if (labs.labs[0].rentRequest(wantedDevice, rentDate, dueDate)) {
                System.out.println(Helper.printAvailable(wantedDevice, rentDate, labs.labs[0]));
                if (labs.labs[0].searchDevice(wantedDevice).rentDevice(rentDate, dueDate, labs.labs[0])) {
                    System.out.println("wanted=" + labs.labs[0].searchDevice(wantedDevice).toString());
                }
            } else {
                if (labs.labs[0].searchDevice(wantedDevice) == null) {
                    System.out.println(Helper.printNonexistent(wantedDevice));
                } else {
                    System.out.println(Helper.printUnavailable(labs.labs[0].searchDevice(wantedDevice), rentDate));
                }
            }

            if (i == 1){
                //return the device
                wantedDevice.returnDevice(labs.labs[0]);

                //issue the same rent request with new date and print the object
                rentDate = "11/05/2016";
                dueDate = "11/17/2016";
            }
        }


         /* TASK 4 - ask for the same device in all labs
          * if you can find a lab, rent the device from that lab
          */
        System.out.println("\n\n *" + " TASK 4 " + "*");

        //select all labs where the device is located and prints details
        Vector<Lab> labsFound = new Vector<>();
        for (Lab lab : labs.labs){
            if (lab.isThereDevice(wantedDevice)){
                labsFound.add(lab);
            }
        }

        if(labsFound.size() > 0 ) {
            int countFound = 0;
            System.out.println("Device" + wantedDevice + "is in the following lab(s):");
            for (Lab lab : labsFound) {
                System.out.print("\t" + ++countFound + ". " + lab.getLabName() + " - ");
                if (wantedDevice.isRented(lab)) {
                    System.out.println(lab.searchDevice(wantedDevice).getRs());
                } else {
                    System.out.println("available to rent.");
                }
            }
        }
        else{
            System.out.println(Helper.printNonexistent(wantedDevice));
        }

        //select all labs where the device is available to rent and print details
        Vector<Lab> labsAvailable = new Vector<>();
        for (Lab lab : labs.labs){
            if(lab.searchDevice(wantedDevice) != null) {
                if (!wantedDevice.isRented(lab)) {
                    labsAvailable.add(lab);
                }
            }
        }

        if(labsAvailable.size() > 0 ) {
            int countAvailable = 0;
            System.out.println("\nDevice" + wantedDevice + "is available to rent at:");
            for (Lab lab : labsAvailable) {
                System.out.println("\t" + ++countAvailable + ". " + lab.getLabName());
            }
        }
        else{
            System.out.println(Helper.printNonexistent(wantedDevice));
        }

        //rent the same device in Newham lab : 3 times
        System.out.println("\n------------------------------------------------------\n");

        Lab labAvailable = labs.rentDeviceAvailable(wantedDevice,rentDate,dueDate);
        String rentDateNewham = "10/30/2016";
        String dueDateNewham = "11/30/2016";

        for (int i = 0; i < 3; i++) {
            if (labAvailable.rentRequest(wantedDevice, rentDateNewham, dueDateNewham)) {
                System.out.println(Helper.printAvailable(wantedDevice, rentDateNewham, labAvailable));
                if (labAvailable.searchDevice(wantedDevice).rentDevice(rentDateNewham, dueDateNewham, labAvailable)) {
                    System.out.println("wanted=" + labAvailable.searchDevice(wantedDevice).toString());
                }
            } else {
                if (labAvailable.searchDevice(wantedDevice) == null) {
                    System.out.println(Helper.printNonexistent(wantedDevice));
                } else {
                    System.out.println(Helper.printUnavailable(labAvailable.searchDevice(wantedDevice), rentDateNewham));
                }
            }

            if(i == 1){
                //return the device to Newham lab
                wantedDevice.returnDevice(labAvailable);

                //issue the rent request to the same device to Newham lab with different dates
                rentDateNewham = "12/01/2016";
                dueDateNewham = "12/18/2016";
            }
        }



        /**
         *  TASK 5 - calculate maximum value tag for each lab
         */
        System.out.println("\n\n *" + " TASK 5 " + "*");
        for (Lab l : labs.getLabs()){
            System.out.println("Lab => " + l.getLabName() + ", Maximum Value => " + l.findMaximumValueTag());
        }



        /**
         *  TASK 6 - inquire about a device
         */
        System.out.println("\n\n *" + " TASK 6 " + "*");

        Vector<Lab> labsInquiry = new Vector<>();
        for (Lab lab : labs.labs){
            if (lab.isThereDevice(wantedDevice)){
                labsInquiry.add(lab);
            }
        }

        System.out.println("Inquire device: " + wantedDevice + ":" + "\n\nLab(s) found: " + labsInquiry.size());

        for (Lab lab : labsInquiry){
            System.out.println("\tLab => " + lab.getLabName() + ":");
            if(wantedDevice.isRented(lab)){
                System.out.println("\t\t  Rented      : Yes");
                System.out.println("\t\t  Overdue     : " + (lab.searchDevice(wantedDevice).isDeviceOverdue() ? "Yes" : "No"));
                System.out.println("\t\t  Return date : " + lab.searchDevice(wantedDevice).availableDate(lab));
            }
            else{
                System.out.println("\t\t  Rented: No");
            }

            System.out.println("\t-------------------------------\n");
        }


         /**
          * TASK 7 - find the lab closest to requested date if all devices were currently rented
          */
        System.out.println("\n\n *" + " TASK 7 " + "*BONUS*");
        String newRentDateRequest = "12/27/2016";
        String newDueDateRequest = "12/30/2016";

        //check device in all labs and return lab where the device is available to rent
        Lab newRequest = labs.rentDeviceAvailable(wantedDevice,newRentDateRequest,newDueDateRequest);

        if( newRequest == null){
            System.out.println("Device " + wantedDevice + "is all rented in all labs.");
            Lab closestAvailable = null;
            long max = 0;
            long maxDiff = 365;
            System.out.println("Request date: " + newRentDateRequest);
            for(Lab lab : labs.labs){
                try {
                  long timeDifference = Helper.timeDifference(lab.searchDevice(wantedDevice).availableDate(lab),newRentDateRequest);
                    //System.out.println(lab.getLabName() + ": " + timeDifference);
                    //negative timeDiff : newRentDateRequest < available date
                   if (timeDifference >= 0 && timeDifference <= maxDiff) {
                       maxDiff = timeDifference;
                       closestAvailable = lab;
                   }

                } catch (DateFormatException e) {
                    e.printStackTrace();
                }
            }

            if(closestAvailable != null) {
                System.out.println("Device " + wantedDevice + " will be available at "
                                     + closestAvailable.getLabName() +" lab on "
                                     + closestAvailable.searchDevice(wantedDevice).availableDate(closestAvailable)
                                     + " which is " + maxDiff + " day(s) before your request date.");
            }
            else{
                System.out.println("Device " + wantedDevice + " is due to return after your request date: " + newRentDateRequest);
            }
        }

    }
}


/**
 * Name: ANCHETA, Jesus Jr
 * Course: JAC444SAA
 * Project: Assignment2_3
 * Date: 11/07/2016
 */
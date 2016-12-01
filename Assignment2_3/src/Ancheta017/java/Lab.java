package Ancheta017.java;

import java.util.Iterator;
import java.util.Vector;

/**
 * This class holds a list of devices in a lab.
 */

public class Lab implements MaxTagValue{
    String labName;
    Vector<MobileDevice> devices;

    /**
     * Constructor
     * @param labName
     */
    public Lab(String labName) {
        this.labName = labName;
        this.devices = new Vector<>();
    }

    /**
     * Add a new device object to the list of devices
     * @param md
     */
    public void addDevice(MobileDevice md) {
        devices.add(md);
    }


    /**
     * Check if a device can be found in the list of devices
     * @param md
     * @return true or false
     */
    public boolean isThereDevice(MobileDevice md) {
        boolean found = false;
        if(searchDevice(md) != null){
          found = true;
        }
        return found;
    }

    /**
     * Check if a device is available to rent in a lab
     * @param wanted
     * @param requestDate
     * @param dueDate
     * @return true or false
     */
    public boolean rentRequest(MobileDevice wanted, String requestDate, String dueDate) {
        MobileDevice device = searchDevice(wanted);
        if (device != null && !device.isRented(this)){
            return true;                                           //device is available to rent
        }
        return false;                                              //device is NOT available to rent or null

    }

    /**
     * Finds the highest valueTag in the list of devices
     * @return integer
     */
    public int findMaximumValueTag() {
        int max = 0;
        for (MobileDevice d : devices){
            if(d.getValueTag() > max){
                max = d.getValueTag();
            }
        }
        return max;
    }

    /**
     * Searches a device in the list of devices
     * @param md
     * @return MobileDevice object
     */
    public MobileDevice searchDevice(MobileDevice md){
        for (MobileDevice device : devices){
            if (device.getDeviceName().equals(md.getDeviceName()) && device.getValueTag() == md.getValueTag()) {
                return device;
            }
        }
        return null;
    }


    //Setters & Getters
    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {

        this.labName = labName;
    }


    @Override
    public String toString() {
        String r = "";
        for (MobileDevice s : devices){
            r += s.toString() + "\n";
        }
        return r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lab)) return false;
        Lab test = (Lab) o;
        if (labName != test.labName) return false;
        if (!(devices.equals(test.devices))) return false;
        return true;

    }

    @Override
    public int hashCode() {
        int result = labName != null ? labName.hashCode() : 0;
        result = 31 * result + (devices != null ? devices.hashCode() : 0);
        return result;
    }
}

/**
 * Name: ANCHETA, Jesus Jr
 * Course: JAC444SAA
 * Project: Assignment2_3
 * Date: 11/07/2016
 */

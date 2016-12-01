package Ancheta017.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class holds the the properties of a device
 */
class MobileDevice {
    String       deviceName;  // the device name
    int          valueTag;    // an integer between -100 and 100
    Lab          lab;         // the lab having this device it its inventory
    RentSettings rs;          // rent settings

    /**
     * Default Constructor
     */
    public MobileDevice() {}

    /**
     * Constructor
     * @param deviceName
     * @param valueTag
     */
    public MobileDevice(String deviceName, int valueTag){
        this.deviceName = deviceName;
        this.valueTag = valueTag;
        try {
            this.rs = new RentSettings();
        } catch (DateFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the rent dates; if dates are not valid catch DateFormatException and return false,
     * if rentDate > dueDate catch RentPeriodException and return false
     * if one the exceptions occur there is no RentSettings object
     * @param rentDate
     * @param dueDate
     * @param lab
     * @return true or false
     */
    public boolean rentDevice(String rentDate, String dueDate, Lab lab){
        MobileDevice deviceToRent = lab.searchDevice(this);
        try {
            deviceToRent.rs = new RentSettings(rentDate, dueDate, lab);
        } catch (DateFormatException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (RentPeriodException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     *  Destroy the RentSettings object for this device
     * @param lab
     */
    public void returnDevice(Lab lab) {
        MobileDevice device = lab.searchDevice(this);
        try {
            if(device != null) {
                device.rs = new RentSettings();
            }
        } catch (DateFormatException e) {
            e.printStackTrace();
        }
    }


    /**
     * Return the date when this device is available in the lab
     * @param lab
     * @return dueDate
     */
    public String availableDate(Lab lab) {
        RentSettings temp = null;
        MobileDevice device = lab.searchDevice(this);
        temp = device.getRs();
        return temp.getDueDate();
    }

    /**
     * Returns true if the current date is greater than the due date
     * @return true or false
     */
    public boolean isDeviceOverdue() {
        RentSettings temp = this.getRs();
        String currentDate = Helper.getCurrentDate();

        try {
            if(Helper.timeDifference(currentDate, temp.getDueDate()) > 0) {
                return false;
            }
        } catch (DateFormatException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Check if the device is rented.
     * @param lab
     * @return true or false
     */
    public boolean isRented(Lab lab) {
        MobileDevice device = lab.searchDevice(this);
        if (device != null && device.isNotAvailable()){
            return true;
        }
        return false;
    }

    /**
     * Returns the device name and value tag of a mobile device object
     * @return string
     */
    public String deviceName() {
        return "(" + deviceName + "," + valueTag + ')';
    }


    //Setters and Getters
    public String getDeviceName() { return deviceName;}

    public int getValueTag() {
        return valueTag;
    }

    public Lab getLab() {
        return lab;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setValueTag(int valueTag) {
        this.valueTag = valueTag;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public RentSettings getRs() {
        return rs;
    }

    public void setRs(RentSettings rs) {
        this.rs = rs;
    }

    public boolean isNotAvailable(){
        return rs.isBorrowed();
    }

    public String getLabName(){
        return lab.getLabName();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MobileDevice)) return false;
        MobileDevice test = (MobileDevice) o;
        if (deviceName != test.deviceName) return false;
        if (valueTag != test.valueTag) return false;
        if(lab != test.lab) return false;
        if(rs != test.rs) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = deviceName != null ? deviceName.hashCode() : 0;
        result = 31 * result + valueTag;
        return result;
    }

    @Override
    public String toString() {
        String s = "";
        if (lab == null) {
            s = deviceName();
        }
        else{
            s = "(" + deviceName + "," + valueTag + "=>" + lab.getLabName() + ") ";
            if(rs.isBorrowed()) {
                s += this.rs.toString();
            }
        }
        return s;
    }



    private class RentSettings {
        private String rentDate;                    // date when the item is requested
        private String dueDate;                     // date when the item must be returned
        private boolean borrowed = false;           // true if the item is rented

        /**
         * Default Constructor
         * @throws DateFormatException
         */
        private RentSettings() throws DateFormatException {
            this.rentDate = "";
            this.dueDate = "";
        }

        /**
         * Constuctor
         * @param rentDate
         * @param dueDate
         * @param lab
         * @throws DateFormatException
         * @throws RentPeriodException
         */
        private RentSettings(String rentDate, String dueDate, Lab lab) throws DateFormatException, RentPeriodException {
                Helper.checkDate(rentDate);
                Helper.checkDate(dueDate);
                if (Helper.timeDifference(rentDate, dueDate) > 0) {
                    this.rentDate = rentDate;
                    this.dueDate = dueDate;
                    this.borrowed = true;
                }
                else {
                    throw new RentPeriodException("Invalid due date: " + dueDate + ", it should be higher than rent date.");
                }
        }

        //Setters and Getters
        public String getRentDate() {
            return rentDate;
        }

        public void setRentDate(String rentDate) {
            this.rentDate = rentDate;
        }

        public String getDueDate() {
            return dueDate;
        }

        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }

        public boolean isBorrowed() { return borrowed; }

        public void setBorrowed(boolean borrowed) {
            this.borrowed = borrowed;
        }

        @Override
        public String toString() {
            return "RentSettings{" +
                    "rentDate='" + rentDate + '\'' +
                    ", dueDate='" + dueDate + '\'' + ", " + MobileDevice.this.lab.labName +
                    ", borrowed=" + borrowed +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof RentSettings)) return false;
            RentSettings test = (RentSettings) o;
            if (rentDate != test.rentDate) return false;
            if (dueDate != test.dueDate) return false;
            if(borrowed != test.borrowed) return false;
            return true;
        }

        @Override
        public int hashCode() {
            int result = rentDate != null ? rentDate.hashCode() : 0;
            result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
            return result;
        }
    }
}


/**
 * Name: ANCHETA, Jesus Jr
 * Course: JAC444SAA
 * Project: Assignment2_3
 * Date: 11/07/2016
 */

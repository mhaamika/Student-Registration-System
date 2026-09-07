package myvc;

import java.util.ArrayList;

public class Member {

    // Member information.
    String firstName;
    String lastName;
    int year;
    int month;
    int day;
    String gender;
    String address;
    String city;
    String postalCode;
    String phoneNumber;

    // Unique membership number.
    int membershipNumber;

    // Stores tournament periods.
    ArrayList<Integer> periods;

    public Member(
            String firstName,
            String lastName,
            int year,
            int month,
            int day,
            String gender,
            String address,
            String city,
            String postalCode,
            String phoneNumber,
            int membershipNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
        this.month = month;
        this.day = day;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.membershipNumber = membershipNumber;

        periods = new ArrayList<Integer>();
    }

    // Returns the member's full name.
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getMembershipNumber() {
        return membershipNumber;
    }

    // Registers the member for a tournament period.
    public boolean registerForPeriod(int period) {

        // Only periods 1, 2 and 3 are available.
        if (period < 1 || period > 3) {
            return false;
        }

        // Prevent duplicate registrations.
        if (periods.contains(period)) {
            return false;
        }

        periods.add(period);

        return true;
    }

    // Returns the member's registered tournament periods.
    public String getRegisteredPeriods() {

        if (periods.size() == 0) {
            return "None";
        }

        String result = "";

        for (int i = 0; i < periods.size(); i++) {

            if (i > 0) {
                result += ", ";
            }

            result += "Period " + periods.get(i);
        }

        return result;
    }

    // Adds a period when loading information from the file.
    public void loadPeriod(int period) {

        if (period >= 1 && period <= 3 && !periods.contains(period)) {

            periods.add(period);
        }
    }

    // Creates the tournament information used when saving to a file.
    public String getPeriodsForFile() {

        String result = "";

        for (int i = 0; i < periods.size(); i++) {

            if (i > 0) {
                result += ",";
            }

            result += periods.get(i);
        }

        return result;
    }
}

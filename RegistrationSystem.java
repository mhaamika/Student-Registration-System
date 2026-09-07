package myvc;

import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class RegistrationSystem {
    // Stores all registered members.
    ArrayList<Member> members;

    // Stores the family information for each member.
    ArrayList<Family> families;

    // Membership numbers start at 1001.
    int nextMembershipNumber;

    //no argument constructor for Registration System
    public RegistrationSystem() {

        members = new ArrayList<Member>();
        families = new ArrayList<Family>();

        nextMembershipNumber = 1001;
    }

    // Adds a member and family information to the system.
    public boolean addMember(Member member, Family family)
    {
        if (members.size() >= 20)
        {
            return false;
        }
        members.add(member);
        families.add(family);
        return true;
    }

    // Creates a member and assigns a membership number.
    public Member createMember(String firstName, String lastName, int year, int month, int day, String gender, String address, String city, String postalCode, String phoneNumber, Family family)
    {
        Member member = new Member(firstName, lastName, year, month, day, gender, address, city, postalCode, phoneNumber, nextMembershipNumber);

        if (addMember(member, family))
        {
            nextMembershipNumber++;
            return member;
        }

        return null;
    }

    // Finds a member using their membership number.
    public Member findMember(int membershipNumber) {

        for (int i = 0; i < members.size(); i++) {

            if (members.get(i).getMembershipNumber()
                    == membershipNumber)
            {
                return members.get(i);
            }
        }

        return null;
    }

    // Sorts members alphabetically using bubble sort.
    public void sortAlphabetically() {

        for (int i = 0; i < members.size() - 1; i++) {

            for (int j = 0; j < members.size() - i - 1; j++) {

                String firstName = members.get(j).getFullName();

                String secondName = members.get(j + 1).getFullName();

                if (firstName.compareToIgnoreCase(secondName) > 0) {

                    // Swap the members.
                    Member temp = members.get(j);

                    members.set(j, members.get(j + 1));
                    members.set(j + 1, temp);

                    // Keep the family information matched.
                    Family familyTemp = families.get(j);

                    families.set(j, families.get(j + 1));
                    families.set(j + 1, familyTemp);
                }
            }
        }
    }

    // Finds the oldest member.
    public Member getOldestMember() {

        if (members.size() == 0) {
            return null;
        }

        Member oldest = members.get(0);

        for (int i = 1; i < members.size(); i++) {

            Member current = members.get(i);

            if (isOlder(current, oldest)) {
                oldest = current;
            }
        }

        return oldest;
    }

    // Finds the youngest member.
    public Member getYoungestMember() {

        if (members.size() == 0) {
            return null;
        }

        Member youngest = members.get(0);

        for (int i = 1; i < members.size(); i++) {

            Member current = members.get(i);

            if (isYounger(current, youngest)) {
                youngest = current;
            }
        }

        return youngest;
    }

    // Checks whether the first member was born earlier.
    public boolean isOlder(Member first, Member second) {

        if (first.getYear() != second.getYear()) {
            return first.getYear() < second.getYear();
        }

        if (first.getMonth() != second.getMonth()) {
            return first.getMonth() < second.getMonth();
        }

        return first.getDay() < second.getDay();
    }

    // Checks whether the first member was born later.
    public boolean isYounger(Member first, Member second) {

        if (first.getYear() != second.getYear()) {
            return first.getYear() > second.getYear();
        }

        if (first.getMonth() != second.getMonth()) {
            return first.getMonth() > second.getMonth();
        }

        return first.getDay() > second.getDay();
    }

    // Returns the number of registered members.
    public int getMemberCount() {
        return members.size();
    }

    // Counts male members.
    public int getBoysCount() {

        int count = 0;

        for (int i = 0; i < members.size(); i++) {

            if (members.get(i).getGender().equalsIgnoreCase("M")) {
                count++;
            }
        }

        return count;
    }

    // Counts female members.
    public int getGirlsCount() {

        int count = 0;

        for (int i = 0; i < members.size(); i++) {

            if (members.get(i).getGender().equalsIgnoreCase("F")) {
                count++;
            }
        }

        return count;
    }

    // Gives access to the list of members.
    public ArrayList<Member> getMembers() {
        return members;
    }


    // =====================================================
    // SAVE MEMBER INFORMATION
    // =====================================================

    public void saveToFile(String fileName) {

        try {

            PrintWriter output = new PrintWriter(fileName);

            // Save every member on a separate line.
            for (int i = 0; i < members.size(); i++) {

                Member member = members.get(i);
                Family family = families.get(i);

                /*
                 * Each | separates a different piece of information.
                 */
                output.println(member.getMembershipNumber() + "|" +
                                member.getFirstName() + "|" +
                                member.getLastName() + "|" +
                                 member.getYear() + "|" +
                                member.getMonth() + "|" +
                                member.getDay() + "|" +
                                member.getGender() + "|" +
                                member.getAddress() + "|" +
                                member.getCity() + "|" +
                                member.getPostalCode() + "|" +
                                member.getPhoneNumber() + "|" +
                                family.getRelation() + "|" +
                                member.getPeriodsForFile()
                );
            }

            output.close();

        } catch (Exception e) {

            System.out.println(
                    "Error saving member information."
            );
        }
    }


    // =====================================================
    // LOAD MEMBER INFORMATION
    // =====================================================

    public void loadFromFile(String fileName) {

        File file = new File(fileName);

        // If the file doesn't exist, there is nothing to load.
        if (!file.exists()) {
            return;
        }

        try {

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();

                if (line.length() == 0) {
                    continue;
                }

                /*
                 * Split the saved line into separate pieces.
                 */
                String[] data = line.split("\\|");

                if (data.length < 13) {
                    continue;
                }

                int membershipNumber = Integer.parseInt(data[0]);

                String firstName = data[1];
                String lastName = data[2];

                int year = Integer.parseInt(data[3]);

                int month = Integer.parseInt(data[4]);

                int day = Integer.parseInt(data[5]);

                String gender = data[6];
                String address = data[7];
                String city = data[8];
                String postalCode = data[9];
                String phoneNumber = data[10];
                String relation = data[11];

                // Recreate the Member object.
                Member member = new Member(firstName, lastName, year, month, day, gender, address, city, postalCode, phoneNumber, membershipNumber);

                // Restore tournament registrations.
                if (data[12].length() > 0) {

                    String[] savedPeriods = data[12].split(",");

                    for (int i = 0; i < savedPeriods.length; i++) {

                        int period = Integer.parseInt(savedPeriods[i]);

                        member.loadPeriod(period);
                    }
                }

                // Recreate the Family object.
                Family family = new Family(
                        firstName,
                        lastName,
                        relation,
                        address,
                        city,
                        postalCode,
                        phoneNumber
                );

                // Add the loaded objects to the system.
                members.add(member);
                families.add(family);

                // Make sure future membership numbers are unique.
                if (membershipNumber >= nextMembershipNumber) {

                    nextMembershipNumber =
                            membershipNumber + 1;
                }
            }

            fileScanner.close();

        } catch (Exception e) {

            System.out.println(
                    "Error loading member information."
            );
        }
    }
}

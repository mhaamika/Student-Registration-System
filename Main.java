package myvc;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    // Scanner gets input from the user.
    static Scanner scanner = new Scanner(System.in);

    // This object manages the members.
    static RegistrationSystem system = new RegistrationSystem();

    // Name of the file used to save member information.
    static String fileName = "members.txt";

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        // Load previously saved members when the program starts.
        system.loadFromFile(fileName);

        System.out.println("==============================================");
        System.out.println("   YOUTH VOLLEYBALL CLUB REGISTRATION SYSTEM");
        System.out.println("==============================================");

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    registerMember();
                    break;

                case 2:
                    displayMembers();
                    break;

                case 3:
                    displayMembersAlphabetically();
                    break;

                case 4:
                    registerForTournament();
                    break;

                case 5:
                    displayStatistics();
                    break;

                case 6:
                    searchMember();
                    break;

                case 7:
                    system.saveToFile(fileName);
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please choose 1-7.");
            }
        }

        System.out.println();
        System.out.println("Thank you for using the MYVC Registration System!");

        scanner.close();
    }



    /**
     * Displays main menu
     */
    public static void displayMenu() {

        System.out.println();
        System.out.println("--------------- MAIN MENU ----------------");
        System.out.println("1. Register new member");
        System.out.println("2. View all members");
        System.out.println("3. View members alphabetically");
        System.out.println("4. Register for tournament");
        System.out.println("5. View club statistics");
        System.out.println("6. Search for member");
        System.out.println("7. Save and exit");
        System.out.println("------------------------------------------");
    }


    /**
     *  Collects information and registers a new member.
     */
    public static void registerMember() {

        if (system.getMemberCount() >= 20) {
            System.out.println("The club has reached the maximum of 20 members.");
            return;
        }

        System.out.println();
        System.out.println("---------- NEW MEMBER REGISTRATION ----------");

        String firstName = readName("First name: ");
        String lastName = readName("Last name: ");

        String relation = readRequired("Parent/guardian relationship: ");

        int year;
        int month;
        int day;

        /**
         * Repeats until a valid birthday is entered
         */
        do {

            year = readInt("Birth year (2007-2014): ");
            month = readInt("Birth month (1-12): ");
            day = readInt("Birth day (1-31): ");

            if (!validBirthday(year, month, day)) {
                System.out.println("Invalid birthday. Please try again.");
            }

        } while (!validBirthday(year, month, day));

        String gender = readGender();

        String address = readAddress();

        String city = readCity();

        String postalCode = readPostalCode();

        String phone = readPhone();

        // Create the family/guardian object.
        Family family = new Family(
                firstName,
                lastName,
                relation,
                address,
                city,
                postalCode,
                phone
        );

        // Create and add the member.
        Member member = system.createMember(
                firstName,
                lastName,
                year,
                month,
                day,
                gender,
                address,
                city,
                postalCode,
                phone,
                family
        );

        if (member == null) {
            System.out.println("Unable to register member.");
            return;
        }

        System.out.println();
        System.out.println("Member successfully registered!");
        System.out.println("Name: " + member.getFullName());
        System.out.println("Membership #: " + member.getMembershipNumber());

        // Ask if the member wants to register for a tournament.
        registerForTournament(member);

        // Save the updated information.
        system.saveToFile(fileName);
    }

    /**
     *  Displays all registered members.
      */
    public static void displayMembers() {

        ArrayList<Member> members = system.getMembers();

        if (members.size() == 0) {
            System.out.println("No members have been registered.");
            return;
        }

        System.out.println();
        System.out.println("--------------- ALL MEMBERS ---------------");

        for (int i = 0; i < members.size(); i++) {
            displayMember(members.get(i));
        }
    }

    /**
       Displays information for one member
     */
    public static void displayMember(Member member) {

        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Name: " + member.getFullName());
        System.out.println("Membership #: " + member.getMembershipNumber());

        System.out.println(
                "Birthday: "
                        + member.getMonth() + "/"
                        + member.getDay() + "/"
                        + member.getYear()
        );

        System.out.println("Gender: " + member.getGender());
        System.out.println("Address: " + member.getAddress());
        System.out.println("City: " + member.getCity());
        System.out.println("Postal Code: " + member.getPostalCode());
        System.out.println("Phone: " + member.getPhoneNumber());

        System.out.println(
                "Tournament Periods: "
                        + member.getRegisteredPeriods()
        );
    }

    /**
     * Sorts and displays members alphabetically.
     */
    public static void displayMembersAlphabetically() {

        if (system.getMemberCount() == 0) {
            System.out.println("No members have been registered.");
            return;
        }

        system.sortAlphabetically();

        ArrayList<Member> members = system.getMembers();

        System.out.println();
        System.out.println("------- MEMBERS ALPHABETICALLY -------");

        for (int i = 0; i < members.size(); i++) {

            System.out.println(
                    (i + 1) + ". " + members.get(i).getFullName()
            );
        }
    }

    /**
     *  Searches for a member using their membership number.
     */

    public static void searchMember() {

        int number = readInt("Enter membership #: ");

        Member member = system.findMember(number);

        if (member == null) {
            System.out.println("Member was not found.");
        } else {
            displayMember(member);
        }
    }

    /**
     *  Allows the user to choose a member for tournament registration.
     */
    public static void registerForTournament() {

        if (system.getMemberCount() == 0) {
            System.out.println("No members have been registered.");
            return;
        }

        int number = readInt("Enter membership #: ");

        Member member = system.findMember(number);

        if (member == null) {
            System.out.println("Member was not found.");
            return;
        }

        registerForTournament(member);
    }


    /**
     *  Registers a specific member for tournament periods.
     */
    public static void registerForTournament(Member member) {

        String answer;

        do {

            System.out.println();
            System.out.println("--------- TOURNAMENT PERIODS ---------");
            System.out.println("1. Period 1: June 2-27");
            System.out.println("2. Period 2: July 7-31");
            System.out.println("3. Period 3: August 4-29");

            int period = readInt("Choose a period (1-3): ");

            if (member.registerForPeriod(period)) {

                System.out.println(
                        "Successfully registered for Period " + period + "."
                );

                system.saveToFile(fileName);

            } else {

                System.out.println(
                        "Invalid period or member is already registered."
                );
            }

            answer = readRequired(
                    "Register for another period? (yes/no): "
            );

        } while (answer.equalsIgnoreCase("yes"));
    }


    /**
     *  Displays basic statistics about the club.
     */
    public static void displayStatistics() {

        System.out.println();
        System.out.println("----------- CLUB STATISTICS -----------");

        System.out.println(
                "Total members: " + system.getMemberCount()
        );

        System.out.println(
                "Boys: " + system.getBoysCount()
        );

        System.out.println(
                "Girls: " + system.getGirlsCount()
        );

        Member oldest = system.getOldestMember();
        Member youngest = system.getYoungestMember();

        if (oldest != null) {
            System.out.println(
                    "Oldest member: " + oldest.getFullName()
            );
        }

        if (youngest != null) {
            System.out.println(
                    "Youngest member: " + youngest.getFullName()
            );
        }
    }

    // Makes sure the user enters a valid name.
    public static String readName(String message) {

        while (true) {

            String name = readRequired(message);

            if (name.length() >= 2) {
                return name;
            }

            System.out.println(
                    "Name must contain at least 2 characters."
            );
        }
    }

    // Makes sure the address is valid.
    public static String readAddress() {

        while (true) {

            String address = readRequired("Address: ");

            if (address.length() >= 2) {
                return address;
            }

            System.out.println("Invalid address.");
        }
    }

    // Makes sure the city is valid.
    public static String readCity() {

        while (true) {

            String city = readRequired("City: ");

            if (city.length() >= 2) {
                return city;
            }

            System.out.println("Invalid city.");
        }
    }

    // Makes sure the postal code has six characters.
    public static String readPostalCode() {

        while (true) {

            String postalCode = readRequired("Postal code: ");

            if (postalCode.length() == 6) {
                return postalCode.toUpperCase();
            }

            System.out.println(
                    "Postal code must contain 6 characters."
            );
        }
    }

    // Makes sure the phone number contains at least 10 digits.
    public static String readPhone() {

        while (true) {

            String phone = readRequired("Phone number: ");

            String digits = phone.replaceAll("\\D", "");

            if (digits.length() >= 10) {
                return phone;
            }

            System.out.println(
                    "Phone number must contain at least 10 digits."
            );
        }
    }

    // Makes sure the user enters M or F.
    public static String readGender() {

        while (true) {

            String gender = readRequired("Gender (M/F): ");

            if (gender.equalsIgnoreCase("M")
                    || gender.equalsIgnoreCase("F")) {

                return gender.toUpperCase();
            }

            System.out.println("Please enter M or F.");
        }
    }

    // Makes sure the user does not enter an empty value.
    public static String readRequired(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (input.length() > 0) {
                return input;
            }

            System.out.println("Input cannot be empty.");
        }
    }

    // Reads an integer from the user.
    public static int readInt(String message) {

        while (true) {

            System.out.print(message);

            try {

                return Integer.parseInt(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
            }
        }
    }

    /**
     * Checks whether the birthday is within the allowed range.
     */

    public static boolean validBirthday(int year, int month, int day) {

        return year >= 2007
                && year <= 2014
                && month >= 1
                && month <= 12
                && day >= 1
                && day <= 31;
    }
}

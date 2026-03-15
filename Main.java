import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

class Employee {

    String id;
    String name;
    String dob;
    String email;
    LocalDate joiningDate;
    String type;

    int vacationLeave;
    int sickLeave;

    public Employee(String id, String name, String dob, String email, LocalDate joiningDate, String type) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.joiningDate = joiningDate;
        this.type = type;

        calculateLeave();
    }

    void calculateLeave() {

        int year = 2025;
        int totalDays = 365;

        if (LocalDate.of(year,1,1).isLeapYear()) {
            totalDays = 366;
        }

        LocalDate endDate = LocalDate.of(year, 12, 31);

        long remainingDays = ChronoUnit.DAYS.between(joiningDate, endDate) + 1;

        int totalVacation = 0;
        int totalSick = 0;

        if (type.equalsIgnoreCase("Officer")) {
            totalVacation = 15;
            totalSick = 10;
        } else {
            totalVacation = 10;
            totalSick = 7;
        }

        double vacationCalc = (remainingDays * totalVacation) / (double) totalDays;
        double sickCalc = (remainingDays * totalSick) / (double) totalDays;

        vacationLeave = roundValue(vacationCalc);
        sickLeave = roundValue(sickCalc);
    }

    int roundValue(double value) {

        if (value - Math.floor(value) < 0.5) {
            return (int)Math.floor(value);
        } else {
            return (int)Math.ceil(value);
        }
    }

    void display() {

        System.out.println("\nEmployee Information");
        System.out.println("---------------------");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Date of Birth: " + dob);
        System.out.println("Email: " + email);
        System.out.println("Joining Date: " + joiningDate);
        System.out.println("Employee Type: " + type);
        System.out.println("Vacation Leave: " + vacationLeave);
        System.out.println("Sick Leave: " + sickLeave);
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for (int i = 1; i <= 3; i++) {

            System.out.println("\nEnter Employee " + i + " Information");

            System.out.print("ID: ");
            String id = sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Date of Birth: ");
            String dob = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Joining Date (YYYY-MM-DD): ");
            LocalDate joiningDate = LocalDate.parse(sc.nextLine());

            System.out.print("Employee Type (Officer/Staff): ");
            String type = sc.nextLine();

            Employee emp = new Employee(id, name, dob, email, joiningDate, type);

            emp.display();
        }

        sc.close();
    }
}
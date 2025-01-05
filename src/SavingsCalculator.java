import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SavingsCalculator {
    public static void main(String[] args) {
        Scanner scan  = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        double salary = 0.0;
        double averageHours = 0.0;
        int averageDays = 0;
        LocalDate travelDate = null;

        System.out.println("Tell me.. where are you going?");
        String country = scan.nextLine();
        System.out.println("I dont really care but anyway...");

        // Get the salary
        while (true){
            try {
                System.out.println("What is your salary? enter in £##.##p format");
                salary = scan.nextDouble();
                if (salary <= 0){
                    throw new IllegalArgumentException("Salary must be greater than zero");
                }
                System.out.println("You earn " + salary + " p/h");
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid input");
                scan.nextLine(); // clear the invalid input
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // get the average hours
        while (true){
            try {
                System.out.println("How many hours will you work per day on average?");
                averageHours = scan.nextDouble();
                if (averageHours <= 0){
                    throw new IllegalArgumentException("Average hours must be greater than zero");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid input for the average hours");
                scan.nextLine(); // Clear this invalid input
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }

        // Get the average days
        while (true){
            try {
                System.out.println("How many days will you work per week on average?");
                averageDays = scan.nextInt();
                if (averageDays <= 0 || averageDays > 7){
                    throw new IllegalArgumentException("Average days must be between 1 and 7");
                } break;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid input for the average days");
                scan.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }

        // Ask for the travel date
        while (true){
            try {
                System.out.println("When are you travelling? (dd-MM-yyyy)");

                // Clear the scanner buffer if needed
                if (scan.hasNextLine()) {
                    scan.nextLine();
                }

                String travelDateString = scan.nextLine();
                travelDate = LocalDate.parse(travelDateString, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Please enter the travel date in a valid format!");
            }
        }

        // create instance and process calculations
        SavingsCalculator calculator = new SavingsCalculator();
        String result = calculator.howLongUntilTravel(travelDate);
        System.out.println("You have " + result + " days until you go to " + country);

        int daysUntilTravel = Integer.parseInt(result.replaceAll("\\D+", ""));
        int daysWorked = calculator.daysWorked(daysUntilTravel, averageDays);
        System.out.println("You worked " + daysWorked + " days");

        String totalEarned = calculator.totalSalary(salary, daysWorked, averageHours);
        System.out.println(totalEarned);
    }

    public String howLongUntilTravel(LocalDate travelday) {
        LocalDate today = LocalDate.now();
        long daysBetween = travelday.toEpochDay() - today.toEpochDay();
        if (daysBetween < 0) {
            throw new IllegalArgumentException("Travel date must be in the future.");
        }
        return "Days until travel: " + daysBetween;
    }

    public int daysWorked(int totalDays, int averageDays) {
        // Calculate total weeks and multiply by average workdays per week
        return (totalDays / 7) * averageDays;
    }

    public String totalSalary(double salary, int daysWorked, double averageHours) {
        double totalHours = daysWorked * averageHours;
        double total = salary * totalHours;
        double totalBeforeTax = total;
        double totalAfterTax = (total / 100) * 83.28;
        return String.format(
                "You will earn %.2f before tax and %.2f after tax if you work %.2f hours per day",
                totalBeforeTax, totalAfterTax, averageHours
        );
    }
}

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class save {
    public static void main(String[]args)
    {
        Scanner scan = new Scanner(System.in);
        //FORMAT THE DATE
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
        //ASK FOR SALARY
        System.out.println("What is your salary? enter in £##.##p format");
        double salary = scan.nextDouble();
        System.out.println("You earn " + salary + "p/h");



        //ASK FOR AVERAGE HOURS
        System.out.println("How many hours will you work per day on average");
        double averageHours = scan.nextDouble();

        //ASK FOR TRAVEL DATE
        System.out.println("When are you travelling? : dd-MM-yyyy");
        String travelDateString = scan.next();
        LocalDate travelDate = LocalDate.parse(travelDateString, formatter);

        //INSTANCE OF THE CLASS SO I CAN USE METHOD
        save save = new save();
        String result = save.howLongUntilTravel(travelDate);
        System.out.println("You have "+result+" days until you go to Vietnam");

        int daysUntilTravel = Integer.parseInt(result.replaceAll("\\D+", ""));
        int daysWorked = save.daysWorked(daysUntilTravel);
        System.out.println("You worked "+daysWorked+" days");


        String totalEarned = save.totalSalary(salary,daysWorked, averageHours);
        System.out.println(totalEarned);






    }

    public String howLongUntilTravel (LocalDate travelday)
    {
        //GET THE CURRENT DATE
        LocalDate today = LocalDate.now();

        //CALCULATE TIME BETWEEN TWO DATES
        long daysBetween = travelday.toEpochDay() -today.toEpochDay();

        // Print the result
        return "Days until travel: " + daysBetween;

    }

    public int daysWorked(int totaldays)
    {
        return (totaldays/7)*4;
    }

    public String totalSalary(double salary, int daysworked, double averageHours)
    {
        double totalHours = daysworked * averageHours;
        double total =  salary * totalHours;
        double totalBeforeTax = (total);
        double totalAfterTax = (total/100)*83.28;
        return String.format(
                "You will earn %.2f before tax and %.2f after tax if you work %.2f hours per day",
                totalBeforeTax, totalAfterTax, averageHours
        );
    }


}

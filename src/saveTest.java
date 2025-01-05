import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;


class saveTest extends save {

    @Test
    void testHowLongUntilTravel(){
        save saveInstance = new save();

        // simulate a date 10 days from now
        LocalDate today = LocalDate.now();
        LocalDate travelDay = today.plusDays(10);

        String result = saveInstance.howLongUntilTravel(travelDay);

        assertTrue(result.contains("10"), "Days until travel should be 10");
    }

    @Test
    void testDaysWorked() {
        save saveInstance = new save();

        // Test with a total of 28 days
        int daysUntilTravel = 28;
        int expectedDaysWorked = 16; // (28 / 7) * 4
        int result = saveInstance.daysWorked(daysUntilTravel);

        assertEquals(expectedDaysWorked, result, "Worked days should match the expected value");
    }

    @Test
    void testTotalSalary() {
        save saveInstance = new save();

        // Test input
        double salary = 10.50; // £10.50/hour
        int daysWorked = 16;  // Assume 16 working days
        double averageHours = 8; // 8 hours/day

        // Expected outputs
        double totalHours = daysWorked * averageHours;
        double totalBeforeTax = salary * totalHours; // £10.50 * 16 * 8
        double totalAfterTax = (totalBeforeTax / 100) * 83.28;

        // Call the method and verify results
        String result = saveInstance.totalSalary(salary, daysWorked, averageHours);
        assertTrue(result.contains(String.format("%.2f", totalBeforeTax)), "Total before tax should match");
        assertTrue(result.contains(String.format("%.2f", totalAfterTax)), "Total after tax should match");
    }


}
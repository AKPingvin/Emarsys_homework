package duedatecalculator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.Instant;

import static org.junit.Assert.*;

public class DueDateCalculatorServiceTest {

    private DueDateCalculatorService dueDateCalculatorService;

    private static final String submissionTimeString = "2021-06-15T10:15:30Z";
    private static final Instant expectedDueDateTime = Instant.parse("2021-06-22T09:15:30Z");

    @Before
    public void setup() {
        dueDateCalculatorService = new DueDateCalculatorService();
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void calculateDueDate_shallPass() {
        Instant resultDueDateTime = dueDateCalculatorService.calculateDueDate(Instant.parse(submissionTimeString), 39);

        assertEquals(expectedDueDateTime, resultDueDateTime);
    }

    @Test
    public void calculateDueDate_shallFail_onNullTurnAroundHours() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Submission time and turn around hours cannot be null in calculate due date.");
        dueDateCalculatorService.calculateDueDate(Instant.parse(submissionTimeString), null);
    }

    @Test
    public void calculateDueDate_shallFail_onNullSubmissionTime() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Submission time and turn around hours cannot be null in calculate due date.");
        dueDateCalculatorService.calculateDueDate(null, 39);
    }

    @Test
    public void calculateDueDate_shallFail_onNullTurnAroundHoursAndNullSubmissionTime() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Submission time and turn around hours cannot be null in calculate due date.");
        dueDateCalculatorService.calculateDueDate(null, null);
    }

}

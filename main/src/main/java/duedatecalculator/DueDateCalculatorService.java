package duedatecalculator;

import java.time.*;

public class DueDateCalculatorService {

    public Instant calculateDueDate(Instant submissionTime, Integer turnAroundHours) {
        if (submissionTime != null && turnAroundHours != null) {
            LocalDateTime dueDateTime = LocalDateTime.ofInstant(submissionTime, ZoneId.of("UTC"));

            int turnAroundDays = ((turnAroundHours + (dueDateTime.getHour() - (dueDateTime.getMinute() == 0 ? 9 : 8))) / 8);

            for (int turnAroundDayCounter = 0; turnAroundDayCounter < turnAroundDays; turnAroundDayCounter++){
                if (dueDateTime.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                    dueDateTime = dueDateTime.plusDays(3);
                } else {
                    dueDateTime = dueDateTime.plusDays(1);
                }
            }

            int turnAroundPlusHours = turnAroundHours - (turnAroundDays * 8);
            dueDateTime = dueDateTime.plusHours(turnAroundPlusHours);

            return dueDateTime.toInstant(ZoneOffset.UTC);
        }
        throw new IllegalArgumentException("Submission time and turn around hours cannot be null in calculate due date.");
    }

}
package de.quastenflossler.snail.ui.control;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class SprintCalendarGridPane extends VBox {

    private static final int VISIBLE_NR_WEEKS = 5;
    private static final int VISIBLE_DAYS_IN_WEEK = 7;

    private YearMonth currentYearMonth;
    private List<SingleCalendarDayAnchorPane> allCalendarDays = new ArrayList<>(35);
    private Text calendarTitle;

    private LocalDate beginOfFirstSprint;
    private Integer sprintDuration;
    private Boolean sprintChangeDayFlag;

    public SprintCalendarGridPane() {

        currentYearMonth = YearMonth.now();

        GridPane calendar = initCalendar();
        GridPane dayLabels = createDayLabels();
        HBox titleBar = createTitleBar();

        populateCalendar(currentYearMonth);

        getChildren().addAll(titleBar, dayLabels, calendar);
    }

    public LocalDate getBeginOfFirstSprint() {
        return beginOfFirstSprint;
    }

    public void setBeginOfFirstSprint(final LocalDate beginOfFirstSprint) {
        this.beginOfFirstSprint = beginOfFirstSprint;
    }

    public Integer getSprintDuration() {
        return sprintDuration;
    }

    public void setSprintDuration(final Integer sprintDuration) {
        this.sprintDuration = sprintDuration;
    }

    public Boolean getSprintChangeDayFlag() {
        return sprintChangeDayFlag;
    }

    public void setSprintChangeDayFlag(final Boolean sprintChangeDayFlag) {
        this.sprintChangeDayFlag = sprintChangeDayFlag;
    }

    private GridPane initCalendar() {

        GridPane calendar = new GridPane();
        calendar.setPrefSize(800, 600);
        calendar.setGridLinesVisible(true);

        prepareVisibleWeeks(calendar);
        return calendar;
    }

    private void prepareVisibleWeeks(final GridPane calendar) {

        for (int i = 0; i < VISIBLE_NR_WEEKS; i++) {
            for (int j = 0; j < VISIBLE_DAYS_IN_WEEK; j++) {

                SingleCalendarDayAnchorPane dayAnchorPane = new SingleCalendarDayAnchorPane();
                dayAnchorPane.setPrefSize(250, 250);
                calendar.add(dayAnchorPane, j, i);

                allCalendarDays.add(dayAnchorPane);
            }
        }
    }

    private void previousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);
    }

    /**
     * Move the month forward by one. Repopulate the calendar with the correct dates.
     */
    private void nextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);
    }

    private GridPane createDayLabels() {

        Text[] dayNames = new Text[]{new Text("Monday"), new Text("Tuesday"), new Text("Wednesday"),
                new Text("Thursday"), new Text("Friday"), new Text("Saturday"),
                new Text("Sunday")};

        GridPane dayLabels = new GridPane();
        dayLabels.setPrefWidth(600);

        int col = 0;

        for (Text dayText : dayNames) {

            AnchorPane dayLabelPane = new AnchorPane();
            dayLabelPane.setPrefSize(200, 10);
            dayLabelPane.setBottomAnchor(dayText, 5.0);
            dayLabelPane.getChildren().add(dayText);

            dayLabels.add(dayLabelPane, col++, 0);
        }

        return dayLabels;
    }

    private HBox createTitleBar() {

        calendarTitle = new Text();
        Button previousMonth = new Button("<<");
        previousMonth.setOnAction(e -> previousMonth());

        Button nextMonth = new Button(">>");
        nextMonth.setOnAction(e -> nextMonth());

        HBox titleBar = new HBox(previousMonth, calendarTitle, nextMonth);
        titleBar.setAlignment(Pos.BASELINE_CENTER);

        return titleBar;
    }

    public void populateCalendar(final YearMonth yearMonth) {

        resetColors();

        LocalDate firstDayOfMonth = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);

        while (!firstDayOfMonth.getDayOfWeek().toString().equals("MONDAY")) {
            firstDayOfMonth = firstDayOfMonth.minusDays(1);
        }

        for (SingleCalendarDayAnchorPane singleDay : allCalendarDays) {

            if (singleDay.getChildren().size() != 0) {
                singleDay.getChildren().remove(0);
            }

            Text dayText = new Text(String.valueOf(firstDayOfMonth.getDayOfMonth()));

            singleDay.setDate(firstDayOfMonth);
            singleDay.setTopAnchor(dayText, 5.0);
            singleDay.setLeftAnchor(dayText, 5.0);
            singleDay.getChildren().add(dayText);

            firstDayOfMonth = firstDayOfMonth.plusDays(1);
        }

        calendarTitle.setText(yearMonth.getMonth().toString() + " " + yearMonth.getYear());

        colorSprints();
        markBeginOfFirstSprint();
    }

    private void markBeginOfFirstSprint() {

        if (beginOfFirstSprint == null) {
            return;
        }

        if (!currentYearMonth.getMonth().equals(beginOfFirstSprint.getMonth())) {
            return;
        }

        for (SingleCalendarDayAnchorPane dayPane : allCalendarDays) {

            if (dayPane.getDate().equals(beginOfFirstSprint)) {

                BackgroundFill backgroundFill = new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY);
                dayPane.setBackground(new Background(backgroundFill));
            }
        }
    }

    private void resetColors() {

        allCalendarDays.forEach(v -> v.setStyle(null));
        allCalendarDays.forEach(v -> v.setBackground(null));
    }

    private void colorSprints() {

        if (beginOfFirstSprint == null) {
            return;
        }

        // TODO compare dates
        if (currentYearMonth.getMonth().getValue() < beginOfFirstSprint.getMonth().getValue()) {
            return;
        }

        int currDayInSprint = 1;
        sprintDuration = 9;

        for (SingleCalendarDayAnchorPane dayPane : allCalendarDays) {

            if (dayPane.getDate().getDayOfWeek().equals(DayOfWeek.SATURDAY) ||
                    dayPane.getDate().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {

                BackgroundFill backgroundFill = new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY);
                dayPane.setBackground(new Background(backgroundFill));

            } else if(currDayInSprint % sprintDuration == 0) {

                BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY);
                dayPane.setBackground(new Background(backgroundFill));

                currDayInSprint = 0;

            } else if (dayPane.getDate().compareTo(beginOfFirstSprint) >= 0) {

                BackgroundFill backgroundFill = new BackgroundFill(Color.FUCHSIA, CornerRadii.EMPTY, Insets.EMPTY);
                dayPane.setBackground(new Background(backgroundFill));

                currDayInSprint++;
            }
        }
    }

}

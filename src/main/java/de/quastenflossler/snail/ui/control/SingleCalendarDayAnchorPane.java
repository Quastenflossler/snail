package de.quastenflossler.snail.ui.control;

import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

public class SingleCalendarDayAnchorPane extends AnchorPane {

    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }
}

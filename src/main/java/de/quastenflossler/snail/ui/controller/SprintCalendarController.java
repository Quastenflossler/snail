package de.quastenflossler.snail.ui.controller;

import de.quastenflossler.snail.ui.control.SprintCalendarGridPane;
import de.quastenflossler.snail.ui.model.UserPreferencesModel;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.inject.Named;

@Named(value = SprintCalendarController.RESOURCE_NAME)
public class SprintCalendarController {

    public static final String RESOURCE_NAME = "SprintCalendarController";

    private static final Logger LOGGER = LoggerFactory.getLogger(SprintCalendarController.class);

    @Resource(name = UserPreferencesModel.RESOURCE_NAME)
    private UserPreferencesModel userPreferencesModel;

    @FXML
    public SprintCalendarGridPane sprintCalendarVBox;

    public void initialize() {

        sprintCalendarVBox.setBeginOfFirstSprint(userPreferencesModel.getBeginOfFirstSprint());
        sprintCalendarVBox.setSprintDuration(9);
        sprintCalendarVBox.setSprintChangeDayFlag(userPreferencesModel.isSprintChangeDayFlag());
    }
}

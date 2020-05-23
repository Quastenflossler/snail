package de.quastenflossler.snail.ui.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.Locale;

@Named(value = UserPreferencesModel.RESOURCE_NAME)
public class UserPreferencesModel {

    public static final String RESOURCE_NAME = "UserPreferencesModel";

    private final ObjectProperty<Locale> locale = new SimpleObjectProperty<>();
    private final ObservableList<Locale> supportedLocales = FXCollections.observableArrayList(Locale.ENGLISH, Locale.GERMANY);
    private final StringProperty exportPath = new SimpleStringProperty();
    private final StringProperty jiraUrl = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> beginOfFirstSprint = new SimpleObjectProperty<>();
    private final IntegerProperty sprintDuration = new SimpleIntegerProperty();
    private final BooleanProperty sprintChangeDayFlag = new SimpleBooleanProperty();

    public Locale getLocale() {
        return locale.get();
    }

    public ObjectProperty<Locale> localeProperty() {
        return locale;
    }

    public void setLocale(final Locale locale) {
        this.locale.set(locale);
    }

    public ObservableList<Locale> getSupportedLocales() {
        return supportedLocales;
    }

    public String getExportPath() {
        return exportPath.get();
    }

    public StringProperty exportPathProperty() {
        return exportPath;
    }

    public void setExportPath(final String exportPath) {
        this.exportPath.set(exportPath);
    }

    public String getJiraUrl() {
        return jiraUrl.get();
    }

    public StringProperty jiraUrlProperty() {
        return jiraUrl;
    }

    public void setJiraUrl(final String jiraUrl) {
        this.jiraUrl.set(jiraUrl);
    }

    public LocalDate getBeginOfFirstSprint() {
        return beginOfFirstSprint.get();
    }

    public ObjectProperty<LocalDate> beginOfFirstSprintProperty() {
        return beginOfFirstSprint;
    }

    public void setBeginOfFirstSprint(final LocalDate beginOfFirstSprint) {
        this.beginOfFirstSprint.set(beginOfFirstSprint);
    }

    public int getSprintDuration() {
        return sprintDuration.get();
    }

    public IntegerProperty sprintDurationProperty() {
        return sprintDuration;
    }

    public void setSprintDuration(final int sprintDuration) {
        this.sprintDuration.set(sprintDuration);
    }

    public boolean isSprintChangeDayFlag() {
        return sprintChangeDayFlag.get();
    }

    public BooleanProperty sprintChangeDayFlagProperty() {
        return sprintChangeDayFlag;
    }

    public void setSprintChangeDayFlag(final boolean sprintChangeDayFlag) {
        this.sprintChangeDayFlag.set(sprintChangeDayFlag);
    }
}

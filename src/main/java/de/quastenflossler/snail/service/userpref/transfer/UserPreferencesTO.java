package de.quastenflossler.snail.service.userpref.transfer;

import de.quastenflossler.snail.service.core.transfer.AbstractTransferObject;

import java.time.LocalDate;
import java.util.Locale;

public class UserPreferencesTO extends AbstractTransferObject {

    private static final long serialVersionUID = 4882795078626556699L;

    private Locale language;
    private String exportPath;
    private String jiraUrl;
    private LocalDate beginOfFirstSprint;
    private Integer sprintDuration;
    private Boolean sprintChangeDayFlag;

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(final Locale language) {
        this.language = language;
    }

    public String getExportPath() {
        return exportPath;
    }

    public void setExportPath(final String exportPath) {
        this.exportPath = exportPath;
    }

    public String getJiraUrl() {
        return jiraUrl;
    }

    public void setJiraUrl(final String jiraUrl) {
        this.jiraUrl = jiraUrl;
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
}

package de.quastenflossler.snail.service.agile.transfer;

public class SprintStatisticsTO {

    private Long sprintId;
    private Integer numberOfPlannedStories;
    private Integer numberOfSolvedStories;
    private Integer totalSolvedStoryPoints;

    public Long getSprintId() {
        return sprintId;
    }

    public void setSprintId(final Long sprintId) {
        this.sprintId = sprintId;
    }

    public Integer getNumberOfPlannedStories() {
        return numberOfPlannedStories;
    }

    public void setNumberOfPlannedStories(final Integer numberOfPlannedStories) {
        this.numberOfPlannedStories = numberOfPlannedStories;
    }

    public Integer getNumberOfSolvedStories() {
        return numberOfSolvedStories;
    }

    public void setNumberOfSolvedStories(final Integer numberOfSolvedStories) {
        this.numberOfSolvedStories = numberOfSolvedStories;
    }

    public Integer getTotalSolvedStoryPoints() {
        return totalSolvedStoryPoints;
    }

    public void setTotalSolvedStoryPoints(final Integer totalSolvedStoryPoints) {
        this.totalSolvedStoryPoints = totalSolvedStoryPoints;
    }
}

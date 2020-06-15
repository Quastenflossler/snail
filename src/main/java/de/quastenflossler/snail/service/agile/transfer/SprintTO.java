package de.quastenflossler.snail.service.agile.transfer;

import de.quastenflossler.snail.service.core.transfer.AbstractTransferObject;

import java.util.Date;

public class SprintTO extends AbstractTransferObject {

    private static final long serialVersionUID = 2493786804531178510L;

    private String name;
    private String goal;
    private Date begin;
    private Date end;
    private SprintDuration duration;
    private SprintStatus status;
    private SprintStatisticsTO statistics;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(final String goal) {
        this.goal = goal;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(final Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(final Date end) {
        this.end = end;
    }

    public SprintDuration getDuration() {
        return duration;
    }

    public void setDuration(final SprintDuration duration) {
        this.duration = duration;
    }

    public SprintStatus getStatus() {
        return status;
    }

    public void setStatus(final SprintStatus status) {
        this.status = status;
    }

    public SprintStatisticsTO getStatistics() {
        return statistics;
    }

    public void setStatistics(final SprintStatisticsTO statistics) {
        this.statistics = statistics;
    }
}

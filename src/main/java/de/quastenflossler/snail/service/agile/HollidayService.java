package de.quastenflossler.snail.service.agile;

import java.time.LocalDate;

public interface HollidayService {

    void addHollidayDate(Long teamMemberId, LocalDate localDate);

    void removeHollidayDate(Long teamMemberId, LocalDate localDate);
}

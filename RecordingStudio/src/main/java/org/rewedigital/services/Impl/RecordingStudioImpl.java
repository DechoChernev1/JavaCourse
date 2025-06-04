package org.rewedigital.services.Impl;

import org.rewedigital.services.AbstractStudio;
import org.rewedigital.services.RecordingStudio;

import java.util.Objects;

public class RecordingStudioImpl extends AbstractStudio implements RecordingStudio, Comparable<RecordingStudioImpl> {

    public RecordingStudioImpl(String id, double priceInBGN, int maxHoursPerDay) {
        super(id, priceInBGN, maxHoursPerDay);
    }

    @Override
    public double getIncomeInBGN() {
        return priceInBGN * rentedHours;
    }

    @Override
    public double getIncomeInEUR() {
        return getIncomeInBGN() / exchangeRateEURtoBGN;
    }

    @Override
    public int compareTo(RecordingStudioImpl otherStudio) {
        return Double.compare(this.getIncomeInBGN(), otherStudio.getIncomeInBGN());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordingStudioImpl studio = (RecordingStudioImpl) o;
        return getId().equals(studio.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

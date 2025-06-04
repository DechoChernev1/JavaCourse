package org.rewedigital.services;

import org.rewedigital.services.Impl.RecordingStudioImpl;

public interface RecordingStudio {
    double getIncomeInBGN();
    double getIncomeInEUR();

    int compareTo(RecordingStudioImpl otherStudio);
}

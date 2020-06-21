package de.quastenflossler.snail.ui.stage;

import javafx.stage.Modality;
import javafx.stage.StageStyle;

public enum SnailStage {

    PRIMARY("Snail", 1024, 768, StageStyle.DECORATED, Modality.NONE),
    SETTINGS("Snail - Settings", 0, 0, StageStyle.DECORATED, Modality.APPLICATION_MODAL);

    private String title;
    private double minWidth;
    private double minHeight;
    private StageStyle style;
    private Modality modality;

    SnailStage(final String title, final double minWidth, final double minHeight,
               final StageStyle style, final Modality modality) {

        this.title = title;
        this.minWidth = minWidth;
        this.minHeight = minHeight;
        this.style = style;
        this.modality = modality;
    }

    public String getTitle() {
        return title;
    }

    public StageStyle getStyle() {
        return style;
    }

    public Modality getModality() {
        return modality;
    }

    public double getMinWidth() {
        return minWidth;
    }

    public double getMinHeight() {
        return minHeight;
    }

    @Override
    public String toString() {
        return title;
    }
}

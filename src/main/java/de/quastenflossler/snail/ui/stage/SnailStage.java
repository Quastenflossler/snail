package de.quastenflossler.snail.ui.stage;

import javafx.stage.Modality;
import javafx.stage.StageStyle;

public enum SnailStage {

    PRIMARY("Snail", StageStyle.UTILITY, Modality.NONE),
    SETTINGS("Snail - Settings", StageStyle.DECORATED, Modality.APPLICATION_MODAL);

    private String title;
    private StageStyle style;
    private Modality modality;

    SnailStage(final String title, final StageStyle style, final Modality modality) {

        this.title = title;
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

    @Override
    public String toString() {
        return title;
    }
}

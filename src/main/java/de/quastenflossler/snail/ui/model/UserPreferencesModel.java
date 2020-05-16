package de.quastenflossler.snail.ui.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Named;
import java.util.Locale;

@Named(value = UserPreferencesModel.RESOURCE_NAME)
public class UserPreferencesModel {

    public static final String RESOURCE_NAME = "UserPreferencesModel";

    private final ObjectProperty<Locale> locale = new SimpleObjectProperty<>();
    private final ObservableList<Locale> supportedLocales = FXCollections.observableArrayList(Locale.ENGLISH, Locale.GERMANY);
    private final StringProperty exportPath = new SimpleStringProperty();

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
}

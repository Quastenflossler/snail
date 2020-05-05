module quastenflossler.snail {

    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    requires javax.inject;
    requires org.slf4j;
    requires spring.context;
    requires spring.beans;
    requires org.apache.commons.lang3;
    requires spring.boot.autoconfigure;
    requires itextpdf;
    requires javax.annotation.api;

    exports de.quastenflossler.snail;
    exports de.quastenflossler.snail.config;
    exports de.quastenflossler.snail.ui;
    exports de.quastenflossler.snail.service.domain;
    exports de.quastenflossler.snail.ui.command;
    exports de.quastenflossler.snail.ui.command.impl;

    opens de.quastenflossler.snail to spring.core;
    opens de.quastenflossler.snail.config to spring.core;
    opens de.quastenflossler.snail.ui to spring.core;
    opens de.quastenflossler.snail.service.domain to spring.core;
    opens de.quastenflossler.snail.ui.command to spring.core;
    opens de.quastenflossler.snail.ui.command.impl to spring.beans;
    opens de.quastenflossler.snail.service.domain.impl to spring.beans;
}
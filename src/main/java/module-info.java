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
    exports de.quastenflossler.snail.service.core.domain.impl;
    exports de.quastenflossler.snail.service.issue.domain.impl;
    exports de.quastenflossler.snail.ui.command;
    exports de.quastenflossler.snail.ui.command.impl;
    exports de.quastenflossler.snail.ui.controller;
    exports de.quastenflossler.snail.service.issue to spring.beans;
    exports de.quastenflossler.snail.service.userpref to spring.beans;
    exports de.quastenflossler.snail.ui.control to javafx.fxml;

    opens de.quastenflossler.snail to spring.core;
    opens de.quastenflossler.snail.config to spring.core;
    opens de.quastenflossler.snail.ui to spring.core;
    opens de.quastenflossler.snail.service.core.domain.impl to spring.core, spring.beans;
    opens de.quastenflossler.snail.service.issue.domain.impl to spring.core;
    opens de.quastenflossler.snail.ui.command to spring.core;
    opens de.quastenflossler.snail.ui.controller to spring.core,javafx.fxml;
    opens de.quastenflossler.snail.service.issue to spring.core;
    opens de.quastenflossler.snail.service.userpref to spring.core;
    opens de.quastenflossler.snail.ui.command.impl to spring.core, spring.beans;
    opens de.quastenflossler.snail.service.userpref.transfer to org.apache.commons.lang3;
    opens de.quastenflossler.snail.service.issue.transfer to org.apache.commons.lang3;
    opens de.quastenflossler.snail.service.core.transfer to org.apache.commons.lang3;

    opens de.quastenflossler.snail.service.userpref.domain to spring.beans;

}
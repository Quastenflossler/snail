package de.quastenflossler.snail.service.domain;

import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.IOException;

public interface SmartIssue extends BasicIssue {

    File printAsPdf() throws IOException, DocumentException;
}

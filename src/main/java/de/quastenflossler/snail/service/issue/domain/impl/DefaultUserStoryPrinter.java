package de.quastenflossler.snail.service.issue.domain.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.quastenflossler.snail.service.core.exception.DataValidationServiceException;
import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.issue.domain.BasicIssue;
import de.quastenflossler.snail.service.issue.domain.UserStoryPrinter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

@Named(value = DefaultUserStoryPrinter.RESOURCE_NAME)
public class DefaultUserStoryPrinter implements UserStoryPrinter {

    public static final String RESOURCE_NAME = "DefaultUserStoryPrinter";

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUserStoryPrinter.class);

    private static final String FILE_EXTENSION_PDF = ".pdf";

    @Override
    public String createPdf(final BasicIssue issue, final UserStoryLayout layout, final String exportPath)
            throws InternalServiceException, DataValidationServiceException {

        try {

            return try_createPdf(issue, layout, exportPath);

        } catch (DocumentException | IOException | URISyntaxException e) {
            throw new InternalServiceException("Pdf creation failed!", e);
        }
    }

    private String try_createPdf(final BasicIssue issue, final UserStoryLayout layout, final String exportPath) throws IOException, DocumentException, URISyntaxException, DataValidationServiceException {

        validateIssueContent(issue);

        Document document = new Document(PageSize.A5);

        String fileName = exportPath + "\\" + issue.getKey() + FILE_EXTENSION_PDF;
        LOGGER.debug("pdf will be saved to {}", fileName);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

        PdfPTable table = new PdfPTable(4);
        table.setTotalWidth(396);
        table.setSpacingBefore(10);
        table.setSpacingAfter(10);
        table.setWidths(new float[]{0.2f, 0.2f, 0.2f, 0.2f});
        table.setLockedWidth(true);

        Font boldTitle = new Font();
        boldTitle.setStyle(Font.BOLD);
        boldTitle.setSize(16);

        Font bigStoryPointsFont = new Font();
        bigStoryPointsFont.setStyle(Font.BOLD);
        bigStoryPointsFont.setSize(22);

        PdfPCell titleCell = createCellWithHint(issue.getSummary(), boldTitle, "Summary:", Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 5, 10);
        titleCell.setFixedHeight(72);

        table.addCell(titleCell);

        URL ingoFilePath = getClass().getResource("/assets/ingo.png");
        File ingoFile = new File(ingoFilePath.toURI());
        LOGGER.info(ingoFile.toString());
        Image logoImage = Image.getInstance(ingoFilePath.toString());
        table.addCell(createCell(logoImage, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE));

        table.addCell(createCellWithHint(issue.getKey(), "Issue Key:", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 5, 10));
        table.addCell(createCellWithHint(issue.getEpic().getKey(), "Epic Key:", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 5, 10));
        table.addCell(createCellWithHint(issue.getEpic().getDescription(), "Epic:", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 3, 5, 10));

        table.addCell(createCellWithHint(issue.getStakeholder(), "Stakeholder:", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 2, 5, 10));
        table.addCell(createCellWithHint(issue.getPlannedSprint(), "Planned Sprint:", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 5, 10));
        table.addCell(createCellWithHint(issue.getDeadline(), "Deadline:", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 5, 10));

        PdfPCell descriptionCell = createCellWithHint(issue.getDescription(), "Description:", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, Element.ALIGN_TOP, 3, 5, 10);
        descriptionCell.setFixedHeight(150);

        table.addCell(descriptionCell);
        table.addCell(createCellWithHint(String.valueOf(issue.getStoryPoints()), bigStoryPointsFont, "Story Points:", Element.ALIGN_CENTER, Element.ALIGN_TOP, 1, 5, 10));

        PdfPCell acceptanceCriteriaCell = createCellWithHint(issue.getAcceptanceCriteria(), "Acceptance Criteria:", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, Element.ALIGN_TOP, 4, 5, 10);
        acceptanceCriteriaCell.setFixedHeight(269.28f);

        table.addCell(acceptanceCriteriaCell);
//        table.writeSelectedRows(0, 5, document.leftMargin(), document.topMargin() + table.getTotalHeight() + 200, writer.getDirectContent());
        table.writeSelectedRows(0, 5, 10, 15 + table.getTotalHeight(), writer.getDirectContent());

//        document.add(table);

        document.close();
        writer.close();

        LOGGER.debug("pdf saved to {}", fileName);

        File file = new File(fileName);
        return file.toString();
    }

    private PdfPCell createCellWithHint(final String text, final Font font, final String hint, final int horizontalAlginment,
                                        final int verticalAlignment, final int colspan, final int verticalPadding, final int horizontalPadding) {

        Font small = new Font();
        small.setSize(6);
        small.setStyle(Font.FontStyle.UNDERLINE.getValue());

        String hintText = hint + "\n\n";
        Paragraph titleHint = new Paragraph(hintText, small);
        Paragraph phrase = new Paragraph(text, font);
        titleHint.add(phrase);

        PdfPCell cell = new PdfPCell(titleHint);

        cell.setBorderColor(BaseColor.BLACK);
        cell.setHorizontalAlignment(horizontalAlginment);
        cell.setVerticalAlignment(verticalAlignment);
        cell.setPaddingLeft(horizontalPadding);
        cell.setPaddingRight(horizontalPadding);
        cell.setPaddingTop(verticalPadding);
        cell.setPaddingBottom(verticalPadding);
        cell.setColspan(colspan);

        return cell;
    }

    private PdfPCell createCellWithHint(final String text, final String hint, final int alignLeft, final int alignMiddle,
                                        final int horizontalAlginment, final int verticalAlignment, final int colspan,
                                        final int verticalPadding, final int horizontalPadding) {

        Font small = new Font();
        small.setSize(6);
        small.setStyle(Font.FontStyle.UNDERLINE.getValue());

        String hintText = hint + "\n\n";
        Paragraph titleHint = new Paragraph(hintText, small);
        Paragraph phrase = new Paragraph(text);
        titleHint.add(phrase);

        PdfPCell cell = new PdfPCell(titleHint);

        cell.setBorderColor(BaseColor.BLACK);
        cell.setHorizontalAlignment(horizontalAlginment);
        cell.setVerticalAlignment(verticalAlignment);
        cell.setPaddingLeft(horizontalPadding);
        cell.setPaddingRight(horizontalPadding);
        cell.setPaddingTop(verticalPadding);
        cell.setPaddingBottom(verticalPadding);
        cell.setColspan(colspan);

        return cell;
    }

    private PdfPCell createCell(final String text, final Font font, final int horizontalAlginment, final int verticalAlignment) {

        Font small = new Font();
        small.setSize(6);
        small.setStyle(Font.FontStyle.UNDERLINE.getValue());

        Paragraph titleHint = new Paragraph("Summary:\n\n", small);
        Paragraph phrase = new Paragraph(text, font);
        titleHint.add(phrase);

        PdfPCell cell = new PdfPCell(titleHint);

        cell.setBorderColor(BaseColor.BLACK);
        cell.setHorizontalAlignment(horizontalAlginment);
        cell.setVerticalAlignment(verticalAlignment);
        cell.setPadding(10);

        return cell;
    }

    private PdfPCell createCell(final String text, final int horizontalAlginment, final int verticalAlignment) {

        PdfPCell cell = new PdfPCell(new Paragraph(text));

        cell.setBorderColor(BaseColor.BLACK);
        cell.setHorizontalAlignment(horizontalAlginment);
        cell.setVerticalAlignment(verticalAlignment);
        cell.setPadding(10);

        return cell;
    }

    private PdfPCell createCell(final Image image, final int horizontalAlginment, final int verticalAlignment) {

        PdfPCell cell = new PdfPCell(image);

        cell.setBorderColor(BaseColor.BLACK);
        cell.setHorizontalAlignment(horizontalAlginment);
        cell.setVerticalAlignment(verticalAlignment);
        cell.setPadding(10);

        return cell;
    }

    private void validateIssueContent(final BasicIssue issue) throws DataValidationServiceException {

        if (StringUtils.isEmpty(issue.getKey())) {
            throw new DataValidationServiceException("Issue key is mandatory!");
        }

        if (issue.getKey().length() > 8) {
            throw new DataValidationServiceException("Issue key is limited to 8 characters for this layout!");
        }

        if (issue.getSummary().length() > 80) {
            throw new DataValidationServiceException("Issue title is limited to 80 characters for this layout!");
        }

        if (issue.getEpic().getKey().length() > 8) {
            throw new DataValidationServiceException("Epic key is limited to 8 characters for this layout!");
        }

        if (issue.getEpic().getDescription().length() > 25) {
            throw new DataValidationServiceException("Epic name is limited to 25 characters for this layout!");
        }

        if (issue.getStakeholder().length() > 15) {
            throw new DataValidationServiceException("Stakeholder is limited to 15 characters for this layout!");
        }

        if (issue.getPlannedSprint().length() > 15) {
            throw new DataValidationServiceException("Planned sprint is limited to 15 characters for this layout!");
        }

        if (issue.getDeadline().length() > 12) {
            throw new DataValidationServiceException("Deadline is limited to 12 characters for this layout!");
        }
    }
}

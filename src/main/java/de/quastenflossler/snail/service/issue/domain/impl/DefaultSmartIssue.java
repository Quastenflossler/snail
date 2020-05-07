package de.quastenflossler.snail.service.issue.domain.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.quastenflossler.snail.service.issue.domain.SmartIssue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DefaultSmartIssue extends DefaultBasicIssue implements SmartIssue {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSmartIssue.class);

    @Override
    public File printAsPdf() throws IOException, DocumentException {

        Document document = new Document(PageSize.A5);

        String fileName = getKey() + ".pdf";
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

        PdfPTable table = new PdfPTable(5);
        table.setTotalWidth(396);
        table.setSpacingBefore(10);
        table.setSpacingAfter(10);
        table.setWidths(new float[]{0.2f, 0.2f, 0.2f, 0.2f, 0.2f});
        table.setLockedWidth(true);

        Font boldTitle = new Font();
        boldTitle.setStyle(Font.BOLD);
        boldTitle.setSize(16);

        Font bigStoryPointsFont = new Font();
        bigStoryPointsFont.setStyle(Font.BOLD);
        bigStoryPointsFont.setSize(22);

        PdfPCell titleCell = createCellWithHint(getSummary(), boldTitle, "Summary:", Element.ALIGN_LEFT, Element.ALIGN_TOP, 4, 5, 10);
        titleCell.setFixedHeight(72);

        table.addCell(titleCell);

        try {

            String ingoFile = getClass().getResource("/assets/ingo.png").getFile();
            LOGGER.info(ingoFile);
            Image logoImage = Image.getInstance(ingoFile);
            table.addCell(createCell(logoImage, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE));

        } catch (Exception e) {

            table.addCell(createCell("Image not found", Element.ALIGN_CENTER, Element.ALIGN_MIDDLE));
            LOGGER.error("Holy shit!", e);
        }

        table.addCell(createCellWithHint( getKey(),"Issue Key:", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 5, 10));
        table.addCell(createCellWithHint( getEpic().getKey(),"Epic Key:", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 5, 10));
        table.addCell(createCellWithHint( getEpic().getDescription(),"Epic:", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 3, 5, 10));

        table.addCell(createCellWithHint( getStakeholder(),"Stakeholder:", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 2, 5, 10));
        table.addCell(createCellWithHint( getPlannedSprint(),"Planned Sprint:", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 2, 5, 10));
        table.addCell(createCellWithHint( getDeadline(),"Deadline:", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 5, 10));

        PdfPCell descriptionCell = createCellWithHint(getDescription(), "Description:", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, Element.ALIGN_TOP, 4, 5, 10);
        descriptionCell.setFixedHeight(150);

        table.addCell(descriptionCell);
        table.addCell(createCellWithHint(String.valueOf(getStoryPoints()), bigStoryPointsFont,"Story Points:", Element.ALIGN_CENTER, Element.ALIGN_TOP, 1, 5, 10));

        PdfPCell acceptanceCriteriaCell = createCellWithHint(getAcceptanceCriteria(), "Acceptance Criteria:", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, Element.ALIGN_TOP, 5, 5, 10);
        acceptanceCriteriaCell.setFixedHeight(269.28f);

        table.addCell(acceptanceCriteriaCell);
//        table.writeSelectedRows(0, 5, document.leftMargin(), document.topMargin() + table.getTotalHeight() + 200, writer.getDirectContent());
        table.writeSelectedRows(0, 5, 10, 15 + table.getTotalHeight(), writer.getDirectContent());

//        document.add(table);

        document.close();
        writer.close();

        File file = new File(fileName);
//        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
        return file;
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
}

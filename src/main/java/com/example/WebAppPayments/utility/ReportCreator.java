package com.example.WebAppPayments.utility;

import com.example.WebAppPayments.model.entity.Payment;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * The class used in reports creating
 */
public class ReportCreator {

    private static Font bigFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    private static final Logger logger = Logger.getLogger(ReportCreator.class);

    private ReportCreator() {
    }

    public static void createDocument(List<Payment> paymentList, int id_user, int type) {
        try {

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("E:/Temp_payments/Doc_for_user_" + id_user + ".pdf"));

            document.open();

            PdfPTable table = new PdfPTable(5);
            switch (type) {
                case 1: {
                    addYearTitlePage(document);
                    break;
                }
                case 2: {
                    addMonthTitlePage(document);
                    break;
                }
                case 3: {
                    addWeekTitlePage(document);
                    break;
                }
                case 4: {
                    addNowTitlePage(document);
                    break;
                }
            }


            addTableHeader(table);
            addRows(table, paymentList);

            document.add(table);
            document.close();

        } catch (Exception e) {
            logger.info("REPORT CREATOR EXCEPTION", e);
        }
    }

    private static void addNowTitlePage(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();

        preface.add(new Paragraph("Payment check:", bigFont));

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        preface.add(new Paragraph("Datetime of payment: " + formatter.format(new Date()), smallFont));

        preface.add(new Paragraph(" "));

        document.add(preface);
    }

    private static void addWeekTitlePage(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();

        preface.add(new Paragraph("Payment report of current week:", bigFont));

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        preface.add(new Paragraph("Date of report generation: " + formatter.format(new Date()), smallFont));

        preface.add(new Paragraph(" "));

        document.add(preface);
    }

    private static void addMonthTitlePage(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();

        preface.add(new Paragraph("Payment report of current month:", bigFont));

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        preface.add(new Paragraph("Date of report generation: " + formatter.format(new Date()), smallFont));

        preface.add(new Paragraph(" "));

        document.add(preface);
    }

    private static void addYearTitlePage(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();

        preface.add(new Paragraph("Payment report of current year:", bigFont));

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        preface.add(new Paragraph("Date of report generation: " + formatter.format(new Date()), smallFont));

        preface.add(new Paragraph(" "));

        document.add(preface);
    }

    private static void addTableHeader(PdfPTable table) {
        Stream.of("Card number" ,"Payment date", "Operation type", "Sum", "Destination")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private static void addRows(PdfPTable table, List<Payment> paymentList) {
        for (Payment payment : paymentList) {
            table.addCell(payment.getCardNumber());
            table.addCell(payment.getPaymentDate());
            table.addCell(payment.getIdType());
            table.addCell(String.valueOf(payment.getSum()));
            table.addCell(payment.getDestination());
        }
    }
}

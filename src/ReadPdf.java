import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;


public class ReadPdf extends PDFTextStripper {

    static float pWid;
    static String[][] positions = new String[1000000][10];
    static List<Position> positionList = new ArrayList<>();
    private int as = 0;

    ReadPdf() throws IOException {
    }

    void ReadData() throws IOException {

        File file = new File("C:\\Users\\Rukshan\\Documents\\Research\\RTest01\\Test.pdf");
        PDDocument document = PDDocument.load(file);
        PDFTextStripper stripper = new ReadPdf();
        stripper.setSortByPosition(true);
        stripper.setStartPage(0);
        stripper.setEndPage(document.getNumberOfPages());
        Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
        stripper.writeText(document, dummy);
    }
    /**
     * Override the default functionality of PDFTextStripper.
     */
    @Override
    protected void writeString(String string, List<TextPosition> textPositions) throws IOException {

        for (TextPosition text1 : textPositions) {
            positionList.add(new Position(text1.getUnicode(),text1.getXDirAdj(),text1.getYDirAdj()));
            pWid = text1.getPageWidth();
        }
    }

}
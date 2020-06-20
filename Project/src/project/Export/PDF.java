package project.Export;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import org.apache.log4j.BasicConfigurator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import project.Helper.Asistan;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class PDF {
    public void Export_PDF(AnchorPane root){
        BasicConfigurator.configure();
        System.out.println("ben");
        root.getChildrenUnmodifiable();
        System.out.println("yoruldum");
        try {
            WritableImage nodeshot = root.snapshot(new SnapshotParameters(), null);
            System.out.println("hayat");
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageIO.write(SwingFXUtils.fromFXImage(nodeshot, null), "png", output);
            output.close();
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            PDImageXObject pdfimage;
            PDPageContentStream content;
            pdfimage = PDImageXObject.createFromByteArray(doc, output.toByteArray(), "jpg");
            content = new PDPageContentStream(doc, page);
            PDRectangle box = page.getMediaBox();
            double factor = Math.min(box.getWidth() / nodeshot.getWidth(), box.getHeight() / nodeshot.getHeight());
            float height = (float) (nodeshot.getHeight() * factor);
            content.drawImage(pdfimage, 0, box.getHeight() - height, (float) (nodeshot.getWidth() * factor), height);
            content.close();
            doc.addPage(page);
            File outputFile = new File("C:\\Users\\busra\\IdeaProjects\\INF202\\Project\\src\\project\\Files\\PDFFiles\\" + "Manyetik Parçacık Muayene Raporu.pdf");
            doc.save(outputFile);
            doc.close();
            Asistan.openFileWithDesktop(outputFile);
            System.out.println("Helall bee çalıştı");
        } catch (Exception e) {

        }
    }
}

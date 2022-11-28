package utils;

import aquality.selenium.core.logging.Logger;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static utils.Constants.PATH_TO_DIFFERENCE_DESTINATION;
import static utils.Constants.PIXEL_TOLERANCE;

public class ImageProcessingUtils {

    public static void downloadVIAImageIO(String src, String fileAddress) {
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new URL(src));
        } catch (IOException e) {
            Logger.getInstance().error("Data cannot be processed!");
            throw new RuntimeException(e);
        }
        File downloadedFile = new File(fileAddress);
        try {
            ImageIO.write(bufferedImage, "jpg", downloadedFile);
        } catch (IOException e) {
            Logger.getInstance().error("Cannot write into specified format!");
            throw new RuntimeException(e);
        }
    }

    public static ImageComparisonState areImagesEqual(String pathToExpectedImage, String pathToUploadedImage) {
        BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(pathToExpectedImage);
        BufferedImage actualImage = ImageComparisonUtil.readImageFromResources(pathToUploadedImage);
        ImageComparison imageComparison = new ImageComparison(expectedImage, actualImage);

        imageComparison.setPixelToleranceLevel(PIXEL_TOLERANCE);

        ImageComparisonResult imageComparisonResult = imageComparison.compareImages();
        BufferedImage resultImage = imageComparisonResult.getResult();
        ImageComparisonUtil.saveImage(new File(PATH_TO_DIFFERENCE_DESTINATION), resultImage);
        return imageComparisonResult.getImageComparisonState();
    }
}

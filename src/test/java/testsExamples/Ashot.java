package testsExamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmentable;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.cropper.indent.IndentCropper;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ru.yandex.qatools.ashot.cropper.indent.IndentFilerFactory.blur;

public class Ashot {
    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }


    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void aShot() throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://yandex.ru");
        WebElement weatherElement = driver.findElement(By.xpath("//div[@class='weather']"));

        //страница
        Screenshot myScreenshot = new AShot().takeScreenshot(driver);
        ImageIO.write(myScreenshot.getImage(),"PNG",new File("c:\\TMP\\myScreenshot.png"));

        //элемент
        Screenshot screenshotOfElement = new AShot()
                .takeScreenshot(driver, weatherElement);
        ImageIO.write(screenshotOfElement.getImage(),"PNG",new File("c:\\TMP\\screenshotOfElement.png"));

        //элемент, а все остальное заблюрено вокруг
        Screenshot screenshotOfElementWithBlur = new AShot().imageCropper(new IndentCropper().addIndentFilter(blur()))
                .takeScreenshot(driver, weatherElement);
        ImageIO.write(screenshotOfElementWithBlur.getImage(),"PNG",new File("c:\\TMP\\screenshotOfElementBlur.png"));

        //сравнение картинок
        BufferedImage expectedImage = ImageIO.read(new File("c:\\TMP\\screenshotOfElementBlur.png"));

        Screenshot myScreenshotIgnoredElement = new AShot().takeScreenshot(driver, weatherElement);
        BufferedImage actualImage = myScreenshotIgnoredElement.getImage();

        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(expectedImage,actualImage);
        //BufferedImage diffImage = diff.getMarkedImage();

        //сохранение разницы
        File diffFile = new File("c:\\TMP\\marked.png");
        ImageIO.write(diff.getMarkedImage(), "png", diffFile);

        Assert.assertFalse(diff.hasDiff(),"images are NOT the same!");

        driver.quit();
    }
}

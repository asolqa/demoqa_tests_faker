package helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.sessionId;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

@SuppressWarnings("UnusedReturnValue")
public class Attach {
    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(@SuppressWarnings("unused") String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(@SuppressWarnings("unused") String attachName, String message) {
        return message;
    }

    public static void browserConsoleLogs() {
        if (Configuration.browser.equals("chrome")) {
            attachAsText(
                    "Browser console logs",
                    String.join("\n", Selenide.getWebDriverLogs(BROWSER))
            );
        }
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String addVideo() {
        if (Configuration.browser.equals("chrome")) {
            return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                    + getVideoUrl()
                    + "'type='video/mp4'></video></body></html>";
        } else {
            return """
                    <html><body>No video available for this browser</body></html>
                    """;
        }
    }

    public static URL getVideoUrl() {
        String videoUrl = "https://" + System.getProperty("wdHost", "selenoid.autotests.cloud") + "/video/" + sessionId() + ".mp4";
        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

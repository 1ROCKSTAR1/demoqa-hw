package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DownloadFilesTests {

    private ClassLoader cl = DownloadFilesTests.class.getClassLoader();

    @Test
    @DisplayName("Проверка содержимого zip")
    public void downloadZipTest() throws Exception {
        open("https://github.com/1ROCKSTAR1/source/blob/main/files/testFiles.zip");
        File downloadedZip = $(".react-blob-header-edit-and-raw-actions [href*='/main/files/testFiles.zip']")
                .download();

        assertTrue(downloadedZip.exists(), "ZIP файл должен существовать");
        assertTrue(downloadedZip.length() > 0, "ZIP файл не должен быть пустым");

        try (FileInputStream fis = new FileInputStream(downloadedZip);
             ZipInputStream zis = new ZipInputStream(fis)) {

            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                System.out.println(entry.getName());
            }
        } //TODO дописать автотест на проверку ZIP
    }
}

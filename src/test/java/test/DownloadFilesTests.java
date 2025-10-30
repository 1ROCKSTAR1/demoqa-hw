package test;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.FilesUtil.*;

@Feature("FilesTest")
public class DownloadFilesTests {

    @Test
    @DisplayName("Проверка содержимого в ZIP")
    public void downloadZipTest() throws Exception {
        open("https://github.com/1ROCKSTAR1/source/blob/main/files/testFiless.zip");
        File downloadedZip = $(".react-blob-header-edit-and-raw-actions [href*='/main/files/testFiless.zip']")
                .download();

        assertTrue(downloadedZip.exists(), "ZIP файл должен существовать");
        assertTrue(downloadedZip.length() > 0, "ZIP файл не должен быть пустым");

        try (FileInputStream fis = new FileInputStream(downloadedZip);
             ZipInputStream zis = new ZipInputStream(fis)) {

            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();
                System.out.println(fileName);

                byte[] fileData = zis.readAllBytes();

                if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {
                    System.out.println(">>> Найден Excel файл: " + fileName);
                    verifyXlsxFile(fileData);
                }
                if (fileName.endsWith(".csv")) {
                    System.out.println(">>> Найден Csv файл: " + fileName);
                    verifyCsvFile(fileData);
                }
                if (fileName.endsWith(".docx")) {
                    System.out.println(">>> Найден DOCX файл: " + fileName);
                    verifyDocxFile(fileData);
                }

                zis.closeEntry();
            }
        }
    }

    @Test
    @DisplayName("Тест для JSON")
    public void downloadJsonTest() throws Exception {
        open("https://github.com/1ROCKSTAR1/source/blob/main/files/testJSON.json");
        File downloadedJson = $(".react-blob-header-edit-and-raw-actions [href*='/main/files/testJSON.json']")
                .download();

        verifyCarJsonFile(downloadedJson);
    }
}



package test;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;
import static utils.FilesUtil.*;

@Feature("FilesTest")
public class DownloadFilesTests {

    @Test
    @DisplayName("Проверка содержимого в ZIP (xls)")
    public void downloadZipXlsTest() throws Exception {
        open("https://github.com/1ROCKSTAR1/source/blob/main/files/testFiless.zip");
        File downloadedZip = $(".react-blob-header-edit-and-raw-actions [href*='/main/files/testFiless.zip']")
                .download();

        try (FileInputStream fis = new FileInputStream(downloadedZip);
             ZipInputStream zis = new ZipInputStream(fis)) {

            ZipEntry entry;
            boolean xlsFileFound = false;
            int xlsFilesCount = 0;

            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();
                System.out.println("Найден файл: " + fileName);

                byte[] fileData = zis.readAllBytes();

                assertTrue(fileData.length > 0, "Файл " + fileName + " не должен быть пустым");

                if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {
                    System.out.println(">>> Найден Excel файл: " + fileName);
                    xlsFileFound = true;
                    xlsFilesCount++;
                    verifyXlsxFile(fileData);

                }
                zis.closeEntry();
            }
            System.out.println("Найдено XLS-файлов " + xlsFilesCount);
            assertTrue(xlsFileFound, "ZIP архив не должен быть пустым");

        } catch (ZipException e) {
            fail("ZIP архив поврежден или пуст: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Проверка содержимого в ZIP (doc)")
    public void downloadZipDocTest() throws Exception {
        open("https://github.com/1ROCKSTAR1/source/blob/main/files/testFiless.zip");
        File downloadedZip = $(".react-blob-header-edit-and-raw-actions [href*='/main/files/testFiless.zip']")
                .download();

        try (FileInputStream fis = new FileInputStream(downloadedZip);
             ZipInputStream zis = new ZipInputStream(fis)) {

            ZipEntry entry;
            boolean docFileFound = false;
            int docFilesCount = 0;

            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();
                System.out.println("Найден файл: " + fileName);

                byte[] fileData = zis.readAllBytes();

                assertTrue(fileData.length > 0, "Файл " + fileName + " не должен быть пустым");

                if (fileName.endsWith(".docx")) {
                    System.out.println(">>> Найден DOC файл: " + fileName);
                    docFileFound = true;
                    docFilesCount++;
                    verifyDocxFile(fileData);
                }

                zis.closeEntry();
            }
            System.out.println("Найдено DOC-файлов " + docFilesCount);
            assertTrue(docFileFound, "ZIP архив не должен быть пустым");

        } catch (ZipException e) {
            fail("ZIP архив поврежден или пуст: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Проверка содержимого в ZIP (csv)")
    public void downloadZipTest() throws Exception {
        open("https://github.com/1ROCKSTAR1/source/blob/main/files/testFiless.zip");
        File downloadedZip = $(".react-blob-header-edit-and-raw-actions [href*='/main/files/testFiless.zip']")
                .download();

        try (FileInputStream fis = new FileInputStream(downloadedZip);
             ZipInputStream zis = new ZipInputStream(fis)) {

            ZipEntry entry;
            boolean csvFileFound = false;
            int csvFilesCount = 0;

            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();
                System.out.println("Найден файл: " + fileName);

                byte[] fileData = zis.readAllBytes();

                assertTrue(fileData.length > 0, "Файл " + fileName + " не должен быть пустым");

                if (fileName.endsWith(".csv")) {
                    System.out.println(">>> Найден CSV файл: " + fileName);
                    csvFileFound = true;
                    csvFilesCount++;
                    verifyCsvFile(fileData);

                    zis.closeEntry();
                }
            }
            System.out.println("Найдено CSV-файлов " + csvFilesCount);
            assertTrue(csvFileFound, "ZIP архив не должен быть пустым");

        } catch (ZipException e) {
            fail("ZIP архив поврежден или пуст: " + e.getMessage());
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



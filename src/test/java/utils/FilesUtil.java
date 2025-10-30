package utils;

import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import io.qameta.allure.Step;
import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FilesUtil {

    @Step("Проверка XLS")
    public static void verifyXlsxFile(byte[] fileData) throws Exception {
        System.out.println("Проверяем XLSX файл");
        try (ByteArrayInputStream bis = new ByteArrayInputStream(fileData)) {
            XLS xls = new XLS(bis);

            String actualValue = xls.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue();
            Assertions.assertTrue(actualValue.contains("Expected String"));

        }
    }

    @Step("Проверка CSV")
    public static void verifyCsvFile(byte[] fileData) throws Exception {
        System.out.println("Проверяем CSV файл");
        try (ByteArrayInputStream bis = new ByteArrayInputStream(fileData);
             InputStreamReader reader = new InputStreamReader(bis);
             CSVReader csvReader = new CSVReader(reader)) {

            List<String[]> data = csvReader.readAll();

            Assertions.assertEquals(2, data.size());
            Assertions.assertArrayEquals(
                    new String[]{"One", "Expected String"},
                    data.get(0)
            );
            Assertions.assertArrayEquals(
                    new String[]{"Two", "Expected String2"},
                    data.get(1)
            );
        }
    }

    @Step("Проверка DOCX")
    public static void verifyDocxFile(byte[] fileData) throws Exception {
        System.out.println("Проверяем DOCX файл testDOCX.docx");

        try (ByteArrayInputStream bis = new ByteArrayInputStream(fileData);
             ZipInputStream docxZis = new ZipInputStream(bis)) {

            ZipEntry docxEntry;
            while ((docxEntry = docxZis.getNextEntry()) != null) {
                if (docxEntry.getName().equals("word/document.xml")) {

                    byte[] xmlData = docxZis.readAllBytes();
                    String xmlContent = new String(xmlData);

                    if (xmlContent.contains("<w:t>")) {
                        int start = xmlContent.indexOf("<w:t>") + 5;
                        int end = xmlContent.indexOf("</w:t>", start);
                        if (end > start) {
                            String extractedText = xmlContent.substring(start, end);

                            Assertions.assertTrue(extractedText.contains("Expected String"));
                        }
                    }
                }
                docxZis.closeEntry();
            }
        }
    }

    @Step("Проверка JSON")
    public static void verifyCarJsonFile(File json) throws Exception {
        System.out.println("Проверяем JSON файл");

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

            Assertions.assertTrue(jsonNode.has("car"), "JSON должен содержать объект 'car'");

            JsonNode carNode = jsonNode.get("car");
            Assertions.assertEquals("Toyota", carNode.get("brand").asText());
            Assertions.assertEquals("Camry", carNode.get("model").asText());
            Assertions.assertEquals(2023, carNode.get("year").asInt());
            Assertions.assertEquals("Silver", carNode.get("color").asText());

            Assertions.assertTrue(carNode.has("engine"), "Car должен содержать объект 'engine'");
            JsonNode engineNode = carNode.get("engine");
            Assertions.assertEquals("Hybrid", engineNode.get("type").asText());
            Assertions.assertEquals(208, engineNode.get("horsepower").asInt());
            Assertions.assertEquals("Gasoline/Electric", engineNode.get("fuelType").asText());

            System.out.println("✅ JSON структура корректна");
        }
    }

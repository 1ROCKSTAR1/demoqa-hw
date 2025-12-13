# 🎯 HomeWork

<div align="center">

<img src="https://github.com/1ROCKSTAR1/source/blob/main/icons/Java.svg" width="56" height="56">
<img src="https://github.com/1ROCKSTAR1/source/blob/main/icons/JUnit5.svg" width="56" height="56">
<img src="https://github.com/1ROCKSTAR1/source/blob/main/icons/Gradle.svg" width="56" height="56">
<img src="https://github.com/1ROCKSTAR1/source/blob/main/icons/Intelij_IDEA.svg" width="56" height="56">
<img src="https://github.com/1ROCKSTAR1/source/blob/main/icons/Allure_Report.svg" width="56" height="56">
<img src="https://github.com/1ROCKSTAR1/source/blob/main/icons/selenoid.png" width="60" height="50">
<img src="https://github.com/1ROCKSTAR1/source/blob/main/icons/Jenkins.svg" width="56" height="56">
<img src="https://github.com/1ROCKSTAR1/source/blob/main/icons/rest-assured.png" width="56" height="56">

</div>

## 📚 Описание проекта

Учебный фреймворк для автоматизированного тестирования веб-приложений. Проект реализует комплексный подход к тестированию с поддержкой API и UI слоев, интегрирован в CI/CD pipeline и оснащен системой отчетности.

## 🛠 Технологии

### Основной стек:
![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=java&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-8.0-02303A?style=flat-square&logo=gradle&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-5.9-25A162?style=flat-square&logo=junit5&logoColor=white)

### Инструменты:
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-2024-000000?style=flat-square&logo=intellij-idea&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=flat-square&logo=git&logoColor=white)

    📦 Проект автоматизации тестирования

    ├── 📁 src/main/java/

    │   ├── 📁 api/                    # Чистое API тестирование

    │   ├── 📁 apiui/                  # Гибридное тестирование (API+UI)

    │   │   ├── 📁 demoga/            # Тесты сайта demoga

    │   │   │   ├── BaseTest.java     # 🧪 Базовый тест-класс

    │   │   │   ├── ImprovedApiUITests.java # 🔄 Улучшенные гибрид -есты

    │   │   │   ├── ProfilePage.java  # 📄 Page Object профиля

    │   │   │   └── SimpleApiUITests.java # ⚡ Простые гибрид-тесты

    │   │   ├── 📁 demowebshop/       # Тесты интернет-магазина

    │   │   ├── 📁 helpers/           # 🛠️ Вспомогательные классы

    │   │   ├── 📁 models/            # 📊 Модели данных

    │   │   ├── 📁 specs/             # 📋 Спецификации

    │   │   │   ├── 📁 data/          # 📈 Тестовые данные

    │   │   │   └── 📁 helpers/       # 📎 Утилиты отчетности

    │   │   │       ├── Attach.java           # 📎 Вложения 

    │   │   │       └── CustomAllureListener.java # 🔊 Кастомный листенер


    │   │   ├── 📁 pages/             # 🎨 Pages

    │   │   │   └── 📁 test/          # 🧪 UI тесты

    │   │   │       ├── AllureExampleTests.java    # 📊 Примеры Allure

    │   │   │       ├── CssXpathExample.java       # 🔍 Примеры локаторов

    │   │   │       ├── DataProviderTests.java     # 📦 Параметризованные тесты

    │   │   │       ├── DownloadFilesTests.java    # 💾 Тесты загрузки файлов

    │   │   │       ├── PomTests.java             # 🏗️ Тесты с POM

    │   │   │       ├── SingleTest.java           # ⚡ Одиночный тест

    │   │   │       └── TextBoxTests.java         # 📝 Тесты текстовых полей

    │   │   └── 📁 utils/             # 🧰 Общие утилиты

    │   ├── 📁 test/resources/        # 🎯 Ресурсы для тестов

    │   └── 📁 resources/             # 📚 Основные ресурсы

    ├── 📄 .gitignore                 # 🙈 Игнорируемые файлы

    └── 📄 build.gradle               # 🏗️ Конфигурация сборки



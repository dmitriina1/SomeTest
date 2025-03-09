import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.YandexAfterSearch;
import pages.YandexBeforeSearch;

import java.util.List;

public class Tests extends BaseTest{

    @Feature("Проверка курса валют")
    @DisplayName("Проверка курса валют - базовая")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#providerFindLaptopInCatalog")
    public void testOpenYandex(String searchQuery, String title, String laptop) {
        chromeDriver.get("https://market.yandex.ru/");
       //YandexBeforeSearch googleBeforeSearch = new YandexBeforeSearch(chromeDriver);
//        googleBeforeSearch.find(searchQuery);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String pageTitle = chromeDriver.getTitle();
        System.out.println(pageTitle);
       Assertions.assertTrue(pageTitle.contains("Яндекс Маркет"),"Тайтл "+pageTitle+" на сайте не содержит Яндекс Маркет");
    }

    @Feature("Проверка курса валют")
    @DisplayName("Проверка курса валют - базовая")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#providerFindLaptopInCatalog")
    public void testCatalogTest(){
        chromeDriver.get("https://market.yandex.ru/");
        YandexBeforeSearch yandexBeforeSearch = new YandexBeforeSearch(chromeDriver);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        yandexBeforeSearch.buttonClick();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Feature("Проверка курса валют")
    @DisplayName("Проверка курса валют - базовая")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#providerFindLaptopInCatalog")
    public void testElectronicTest(){
        chromeDriver.get("https://market.yandex.ru/");
        YandexBeforeSearch yandexBeforeSearch = new YandexBeforeSearch(chromeDriver);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        yandexBeforeSearch.buttonClick();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        yandexBeforeSearch.electronicMouseOver();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Feature("Проверка курса валют")
    @DisplayName("Проверка курса валют - базовая")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#providerFindLaptopInCatalog")
    public void testLaptopTest(){
        chromeDriver.get("https://market.yandex.ru/");
        YandexBeforeSearch yandexBeforeSearch = new YandexBeforeSearch(chromeDriver);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        yandexBeforeSearch.buttonClick();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        yandexBeforeSearch.electronicMouseOver();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        yandexBeforeSearch.laptopClick();
    }


    @Feature("Проверка курса валют")
    @DisplayName("Проверка курса валют - базовая")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#providerFindLaptopInCatalog")
    public void testLaptopTitleTest(){
        chromeDriver.get("https://market.yandex.ru/");
        //YandexBeforeSearch googleBeforeSearch = new YandexBeforeSearch(chromeDriver);
//        googleBeforeSearch.find(searchQuery);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String pageTitle = chromeDriver.getTitle();
        System.out.println(pageTitle);
        Assertions.assertTrue(pageTitle.contains("Ноутбуки"),"Тайтл "+pageTitle+" на сайте не содержит Ноутбуки");
    }

    @Feature("Проверка курса валют")
    @DisplayName("Проверка курса валют - базовая")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#providerFindLaptopInCatalog")
    public void testMoneyTest(){
        chromeDriver.get("https://market.yandex.ru/");
        YandexBeforeSearch yandexBeforeSearch = new YandexBeforeSearch(chromeDriver);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        yandexBeforeSearch.buttonClick();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        yandexBeforeSearch.electronicMouseOver();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        yandexBeforeSearch.laptopClick();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        YandexAfterSearch yandexAfterSearch = new YandexAfterSearch(chromeDriver);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        yandexAfterSearch.inputMoneyInterval("10000","30000");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        yandexAfterSearch.inputBrands("Lenovo", "HP");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(yandexAfterSearch.CountOfElementsOnFirstPage()>12);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        yandexAfterSearch.CountOfElementsOnAllPages();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(yandexAfterSearch.checkFilters("10000", "30000", List.of("HP", "Lenovo", "ThinkPad")));
        System.out.println("--------------------------------------------------");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        yandexAfterSearch.rememberFirstLaptop();
    }

}

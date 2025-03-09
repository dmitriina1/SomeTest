package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class YandexAfterSearch {

    private WebDriver driver;

    private WebDriverWait wait;

    private WebElement minimumPricePlace;
    private WebElement maximumPricePlace;
    private WebElement selectorHP;
    private WebElement selectorLenovo;


    public org.openqa.selenium.WebDriver getWebDriver() {
        return driver;
    }

    public void inputMoneyInterval(String minimum, String maximum) {
        wait.until(visibilityOfElementLocated(By.xpath("//div[contains(@data-grabber, 'SearchFilters')]//span[contains(@data-auto, 'filter-range-min')]//label[contains(text(), 'Цена')]/..//input")));
        this.minimumPricePlace = driver.findElement(By.xpath("//div[contains(@data-grabber, 'SearchFilters')]//span[contains(@data-auto, 'filter-range-min')]//label[contains(text(), 'Цена')]/..//input"));
        minimumPricePlace.click();
        minimumPricePlace.sendKeys(minimum + Keys.ENTER);


        wait.until(visibilityOfElementLocated(By.xpath("//div[contains(@data-grabber, 'SearchFilters')]//span[contains(@data-auto, 'filter-range-max')]//label[contains(text(), 'Цена')]/..//input")));
        this.maximumPricePlace = driver.findElement(By.xpath("//div[contains(@data-grabber, 'SearchFilters')]//span[contains(@data-auto, 'filter-range-max')]//label[contains(text(), 'Цена')]/..//input"));
        maximumPricePlace.click();
        maximumPricePlace.sendKeys(maximum + Keys.ENTER);
    }


    public void inputBrands(String nameOne, String nameTwo) {
        wait.until(visibilityOfElementLocated(By.xpath("//div[contains(@data-grabber, 'SearchFilters')]//div[contains(@data-zone-data, 'Бренд')]//div[contains(@data-zone-name, 'FilterValue')]//span[contains(text(), '" + nameOne + "' )]")));
        this.selectorLenovo = driver.findElement(By.xpath("//div[contains(@data-grabber, 'SearchFilters')]//div[contains(@data-zone-data, 'Бренд')]//div[contains(@data-zone-name, 'FilterValue')]//span[contains(text(), '" + nameOne + "')]"));
        selectorLenovo.click();

        wait.until(visibilityOfElementLocated(By.xpath("//div[contains(@data-grabber, 'SearchFilters')]//div[contains(@data-zone-data, 'Бренд')]//div[contains(@data-zone-name, 'FilterValue')]//span[contains(text(), '" + nameTwo + "')]")));
        this.selectorHP = driver.findElement(By.xpath("//div[contains(@data-grabber, 'SearchFilters')]//div[contains(@data-zone-data, 'Бренд')]//div[contains(@data-zone-name, 'FilterValue')]//span[contains(text(), '" + nameTwo + "')]"));
        selectorHP.click();
    }

    /**
     * На одной странице 16 товаров
     */

    List<WebElement> list;

    public int CountOfElementsOnFirstPage() {
//        while(driver.findElement(By.xpath("//div[contains(@data-baobab-name, 'pager')]")) == null){
//           driver.findElements(By.xpath("//div[contains(@data-zone-name, 'searchResults')]//div[contains(@data-auto, 'SerpList')]//div[contains(@data-auto-themename, 'listDetailed')]"));
//        }
        List<WebElement> foundElements = new ArrayList<>();
        Actions actions = new Actions(driver);

        // Прокручиваем страницу, пока не найдем элемент
        while (true) {
            // Находим элементы по заданному XPath и добавляем их в список
            List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@data-auto, 'SerpList')]//div[contains(@data-auto-themename, 'listDetailed')]"));
            foundElements.addAll(elements);

            // Проверяем, найден ли целевой элемент
            if (driver.findElements(By.xpath("//div[contains(@data-baobab-name, 'pager')]")).size() > 0) {
                break; // Если элемент найден, выходим из цикла
            }

            // Прокручиваем страницу вниз, имитируя нажатие клавиши "Page Down"
            actions.sendKeys(org.openqa.selenium.Keys.PAGE_DOWN).perform();

            // Ждем некоторое время, чтобы страница успела загрузить новые элементы
            try {
                Thread.sleep(2000); // Можно использовать WebDriverWait для более надежного ожидания
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return foundElements.size();
    }

    public void CountOfElementsOnAllPages() {
        List<WebElement> foundElements = new ArrayList<>();
        Actions actions = new Actions(driver);
        long startTime = System.currentTimeMillis();
        long timeLimit = 2 * 60 * 1000; // 2 минуты в миллисекундах

        int maxLimit = 800;
        int count = 0;

        while (System.currentTimeMillis() - startTime < timeLimit || count >= maxLimit) {
            if (driver.findElements(By.xpath("//div[contains(@data-baobab-name, 'pager')]" +
                    "//div[contains(@data-baobab-name, 'next')]")).isEmpty()) {
                List<WebElement> elements = driver.findElements(By.xpath(
                        "//div[contains(@data-auto, 'SerpList')]" +
                                "//div[contains(@data-auto-themename, 'listDetailed')]"));
                foundElements.addAll(elements);
                break;
            } else {
                actions.sendKeys(org.openqa.selenium.Keys.PAGE_DOWN).perform();
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                            "//div[contains(@data-auto, 'SerpList')]" +
                                    "//div[contains(@data-auto-themename, 'listDetailed')]")));
                } catch (TimeoutException e) {
                    System.out.println("Элемент не появился вовремя.");
                    break;
                }
            }
            count++;
        }

        list = foundElements;
        // System.out.println(list.size());
    }

    public boolean checkFilters(String minimum, String maximum, List<String> namesOfBrand) {
//        List<WebElement> find = new ArrayList<>();
//        find = list.stream().filter().allMatch(product ->
//                (product.findElement(By.xpath("//div[contains(@data-baobab-name, 'title')]//span")).getText().contains("HP") || product.findElement(By.xpath("//div[contains(@data-baobab-name, 'title')]//span")).getText().contains("Lenovo")));
//                // product.getPrice() >= 10000 && product.getPrice() <= 30000
//        List<WebElement> find = new ArrayList<>();
//        for (WebElement element : list) {
//            element.findElement(By.xpath(".//div[contains(@data-baobab-name, 'title')]//span")).click();
//            WebElement el = driver.findElement(By.xpath("//div[contains(@data-zone-name, 'fullSpecs')]//div[contains(@aria-label, 'Характеристики')]//span[contains(text(),'Бренд')]/../.. /following-sibling::div[1]//span"));
//            if(el.getText().contains("HP") || el.getText().contains("Lenovo"))
//                find.add(el);
//            driver.close();
//        }
        /**
         * Полуение заголовков
         */
        List<WebElement> find = list.stream()
                .filter(product -> {
                    String titleText = product.findElement(By.xpath(
                            ".//div[contains(@data-baobab-name, 'title')]//span")).getText().toLowerCase();
                    return namesOfBrand.stream()
                            .map(String::toLowerCase)
                            .anyMatch(titleText::contains); // Возвращаем boolean
                })
                .collect(Collectors.toList());
/**
 * Дополнить, если не находит в названии - лезь в карточку
 */
        //  System.out.println(list.get(0).findElement(By.xpath("//div[contains(@data-auto, 'SerpList')]//div[contains(@data-auto-themename, 'listDetailed')]//div[contains(@data-baobab-name, 'price')]//span[contains(@data-auto,'snippet-price-current')]//span")).getText());
        System.out.println(find.size());
        System.out.println(list.size());
        List<WebElement> remaining = list.stream()
                .filter(product -> !find.contains(product)) // Оставляем элементы, которых нет в find
                .collect(Collectors.toList());
        if(remaining.size()>0){
            for (WebElement element : remaining) {
                element.click();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Получаем список вкладок
                ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

                // Переключаемся на последнюю открытую вкладку
                driver.switchTo().window(tabs.get(tabs.size() - 1));

                WebElement brand = driver.findElement(By.xpath("//div[contains(@data-zone-name," +
                        " 'fullSpecs')]//div[contains(@aria-label, 'Характеристики')]//span[contains(text(),'Бренд')]" +
                        "/../.. /following-sibling::div[1]//span"));
                String brandText = brand.getText().trim(); // Убираем лишние пробелы

                boolean containsBrand = namesOfBrand.stream().anyMatch(brandText::equalsIgnoreCase);
                if(containsBrand){
                    find.add(element);
                }
                // Закрываем текущую (новую) вкладку
                driver.close();

                // Переключаемся обратно на первую вкладку
                driver.switchTo().window(tabs.get(0));
            }
        }
        System.out.println("---------------------------------------------------------");
        System.out.println(find.size());
        System.out.println(list.size());
        return find.size() == list.size();
    }

    WebElement firstLaptop;

//    public void rememberFirstLaptop(){
//        Actions actions = new Actions(driver);
//        long startTime = System.currentTimeMillis();
//        long timeLimit = 60 * 1000; // 2 минуты в милли
//        while (System.currentTimeMillis() - startTime < timeLimit){
//            if(driver.findElements(By.xpath("//div[contains(@data-zone-name, 'menu')]")).isEmpty()) {
//                firstLaptop = driver.findElement((By.xpath("//div[contains(@data-auto, 'SerpList')]" +
//                        "//div[contains(@data-auto-themename, 'listDetailed')]")));
//                System.out.println(firstLaptop.getText());
//                break;
//            }
//            else {
//                actions.sendKeys(org.openqa.selenium.Keys.PAGE_UP).perform();
//                try {
//                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
//                            "//div[contains(@data-zone-name, 'menu')]")));
//                } catch (TimeoutException e) {
//                    System.out.println("Элемент не появился вовремя.");
//                    break;
//                }
//            }
//        }
//
//    }

    public void rememberFirstLaptop() {
        Actions actions = new Actions(driver);
//        long startTime = System.currentTimeMillis();
//        long timeLimit = 60 * 1000; // 60 секунд
//
//        while (System.currentTimeMillis() - startTime < timeLimit) {
//            // Проверяем, если меню отсутствует, находим первый ноутбук
//            if (driver.findElements(By.xpath("//div[contains(@data-zone-name, 'menu')]")).isEmpty()) {
//                List<WebElement> laptops = driver.findElements(By.xpath(
//                        "//div[contains(@data-auto, 'SerpList')]//div[contains(@data-auto-themename, 'listDetailed')]"));
//
//                if (!laptops.isEmpty()) {  // Проверяем, что нашли ноутбуки
//                    firstLaptop = laptops.get(0); // Берём первый элемент
//                    System.out.println(firstLaptop.getText());
//                    break;
//                }
//            }
//
//            // Поднимаемся вверх
            actions.sendKeys(Keys.HOME).perform(); // Поднимаемся в начало страницы
//
//            try {
//                wait.until(ExpectedConditions.visibilityOfElementLocated(
//                        By.xpath("//div[contains(@data-zone-name, 'menu')]")));
//            } catch (TimeoutException e) {
//                System.out.println("Меню не появилось вовремя.");
//                break;
//            }
//        }
        firstLaptop = list.get(0);
        System.out.println(firstLaptop.getText());
    }


    public YandexAfterSearch(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.list = new ArrayList<>();
    }

    public YandexAfterSearch(WebDriver driver, String searchQuery) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        driver.get("https://www.google.ru/search?q=" + searchQuery);
    }

    public void checkingTitleByText(String link) {
        wait.until(visibilityOfElementLocated(By.xpath("//div[@id='rso']//div/div//div[not(@style)]/div/div/span/a/h3[contains(.,'" + link + "')]")));
        Assertions.assertFalse(driver.findElements(By.xpath("//div[@id='rso']//div/div//div[not(@style)]/div/div/span/a/h3[contains(.,'" + link + "')]")).size() == 0,
                "Не найдено тайтла с текстом: '" + link);
    }

    public void goPageByLinkName(String link) {
        wait.until(visibilityOfElementLocated(By.xpath("//div[@id='rso']//div/div//div[not(@style)]/div/div/span/a/h3[contains(.,'" + link + "')]")));
        driver.findElement(By.xpath("//div[@id='rso']//div/div//div[not(@style)]/div/div/span/a/h3[contains(.,'" + link + "')]")).click();

        Set<String> tabs = driver.getWindowHandles();
        for (String tab : tabs)
            driver.switchTo().window(tab);
    }

}

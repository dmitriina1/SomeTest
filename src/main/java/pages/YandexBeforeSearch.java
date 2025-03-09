package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class YandexBeforeSearch {

    private WebElement electronicPlace;
    private WebElement catalogButton;
    private WebElement laptopLink;
    private WebDriver driver;

    private WebDriverWait wait;

    public YandexBeforeSearch(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(driver, 10);
        wait.until(visibilityOfElementLocated(By.xpath("//header[contains(@data-baobab-name, 'header')]//div[contains(@data-baobab-name, 'catalog')]")));
        this.catalogButton = driver.findElement(By.xpath("//header[contains(@data-baobab-name, 'header')]//div[contains(@data-baobab-name, 'catalog')]"));
//        this.buttonSearch = driver.findElement(By.xpath("//div[not (@jsname)]/center/*[@value='Поиск в Google']"));
    }

    @Step("Поиск в Google по слову {text}")
    public void buttonClick(){
        catalogButton.click();
    }

    @Step("Перебор и нахождение электроники")
    public void electronicMouseOver(){
        wait.until(visibilityOfElementLocated(By.xpath("//div[contains(@data-zone-name, 'catalog-content')]//ul[contains(@role, 'tablist')]//a//span[contains(text(), 'Электроника')]")));
        this.electronicPlace = driver.findElement(By.xpath("//div[contains(@data-zone-name, 'catalog-content')]//ul[contains(@role, 'tablist')]//a//span[contains(text(), 'Электроника')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(electronicPlace).perform();
    }

    @Step("Перебор и нахождение электроники")
    public void laptopClick(){
        wait.until(visibilityOfElementLocated(By.xpath("//div[contains(@role,'tabpanel')]//div[contains(@data-auto, 'category')]//ul//li//a[contains(text(), 'Ноутбуки')]")));
        this.laptopLink = driver.findElement(By.xpath("//div[contains(@role,'tabpanel')]//div[contains(@data-auto, 'category')]//ul//li//a[contains(text(), 'Ноутбуки')]"));
        laptopLink.click();
    }
}

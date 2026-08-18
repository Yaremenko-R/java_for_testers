package tests;

import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase{

  @Test
  public void canCreateContact() {
    driver.findElement(By.linkText("add new")).click();
    driver.findElement(By.name("firstname")).click();
    driver.findElement(By.name("firstname")).sendKeys("qwe");
    driver.findElement(By.name("middlename")).click();
    driver.findElement(By.name("middlename")).sendKeys("qwe");
    driver.findElement(By.name("lastname")).click();
    driver.findElement(By.name("lastname")).sendKeys("qwe");
    driver.findElement(By.name("address")).click();
    driver.findElement(By.name("address")).sendKeys("moscow");
    driver.findElement(By.name("mobile")).click();
    driver.findElement(By.name("mobile")).sendKeys("+74951111111");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).sendKeys("q@m.ru");
    driver.findElement(By.cssSelector("input:nth-child(71)")).click();
    driver.findElement(By.linkText("home page")).click();
  }
}

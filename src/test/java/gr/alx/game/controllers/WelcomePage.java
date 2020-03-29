package gr.alx.game.controllers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class WelcomePage
{

   @FindBy(className = "ui-layout-unit-content")
   private WebElement contentDiv;

    @FindBy(css = "tr")
    private List<WebElement> userTableRows;

   public WebElement getContentDiv()
   {
      return this.contentDiv;
   }

    public List getUserTableRows() {
        return userTableRows;
    }
}
package com.epam.hackathon.emotional_help.testing.ui.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;

public class SelfTestPage extends BasePage{

    @FindBy(css = "main p:not(:only-child)")
    private WebElement questionNumberParagraph;

    @FindBy(css = "main p:only-child")
    private WebElement questionTextParagraph;

    @FindBy(css = "main button")
    private List<WebElement> answerButtons;

    @FindBy(className = "recharts-wrapper")
    private WebElement resultDiagram;

    @FindBy(css = "main p+a")
    private WebElement videoLink;

    @FindBy(css = "main a+p")
    private WebElement tipParagraph;

    public SelfTestPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {
        driver.navigate().to(BASE_URI + "/selftest");
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        assertTrue("Expected to be on self test page, but was on " + url, url.contains("selftest"));
    }

    public String getQuestionNumberText() {
        return questionNumberParagraph.getText();
    }

    public String getQuestionText() {
        return questionTextParagraph.getText();
    }

    public void chooseAnswerByIndex(int index) {
        answerButtons.get(index).click();
    }

    public boolean isResultDiagramDisplayed() {
        return resultDiagram.isDisplayed();
    }

    public boolean isVideoLinkDisplayed() {
        return videoLink.isDisplayed();
    }

    public boolean isTipParagraphDisplayed() {
        return tipParagraph.isDisplayed();
    }
}

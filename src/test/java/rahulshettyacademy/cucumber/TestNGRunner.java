package rahulshettyacademy.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//Cucumber depends on TestNG, Junit (selected based on assertions used)
@CucumberOptions(features="C:/Users/Lenovo/workspace/SeleniumFrameworkDesign/src/test/java/rahulshettyacademy/cucumber",glue="rahulshettyacademy.stepdefinitions",
monochrome=true,tags="@Regression",plugin= {"html:target/cucumber.html"})
public class TestNGRunner extends AbstractTestNGCucumberTests{

}

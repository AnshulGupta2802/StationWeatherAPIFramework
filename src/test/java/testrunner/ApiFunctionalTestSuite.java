package testrunner;

import init.ApiInit;

import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features={"src/test/resources"},
glue = "stepdefinitions",
tags = {"@test"}
		)

public class ApiFunctionalTestSuite {

@BeforeClass
public static void setup(){
	ApiInit apiInit = new ApiInit();
	apiInit.init();
	}
}

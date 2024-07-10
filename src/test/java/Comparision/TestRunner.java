package Comparision;

import org.junit.runner.RunWith;
import io.cucumber.junit.*;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)

//@CucumberOptions(features= "src/test/resources/FeatureFiles",
@CucumberOptions(features= "src/test/resources/FeatureFiles/NDC-FareFamilySearch-OneWay_1ADT.feature",
tags = "@18_2",
glue= {"versioncomparision"},
plugin = {"json:target/cucumber.json"}
//plugin = {"html:target/CucumberReport.html"},
		
)

public class TestRunner {
	
	
	
	

}

package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
       // features = {".//features/"},
         features = {".//features/Login.feature"},
        //  features = {".//features/LoginDDT.feature"},
        glue = "stepDefinitions",
        // to check feature file has all steps defined
        dryRun = false,
        // tags ="@sanity" // scenarios taged with sanity
        //tags ="@sanity and @regression" // scenarios tagged with sanity and regression
        //tags ="@sanity or @regression" // all scenarios tagged with sanity and regression
      //  tags = "@sanity and not @regression"//Sanity but not regression

        plugin= {"pretty",
            "html:reports/myreport.html",
                "rerun:target/rerun.txt", //To capture failures
            }


)
class TestRun {
}

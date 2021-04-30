package webPro.webProject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AppTest 
{
 
	@ParameterizedTest
	@CsvSource({
			"Luka Doncic,    2020-21,   3.0",
			"Luka Doncic,    2019-20,   2.8",
			"Michael Jordan,  1994-95,   0.9",
			"Tracy McGrady,   2003-04,   2.6",
			"Scottie Pippen,  1993-94,   0.9",
			"Stephen Curry,   2020-21,   5.2",
			"Kobe Bryant,   2014-15,   1.5",
	})
	
    void test(String name, String year, String expectedResult) throws IOException
    {
    	App test = new App();
        assertEquals(expectedResult,  test.getData(name).get(year), 
        		() -> name + " + " + year + " should equal " + expectedResult);
    
    }
}
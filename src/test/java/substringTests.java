import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test for {@link String}
 */

public class substringTests {

    @DataProvider(name = "validSubstrings")
    public Object[][] indexesAndResult(){
        return new Object[][]{
                {5,10, "is a "},
                {1,1, ""},
                {0,setup().length(), setup()}
        };
    }

    @DataProvider(name = "negativeSubstrings")
    public Object[][] indexes(){
        return new Object[][]{
                {10,5},
                {-1,4},
                {0,setup().length()+1},
                {0,Integer.MAX_VALUE+1}
        };
    }

    @BeforeSuite
    public String setup(){
        String sourceString = "This is a test string for gett assignment";
        return sourceString;
    }

    @Test(dataProvider = "validSubstrings")
    public void testValidSubstrings(int beginIndex, int endIndex, String result){
        Assert.assertEquals(setup().substring(beginIndex, endIndex), result);
    }

    @Test(dataProvider = "negativeSubstrings",
            expectedExceptions = {StringIndexOutOfBoundsException.class},
            expectedExceptionsMessageRegExp = "String index out of range: .*")
    public void testNegativeSubstrings(int beginIndex, int endIndex){
        Assert.fail(setup().substring(beginIndex, endIndex));
    }
}

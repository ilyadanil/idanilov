import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * This class contains tests for standard substring method
 */

public class SubstringTests {

    private static final String sourceString = "This is a test string for gett assignment";

    /**
     * Following data provider contains parameters for positive tests. Order
     * of parameters: inputString, beginIndex, endIndex, expectedResult. Cases tested currently:
     * - getting part of input string
     * - getting empty string
     * - getting full string
     */
    @DataProvider(name = "test data for positive tests")
    public Object[][] positiveTestData(){
        return new Object[][]{
                {sourceString, 5, 10, "is a "},
                {sourceString, 1, 1, ""},
                {sourceString, 0, sourceString.length(), sourceString}
        };
    }

    /**
     * Following data provider contains parameters for negative tests. Order
     * of parameters: inputString, beginIndex, endIndex. Cases tested currently:
     * - beginIndex > endIndex
     * - beginIndex < 0
     * - endIndex > length of input string
     * - endIndex is out of Integer range
     */
    @DataProvider(name = "test data for negative tests")
    public Object[][] negativeTestData(){
        return new Object[][]{
                {sourceString, 10, 5},
                {sourceString, -1, 4},
                {sourceString, 0,sourceString.length()+1},
                {sourceString, 0,Integer.MAX_VALUE+1}
        };
    }

    @Test(dataProvider = "test data for positive tests")
    public void positiveTests(String sourceString, int beginIndex, int endIndex, String result){
        Assert.assertEquals(sourceString.substring(beginIndex, endIndex), result);
    }

    @Test(dataProvider = "test data for negative tests")
    public void negativeTests(String sourceString, int beginIndex, int endIndex){
        int failedIndex;

        if (beginIndex < 0)
             failedIndex = beginIndex;
        else failedIndex = endIndex - beginIndex;

        String expectedMessage = String.format("String index out of range: %d", failedIndex);

        try {
            Assert.fail(sourceString.substring(beginIndex, endIndex));
        }
        catch(Exception e) {
            Assert.assertEquals(e.getMessage(), expectedMessage);
        }
    }
}

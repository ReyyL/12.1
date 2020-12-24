import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class Tests {

    @Test(expected = NullPointerException.class)
    public void testNull() {
        Integer.decode(null);
    }

    @Test(expected = NumberFormatException.class)
    public void testEmptyString() {
        Integer.decode("");
    }

    @Test(expected = NumberFormatException.class)
    public void testString() {
        Integer.decode("String");
    }

    @Test
    public void testZero() {
        Assert.assertEquals(0, (int) Integer.decode("0"));
    }

    @Test
    public void testNegative(){
        Assert.assertEquals(-7, Integer.decode("-7").longValue());
    }

    @Test
    public void testPositive(){
        Assert.assertEquals(4, Integer.decode("+4").longValue());
    }

    @Test
    public void testNegativeOctalDigits() {
        Assert.assertEquals(-124, Integer.decode("-0174").longValue());
        Assert.assertEquals(-312, Integer.decode("-0470").longValue());
        Assert.assertEquals(-394, Integer.decode("-0612").longValue());
    }

    @Test
    public void testPositiveOctalDigits() {
        Assert.assertEquals(63, Integer.decode("077").longValue());
        Assert.assertEquals(21, Integer.decode("025").longValue());
        Assert.assertEquals(234, Integer.decode("0352").longValue());
    }

    @Test
    public void testNegativeHexDigits() {
        Assert.assertEquals(-123, Integer.decode("-0X7B").longValue());
        Assert.assertEquals(-250, Integer.decode("-#FA").longValue());
        Assert.assertEquals(-2744, Integer.decode("-0xAB8").longValue());
    }

    @Test
    public void testPositiveHexDigits() {
        Assert.assertEquals(4779, Integer.decode("0x12AB").longValue());
        Assert.assertEquals(500, Integer.decode("0X1F4").longValue());
        Assert.assertEquals(234, Integer.decode("#EA").longValue());
    }

    @Test(expected = NumberFormatException.class)
    public void testWrongSignPosition1() {
        Integer.decode("0x+44");
        Integer.decode("0X+e2");
        Integer.decode("#+78");
        Integer.decode("0+312");
        Integer.decode("0Xa+1");
    }

    @Test(expected = NumberFormatException.class)
    public void testWrongSignPosition2() {
        Integer.decode("0x-55");
        Integer.decode("0X-1b");
        Integer.decode("#-d3");
        Integer.decode("0-634");
        Integer.decode("#d3-3");
    }

    @Test(expected = NumberFormatException.class)
    public void testSpace() {
        Integer.decode("- 123");
        Integer.decode(" 37");
        Integer.decode("# c2");
        Integer.decode("0x c2");
        Integer.decode("0X c2");
        Integer.decode("0 6534");
        Integer.decode("5345 37");
    }

    @Test
    public void testIntegerMinValue() {
        Assert.assertEquals(Integer.MIN_VALUE, Integer.decode(Integer.toString(Integer.MIN_VALUE)).longValue());
    }

    @Test
    public void testIntegerMaxValue() {
        Assert.assertEquals(Integer.MAX_VALUE, Integer.decode(Integer.toString(Integer.MAX_VALUE)).longValue());
    }
}

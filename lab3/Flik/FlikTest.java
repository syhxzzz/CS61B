import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest{
    @Test
    public void testisSameNumber(){
        int a,b,c,d;
        a=b=99;
        c=d=128;
        assertTrue(Flik.isSameNumber(a,b));
        assertTrue(Flik.isSameNumber(c, d));
    }
}
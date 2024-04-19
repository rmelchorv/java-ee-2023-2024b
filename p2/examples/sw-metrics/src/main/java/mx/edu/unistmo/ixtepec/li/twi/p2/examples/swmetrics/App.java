package mx.edu.unistmo.ixtepec.li.twi.p2.examples.swmetrics;

public class App 
{
    public int majorNum(int a, int b, int c) {
        if (a > b && a > c) {
            return a;
        } else if (c > b) {
            return c;
        } else {
            return b;
        }
    }
}

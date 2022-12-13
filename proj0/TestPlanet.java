public class TestPlanet {
    public static void main(String[] args){
        Planet p1 = new Planet(0,0,0,0,10,"1");
        Planet p2 = new Planet(5,0,0,0,10,"2");
        System.out.println(p1.calcForceExertedBy(p2));
        System.out.println(100*6.67e-11/25);
    }
}
public class NBody {
    public static double readRadius(String fileName){
        In in = new In(fileName);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }
    
    public static Planet[] readPlanets(String fileName){
        In in = new In(fileName);
        int array_length=in.readInt();
        double selfRadius=in.readDouble();
        Planet[] ans = new Planet[array_length];
        for(int i=0;i< array_length; i+=1){
            ans[i]=new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        }
        return ans;
    }
    public static void main(String[] args) {
        double T =Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double radius = readRadius(fileName);
        Planet[] planets = readPlanets(fileName);
        int numOfPlanets = planets.length;
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for(int i=0;i<numOfPlanets;i+=1){
            planets[i].draw();
        }
        StdDraw.enableDoubleBuffering();
        double t=0;
        while(t<T){
            double[] xForces = new double[numOfPlanets];
            double[] yForces = new double[numOfPlanets];
            for(int i=0;i<numOfPlanets;i+=1){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
                planets[i].update(dt, xForces[i], yForces[i]);
                }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(int i=0;i<numOfPlanets;i+=1){
                planets[i].draw();
                }
            StdDraw.show();
            StdDraw.pause(10);
            t+=dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < numOfPlanets; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
            }
    }
}

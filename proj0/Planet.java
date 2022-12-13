import javax.xml.xpath.XPathExpression;

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public double G=6.67e-11;
    public Planet(double xP, double yP, double xV,double yV, double m, String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }
    public Planet(Planet p){
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }
    public double calcDistance(Planet p){
        double dx,dy;
        dx=p.xxPos-xxPos;
        dy=p.yyPos-yyPos;
        return Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
    }
    public double calcForceExertedBy(Planet p){
        double dist=calcDistance(p);
        return G*mass*p.mass*Math.pow(dist, -2);
    }
    public double calcForceExertedByX(Planet p){
        double dx=p.xxPos-xxPos;
        return dx*calcForceExertedBy(p)/calcDistance(p);
    }
    public double calcForceExertedByY(Planet p){
        double dy=p.yyPos-yyPos;
        return dy*calcForceExertedBy(p)/calcDistance(p);
    }
    public double calcNetForceExertedByX(Planet[] arrayPlanets){
        double sum=0;
        for(int i=0;i<arrayPlanets.length;i+=1){
            if(arrayPlanets[i].equals(this)){
                continue;
            }
        sum+=calcForceExertedByX(arrayPlanets[i]);
        }
        return sum;
    }
    public double calcNetForceExertedByY(Planet[] arrayPlanets){
        double sum=0;
        for(int i=0;i<arrayPlanets.length;i+=1){
            if(arrayPlanets[i].equals(this)){
                continue;
            }
        sum+=calcForceExertedByY(arrayPlanets[i]);
        }
        return sum;
    }
    public void update(double dt,double fX,double fY){
        double Ax=fX/mass;
        double Ay=fY/mass;
        xxVel+=dt*Ax;
        yyVel+=dt*Ay;
        xxPos+=xxVel*dt;
        yyPos+=yyVel*dt;
    }
    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }
}
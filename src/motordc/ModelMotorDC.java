
package motordc;


//@author Bogdan Lazar
 
public class ModelMotorDC {
    
    public MotorDC motor;
    private double turatieTinta = Double.NaN;
    private double Kp = Double.NaN;
    private double Ki = Double.NaN;
    private double integralaEroare = 0;
    private double P;
    private double I;

    
    
    ModelMotorDC(MotorDC motor){
        this.motor = motor;
    }

    public void setTuratieTinta(double turatieTinta) {
        this.turatieTinta = turatieTinta;
    }

    public void setKp(double Kp) {
        this.Kp = Kp;
    }
    
    public void setKi(double Ki) {
        this.Ki = Ki;
    }

    public void setDerivEroare(double integralaEroare) {
        this.integralaEroare = integralaEroare;
    }

    public double getTuratieTinta() {
        return turatieTinta;
    }

    public double getKp() {
        return Kp;
    }

    public double getKi() {
        return Ki;
    }

    public double getP() {
        return P;
    }

    public double getI() {
        return I;
    }
    
    
    
    public double[] derivn (double t,double[] stare,double tensiune) {
    // Comanda vine de obiectul motorDC
        var dydt = new double [2];
        // ecuatie curent
        dydt[0] = (1/motor.getLa_H()) * (tensiune - motor.getRa_Ohm() * stare[0] -motor.getKf() * stare[1]);
        //ecuatie viteza turatie
        dydt[1] = (1/motor.getJ()) * (motor.getKf()*stare[0] - motor.getSarcina() - motor.getF() * stare[1]);
        
        
        return dydt;
    }
    
    public double PI(double turatie, double deltaT){
        if( Double.isNaN(turatieTinta))throw new IllegalArgumentException("Turatia tinta nu este specificata");
        if( Double.isNaN(Kp))throw new IllegalArgumentException("Kp nu este specificat");
        if( Double.isNaN(Ki))throw new IllegalArgumentException("Ki nu este specificata");
        double eroare = turatieTinta  - turatie;
        
        eroare = eroare *(2*Math.PI)/60;
        P = Kp * eroare;
        I = Ki * integralaEroare;
        
        double tensiuneControl = P + I ;

        if(tensiuneControl > 12){
            tensiuneControl = 12;
        }
       if(tensiuneControl < -12){
            tensiuneControl = -12;
       }
        integralaEroare = integralaEroare + eroare * deltaT;
        return Math.rint(tensiuneControl);
    }
}
    


package motordc;
// @author Bogdan Lazar

public class MotorDC {
    // Inductanta motor
    private double La_H; // H
    // Rezistenta motor
    private double Ra_Ohm;   //ohm
    // Constanta Motor
    private double Kf; 
    // Constanta de frecare
    private double f; //
    // Momentul de inertie al motorului
    private double J; //Kg.m^
    // Tensiune de alimentare
    private double Va_Volts; // V
    // Sarcina ceruta
    private double Sarcina;
  
    MotorDC(double La_H, double Ra_Ohm, double Kf, double f, double J){
    
        this.La_H = La_H;
        this.Ra_Ohm = Ra_Ohm;
        this.Kf = Kf;
        this.f = f;
        this.J = J;
    }

    public void setLa_H(double La_H) {
        this.La_H = La_H;
    }

    public void setRa_Ohm(double Ra_Ohm) {
        this.Ra_Ohm = Ra_Ohm;
    }

    public void setKf(double Kf) {
        this.Kf = Kf;
    }
    
    public void setF(double f) {
        this.f = f;
    }

    public void setJ(double J) {
        this.J = J;
    }

    public void setVa_Volts(double Va_Volts) {
        this.Va_Volts = Va_Volts;
    }

    public void setSarcina(double Sarcina) {
        this.Sarcina = Sarcina;
    }

    public double getLa_H() {
        return La_H;
    }

    public double getRa_Ohm() {
        return Ra_Ohm;
    }

    public double getKf() {
        return Kf;
    }

    public double getF() {
        return f;
    }

    public double getJ() {
        return J;
    }

    public double getVa_Volts() {
        return Va_Volts;
    }

    public double getSarcina() {
        return Sarcina;
    }

    @Override
    public String toString() {
        return "MotorDC{" + "La_H=" + La_H + ", Ra_Ohm=" + Ra_Ohm + ", Kf=" + Kf +", f=" + f + ", J=" + J + ", Va_Volts=" + Va_Volts + ", Sarcina=" + Sarcina + '}';
    }
}



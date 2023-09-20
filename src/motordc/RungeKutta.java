package motordc;
//@author Bogdan Lazar
import java.util.ArrayList;

public class RungeKutta {

    private boolean PIDEnable = false;
    private double tensiuneIntrare;
    private double timpStart = Double.NaN;
    private double timpStop = Double.NaN;           
    private double[] conditiInitiale = {0, 0};
    private double pasIntegrare = Double.NaN;
 
    public void setInitialValueOfX(double timpStart) {
        this.timpStart = timpStart;
    }

    public void setFinalValueOfX(double timpStop) {
        this.timpStop = timpStop;
    }
    
    public void setInitialValueOfY(double[] conditiInitiale) {
        this.setInitialValuesOfY(conditiInitiale);
    }

    public void setInitialValuesOfY(double[] conditiInitiale) {
        this.conditiInitiale = conditiInitiale;
    }

    public void setPasIntegrare(double pasIntegrare) {
        this.pasIntegrare = pasIntegrare;
    }

    public void setPIDEnable(boolean PIDEnable) {
        this.PIDEnable = PIDEnable;
    }

    public void setTensiuneIntrare(double tensiuneIntrare) {
        this.tensiuneIntrare = tensiuneIntrare;
    }

    public double getX0() {
        return timpStart;
    }

    public double getXn() {
        return timpStop;
    }

    public double[] getYy0() {
        return conditiInitiale;
    }

    public double getPasIntegrare() {
        return pasIntegrare;
    }

    public boolean isPIDEnable() {
        return PIDEnable;
    }

    public ArrayList RungeKuttaOrdin4(ModelMotorDC g) {
        if (Double.isNaN(this.timpStart)) {
            throw new IllegalArgumentException("Introduceti timpul de start");
        }
        if (Double.isNaN(this.timpStop)) {
            throw new IllegalArgumentException("Introduceti timpul de final");
        }
        if (this.conditiInitiale == null) {
            throw new IllegalArgumentException("Introduceti valorile initiale");
        }
        if (Double.isNaN(this.pasIntegrare)) {
            throw new IllegalArgumentException("Introduceti pasul de esantionare");
        }

        double[] k1 = new double[2];
        double[] k2 = new double[2];
        double[] k3 = new double[2];
        double[] k4 = new double[2];
        double[] y = new double[2];
        double[] yd = new double[2];
        double[] dydx = new double[2];
        double x = 0.0D;
        ArrayList out = new ArrayList();
        double[] timp;
        double[] turatie;
        double[] curent;
        tensiuneIntrare = g.motor.getVa_Volts();
        if (PIDEnable == true) {
            tensiuneIntrare = 12;
        }
        
        int nrPasi = (int)Math.rint((this.timpStop - this.timpStart) / this.pasIntegrare);
        timp = new double[nrPasi];
        curent = new double[nrPasi];
        turatie = new double[nrPasi];
        System.arraycopy(this.conditiInitiale, 0, y, 0, 2);
        timp[0] = timpStart;
        
        
        for (int j = 1; j < nrPasi; j++) {

            x = this.timpStart + j * pasIntegrare;
            dydx = g.derivn(x, y, tensiuneIntrare);
            for (int i = 0; i < 2; i++) {
                k1[i] = pasIntegrare * dydx[i];
            }

            for (int i = 0; i < 2; i++) {
                yd[i] = y[i] + k1[i] / 2;
            }
            dydx = g.derivn(x + pasIntegrare / 2, yd, tensiuneIntrare);
            for (int i = 0; i < 2; i++) {
                k2[i] = pasIntegrare * dydx[i];
            }

            for (int i = 0; i < 2; i++) {
                yd[i] = y[i] + k2[i] / 2;
            }
            dydx = g.derivn(x + pasIntegrare / 2, yd, tensiuneIntrare);
            for (int i = 0; i < 2; i++) {
                k3[i] = pasIntegrare * dydx[i];
            }

            for (int i = 0; i < 2; i++) {
                yd[i] = y[i] + k3[i];
            }
            dydx = g.derivn(x + pasIntegrare, yd, tensiuneIntrare);
            for (int i = 0; i < 2; i++) {
                k4[i] = pasIntegrare * dydx[i];
            }

            for (int i = 0; i < 2; i++) {
                y[i] += k1[i] / 6 + k2[i] / 3 + k3[i] / 3 + k4[i] / 6;
                if (i == 0) {
                    curent[j] = y[i];
                }
                if (i == 1) {
                    turatie[j] = y[i];
                }
            }
            if(PIDEnable == true){
                tensiuneIntrare = g.PI(turatie[j]* 60/(2*Math.PI),pasIntegrare);
            }
            timp[j] = x;
        }
        
        for(int i = 0; i< turatie.length; i++){
          turatie[i] = turatie[i] * 60/(2*Math.PI) ;
        }
        out.add(timp);
        out.add(curent);
        out.add(turatie);

        return out;
    }
}

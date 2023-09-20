package motordc;

import static Utilitati.Export.PrintToTXT;
import Utilitati.LinePlot;
import java.util.ArrayList;

 // @author Bogdan
 
public class TestMotorDC {

    public static void main(String arg[]){
        double timpStart = 0;
        double timpStop = 0.5;
        double pas = 0.001;
        ArrayList output = new ArrayList();
        double[] initial = {0,0};
        
        
       MotorDC motor = new MotorDC(0.12,6,0.0141,0.00000604,0.00000161);
       ModelMotorDC model = new ModelMotorDC(motor);
       RungeKutta rk = new RungeKutta();
       
       model.setKp(0.3525);
       model.setKi(0.048589);
       motor.setSarcina(0.001);
       motor.setVa_Volts(12);
       model.setTuratieTinta(1500);
       rk.setInitialValueOfX(timpStart);
       rk.setFinalValueOfX(timpStop);
       rk.setInitialValueOfY(initial);
      // rk.setPasEsantion(pas);
       //rk.setPasIntegrare(0.001);
       rk.setPasIntegrare(pas);
       rk.setPIDEnable(false);
       
       
        output = rk.RungeKuttaOrdin4(model);
      
        
        
        
        System.out.println(motor.toString());
        
        LinePlot graficCurent = new LinePlot("Intensitatea curentului",(double[])output.get(0),(double[])output.get(1));
        LinePlot graficTuratie = new LinePlot("Turatia motorului",(double[])output.get(0),(double[])output.get(2));
        
        graficCurent.setTitle("Intensitatea curentului");
        graficTuratie.setTitle("Turatia motorului");
        
        graficCurent.setVisible(true);
        graficTuratie.setVisible(true);
        PrintToTXT("C:\\Users\\Bogdan\\Documents\\Licenta\\Algoritm_RK_MCC matlab",output);
    }
}

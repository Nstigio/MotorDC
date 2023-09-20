
package motordc;

import org.junit.Test;
import static org.junit.Assert.*;


 // @author Bogdan Lazar
 
public class ModelMotorDCTest {
    
    MotorDC motor = new MotorDC(0.12,6,0.0141,0.00000604,0.00000161);
    ModelMotorDC instance = new ModelMotorDC(motor);
    
    public ModelMotorDCTest() {
    }
    
    /**
     * Test of PI method, of class ModelMotorDC.
     */
    @Test
    public void testP() {
        System.out.println("Test Componenta P: \n turatie :5000 rpm \n turatie tinta: 4944 rpm \n Kp: 0.3525");
        double turatie = 5000;
        double deltaT = 0.0;
        instance.setKp(0.3525);
        instance.setKi(0);
        instance.setTuratieTinta(4944);
        double expResult =-2;
        double result = instance.PI(turatie, deltaT);
        assertEquals(expResult, result, 0);
        System.out.println("resultat:" + expResult +"\n\n");
    }
    @Test
    public void testI(){
        
        System.out.println("Test Componenta I: \n turatie :5000 rpm \n turatie tinta: 4944 rpm \n Ki: 0.048589");
        double turatie = 5000;
        double deltaT = 0.0;
        instance.setKi(0.048589);
        instance.setKp(0);
        instance.setDerivEroare(80);
        instance.setTuratieTinta(4944);
        double expResult = 4;
        double result = instance.PI(turatie, deltaT);
        assertEquals(expResult, result, 0);
        System.out.println("resultat:" + expResult +"\n\n");
    }
    
    @Test
    public void testPI(){
        System.out.println("Test Componenta PI: \n turatie :5000 rpm \n turatie tinta: 4944 rpm \n Kp: 0.3525 \n Ki: 0.048589");
        double turatie = 5000;
        double deltaT = 5;
        instance.setKi(0.048589);
        instance.setKp(0.3525);
        instance.setDerivEroare(70);
        instance.setTuratieTinta(4944);
        double expResult = 1;
        double result = instance.PI(turatie, deltaT);
        assertEquals(expResult, result, 0);
        System.out.println("resultat:" + expResult +"\n\n");
    }
    
    @Test
    public void TestPIMax(){
        System.out.println("Test Componenta PI_max: \n turatie :5000 rpm \n turatie tinta: 4944 rpm\n Kp:0.3525 \n Ki: 0.048589");
        double turatie = 3000;
        double deltaT = 0.0;
        instance.setKi(0.048589);
        instance.setKp(0.3525);
        instance.setDerivEroare(80);
        instance.setTuratieTinta(5000);
        double expResult = 24;
        double result = instance.PI(turatie, deltaT);
        assertEquals(expResult, result, 0);
        System.out.println("resultat:" + expResult +"\n\n");
    }
    
    @Test
    public void TestPiMin(){
        System.out.println("Test Componenta PI_min: \n turatie :5000 rpm \n turatie tinta: 4944 rpm \n Kp:0.3525 \n Ki: 0.0485893");
        double turatie = 5000;
        double deltaT = 0.0;
        instance.setKi(0.048589);
        instance.setKp(0.3525);
        instance.setDerivEroare(80);
        instance.setTuratieTinta(3000);
        double expResult = -24;
        double result = instance.PI(turatie, deltaT);
        assertEquals(expResult, result, 0);
        System.out.println("resultat:" + expResult +"\n\n");
    }
}


package Utilitati;

 //@author Bogdan Lazar

import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Label;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.plots.axes.AxisRenderer;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
public class LinePlot extends JFrame {
    
        Label labelX = new Label();
        Label labelY = new Label();
        AxisRenderer axe;
        
    public LinePlot(String titlu,double[] timp, double[] intrare) {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        
        DataTable data = new DataTable(Double.class, Double.class);
        for (int i = 0; i < timp.length; i++) {
            
            data.add(timp[i],intrare[i]);
        }
        XYPlot plot = new XYPlot(data);
        plot.getTitle().setText(titlu);

        labelX.setText("t [s]");
        if(titlu.equals("Intensitatea curentului"))  labelY.setText("I [A]");
        if(titlu.equals("Turatia motorului")) labelY.setText("w [rpm]");
        
        plot.getAxisRenderer(XYPlot.AXIS_X).setLabel(labelX);
        plot.getAxisRenderer(XYPlot.AXIS_Y).setLabel(labelY);
        
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        Color color = new Color(0.0f, 0.3f, 1.0f);
        plot.getPointRenderers(data).get(0).setColor(color);
        plot.getLineRenderers(data).get(0).setColor(color);
        plot.getTitle().setText(titlu);
    } 
}
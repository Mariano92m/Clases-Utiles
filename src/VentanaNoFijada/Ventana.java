package VentanaNoFijada;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Ventana extends JFrame{
    private Dimension dim;
 
    public Ventana(){
        //con esto obtienes en tamano en en x y y de tu monitor
        dim=super.getToolkit().getScreenSize();
        super.setSize(dim);
        super.setUndecorated(true);
        super.setVisible(true);
    }
    public static void main(String wil[]){
        new Ventana();
    }
}

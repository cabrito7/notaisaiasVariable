/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author carlosmamut1
 */
public class Fachada implements ActionListener {
    private ControlPrincipal cPrincipal;
    private VentanaPrincipal vPrincipal;
    
    
    public Fachada(ControlPrincipal cPrincipal) {
        this.cPrincipal = cPrincipal;
        this.vPrincipal = new VentanaPrincipal();
        this.vPrincipal.setVisible(true);
        this.vPrincipal.jButton1.addActionListener(this);
        this.vPrincipal.getvGrafial().jButton2.addActionListener(this);
        this.vPrincipal.getvGrafial().jButton3.addActionListener(this);
        this.vPrincipal.getvGrafial().jButton4.addActionListener(this);
        this.vPrincipal.getvResultados().jButton1.addActionListener(this);
        
    }

    public VentanaPrincipal getvPrincipal() {
        return vPrincipal;
    }

    public void setvPrincipal(VentanaPrincipal vPrincipal) {
        this.vPrincipal = vPrincipal;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if("Enviar".equals(e.getActionCommand())) {
            if(this.cPrincipal.validarValorNumerico(this.vPrincipal.jTextField1.getText())) {
            this.vPrincipal.getvGrafial().setVisible(true);
            this.vPrincipal.setVisible(false);
            }
            else {
                this.vPrincipal.mostrarMensajeError("Los grafos deben tener un valor NUMERICO de vertices mayor a 0");
            }
        }
        if("Salir".equals(e.getActionCommand())) {
            this.vPrincipal.getvGrafial().setVisible(false);
            System.exit(0);
        }
        if("AgregarArista".equals(e.getActionCommand())) {
            this.cPrincipal.agregarArista(this.vPrincipal.getvGrafial().jTextField2.getText(), this.vPrincipal.getvGrafial().jTextField3.getText(), this.vPrincipal.jCheckBox1.isSelected());
        }
        if("EmpezarSimulacion".equals(e.getActionCommand())) {
            this.cPrincipal.crearGrafoEnControlPrincipal(this.vPrincipal.jCheckBox1.isSelected());
            this.vPrincipal.getvResultados().setVisible(true);
        }
        if("EnviarResultados".equals(e.getActionCommand())) {

            String texto = this.cPrincipal.dijkstra(this.vPrincipal.getvResultados().jTextField1.getText(), this.cPrincipal.crearMatrizDeAdyacencia());
            this.vPrincipal.getvResultados().jLabel3.setText(texto);
        }
    }
}

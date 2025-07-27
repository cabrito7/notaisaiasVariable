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
            this.vPrincipal.vGrafial.setVisible(true);
            this.vPrincipal.setVisible(false);
            
            }
            else {
                this.vPrincipal.mostrarMensajeError("Los grafos deben tener un valor NUMERICO de vertices mayor a 0");
            }
        }
        if("Salir".equals(e.getActionCommand())) {
            System.exit(0);
        }
        if("AgregarNodo".equals(e.getActionCommand())) {
            
        }
        if("EmpezarSimulacion".equals(e.getActionCommand())) {
            
        }
    }
}

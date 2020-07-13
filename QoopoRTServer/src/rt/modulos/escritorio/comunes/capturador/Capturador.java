/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rt.modulos.escritorio.comunes.capturador;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import comunes.Interfaz;
import rt.modulos.escritorio.comunes.capturador.robot.DirectRobot;

/**
 * Clase que se encarga de realizar las capturas de pantalla
 * @author aigarcia
 */
public abstract class Capturador implements Serializable {

    protected Robot robot;
    protected Interfaz servicio;
    protected DirectRobot directRobot;

    public abstract BufferedImage capturar(Rectangle recuadro);

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public Interfaz getServicio() {
        return servicio;
    }

    public void setServicio(Interfaz servicio) {
        this.servicio = servicio;
    }

    public DirectRobot getDirectRobot() {
        return directRobot;
    }

    public void setDirectRobot(DirectRobot directRobot) {
        this.directRobot = directRobot;
    }

}

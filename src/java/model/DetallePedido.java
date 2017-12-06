/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Jonathan
 */
public class DetallePedido {

    private Integer idDetalle;
    private int cantidad;
    private int idPedido;
    private int idCarretera;

    public DetallePedido() {
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCarretera() {
        return idCarretera;
    }

    public void setIdCarretera(int idCarretera) {
        this.idCarretera = idCarretera;
    }

}

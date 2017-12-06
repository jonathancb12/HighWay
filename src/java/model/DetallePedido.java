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
    private Pedido idPedido;

    private Carretera idCarretera;

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

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    public Carretera getIdCarretera() {
        return idCarretera;
    }

    public void setIdCarretera(Carretera idCarretera) {
        this.idCarretera = idCarretera;
    }

}

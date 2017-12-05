package model;

/**
 *
 * @author Jonathan
 */
public class Pedido {

    private Integer idPedido;
    private int cantidad;
    private String formaPago;
    private String retiro;
    private int rutEmpresa;
    private int idCarretera;

    public Pedido() {
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getRetiro() {
        return retiro;
    }

    public void setRetiro(String retiro) {
        this.retiro = retiro;
    }

    public int getRut() {
        return rutEmpresa;
    }

    public void setRut(int rut) {
        this.rutEmpresa = rut;
    }

    public int getIdCarretera() {
        return idCarretera;
    }

    public void setIdCarretera(int idCarretera) {
        this.idCarretera = idCarretera;
    }

}

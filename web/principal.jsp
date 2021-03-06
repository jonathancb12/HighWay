<%-- 
    Document   : principal
    Created on : 02-12-2017, 1:16:55
    Author     : Jonathan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página principal</title>  
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body><br>
        <div class="container">
            <form method="get" action="STotal">
                <div class="col-md-1">
                    <img class="text-center" src="image/logo.png" width="100"/><br><br>
                    <div class="text-center">
                        <label><a href="index.jsp">Inicio</a></label><br>
                        <label><a href="pedidosAnteriores.jsp">Ver Pedidos</a></label><br>
                        <label><a href="ayuda.jsp">Ayuda</a></label>                   
                    </div>
                </div>
                <br><div class="col-md-7 text-center">
                    <label>Datos Empresa</label>
                </div>
                <div class="col-sm-2 col-sm-offset-1">
                    <label><a href="carreteras.jsp">Ver Carreteras</a></label>
                </div><br><br>
                <div class="col-sm-2 col-sm-offset-1">
                    Rut: <br><br>
                    Nombre: <br><br>
                    Dirección: <br><br>
                    Comprado por: <br><br>
                </div>
                <div class="row">
                    <input name="rut" type="number" value="${empresa.rut}" class="input-group" /><br>
                    <input name="nombre" type="text" value="${empresa.nombre}" class="input-group"/><br>
                    <input name="direccion" type="text" value="${empresa.direccion}" class="input-group"/><br>
                    <input name="comprador" type="text" value="${comprador}" class="input-group" />
                </div><br>
                <div class="row">
                    <div class="col-md-5 col-sm-offset-2">
                        Seleccione carretera y agregue al pedido<br><br>
                        <select name="carretera" class="form-control">
                            <option selected="true" disabled="true" value="Carreteras">Carreteras</option>
                            <c:forEach items="${carreteras}" var="car">
                                <option value="${car.id}"><c:out value="${car.carretera}"/></option>
                            </c:forEach>
                        </select>
                    </div><br><br><input type="submit" value="Agregar" class="btn-primary" onclick='this.form.action = "SAgregarPedido"'/>
                </div>
                <div class="col-md-2">
                    <br>
                    <label class="text-info">Opciones de Pago</label><br>
                    <input type="radio" name="pago" value="Transferencia"/> Transferencia<br>
                    <input type="radio" name="pago" value="Pago en Linea" /> Pago en Línea<br>
                    <input type="radio" name="pago" value="Orden de Compra" /> Orden de Compra
                    <br><br><br>
                    <label class="text-info">Opciones de Retiro</label><br>
                    <input type="radio" name="retiro" value="Oficina"/> Oficina<br>
                    <input type="radio" name="retiro" value="Envio Cliente" /> Envío Cliente<br>
                </div>
                <br><br>
                <div class="row">
                    <div class="col-md-6">
                        <div class="datagrid"><table>
                                <thead>
                                    <tr><th>Carretera</th>
                                        <th>Cantidad</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${carreterasPedido}" var="cp">
                                        <tr>
                                    <input name="carreteraID" type="number" value="${cp.id}" hidden="true"/>
                                    <td><c:out value="${cp.carretera}"/></td>
                                    <td><input name="${cp.id}" type="number" value="${cantidad[cp.id]}" onchange="this.form.submit()" class="input-group"/></td>
                                    <td><button name="id" onclick='this.form.action = "SQuitarItem"' style="border: none;background-color: #ffffff" value="${cp.id}">[-]</button></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table></div><br>
                        <div class="col-md-5">
                            <label class="text-dark">Total a Pagar: $ <c:out value="${total}" default="0"/></label>
                        </div>
                        <div class="col-md-2">
                            <input type="submit" value="Hacer Pedido" class="btn-primary" onclick='this.form.action = "SConfirmarPedido"'/>
                        </div><br><br>
                        <label style="color: red"><c:out value="${mensaje}" default=""/></label>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>

<%-- 
    Document   : voucher
    Created on : 02-12-2017, 19:57:35
    Author     : Jonathan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Voucher</title>  
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body><br>
        <div class="container">
            <form>
                <div class="col-md-1">
                    <img class="text-center" src="image/logo.png" width="100"/><br><br>
                    <div class="text-center">
                        <label><a href="index.jsp">Inicio</a></label><br>
                        <label><a href="pedidosAnteriores.jsp">Ver Pedidos</a></label><br>
                        <label><a href="ayuda.jsp">Ayuda</a></label>                   
                    </div>
                </div>
                <br>
                <div class="col-sm-2 col-md-offset-7">
                    <label><a href="carreteras.jsp">Ver Carreteras</a></label>
                </div><br><br>
                <div class="row col-sm-offset-2">
                    <div class="col-md-4 col-sm-offset-1">
                        <h3>Pedido Número:  <c:out value="${pedido.idPedido}" default=""/></h3>
                    </div>
                    <br><br><br>
                    <div class="col-md-6">
                        <br>
                        <div class="datagrid"><table>
                                <thead>
                                    <tr><th>Carretera</th>
                                        <th>Cantidad</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Ruta 68</td>
                                        <td><c:out value="${cantidad1[1]}" default="0"/></td>
                                    </tr>
                                    <tr>
                                        <td>Autopista Del Sol</td>
                                        <td><c:out value="${cantidad1[2]}" default="0"/></td>
                                    </tr>
                                    <tr>
                                        <td>Ruta Guardia Vieja</td>
                                        <td><c:out value="${cantidad1[3]}" default="0"/></td>
                                    </tr>
                                    <tr>
                                        <td>Ruta Troncal Sur</td>
                                        <td><c:out value="${cantidad1[4]}" default="0"/></td>
                                    </tr>
                                </tbody>
                            </table></div><br>
                        <div class="col-md-5 col-sm-offset-2">
                            <h4 class="text-dark">Total a Pagar: $ <c:out value="${totalPedido}" default="0"/></h4>
                            <br>
                        </div>
                        <h4 class="col-md-8">OPCIÓN DE ENVIO: <c:out value="${pedido.retiro}" default=""/></h4>
                    </div>
                </div>
            </form>
        </div>
    </body>
    <br><br><br>
    <footer class="col-md-offset-4">
        <label>Muchas gracias por preferirnos</label>
    </footer>
</html>

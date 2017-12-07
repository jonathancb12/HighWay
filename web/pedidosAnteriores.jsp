<%-- 
    Document   : pedidosAnteriores
    Created on : 03-12-2017, 22:44:01
    Author     : Jonathan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedidos anteriores</title>  
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body><br>
        <div class="container">
            <form action="SPedidosAnteriores">
                <div class="col-md-1">
                    <img class="text-center" src="image/logo.png" width="100"/><br><br>
                    <div class="text-center">
                        <label><a href="index.jsp">Inicio</a></label><br>
                        <label><a href="pedidosAnteriores.jsp">Ver Pedidos</a></label><br>
                        <label><a href="ayuda.jsp">Ayuda</a></label>                   
                    </div>
                </div>
                <br><div class="col-md-7 text-center"></div>
                <div class="col-sm-2 col-sm-offset-1">
                    <label><a href="carreteras.jsp">Ver Carreteras</a></label>
                </div><br><br><br>
                <div class="col-md-5 col-md-offset-2">
                    <input name="rut" type="number" class="input-buscar" placeholder="Rut Cliente"/> 
                    <input type="submit" value="Buscar" class="btn btn-primary"/>
                    <label style="color: red"><c:out value="${mensaje}" default=""/></label>
                </div><br><br><br>
                <div class="col-md-7 col-md-offset-1 datagrid"><table>
                        <thead>
                            <tr><th>Carretera</th>
                                <th>Cantidad</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${pedidos}">
                                <c:forEach items="${detalles}" var="d">
                                    <tr>
                                        <td><c:out value="${cadenas}"/></td>
                                        <td>${d.cantidad}</td>
                                        <td>[+]</td>
                                    </tr>
                                </c:forEach>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </form>
        </div>
    </body>
</html>

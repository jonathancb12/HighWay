<%-- 
    Document   : ayuda
    Created on : 04-12-2017, 22:52:57
    Author     : Jonathan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
            <form>
                <div class="col-md-1">
                    <img class="text-center" src="image/logo.png" width="100"/><br><br>
                    <div class="text-center">
                        <label><a href="index.jsp">Inicio</a></label><br>
                        <label><a href="pedidosAnteriores.jsp">Ver Pedidos</a></label><br>
                        <label><a href="ayuda.jsp">Ayuda</a></label>                   
                    </div>
                </div>
                <br><div class="col-md-7 text-center">
                    <label>Datos Empresa</label></div>
                <div class="col-sm-2 col-sm-offset-1">
                    <label><a href="carreteras.jsp">Ver Carreteras</a></label>
                </div><br><br>
                </div><br>
                <div class="row">
                    <div class="col-md-5 col-sm-offset-2">
                        Seleccione carretera y agregue al pedido<br><br>
                        <select name="carretera" class="form-control">
                            <option selected="true" disabled="true">Carreteras</option>
                            <c:forEach items="${carreteras}" var="car">
                                <option value="${car.id}"><c:out value="${car.carretera}"/></option>
                            </c:forEach>
                        </select>
                    </div><br><br><input type="submit" value="Agregar" class="btn-primary"/>
                </div>
                <br><br>
            </form>
        </div>
    </body>
</html>

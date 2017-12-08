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
                    <h3>Datos de Contacto</h3></div><br><br><br><br>
                <div class="col-md-7 text-center col-md-offset-1">
                    <form action="SAyuda" method="POST">
                    <table width="450px"><!---Este tamaño en px es personalizable -->
                        <tr>
                            <td valign="top">
                                <label for="first_name">Nombre</label>
                            </td>
                            <td valign="top">
                                <input  type="text" name="name" maxlength="50" size="30" required="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td valign="top">
                                <label for="last_name">Apellido </label>
                            </td>
                            <td valign="top">
                                <input  type="text" name="apellido" maxlength="50" size="30" required="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td valign="top">
                                <label for="mail">E-mail </label>
                            </td>
                            <td valign="top">
                                <input  type="text" name="mail" maxlength="80" size="30" required="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                <label for="telephone">Teléfono </label>
                            </td>
                            <td>
                                <input  type="number" name="telephone" maxlength="10" size="30" required="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td valign="top">
                                <label for="message">Mensaje </label>
                            </td>
                            <td valign="top">
                                <textarea  name="message" maxlength="1000" cols="33" rows="6" required="true"/></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="text-align:center">
                                <br>
                                <input type="submit" value="Enviar" class="btn btn-primary">   
                            </td>
                        </tr>
                    </table>
            </form>
                </div>
                <div class="col-sm-2 col-sm-offset-1">
                    <label><a href="carreteras.jsp">Ver Carreteras</a></label>
                </div><br><br>
                <br><br>
            </form>
        </div>
    </body>
</html>
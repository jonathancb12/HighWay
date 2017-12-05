<%-- 
    Document   : index
    Created on : 02-12-2017, 15:31:18
    Author     : Jonathan
--%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <title>Inicio</title>
    </head>
    <body class="body">
        <br>
        <br><br><br>
        <form>
            <div class="container text-center col-sm-offset-3" style="margin-top: 13%">
                <div class="col-md-3">
                    <button type="submit" class="btn btn-block btn-primary btn-lg" onclick='this.form.action = "principal.jsp"'>Nuevo Pedido</button>
                </div>
                <div class="col-md-3 col-sm-offset-1">
                    <button type="submit" class="btn btn-block btn-primary btn-lg">Pedidos Anteriores</button>
                </div>
            </div>
        </form>
    </body>
</html>

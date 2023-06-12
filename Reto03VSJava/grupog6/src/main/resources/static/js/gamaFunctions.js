
let URL_SERVICE = "https://gba7c74d8d16c69-bdinstanciaapexg6.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/car/car" // URL tomada de car en oracle cloud


function addCar()
{
    let myData =    {                                         // Datos para enviar a la BD 
                    id: $("#id").val(), 
                    brand: $("#brand").val(),
                    model: $("#model").val(),
                    category_id: $("#category_id").val(),
                    };

    $.ajax
    ({
            url: URL_SERVICE,
            type:"POST",
            data: myData,                                   // Carga los valores de cada item en la Base de datos
            success:function(respuesta)
            {
                clearScreen();
                alert("Datos guardados");                   // Publica mensaje en navegador
            },
            error: function(XMLHttpRequest, textStatus, errorThrown)    // Deteccion de error sino recibe respuesta
            {
                alert("Status: " + textStatus);
                alert("Error: " + errorThrown);
            }

    });
}

function listCar()
{
    $.ajax
    ({
        url: URL_SERVICE,
        type:"GET",
        dataType: "JSON",                               // Respuesta de la BD
        success:function(respuesta)
        {
            drawTable(respuesta.items);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown)    // Deteccion de error sino recibe respuesta
        {
            alert("Status: " + textStatus);
            alert("Error: " + errorThrown);
        }

    });
}

function drawTable(items)
{
    let myTable = "<table + border = 1>"        // Creacion de tabla con un borde definido
        myTable = myTable + "<thead>"           // Crea encabezado de la tabla
        
        myTable = myTable + "<td>" + "ID"  + "</td>"            // Definicion de lineas de tabla segun parametro
        myTable = myTable + "<td>" + "BRAND"  + "</td>"
        myTable = myTable + "<td>" + "MODEL"  + "</td>"
        myTable = myTable + "<td>" + "CATEGORY_ID"  + "</td>"
        myTable = myTable + "<td>" + "OPCIONES"  + "</td>"

        myTable = myTable + "</thead>" 

    for(cnt=0; cnt<items.length;cnt++)                          // Lectura de cada item
        {
            myTable = myTable + "<tr>"                          // Ubicacion en fila de la tabla

            myTable = myTable + "<td>" + items[cnt].id  + "</td>"       // Ubicacion en columna de cada fila
            myTable = myTable + "<td>" + items[cnt].brand  + "</td>"
            myTable = myTable + "<td>" + items[cnt].model  + "</td>"
            myTable = myTable + "<td>" + items[cnt].category_id  + "</td>"
            myTable = myTable + "<td> <button onclick='getOneCar("+items[cnt].id+")'>Editar</button>";    // Crear boton seleccionar un item
            myTable = myTable + "<td> <button onclick='delCar("+items[cnt].id+")'>Borrar</button>";    // Crear boton borrar por item
            myTable = myTable + "</tr>"

        }

        myTable = myTable + "</table>"
        $("#resultado").append(myTable);
}

function clearScreen()
{
    $("#resultado").empty();                    // Borra el contenedor resultado
    $("#id").val("");                           // Borra cada item
    $("#brand").val("");
    $("#model").val("");
    $("#category_id").val("");
    listCar();
}

function updCar()
{
    let myData =    {                                         // Datos para enviar a la BD 
                    id: $("#id").val(), 
                    brand: $("#brand").val(),
                    model: $("#model").val(),
                    category_id: $("#category_id").val(),
                    };

        let myDataToSend = JSON.stringify(myData)           // Conversion de dato numerico a formato JSON

        $.ajax
        ({
        url: URL_SERVICE,
        type:"PUT",
        data: myDataToSend,                                   // Carga los valores de cada item en la Base de datos
        contentType: "application/JSON",
        success:function(respuesta)
        {
            clearScreen();
            alert("Datos Modificados");                   // Publica mensaje en navegador
        },
        error: function(XMLHttpRequest, textStatus, errorThrown)    // Deteccion de error sino recibe respuesta
        {
            alert("Status: " + textStatus);
            alert("Error: " + errorThrown);
        }

        });
}

function delCar(idaBorrar)
{
   let myData =   {                                         // Datos para enviar a la BD 
                    id: idaBorrar
                };

        let myDataToSend = JSON.stringify(myData)           // Conversion de dato numerico a formato JSON

        $.ajax
        ({
        url: URL_SERVICE,
        type:"DELETE",
        data: myDataToSend,                                       // Carga los valores de cada item en la Base de datos
        contentType:"application/JSON",                           // Formato de envio de informacion a BD
            success:function(respuesta)
            {
                clearScreen();
                alert("Datos Eliminados");                   // Publica mensaje en navegador
            },
            error: function(XMLHttpRequest, textStatus, errorThrown)    // Deteccion de error sino recibe respuesta
            {
                alert("Status: " + textStatus);
                alert("Error: " + errorThrown);
            }

        });
}

function getOneCar(idaConsultar)
{
    $.ajax
    ({
        url: URL_SERVICE + "/" + idaConsultar,
        type:"GET",
        dataType: "JSON",                               // Respuesta de la BD
        success:function(respuesta)
        {
            //drawTable(respuesta.items);
            //clearScreen();
            screenToModify(respuesta.items);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown)    // Deteccion de error sino recibe respuesta
        {
            alert("Status: " + textStatus);
            alert("Error: " + errorThrown);
        }

    });
}

function screenToModify(items)
{                   
    $("#id").val(items[0].id);                           
    $("#brand").val(items[0].brand);
    $("#model").val(items[0].model);
    $("#category_id").val(items[0].category_id);
}
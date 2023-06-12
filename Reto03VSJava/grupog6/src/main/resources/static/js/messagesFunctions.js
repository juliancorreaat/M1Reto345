
let URL_SERVICE3 = "https://gba7c74d8d16c69-bdinstanciaapexg6.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/message/message"


function addMessage()
{
    let myDataM =    {                                         // Datos para enviar a la BD 
                    id: $("#id").val(), 
                    messagetext: $("#messagetext").val(),
                    };

        $.ajax
        ({
        url: URL_SERVICE3,
        type:"POST",
        data: myDataM,                                   // Carga los valores de cada item en la Base de datos
        success:function(respuestaMessage)
        {
            clearScreenMessage();
            alert("Datos guardados");                   // Publica mensaje en navegador
        },
        error: function(XMLHttpRequest, textStatus, errorThrown)    // Deteccion de error sino recibe respuesta
        {
            alert("Status: " + textStatus);
            alert("Error: " + errorThrown);
        }

        });
}

//---------------------------------------------------------------------


function listMessage()
{
    $.ajax
    ({
        url: URL_SERVICE3,
        type:"GET",
        dataType: "JSON",                               // Respuesta de la BD
        success:function(respuestaMessage)
        {
            drawTableMessage(respuestaMessage.items);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown)    // Deteccion de error sino recibe respuesta
        {
            alert("Status: " + textStatus);
            alert("Error: " + errorThrown);
        }

    });
}

//---------------------------------------------------------------------


function drawTableMessage(items)
{
    let myTableM = "<table + border = 1>"        // Creacion de tabla con un borde definido
        myTableM = myTableM + "<thead>"           // Crea encabezado de la tabla
        
        myTableM = myTableM + "<td>" + "ID"  + "</td>"            // Definicion de lineas de tabla segun parametro
        myTableM = myTableM + "<td>" + "MESSAGE"  + "</td>"
        myTableM = myTableM + "<td>" + "OPCIONES"  + "</td>"

        myTableM = myTableM + "</thead>" 

    for(cnt=0; cnt<items.length;cnt++)                          // Lectura de cada item
        {
            myTableM = myTableM + "<tr>"                          // Ubicacion en fila de la tabla

            myTableM = myTableM + "<td>" + items[cnt].id  + "</td>"       // Ubicacion en columna de cada fila
            myTableM = myTableM + "<td>" + items[cnt].messagetext  + "</td>"
            myTableM = myTableM + "<td> <button onclick='getOneMessage("+items[cnt].id+")'>Editar</button>";    // Crear boton seleccionar un item
            myTableM = myTableM + "<td> <button onclick='delMessage("+items[cnt].id+")'>Borrar</button>";    // Crear boton borrar por item
            myTableM = myTableM + "</tr>"

        }

        myTableM = myTableM + "</table>"
        $("#resultadoMessage").append(myTableM);
}

//---------------------------------------------------------------------


function clearScreenMessage()
{
    $("#resultadoMessage").empty();                    // Borra el contenedor resultado
    $("#id").val("");                           // Borra cada item
    $("#messagetext").val("");
    listMessage();
}

//---------------------------------------------------------------------


function updMessage()
{
    let myDataM =    {                                         // Datos para enviar a la BD 
        id: $("#id").val(), 
        messagetext: $("#messagetext").val(),
        };

    let myDataToSendM = JSON.stringify(myDataM)           // Conversion de dato numerico a formato JSON

        $.ajax
        ({
        url: URL_SERVICE3,
        type:"PUT",
        data: myDataToSendM,                                   // Carga los valores de cada item en la Base de datos
        contentType: "application/JSON",
        success:function(respuestaMessage)
        {
            clearScreenMessage();
            alert("Datos Modificados");                   // Publica mensaje en navegador
        },
        error: function(XMLHttpRequest, textStatus, errorThrown)    // Deteccion de error sino recibe respuesta
        {
            alert("Status: " + textStatus);
            alert("Error: " + errorThrown);
        }

        });
}

//---------------------------------------------------------------------


function delMessage(idaBorrarMessage)
{
    let myDataM =   {                                         // Datos para enviar a la BD 
        id: idaBorrarMessage
    };

let myDataToSendM = JSON.stringify(myDataM)           // Conversion de dato numerico a formato JSON

    $.ajax
    ({
    url: URL_SERVICE3,
    type:"DELETE",
    data: myDataToSendM,                                       // Carga los valores de cada item en la Base de datos
    contentType:"application/JSON",                           // Formato de envio de informacion a BD
    success:function(respuestaMessage)
    {
        clearScreenMessage();
        alert("Datos Eliminados");                   // Publica mensaje en navegador
    },
    error: function(XMLHttpRequest, textStatus, errorThrown)    // Deteccion de error sino recibe respuesta
    {
        alert("Status: " + textStatus);
        alert("Error: " + errorThrown);
    }

    });

}

//---------------------------------------------------------------------


function getOneMessage(consultarMessage)
{
    $.ajax
    ({
        url: URL_SERVICE3 + "/" + consultarMessage,
        type:"GET",
        dataType: "JSON",                               // Respuesta de la BD
        success:function(respuestaMessage)
        {
            modifyMessage(respuestaMessage.items);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown)    // Deteccion de error sino recibe respuesta
        {
            alert("Status: " + textStatus);
            alert("Error: " + errorThrown);
        }

    });
}

//---------------------------------------------------------------------


function modifyMessage(items)
{
    $("#id").val(items[0].id);                           
    $("#messagetext").val(items[0].messagetext);
}

//---------------------------------------------------------------------


let URL_SERVICE2 = "https://gba7c74d8d16c69-bdinstanciaapexg6.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/client/client"  // URL tomada de client en oracle cloud


function drawTableClient(items)
{
    let myTableC = "<table + border = 1>"        // Creacion de tabla con un borde definido
        myTableC = myTableC + "<thead>"           // Crea encabezado de la tabla
        
        myTableC = myTableC + "<td>" + "ID"  + "</td>"            // Definicion de lineas de tabla segun parametro
        myTableC = myTableC + "<td>" + "NAME"  + "</td>"
        myTableC = myTableC + "<td>" + "EMAIL"  + "</td>"
        myTableC = myTableC + "<td>" + "AGE"  + "</td>"
        myTableC = myTableC + "<td>" + "OPTIONS"  + "</td>"

        myTableC = myTableC + "</thead>" 

    for(cnt=0; cnt<items.length;cnt++)                          // Lectura de cada item
        {
            myTableC = myTableC + "<tr>"                          // Ubicacion en fila de la tabla

            myTableC = myTableC + "<td>" + items[cnt].id  + "</td>"       // Ubicacion en columna de cada fila
            myTableC = myTableC + "<td>" + items[cnt].name  + "</td>"
            myTableC = myTableC + "<td>" + items[cnt].email  + "</td>"
            myTableC = myTableC + "<td>" + items[cnt].age  + "</td>"
            myTableC = myTableC + "<td> <button onclick='getOneClient("+items[cnt].id+")'>Edit</button>";    // Crear boton seleccionar un item
            myTableC = myTableC + "<td> <button onclick='delClient("+items[cnt].id+")'>Borrar</button>";    // Crear boton borrar por item
            myTableC = myTableC + "</tr>"

        }

        myTableC = myTableC + "</table>"
        $("#resultadoClient").append(myTableC);
}

//------------------------------------------


function addClient()
{
    let myDataC =    {                                         // Datos para enviar a la BD 
                    id: $("#id").val(), 
                    name: $("#name").val(),
                    email: $("#email").val(),
                    age: $("#age").val(),

                    };


        let mydataSend = JSON.stringify(myDataC);

        $.ajax
        ({
        url: URL_SERVICE2,
        type:"POST",
        data: mydataSend, 
        contentType: "application/JSON",                                  // Carga los valores de cada item en la Base de datos
        success:function(respuestaClient)
        {
            clearScreenClient();
            alert("Datos guardados Clientes");                   // Publica mensaje en navegador
        },
        error: function(XMLHttpRequest, textStatus, errorThrown)    // Deteccion de error sino recibe respuesta
        {
            alert("Status: " + textStatus);
            alert("Error: " + errorThrown);
        }

        });
}

//------------------------------------------


function listClient()
{
    $.ajax
    ({
        url: URL_SERVICE2,
        type:"GET",
        dataType: "JSON",                               // Respuesta de la BD
        success:function(respuestaClient)
        {
            drawTableClient(respuestaClient.items);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown)    // Deteccion de error sino recibe respuesta
        {
            alert("Status: " + textStatus);
            alert("Error: " + errorThrown);
        }

    });
}

//------------------------------------------


function updClient()
{
    let myDataC =    {                                         // Datos para enviar a la BD 
        id: $("#id").val(), 
        name: $("#name").val(),
        email: $("#email").val(),
        age: $("#age").val(),
        };

    let myDataToSendC = JSON.stringify(myDataC)           // Conversion de dato numerico a formato JSON

        $.ajax
        ({
        url: URL_SERVICE2,
        type:"PUT",
        data: myDataToSendC,                                   // Carga los valores de cada item en la Base de datos
        contentType: "application/JSON",
        success:function(respuestaClient)
        {
        clearScreenClient();
        alert("Datos Modificados");                   // Publica mensaje en navegador
        },
        error: function(XMLHttpRequest, textStatus, errorThrown)    // Deteccion de error sino recibe respuesta
        {
        alert("Status: " + textStatus);
        alert("Error: " + errorThrown);
        }

        });
}

//------------------------------------------


function clearScreenClient()
{
    $("#resultadoClient").empty();                    // Borra el contenedor resultado
    $("#id").val("");                           // Borra cada item
    $("#name").val("");
    $("#email").val("");
    $("#age").val("");
    listClient();
}

//------------------------------------------


function delClient(idaBorrarClient)
{
    let myDataC =   {                                         // Datos para enviar a la BD 
        id: idaBorrarClient
    };

    let myDataToSendC = JSON.stringify(myDataC)           // Conversion de dato numerico a formato JSON

        $.ajax
        ({
        url: URL_SERVICE2,
        type:"DELETE",
        data: myDataToSendC,                                       // Carga los valores de cada item en la Base de datos
        contentType:"application/JSON",                           // Formato de envio de informacion a BD
        success:function(respuestaClient)
        {
            clearScreenClient();
            alert("Datos Eliminados");                   // Publica mensaje en navegador
        },
        error: function(XMLHttpRequest, textStatus, errorThrown)    // Deteccion de error sino recibe respuesta
        {
            alert("Status: " + textStatus);
            alert("Error: " + errorThrown);
        }

        });
}

//------------------------------------------


function getOneClient(idaConsultarClient)
{
    $.ajax
    ({
        url: URL_SERVICE2 + "/" + idaConsultarClient,
        type:"GET",
        dataType: "JSON",                               // Respuesta de la BD
        success:function(respuestaClient)
        {
            //drawTable(respuesta.items);
            //clearScreen();
            ModifyClient(respuestaClient.items);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown)    // Deteccion de error sino recibe respuesta
        {
            alert("Status: " + textStatus);
            alert("Error: " + errorThrown);
        }

    });
}

//------------------------------------------


function ModifyClient(items)
{
    $("#id").val(items[0].id);                           
    $("#name").val(items[0].name);
    $("#email").val(items[0].email);
    $("#age").val(items[0].age);
}
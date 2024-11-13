var productos = [];


function cargarProducto(event) {
    event.preventDefault();
    
    
    var codigo = $('#codigo').val(); // Obtener el código del producto desde el formulario
    var unidades = $('#unidades').val();
    

    $.ajax({
        url: '/CiteriumJSP/ventasServlet', // Ruta al servlet
        type: 'GET',
        dataType: 'json',
        data: {codigo: codigo, unidades: unidades}, // Enviar el código del producto como parámetro
        success: function (producto) {
            productos.push(producto);
            console.log(productos);
            mostrarProductoEnTabla(productos);
            mostrarTotal(productos);
            limpiarFormulario();
        },
        error: function (xhr, status, error) {
            console.error('Error al cargar el producto:', status, error);
            alert('Error al cargar el producto. Por favor, inténtelo de nuevo más tarde.');
        }
    });
}

function mostrarProductoEnTabla(productos) {
    var tbody = $('#tablaVentas');
    tbody.empty(); // Limpiar tabla antes de agregar datos

    for (let i = 0; i < productos.length; i++) {
        var row = '<tr>' +
                '<td class="px-4 py-2 border">' + productos[i].nombre + '</td>' +
                '<td class="px-4 py-2 border">' + productos[i].codigo + '</td>' +
                '<td class="px-4 py-2 border">' + productos[i].unidades + '</td>' +
                '<td class="px-4 py-2 border">' + '$ ' + productos[i].precio + '</td>' +
                '<td class="px-4 py-2 border">' +
                '<button class="btnEliminar bg-red" data-id="'+ i + '">' +
                    '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" class="icon-svg">' +
                        '<image href="../img/eliminar.png" width="24" height="24" />' +
                    '</svg>' +
                '</button>' +
            '</td>' +
            '</tr>';
        tbody.append(row);
    }
    
    // Agregar evento de clic a los botones de eliminar
    $('.btnEliminar').on('click', function() {
        var index = $(this).data('id'); // Obtener el índice del producto a eliminar
        eliminarProducto(index); // Llamar a la función para eliminar el producto
    });
    // Mostrar el total actualizado después de mostrar la tabla
    mostrarTotal(productos);
}

function mostrarTotal(productos) {
    var total = $('#totalVenta');
    var totalVentas = 0;

    for (let i = 0; i < productos.length; i++) {
        totalVentas += productos[i].precio; 
    }
    total.text("Total: $" + totalVentas);
} 

function eliminarProducto(index){
    // Verificar que el índice esté dentro del rango válido
    if (index >= 0 && index < productos.length) {
        productos.splice(index, 1); // Eliminar el producto del array
        mostrarProductoEnTabla(productos); // Volver a mostrar la tabla actualizada
    }  
};

function limpiarFormulario() {
    $('#codigo').val(''); // Limpiar el campo código
    $('#unidades').val(''); // Limpiar el campo unidades
}

function realizarCompra() {
    var infoCompra = $('#infoCompra');
    infoCompra.show(); // Mostrar el contenedor de resumen de compra

    // Construir la lista de productos comprados
    var listaProductos = $('#listaProductos');
    listaProductos.empty(); // Limpiar lista antes de agregar datos

    for (var i = 0; i < productos.length; i++) {
        var producto = productos[i];
        var listItem = '<li><strong>' + producto.nombre + '</strong> - Cantidad: ' + producto.unidades + ' - Precio: $' + producto.precio + '</li>';
        listaProductos.append(listItem);
    }

    // Mostrar el total de la compra
    var totalCompra = 0;
    for (var i = 0; i < productos.length; i++) {
        totalCompra += parseFloat(productos[i].precio);
    }
    $('#totalCompra').text("Total de la compra: $" + totalCompra.toFixed(2));
}


function finalizarCompra() {
//    productos = [];
//    var infoCompra = $('#infoCompra');
//    
//    infoCompra.hide();
//    mostrarProductoEnTabla(productos);
    console.log("Ejecutando la función finalizarCompra"); // Aquí se imprimirá el mensaje de depuración

    var infoCompra = $('#infoCompra');
    infoCompra.show();

    // Mostrar lista de productos en la compra
    var listaProductos = $('#listaProductos');
    listaProductos.empty();
    var totalCompra = 0;

    productos.forEach(producto => {
        listaProductos.append(
            `<li><strong>${producto.nombre}</strong> - Cantidad: ${producto.unidades} - Precio: $${producto.precio}</li>`
        );
        totalCompra += producto.precio * producto.unidades;
    });

    $('#totalCompra').text("Total de la compra: $" + totalCompra.toFixed(2));

    // Llamada AJAX para enviar productos y actualizar inventario
    $.ajax({
        url: '/CiteriumJSP/ventasServlet',   // Asegúrate de que esta URL sea correcta
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(productos), // Enviar productos como JSON
        success: function(response) {
            console.log("Respuesta del servidor:", response); // Imprime la respuesta del servidor
            alert('Compra realizada con éxito');
            productos = []; // Vaciar productos después de la compra
            mostrarProductoEnTabla(productos); // Limpiar la tabla
            $('#infoCompra').hide(); // Ocultar la información de la compra
        },
        error: function(xhr, status, error) {
            console.error('Error al realizar la compra:', status, error); // Imprime cualquier error
            alert('Error al realizar la compra. Inténtelo de nuevo.');
        }
    });
}

// Llamar a cargarProducto al cargar la página o cuando sea necesario
$(document).ready(function () {
    //Evento para mostrar productos en el carrito
     $('#formularioVentas').on('submit', cargarProducto);
     
    //Evento para eliminar un producto del carrito
     $('.eliminar').on('click', eliminarProducto(productos));
     
     // Evento para realizar la compra al hacer clic en el botón "Realizar Venta"
    $('#btnComprar').on('click', function() {
        realizarCompra();
    });
    
    $('#btnFinalizar').on('click', function() {
        finalizarCompra();
    });

});



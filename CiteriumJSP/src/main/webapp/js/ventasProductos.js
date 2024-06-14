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
                '<td class="px-4 py-2 border">' + '<button class="btnEliminar bg-red" data-id="'+ i + '" >X<button>'; + '</td>' +
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

// Llamar a cargarProducto al cargar la página o cuando sea necesario
$(document).ready(function () {
     $('#formularioVentas').on('submit', cargarProducto);
     $('.eliminar').on('click', eliminarProducto(productos));
});



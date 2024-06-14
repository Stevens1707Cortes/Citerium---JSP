function cargarProductos() {
    $.ajax({
        url: '/CiteriumJSP/productosServlet', //Servlet
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            mostrarProductosEnTabla(data); // Llamar a función para mostrar productos en la tabla
        },
        error: function(xhr, status, error) {
            console.error('Error al cargar los productos:', status, error);
            alert('Error al cargar los productos. Por favor, inténtelo de nuevo más tarde.');
        }
    });
}

function mostrarProductosEnTabla(productos) {
    var tbody = $('#tbodyProductos');
    tbody.empty(); // Limpiar tabla antes de agregar datos

    $.each(productos, function(index, producto) {
        var row = '<tr>' +
                    '<td class="px-4 py-2 border">' + producto.nombre + '</td>' +
                    '<td class="px-4 py-2 border">' + producto.codigo + '</td>' +
                    '<td class="px-4 py-2 border">' + producto.categoria + '</td>' +
                    '<td class="px-4 py-2 border">' + producto.fecha + '</td>' +
                    '<td class="px-4 py-2 border">' + producto.unidades + '</td>' +
                    '<td class="px-4 py-2 border">' + '$ ' + producto.precio + '</td>' +
                  '</tr>';
        tbody.append(row);
    });
}

// Llamar a cargarProductos al cargar la página o cuando sea necesario
$(document).ready(function() {
    cargarProductos();
});


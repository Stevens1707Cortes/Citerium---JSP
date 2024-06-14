<%-- 
    Document   : home
    Created on : 12/06/2024, 10:11:45 a. m.
    Author     : steve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="bg-gradient-to-r from-sky-800 to-cyan-600" lang="en">
<head>
    <%@ include file="components/head.jsp" %>
</head>
<body>
    <%@ include file="components/header.jsp" %>
    
    <%@ include file="components/nav.jsp" %>

    <main class="ml-auto mb-6 lg:w-[75%] xl:w-[80%] 2xl:w-[85%]">
        <div class="px-6 pt-6 2xl:container">
            <div class=" md:grid-cols-1 lg:grid-cols-1">
                <div class="md:col-span-1 lg:col-span-1">
                    <div class="h-full w-5/6 m-auto py-8 px-6 text-center text-2xl text-slate-950 rounded-xl border border-gray-200 bg-white">
                        <h1>Bienvenidos al sistema POS: Citerium</h1>
                    </div>
                </div>
            </div>

            <div class="contenido h-full w-5/6 m-auto mt-8 py-8 px-6 text-justify text-gray-600 rounded-xl border border-gray-200 bg-white">
                <p>Citerium es una solución intuitiva y eficiente diseñada para simplificar la gestión de ventas y el control de inventario para pequeños negocios. Con una interfaz fácil de usar y funciones clave para ingresar, editar, eliminar productos y realizar ventas de manera rápida y precisa.</p>

                <p>Estas son las principales funciones que Citerium permite usar: </p>

                <ul class="mt-3 mb-6">
                    <li> <a href="home_Inventario.jsp"><u>Inventario</u></a> : Ingresar y Eliminar productos.</li>
                    <li> <a href="home_Producto.jsp"><u>Administrar productos</u></a> : Editar los productos que estan en inventario.</li>
                    <li> <a href="home_Ventas.jsp"><u>Ventas</u></a> : Ingresar una cantidad de productos para su venta.</li>
                </ul>
            </div>
        </div>
    </main>
</body>
</html>

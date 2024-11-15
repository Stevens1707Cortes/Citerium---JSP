<%-- 
    Document   : home_Producto
    Created on : 12/06/2024, 10:58:07 a. m.
    Author     : steve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Producto" %>
<!DOCTYPE html>
<html class="bg-gradient-to-r from-sky-800 to-cyan-600" lang="en">
    <head>
        <%@ include file="components/head.jsp" %>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="../js/cargarProductos.js"></script>
        <script>
            $(document).ready(function () {
                cargarProductos(); // Cargar productos al cargar la página
            });
        </script>
    </head>
    <body>
        <%@ include file="components/header.jsp" %>

        <%@ include file="components/nav_Producto.jsp" %>

        <main class="ml-auto mb-6 lg:w-[75%] xl:w-[80%] 2xl:w-[85%]">
            <div class="px-6 pt-6 2xl:container">
                <div class="grid gap-4 md:grid-cols-1 lg:grid-cols-1">
                    <div class="md:col-span-1 lg:col-span-1 overflow-auto" >
                        <div class="h-full m-auto py-8 px-6 text-center text-gray-600 rounded-xl border border-gray-200 bg-gradient-to-r from-indigo-100 to-cyan-100 overflow-auto">
                            <h1 class="text-2xl mb-6 text-cyan-900">Lista de productos</h1>
                            <table class="listaProductos table-auto w-full">
                                <thead>
                                    <tr>
                                        <th class="px-4 py-2 bg-emerald-300 border">Nombre</th>
                                        <th class="px-4 py-2 bg-emerald-300 border">Código</th>
                                        <th class="px-4 py-2 bg-emerald-300 border">Categoría</th>
                                        <th class="px-4 py-2 bg-emerald-300 border">Fecha</th>
                                        <th class="px-4 py-2 bg-emerald-300 border">Unidades</th>
                                        <th class="px-4 py-2 bg-emerald-300 border">Precio</th>
                                    </tr>
                                </thead>
                                <tbody id="tbodyProductos">
                                    <!-- Aqui se inyectan los datos dinamicamente -->
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="md:col-span-1 lg:col-span-1" >
                        <div class="h-full lg:w-3/6 md:w-4/6 m-auto mt-3 py-8 px-6 text-center text-gray-600 rounded-xl border border-gray-200 bg-gradient-to-r from-indigo-100 to-cyan-100">
                            <form id="ingresarProducto" method="POST" action="/CiteriumJSP/productosServlet">
                                <input type="hidden" name="action" value="update">
                                <h1 class="text-2xl text-cyan-900">Editar Producto</h1>

                                <div>
                                    <div class="formularioInput">
                                        <h3>Producto</h3>
                                        <input type="text" name="nombre" id="producto" placeholder="Queso" required>
                                    </div>
                                    <div class="formularioInput">   
                                        <h3>Codigo de referencia</h3>
                                        <input type="number" name="codigo" id="codigoReferencia" placeholder="13450048" required>
                                    </div>
                                    <div class="formularioInput">
                                        <h3>Categoria</h3>
                                        <fieldset>
                                            <div>
                                                <input type="radio" name="categoria" id="alimentos" value="alimentos" required>
                                                <label for="alimentos">Alimentos</label>

                                                <input type="radio" name="categoria" id="aseo" value="aseo" required> 
                                                <label for="aseo">Aseo</label>

                                                <input type="radio" name="categoria" id="bebidas" value="bebidas" required>
                                                <label for="bebidas">Bebidas</label>
                                            </div>
                                        </fieldset>
                                    </div>
                                    <div class="formularioInput">
                                        <h3>Fecha de registro</h3>
                                        <input type="date" name="fecha" id="fechaRegistro" required>
                                    </div>
                                    <div class="formularioInput">
                                        <h3>Unidades</h3>
                                        <input type="number" name="unidades" id="unidades" placeholder="5" required>
                                    </div>
                                    <div class="formularioInput">
                                        <h3>Precio</h3>
                                        <input type="number" name="precio" id="precio" placeholder="1200" required>
                                    </div>
                                </div>
                                <button class="flex m-auto w-1/4 justify-center rounded-md bg-lime-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-lime-700 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"" type="submit">Editar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>

    </body>
</html>
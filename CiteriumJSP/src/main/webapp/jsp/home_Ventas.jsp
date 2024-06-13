<%-- 
    Document   : home_Ventas
    Created on : 12/06/2024, 10:58:21 a. m.
    Author     : steve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html class="bg-gradient-to-r from-sky-800 to-cyan-600" lang="en">
    <head>
        <%@ include file="components/head.jsp" %>
    </head>
    <body>
        <%@ include file="components/header.jsp" %>

        <%@ include file="components/nav_Ventas.jsp" %>

        <main class="ml-auto mb-6 lg:w-[75%] xl:w-[80%] 2xl:w-[85%]">
            <div class="px-6 pt-6 2xl:container">
                <div class="grid gap-4 md:grid-cols-1 lg:grid-cols-1">
                    <div class="md:col-span-1 lg:col-span-1 overflow-auto" >
                        <div class="h-full w-11/12 m-auto py-8 px-6 text-center text-gray-600 rounded-xl border border-gray-200 bg-gradient-to-r from-indigo-100 to-cyan-100 overflow-auto">
                            <h1 class="text-2xl mb-6 text-teal-800">Productos en caja</h1>
                            <table class="listaProductos table-auto w-full">
                                <thead>
                                    <tr>
                                        <th class="px-4 py-2 bg-teal-400">Nombre</th>
                                        <th class="px-4 py-2 bg-teal-400">Código</th>
                                        <th class="px-4 py-2 bg-teal-400">Cantidad</th>
                                        <th class="px-4 py-2 bg-teal-400">Precio</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="border px-4 py-2">Producto 1</td>
                                        <td class="border px-4 py-2">123</td>
                                        <td class="border px-4 py-2">3</td>
                                        <td class="border px-4 py-2">$ 1000</td>
                                    </tr>
                                    <tr>
                                        <td class="border px-4 py-2">Producto 2</td>
                                        <td class="border px-4 py-2">456</td>
                                        <td class="border px-4 py-2">2</td>
                                        <td class="border px-4 py-2">$ 50</td>
                                    </tr>

                                </tbody>
                            </table>
                            <div class="h-10 w-1/5 mx-auto mt-8 text-center text-xl">
                                <h2>Total: $1050</h2>
                            </div>
                        </div>
                    </div>

                    <div class="md:col-span-1 lg:col-span-1" >
                        <div class="h-full lg:w-3/6 md:w-4/6 m-auto mt-3 py-8 px-6 text-center text-gray-600 rounded-xl border border-gray-200 bg-gradient-to-r from-indigo-100 to-cyan-100">
                            <form id="ingresarProducto">
                                <h1 class="text-2xl text-cyan-900">Producto</h1>

                                <div>
                                    <div class="formularioInput">
                                        <h3>Producto</h3>
                                        <input type="text" name="producto" id="producto" placeholder="Queso" required>
                                    </div>
                                    <div class="formularioInput">   
                                        <h3>Codigo de referencia</h3>
                                        <input type="number" name="codigoReferencia" id="codigoReferencia" placeholder="13450048" required>
                                    </div>
                                    <div class="formularioInput">
                                        <h3>Unidades</h3>
                                        <input type="number" name="unidades" id="unidades" placeholder="5" required>
                                    </div>
                                </div>
                                <button class="flex m-auto w-1/4 justify-center rounded-md bg-lime-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-lime-700 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"" type="submit">Confirmar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>

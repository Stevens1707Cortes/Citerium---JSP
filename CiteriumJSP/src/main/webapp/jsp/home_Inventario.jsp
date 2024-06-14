<%-- 
    Document   : home_Inventario
    Created on : 12/06/2024, 10:57:47 a. m.
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

        <%@ include file="components/nav_Inventario.jsp" %>

        <main class="ml-auto mb-6 lg:w-[75%] xl:w-[80%] 2xl:w-[85%]">
            <%
    // Obtener parámetro de alerta de la URL
    String alerta = request.getParameter("alerta");

    // Verificar si hay un parámetro de alerta y mostrar la alerta correspondiente
    if ("unidadesInsuficientes".equals(alerta)) {
        %>
        <script>
            alert("Unidades del inventario Insuficientes. No se pueden descontar unidades del inventario.");
        </script>
        <%
            }
        %>
            <div class="px-6 pt-6 2xl:container">
                <div class="grid gap-4 md:grid-cols-1 lg:grid-cols-1">
                    <div class="md:col-span-1 lg:col-span-1" >
                        <div class="h-full lg:w-3/6 md:w-3/5 m-auto py-8 px-6 text-center text-gray-600 rounded-xl border border-gray-200 bg-gradient-to-r from-indigo-100 to-cyan-100">
                            <form id="ingresarProducto">
                                <h1 class="text-2xl text-cyan-900">Ingresar Producto</h1>

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
                                        <input type="date" name="fechaRegistro" id="fechaRegistro" required>
                                    </div>
                                    <div class="formularioInput">
                                        <h3>Unidades</h3>
                                        <input type="number" name="unidades" id="unidades" placeholder="5" required>
                                    </div>
                                </div>
                                <button class="flex m-auto w-1/4 justify-center rounded-md bg-lime-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-lime-700 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"" type="submit">Ingresar</button>
                            </form>
                        </div>
                    </div>
                    <div class="md:col-span-1 lg:col-span-1" >
                        <div class="h-full lg:w-3/6 md:w-3/5 m-auto mt-3 py-8 px-6 text-center text-gray-600 rounded-xl border border-gray-200 bg-gradient-to-r from-indigo-100 to-cyan-100">
                            <form id="eliminarProducto">
                                <h1 class="text-2xl text-cyan-900">Descontar Unidades</h1>

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
                                <button class="flex m-auto w-1/4 justify-center rounded-md bg-red-500 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-red-700 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"" type="submit">Eliminar</button>
                            </form>
                        </div>
                    </div>
                    <div class="md:col-span-1 lg:col-span-1" >
                        <div class="h-full lg:w-3/6 md:w-3/5 m-auto mt-3 py-8 px-6 text-center text-gray-600 rounded-xl border border-gray-200 bg-gradient-to-r from-indigo-100 to-cyan-100">
                            <form id="eliminarProducto">
                                <h1 class="text-2xl text-cyan-900">Eliminar producto definitivamente</h1>

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
                                <button class="flex m-auto w-1/4 justify-center rounded-md bg-red-500 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-red-700 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"" type="submit">Eliminar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>

<%-- 
    Document   : index
    Created on : 12/06/2024, 10:00:35 a. m.
    Author     : steve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="bg-gradient-to-r from-indigo-900 to-slate-900" lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesion - Sistema POS: Citerium</title>
    <link rel="icon" type="image/png" href="./img/favicons/favicon.png">
    <link rel="stylesheet" href="css/index.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Anta&family=Covered+By+Your+Grace&family=Roboto+Slab:wght@100..900&family=Staatliches&display=swap" rel="stylesheet">
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
    <main>
        <div class="contenedor w-4/5 xl:w-1/2 flex flex-col justify-center px-6 py-12 lg:px-8 mx-auto mt-10 mb-10 bg-gradient-to-r from-sky-800 to-cyan-600">
            <div class="sm:mx-auto sm:w-full sm:max-w-sm">
              <img id="logo" class="mx-auto" src="img/logo.png" alt="logo-citerium">
              <h2 class="mt-10 text-center text-3xl font-bold leading-9 tracking-tight">Ingresa a tu cuenta</h2>
            </div>
          
            <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
              <form class="space-y-6" action="jsp/home.jsp" method="GET">
                <div>
                  <label for="usuario" class="block text-sm font-medium leading-6 text-gray-100">Usuario</label>
                  <div class="mt-2">
                    <input id="usuario" name="usuario" type="text" placeholder="Ingresa tu usuario" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                  </div>
                </div>
          
                <div>
                  <div class="flex items-center justify-center">
                    <label for="password" class="block text-sm font-medium leading-6 text-gray-100">Contraseña</label>
                  </div>
                  <div class="mt-2">
                    <input id="password" name="password" type="password" autocomplete="current-password" placeholder="Ingresa tu contraseña" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">

                    <div class="text-sm mt-6">
                        <a href="#" class="font-semibold text-amber-300 hover:text-indigo-950">¿Olvidaste tu contraseña?</a>
                      </div>
                  </div>
                </div>
          
                <div>
                  <button type="submit" class="flex w-full justify-center rounded-md bg-lime-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-lime-700 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Ingresar</button>
                </div>
              </form>
          
              <div class="mt-6 text-center text-sm">
                
                <a href="jsp/registro_Usuario.jsp" class="font-semibold leading-6 text-amber-300 hover:text-indigo-950">Registrarse</a>
              </div>
            </div>
          </div>
    </main>
</body>

</html>

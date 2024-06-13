<%-- 
    Document   : registro_Usuario
    Created on : 12/06/2024, 10:50:31 a. m.
    Author     : steve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="bg-gradient-to-r from-indigo-900 to-slate-900" lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrarse - Sistema POS: Citerium</title>
    <link rel="icon" type="image/png" href="../img/favicons/favicon.png">
    <link rel="stylesheet" href="css/index.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Anta&family=Covered+By+Your+Grace&family=Roboto+Slab:wght@100..900&family=Staatliches&display=swap" rel="stylesheet">
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
    <main>
        <form action="home.jsp" method="GET">
            <div class="min-w-screen min-h-screen bg-gradient-to-r from-slate-900 to-indigo-900 flex items-center justify-center px-5 py-5">
                <div class="bg-gray-100 text-gray-500 rounded-3xl shadow-xl w-full overflow-hidden" style="max-width:1000px">
                    <div class="md:flex w-full">
                        <div class="bg-gradient-to-r from-sky-800 to-cyan-600 hidden md:block w-1/2 py-10 px-10">
                            <img class="mt-20" src="../img/Registro.svg" alt="imagen-registro">
                        </div>
                        <div class="w-full md:w-1/2 py-10 px-5 md:px-10">
                            <a class="mb-5" href="../index.jsp">
                                <img src="../img/favicons/icons8-volver-25.png" alt="icono-volver">
                            </a>
                            <div class="text-center mb-10">
                                <h1 class="font-bold text-3xl text-gray-900">Registro</h1>
                                <p>Ingresa tu informacion para registrarte</p>
                            </div>
                            <div>
                                <div class="flex -mx-3">
                                    <div class="w-1/2 px-3 mb-5">
                                        <label for="" class="text-xs font-semibold px-1">Nombre</label>
                                        <div class="flex">
                                            <div class="w-10 z-10 pl-1 text-center pointer-events-none flex items-center justify-center"><i class="mdi mdi-account-outline text-gray-400 text-lg"></i></div>
                                            <input type="text" required class="w-full text-left -ml-10 pl-10 pr-3 py-2 rounded-lg border-2 border-gray-200 outline-none focus:border-indigo-500" placeholder="Alice">
                                        </div>
                                    </div>
                                    <div class="w-1/2 px-3 mb-5">
                                        <label for="" class="text-xs font-semibold px-1">Apellido</label>
                                        <div class="flex">
                                            <div class="w-10 z-10 pl-1 text-center pointer-events-none flex items-center justify-center"><i class="mdi mdi-account-outline text-gray-400 text-lg"></i></div>
                                            <input type="text" required class="w-full text-left -ml-10 pl-10 pr-3 py-2 rounded-lg border-2 border-gray-200 outline-none focus:border-indigo-500" placeholder="Cage">
                                        </div>
                                    </div>
                                </div>
                                <div class="flex -mx-3">
                                    <div class="w-full px-3 mb-5">
                                        <label for="" class="text-xs font-semibold px-1">Numero de identificacion</label>
                                        <div class="flex">
                                            <div class="w-10 z-10 pl-1 text-center pointer-events-none flex items-center justify-center"><i class="mdi mdi-email-outline text-gray-400 text-lg"></i></div>
                                            <input type="number" required class="w-full -ml-10 pl-10 pr-3 py-2 rounded-lg border-2 border-gray-200 outline-none focus:border-indigo-500" placeholder="123456789">
                                        </div>
                                    </div>
                                </div>

                                <div class="flex -mx-3">
                                    <div class="w-full px-3 mb-5">
                                        <label for="" class="text-xs font-semibold px-1">Email</label>
                                        <div class="flex">
                                            <div class="w-10 z-10 pl-1 text-center pointer-events-none flex items-center justify-center"><i class="mdi mdi-email-outline text-gray-400 text-lg"></i></div>
                                            <input type="email" required class="w-full text-left -ml-10 pl-10 pr-3 py-2 rounded-lg border-2 border-gray-200 outline-none focus:border-indigo-500" placeholder="alicecage@example.com">
                                        </div>
                                    </div>
                                </div>
                                <div class="flex -mx-3">
                                    <div class="w-full px-3 mb-5">
                                        <label for="" class="text-xs font-semibold px-1">Contraseña</label>
                                        <div class="flex">
                                            <div class="w-10 z-10 pl-1 text-center pointer-events-none flex items-center justify-center"><i class="mdi mdi-lock-outline text-gray-400 text-lg"></i></div>
                                            <input type="password" required class="w-full -ml-10 pl-10 pr-3 py-2 rounded-lg border-2 border-gray-200 outline-none focus:border-indigo-500" placeholder="************">
                                        </div>
                                    </div>
                                </div>
                                <div class="flex -mx-3">
                                    <div class="w-full px-3 mb-12">
                                        <label for="" class="text-xs font-semibold px-1">Confirma la contraseña</label>
                                        <div class="flex">
                                            <div class="w-10 z-10 pl-1 text-center pointer-events-none flex items-center justify-center"><i class="mdi mdi-lock-outline text-gray-400 text-lg"></i></div>
                                            <input type="password" required class="w-full -ml-10 pl-10 pr-3 py-2 rounded-lg border-2 border-gray-200 outline-none focus:border-indigo-500" placeholder="************">
                                        </div>
                                    </div>
                                </div>
                                <div class="flex -mx-3">
                                    <div class="w-full px-3 mb-5">
                                        <button type="submit" class="block w-full max-w-xs mx-auto bg-lime-600 hover:bg-lime-700 focus:bg-indigo-700 text-white rounded-lg px-3 py-3 font-semibold">Registrarse</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </main>
</body>
</html>

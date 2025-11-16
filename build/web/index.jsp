<%-- 
    Document   : index
    Created on : 5/07/2019, 01:41:27 AM
    Author     : Darwin Garcia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-CO">
    <head>
        <title>Quickelp</title>
        <link rel="shortcut icon" href="sources/favicon.ico" type="image/x-icon">
        <link rel="icon" href="sources/favicon.ico" type="image/x-icon">        
        <meta name="viewport" content="width=device-width, user-scalable=no">
        
        <link rel="stylesheet" href="css/mainpage/bootstrap.css">
        <link rel="stylesheet" href="vendors/linericon/style.css">
        <link rel="stylesheet" href="css/mainpage/font-awesome.min.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="vendors/lightbox/simpleLightbox.css">
        <link rel="stylesheet" href="vendors/nice-select/css/nice-select.css">
        <link rel="stylesheet" href="vendors/animate-css/animate.css">
        
        <!-- main css -->
        <link rel="stylesheet" href="css/mainpage/style.css">
        
    </head>
    <body>
        <main role="main">
            <header class="header_area">
                <div class="main_menu">
                    <nav class="navbar navbar-expand-lg navbar-light">
                        <div class="container">
                            <!-- Brand and toggle get grouped for better mobile display -->
                           
                            <a class="navbar-brand logo_h logotipo" href="index.jsp" style="font-size: 40px; color: #878787"> <img src="sources/logotipo.png" style="position: inherit" alt=""></a>
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>

                            <!-- Enlaces nagegacion -->
                            <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                                <ul class="nav navbar-nav menu_nav ml-auto">
                                    <li class="nav-item active"><a class="nav-link" href="Inicio?accion=raiz"><i class="fa fa-home"></i> Inicio</a></li> 

                                    <li class="nav-item active"><a class="nav-link" href="#Nuestros servicios"><i class="fa fa-wrench"></i> Nuestros Servicios</a></li>

                                    <li class="nav-item active"><a class="nav-link" href="#Acerca de Nosotros"><i class="fa fa-users"></i> Acerca de Nosotros</a></li>
                                    <!-- Enlaces nagegacion -->
                                                              
                                </ul>
                                <ul class="nav navbar-nav menu_nav ml-auto">
                                    <li class="nav-item active">
                                        <a href="Sesion?accion=Ingresar" class="primary-btn text-uppercase"><i class="fa fa-user"></i> Iniciar sesion</a> 
                                    </li></ul> 
                            </div> 
                        </div>
                    </nav>
                </div>
            </header>
            <section class="home_banner_area">
                <div class="banner_inner">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="banner_content">
                                    <h2>Necesitas Ayuda?</h2>
                                    <p>Servimos en todo tipo de equipo y dispositivo como portatil, impresora o Mac.<br>
                                       Quickelp cuenta con tecnicos calificados en reparacion y soporte que resuelve tu necesidad tecnologica desde la comodidad de su hogar u oficina.<br>
                                       Deseas solicitar un servicio?. <br>
                                       </p>
                                       <a class="primary-btn text-uppercase" href="Sesion?accion=Ingresar"> Registrate aqui.</a>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!--================ End Home Banner Area =================-->

            <!--================ Start Feature Area =================-->
            <section class="feature_area">
                <div class="container">
                    <div class="row justify-content-end">
                        <div class="col-lg-4">
                            <div class="single_feature d-flex flex-row pb-30">
                                <div class="icon">
                                    <span class="lnr lnr-book"></span>
                                </div>
                                <div class="desc">
                                    <h4>Rapida Respuesta</h4>
                                    <p>
                                        Solicita el servicio deseado con mayor comodidad, con solo tres pasos en nuestro sistema ya puedes contar con un tecnico en casa .
                                    </p>
                                </div>
                            </div>
                            <div class="single_feature d-flex flex-row pb-30">
                                <div class="icon">
                                    <span class="fa fa-trophy"></span>
                                </div>
                                <div class="desc">
                                    <h4>Atencion Personalizada</h4>
                                    <p>
                                        Nuestros tecnicos calificados tienen el compromiso de satisfacer sus necesidades tecnologicas con atencion oportuna y respuesta eficaz a sus requierimientos.
                                    </p>
                                </div>
                            </div>
                            <div class="single_feature d-flex flex-row">
                                <div class="icon">
                                    <span class="lnr lnr-screen"></span>
                                </div>
                                <div class="desc">
                                    <h4>Cliente Satisfecho</h4>
                                    <p>
                                        Nuestro compromiso es la calidad y transparencia hacia el cliente desde el principio hasta el final del servicio
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <a name ="Nuestros servicios"/>
            <div class="popular_courses lite_bg">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-6">
                            <div class="main_title">
                                <h2> Nuestros servicios</h2>
                                <p>Estos son los servicios que ofrece quickelp para los clientes.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <!-- single course -->
                        <div class="col-lg-3 col-md-6">
                            <div class="single_course">
                                <div class="course_head overlay">

                                    <img class="img-fluid w-100" src="sources/principal/items/software.PNG" alt=""/>
                                    <div class="authr_meta">

                                    </div>
                                </div>
                                <div class="course_content">
                                    <h4>
                                        <a href="#">Mantenimiento software</a>
                                    </h4>
                                    <p> la estabilidad de sus aplicaciones de software y sistemas , de acuerdo a las necesidades operativas y mejoras continuas del sistema según su petición.</p>
                                    <div class="course_meta d-flex justify-content-between">

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- single course -->
                        <div class="col-lg-3 col-md-6">
                            <div class="single_course">
                                <div class="course_head overlay">
                                    <img class="img-fluid w-100" src="sources/principal/items/hard.PNG" alt=""/>
                                    <div class="authr_meta">
                                        
                                    </div>
                                </div>
                                <div class="course_content">
                                    <h4>
                                        <a href="course-details.html">Mantenimiento Hardware</a>
                                    </h4>
                                    <p>Componentes internos de este como a los componentes externos, todo esto ya sea para evitar o prevenir daños futuros o para prolongar la vida útil de este o bien para mejorar el desempeño del equipo de computo</p>
                                    <div class="course_meta d-flex justify-content-between">

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- single course -->
                        <div class="col-lg-3 col-md-6">
                            <div class="single_course">
                                <div class="course_head overlay">

                                    <img class="img-fluid w-100" src="sources/principal/items/fisico.PNG" alt=""/>
                                    <div class="authr_meta">

                                    </div>
                                </div>
                                <div class="course_content">
                                    <h4>
                                        <a href="course-details.html">Revision Fisico</a>
                                    </h4>
                                    <p>Procedimiento ue va desde revisar la parte superficial de los dispositivos del gabinete , hasta los rincones mas estrechos; se realiza antes de dar mantenimiento al equipo.</p>
                                    <div class="course_meta d-flex justify-content-between">

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- single course -->
                        <div class="col-lg-3 col-md-6">
                            <div class="single_course">
                                <div class="course_head overlay">

                                    <img class="img-fluid w-100" src="sources/principal/items/install.png" alt=""/>
                                    <div class="authr_meta">

                                    </div>
                                </div>
                                <div class="course_content">
                                    <h4>
                                        <a href="#">Instalaciones</a>
                                    </h4>
                                    <p>es el proceso fundamental por el cual los nuevos programas piezas son transferidos a un computador con el fin de ser configurados, y preparados para ser desarrollados.</p>
                                    <div class="course_meta d-flex justify-content-between">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!--=============hablando de la empresa =================-->
            <a name ="Acerca de Nosotros"/>
            <div class="department_area section_gap">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-lg-6">
                            <div class="dpmt_courses">
                                <div class="row">
                                    <!-- single course -->
                                    <div class="col-lg-4 col-md-4 col-sm-6 col-12 text-center mt-100">
                                        <div class="single_department">
                                            <div class="dpmt_icon">                                                                        
                                                <img src="sources/principal/dpmt/icon1.png" alt=""/>
                                            </div>
                                            
                                            <h4>Transparencia</h4>
                                        </div>
                                    </div>
                                    <!-- single course -->
                                    <div class="col-lg-4 col-md-4 col-sm-6 col-12 text-center">
                                        <div class="single_department">
                                            <div class="dpmt_icon">
                                                <img src="sources/principal/dpmt/icon2.png" alt="">
                                            </div>
                                            <h4>Calidad de los servicios</h4>
                                        </div>
                                    </div>
                                    <!-- single course -->
                                    <div class="col-lg-4 col-md-4 col-sm-6 col-12 text-center mt-100">
                                        <div class="single_department">
                                            <div class="dpmt_icon">
                                                <img src="sources/principal/dpmt/icon3.png" alt="">
                                            </div>
                                            <h4>Facil de usar</h4>
                                        </div>
                                    </div>
                                    <!-- single course -->
                                    <div class="col-lg-4 col-md-4 col-sm-6 col-12 text-center">
                                        <div class="single_department">
                                            <div class="dpmt_icon">
                                                <img src="sources/principal/dpmt/icon4.png" alt="">
                                            </div>
                                            <h4>Eficiente</h4>
                                        </div>
                                    </div>
                                    <!-- single course -->
                                    <div class="col-lg-4 col-md-4 col-sm-6 col-12 text-center mt--100">
                                        <div class="single_department">
                                            <div class="dpmt_icon">
                                                <img src="sources/principal/dpmt/icon5.png" alt="">
                                            </div>
                                            <h4>Intuitivo</h4>
                                        </div>
                                    </div>
                                    <!-- single course -->
                                    <div class="col-lg-4 col-md-4 col-sm-6 col-12 text-center mt--70">
                                        <div class="single_department">
                                            <div class="dpmt_icon">
                                                <img src="sources/principal/dpmt/olito.png" alt="">
                                            </div>
                                            <h4>Confiable</h4>
                                        </div>
                                    </div>
                                    <!-- single course -->
                                    <div class="offset-lg-4 col-lg-4 col-md-4 col-sm-6 col-12 text-center mt--100">
                                        <div class="single_department">
                                            <div class="dpmt_icon">
                                                <img src="sources/principal/dpmt/icon7.png" alt="">
                                            </div>
                                            <h4>Compromiso</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="offset-lg-1 col-lg-5">
                            <div class="dpmt_right">
                                <h1>Porque elegir Quickelp?</h1>

                                <p>Quickelp es un software que ofrece servicios de reparacion, soporte tecnico, mantenimiento, instalacion y configuracion
                                    de equipos de computo desde la comodidad de su hogar u oficina, y esta dirigida a individuos o empresas que necesiten ayuda con
                                    sus problemas tecnologicos que se presentan a diario.</p> 

                                <p>Por cada servicio que usted solicite, contará con un tecnico que esta dispuesto a resolver su requerimiento de manera efectiva y confiable, ahorrando tiempo y costos
                                    en servicios o reparaciones particulares</p>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--================ fin hablando de quickelp =================-->
            <!-- Pie de Pagina -->
            <footer class="footer-distributed">
                <div class="footer-left">
                    <p style="font-size: 24px">Acerca de <span><a class="logotipo" style="color: #15a28d; font-size: 24px">Quickelp</a></span></p>
                </div>
                <div class="footer-right">
                    <p class="footer-company-name"><!-- CODIGO JAVACRIPT -->
                        Copyright &copy;<script>document.write(new Date().getFullYear());</script>  Derechos de autor | hecho con <i class="fa fa-heart-o" aria-hidden="true"></i> por Quickelp. <a href="https://colorlib.com" target="_blank"></a>
                        <!-- CODIGO JAVASCRIPT --></p>                  
                </div>
                <div class="footer-right">
                    <p class="footer-company-about">
                        <span>Quickelp_Oficial</span></p>

                    <ul class="social-icons">
                        <li><a class="facebook" href="https://es-la.facebook.com/quickelp"><i class="fa fa-facebook"></i></a></li>
                        <li><a class="instagram" href="https://www.instagram.com/quickelp_oficial/"><i class="fa fa-instagram"></i></a></li>
                        <li><a class="twitter" href="https://twitter.com/QuickelpO"><i class="fa fa-twitter"></i></a></li>
                        <li><a class="youtube" href="https://www.youtube.com/channel/UChurMlCmWjeaAd4Vaglc1LQ"><i class="fa fa-youtube"></i></a></li> 
                    </ul>
                </div>
            </footer>
            <!-- Fin del Pie de Pagina -->    
        </main>	
    </body>
    <script src="js/mainpage/jquery-3.2.1.min.js"></script>
    <script src="js/mainpage/popper.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/mainpage/stellar.js"></script>

    <script src="vendors/nice-select/js/jquery.nice-select.min.js"></script>
    <script src="vendors/owl-carousel/owl.carousel.min.js"></script>

    <script src="js/mainpage/owl-carousel-thumb.min.js"></script>
    <script src="js/mainpage/jquery.ajaxchimp.min.js"></script>
    <script src="js/mainpage/mail-script.js"></script>
    <!--gmaps Js-->
    <script src="js/mainpage/theme.js"></script>
    
</html>

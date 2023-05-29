# Bienvenido a Quickelp!

Hola! Bienvenido a la version inicial de la aplicacion **Quickelp**. 
Este proyecto es la base de una aplicación de servicios técnicos de computadoras para empresas basado en un modelo **CRUD** bajo el modelo vista controlador **MVC**. No usa framework. Utiliza base de datos **MySQL** 

No tiene diseño final o maquetado para entrega del software. Contiene solo el codigo fuente.

![NetBeans IDE](https://img.shields.io/badge/NetBeansIDE-1B6AC6.svg?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

# Contenido

Todos los archivos contienen la siguiente estructura. Se puede ejecutar localmente en la computadora **offline!**
<ul>
<li>📂 build</li>
<li>📂 dist</li>
<li>📂 nbproject</li>
<li>📂 src</li>
  <ul><li>📂 java</li>
   <ul><li> 📂 com</li>
     <ul> </li>📂 quickelp</li>
	  <ul></li>📂 programa</li>
		   <ul>  <li> 📂 interfaz</li>
		      <li>📂 negocio</li>
		      <ul><li>📂 persistencia</li>
			     <ul><li> 📂 conexion</li>
			      <li>📂 vo</li>
			    <li>  📂 dao</li>
			    </ul></ul></ul></ul></ul></ul></ul>
<li>📂 test</li>
<li>📂 web</li>
	<ul>
	<li>📂 css</li>
	<li>📂 js</li>
	<li>📂 sources</li>
	<li>📂 views</li>
	</ul>
<li>📄 readme.md</li>
<li>📄 build.xml</li>
</ul>

## Crear archivos y carpetas

Se recomienda utilizar solamente la carpeta **📂 src** o **📂 views** para evitar errores en la ejecucion del proyecto.
En la terminal de Linux usa el comando para crear carpetas:
>mkdir "carpeta" 

## Cambiar a otra carpeta

Puedes cambiar de directorio el archivo desde el explorador de Windows.
En la terminal de comandos de cualquier sistema Linux  a través del comando 
>cd "carpeta" 

Para regresar a la carpeta anterior.
>cd ..

Puedes cambiar de archivo usando el editor favorito o en el programa de entorno de desarrollo IDE

## Renombrar un archivo

Puedes renombrar el archivo desde el explorador de Windows, en la terminal de comandos de cualquier sistema Linux  a través del comando 
>mv "nombre_antiguo" "nombre_nuevo"

bien sea en el entorno grafico Gnome, KDE, XFCE, entre otros que utilices o en el Finder de MacOS usando el click derecho y buscan el menu contextual el boton **Rename**.

## Eliminar un archivo
Puedes borrar el archivo o una carpeta desde el explorador de Windows usando la tecla **Supr**, en la terminal de comandos de cualquier sistema Linux  a través del comando 
>rm "nombre_archivo"

bien sea en el entorno gráfico Gnome, KDE, XFCE, entre otros que utilices o en el Finder de MacOS usando el clic derecho y buscan el menú contextual el botón **Delete**.



## Exportar el proyecto a servidor web

A través del entorno de desarrollo **NetBeans IDE** solo se puede exportar en formato comprimido ZIP
El contenido del ejecutable se encuentra en la carpeta **📂 dist** y el archivo con extensión .war si usa servidor web basado en Apache Tomcat o Glassfish, entre otros. De manera directa no se puede ejecutar el proyecto.


# Como funciona?
Descarga todo el contenido desde este repositorio en Github dando clic en la zona 

## Abrir el proyecto
Pasos para abrir el proyecto desde cero:
<ul>
<li>
Puedes abrir el proyecto en el entorno de desarrollo. **NetBeans IDE 8.2**. </li>
<li>Cuando abres el programa dirigete en el menú **File** luego  **Open Project**. </li>
<li>Selecciona la carpeta que contiene todos los archivos de este proyecto.</li>
</ul>
Para ejecutar la base de datos se requiere usar un motor de base de datos basado en MySQL. Puede utilizar XAMPP o WampServer. Recomiendo abrir el localhost:8080 o el localhost:8000 para abrir el servidor web de base de datos y cargar el archivo con extension .sql incluido en el proyecto

## Guardar los cambios realizados

Dentro del entorno IDE puedes guardar todos los cambios siempre que selecciones el proyecto **Quickelp**. Se recomienda implementar control de versiones, dado que este proyecto no fue usado para entrega final del proyecto.

## Ejecutando el proyecto

Una vez abierto el proyecto, puedes construir la app desde el menu de navegacion, busca en **Run** y luego en **Build Project**

Posteriormente puedes ejecutar el proyecto usando F6 o en el menu de navegacion , busca en **Run** y luego en **Run Project ** 

> **Nota:** Solo puede visualizarse en NetBeans IDE 8.2 (Java EE) y ejecutarse en el kit de desarrollo Java JDK 8. No se ha probado en Eclipse IDE o IntelliJ IDEA, dado que no usa el gestor Maven para ejecutar el proyecto. Tampoco cuenta con control de version Git guardado en el proyecto

## Otras Opciones de Ejecución

Puedes realizar cambios de codigo desde un editor de texto como **Atom, Sublime Text, Visual Studio Code o NeoVim**.
> **Nota:** Tener en cuenta que no se puede realizar pruebas ni ejecutar el proyecto sin **NetBeans IDE**. No se han realizado pruebas de ejecucion desde Eclipse o IntelliJ IDEA


# Publicación

Este proyecto fue terminado en Diciembre 2019 para el SENA como parte de proyecto lectivo en la carrera de Tecnología en Análisis y Diseño de Sistemas de información y solo se publica con fin educativo. Puedes ejecutar el proyecto, realizar cambios o aportes

> **Nota:** Solo se concede uso libre para estudiantes y docentes. 

- - -
👨‍💻 ©MMXIX. Darwin Garcia. 🇨🇴

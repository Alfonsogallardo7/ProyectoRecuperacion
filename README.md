# Proyecto3-ad-psp | Real Estate V2

La aplicación Real State es una aplicación la cual nos permite, crear nuestra propias viviendas, como propietarios, inmobiliarias e interesados, que se crea cuando muestras un interes por una vivienda

## Autor

- Alfonso Gallardo Rodríguez

### Entidades

- Inmobiliaria
- Vivienda
- Usuario
    - Gestor
    - Administrador
    - Propietario
- Interesa

## Arrancar el proyecto

Para arrancar nuestro proyecto deberemos de seguir los siguientes pasos.

### - Descargar e instalar IntelliJ IDEA Community Edition 2021.2.2

### - Descargar JAVA JDK 17

### - Abrimos el IntelliJ IDEA

### - Configuramos la version de JDK

    1. Clickamos en File
    2. A continuación en Proyect Estructure
    3. Por último seleccionamos en Proyect SDK la versión 17 (la cual hemos descargado previamente)

### - Arrancamos el Proyecto 

    1. Hacemos click en el botón de Maven,la cual encontraremos en la esquina superior derecha de la ventana
    2. A continuación clickamos en Pluggins.
    3. Después en Spring Boot
    4. Y por último en Spring Boot Run

## - ¿Qué podemos hacer en RealState?

- Login y Registro
    - Podremos dar de alta un usuario Propietario
    - Podremos dar de alta un usuario Gestor de una Inmobiliaria
    - Podremos dar de alta un usuario Administrador

- Funcionalidades de Inmobiliatia
    - Podemos añadir una nueva inmobiliaria
    - Obtener todas inmobiliarias
    - Obtener los datos de una inmobiliaria (obteniendo un listado sencillo de las viviendas gestionadas por la inmobiliaria).
    - Eliminar una inmobiliaria (sin eliminar las viviendas gestionadas por la inmobiliaria).
    - Obtener todos los gestores de una inmobiliaria
    - Añadir un gestor a una inmobiliaria
    - Borrar un gestor de una inmobiliaria

- Funcionalidades de Propietario
    - Obtener todos los propietarios
    - Obtener los datos de un propietario (obteniendo también lo datos básicos de las viviendas que tienen en propiedad)
    - Eliminar un propietario (eliminando en cascada las viviendas de dicho propietario)

- Funcionalidades de usuarios Interesados
    - Obtener los datos de todos los interesados
    - Obtener los datos de un interesado

- Funcionalidades de Vivienda
    - Crear una nueva vivienda (el propietario será la persona que la crea)
    - Obtener todas las viviendas (trayendo solamente los datos que vayamos a utilizar)
    - Obtener los datos de una vivienda, obteniendo también los datos del propietario 
    - Modificar un vivieda
    - Eliminar una vivienda (eliminando el interes sobre la vivienda, pero sin borrar a los interesados)
    - Establecemos que una vivienda pueda estar gestionada por una inmobiliaria
    - Eliminar la gestión de la vivienda por parte de la inmobiliaria
    - Podemo añadir un nuevo interesado con la información de interés para la vivienda ID.
    - Añadir un nuevo interés para la vivienda ID, pero rescatando la información de interesado con ID2.
    - Eliminar el interes de un interesado de una vivienda
    - Obtener las n vivienda, que tienen más interesados







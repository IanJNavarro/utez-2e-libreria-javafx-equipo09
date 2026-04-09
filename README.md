# Cataologo de Libros - Proyecto Integrador - Javier Adael Diaz Gonzalez - Ian Josue Navarro Arce

Repositorio para la creacion del proyecto integrador de la clase programacion estructurada.

## Descripción

Aplicación de escritorio desarrollada en JavaFX para la gestión de un catálogo de libros. Permite realizar operaciones CRUD con persistencia en archivo local, garantizando la conservación de datos entre ejecuciones.

## Objetivo

Implementar un sistema que integre:

- CRUD completo
- Persistencia en archivo
- Validaciones
- Manejo de errores
- Programación orientada a objetos

## Funcionalidades

- Alta, consulta, actualización y eliminación de libros
- Carga automática de datos al iniciar
- Guardado automático en archivo (.csv o .txt)
- Validación de campos (no vacíos, formato y rangos)
- Prevención de duplicados por ISBN/ID
- Vista de detalle de libro
- Exportación de reporte a archivo

## Interfaz

- Pantalla principal con acciones CRUD
- Formulario para alta/edición
- Pantalla de detalle

## Estructura

- Model: Libro
- Repository/Service: manejo de datos y persistencia
- Controllers: uno por pantalla
- Utilidades para manejo de archivos

## Control de versiones

- main: versión final
- dev: integración
- ramas individuales por integrante

Flujo:

1. Trabajo en ramas personales
2. Merge a dev
3. Pruebas
4. Merge final a main

## Restricciones

- No usar base de datos
- No depender solo de memoria
- Debe conservar datos al reiniciar

# Pasos para Ejecucion

1. Al abrir el proyecto se muestra la lista del catalogo con opciones para agregar, editar, eliminar, ver detalles y exportar reporte
2. Para agregar un libro al catalogo se presiona en el boton "Nuevo" lo que abre una escena que pregunta por los datos del libro a agregar.
3. El boton "Editar" pide que se seleccione un libro dentro del catalogo para poder abrir un formulario que te permite editar los datos del libro excepto el ID.
4. El boton "Eliminar" pide que se seleccione un libro dentro del catalogo para mostrar un pop up de confimacion y proceder a eliminar el libro del catalogo.
5. El boton "Ver Detalle" pide que se seleccione un libro dentro del catalogo para mostrar una escena donde se detalla el contenido del libro.
6. El boton "Exportar Reporte" crea un reporte de el estado actual de los libros que no se actualiza al agregar o quitar libros.

# Persistencia

El proyecto incluye persistencia dentro de un archivo .csv el cual se almacena de manera local dentro de la aplicacion para permitir el acceso continuo a la informacion dentro del catalogo de manera permanente y no solo en memoria. El reporte exportado permite mantener un control de un momento especifico del catalogo para poder llevar un control de datos antiguos que pueden no encontrarse en el catalogo actual.

Los datos se almacenan en un archivo local estructurado.
Se cargan al iniciar y se actualizan tras cada operación.






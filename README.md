# Proyecto Java "employee" Amaris

Este proyecto Java implementa una aplicación web para la gestión de empleados. 

## Características principales

*   **JDK 1.8:**  El proyecto requiere Java Development Kit versión 1.8 para su compilación y ejecución.
*   **Puerto 8081:** La aplicación se ejecuta en el puerto 8081. Asegúrate de que este puerto esté disponible en tu sistema.
*   **RestTemplate:** Se utiliza la clase `RestTemplate` para la comunicación con APIs externas, facilitando el consumo de servicios web RESTful.
*   **Archivo WAR:** El proceso de construcción genera un archivo WAR (Web Application Archive), que se puede desplegar en cualquier servidor de aplicaciones compatible con Java EE.
*   **Arquitectura MVC:** El proyecto sigue el patrón de diseño Modelo-Vista-Controlador (MVC) para una mejor organización del código y separación de responsabilidades.
*   **Maven:** Se utiliza Apache Maven para la gestión de dependencias del proyecto, simplificando la inclusión de bibliotecas y la construcción del proyecto.

## Instrucciones de ejecución

1.  **Clonar el repositorio:** 
    ```bash
    git clone <URL_DEL_REPOSITORIO>
    ```

2.  **Importar el proyecto:** Importa el proyecto en tu IDE favorito como un proyecto Maven.

3.  **Construir el proyecto:** 
    ```bash
    mvn clean install
    ```
    Este comando compilará el código, ejecutará las pruebas (si las hay) y generará el archivo WAR en el directorio `target`.

4.  **Desplegar el archivo WAR:** Despliega el archivo WAR generado en un servidor de aplicaciones como Tomcat, JBoss o WildFly.

5.  **Acceder a la aplicación:** Una vez desplegada, la aplicación estará disponible en las siguientes URL:
    ```
    http://localhost:8081/api/employee/1
    ```
    ```
    http://localhost:8081/api/list-employees
    ```
    
**Nota:**  Asegúrate de tener configurado el JDK 1.8 y Maven en tu sistema antes de ejecutar el proyecto.

## Estructura del proyecto (sugerencia)

Para un proyecto MVC, se recomienda una estructura de directorios similar a la siguiente:

employee/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── amaris/
│   │   │           └── employee/
│   │   │               ├── controller/

│   │   │               ├── model/

│   │   │               ├── service/

│   │   │               └── ...

│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/
│               └── amaris/
│                   └── employee/
│                       └── ...

└── pom.xml

*   **controller:** Contiene la clase que maneja las solicitudes web.
*   **models:** Define la clase que representa los datos de la aplicación.
*   **service:**  Incluye la lógica de negocio y la interacción con las APIs.
*   **resources:** Almacena archivos de configuración como `application.properties`.
*   **pom.xml:**  Archivo de configuración de Maven con las dependencias del proyecto.



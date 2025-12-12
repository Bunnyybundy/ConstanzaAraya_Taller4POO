# AcademiCore – Sistema Académico de Certificaciones Profesionales

## 📌 Descripción del Proyecto
**AcademiCore** es un sistema académico integral desarrollado para la **Universidad Católica del Mish**, cuyo objetivo es digitalizar y automatizar la gestión de certificaciones profesionales en tecnología.  
El sistema complementa la formación de grado con competencias especializadas en áreas de alta demanda como **Desarrollo de Software**, **Sistemas Inteligentes** y **Ciberseguridad**.

La solución aborda problemas detectados en la gestión manual:
- Estudiantes perdiendo oportunidades de certificación por desconocimiento.
- Coordinadores sin herramientas para identificar cuellos de botella.
- Procesos de generación de certificados lentos (hasta 3 semanas).
- Falta de métricas para evaluar efectividad de certificaciones.

Con **AcademiCore**, se garantiza:
- **Transparencia:** Los estudiantes visualizan en tiempo real su progreso, requisitos pendientes y proyección de completación.
- **Eficiencia:** Coordinadores acceden a dashboards analíticos con métricas y asignaturas críticas.
- **Automatización:** Generación instantánea de certificados oficiales al cumplir requisitos académicos.

---

## 👥 Integrantes
- **Integrante 1:** Constanza Fernanda Araya Plaza – 21.609.057-8 – Bunnyybundy 
- **Integrante 2:** Emiliano Angel Toro Rojas – 21.512.702-8 – EmiToro  

---

---

## 🏗️ Estructura del Proyecto
El sistema está organizado en *arquitectura de 3 capas*:

- *Dominio:*  
  Clases principales (Usuario, Estudiante, Coordinador, Administrador, Curso, Nota, Certificacion, RegistroCertificacion, AsignaturasCertificacion).

- *Lógica:*  
  Clase Sistema como *Singleton*, encargada de la gestión centralizada de datos, lectura/escritura de archivos (usuarios.txt, estudiantes.txt, cursos.txt, certificaciones.txt, registros.txt, notas.txt, asignaturas_certificaciones.txt).

- *Presentación:*  
  Interfaz gráfica en *Java Swing* con tres menús principales:
  - *Menú Administrador:* Gestión de usuarios (crear, modificar, eliminar, resetear contraseñas).
  - *Menú Coordinador:* Gestión de certificaciones, métricas y análisis, gestión de estudiantes.
  - *Menú Estudiante:* Perfil, malla curricular interactiva, inscripción a certificaciones, seguimiento de progreso.

---

## 🎯 Funcionalidades por Menú

### Menú Administrador (30 pts)
- Crear cuentas de estudiante o coordinador.
- Modificar cuentas existentes.
- Eliminar cuentas y referencias asociadas.
- Restablecer contraseñas.

### Menú Coordinador (50 pts)
- Modificar líneas de certificación.
- Generar certificados de estudiantes que completaron la certificación.
- Panel de métricas: estadísticas de inscripciones y análisis de asignaturas críticas.
- Gestión de estudiantes: consultar perfiles completos y validar avances académicos.

### Menú Estudiante (85 pts)
- Perfil: información personal, malla curricular, promedio general y por semestre.
- Malla curricular interactiva: visualización gráfica por semestres, colores según estado, detalles al clic.
- Inscripción a certificaciones: listado, requisitos, validaciones y prerrequisitos.
- Seguimiento de progreso: dashboard con barras de avance, aplicación de Visitor, asignaturas pendientes.

---

## 🧩 Patrones de Diseño Implementados
- *Singleton:* Clase Sistema para gestión centralizada de datos.  
- *Factory:* Creación de usuarios (Administrador, Coordinador, Estudiante).  
- *Strategy:* Cálculo de promedios (PromedioSimple, PromedioPonderado).  
- *Visitor:* Validación de certificaciones y progreso académico (ValidarCertiVisitor).  

---

## 📂 Archivos de Datos
- usuarios.txt → Coordinadores y administradores.  
- estudiantes.txt → Información de estudiantes.  
- cursos.txt → Asignaturas con NRC, créditos y prerrequisitos.  
- certificaciones.txt → Líneas de certificación.  
- registros.txt → Inscripciones de estudiantes en certificaciones.  
- notas.txt → Calificaciones y estados de asignaturas.  
- asignaturas_certificaciones.txt → Relación entre cursos y certificaciones.  

---
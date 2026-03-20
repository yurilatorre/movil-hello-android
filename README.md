Yuriani Zaray Latorre Osman
1097782670

06/03/2026

Función del AndroidManifest.xml
* Es el archivo donde se declara la información principal de la aplicación, como las actividades, permisos, servicios y configuración básica que el sistema necesita para ejecutar la app en Android.

Diferencia entre activity_main.xml y MainActivity.kt
* activity_main.xml : Define el diseño de la interfaz gráfica (botones, textos, imágenes) de la pantalla.
* MainActivity.kt : Contiene el código en Kotlin que controla la lógica y el comportamiento de esa pantalla en Android.

Gestión de recursos en Android
* Es la forma en que Android organiza y utiliza archivos como imágenes, textos, colores y diseños dentro de la carpeta res, para poder usarlos fácilmente en la aplicación.

Aplicaciones famosas que usan Kotlin
* Pinterest
* Trello
* Evernote
* Netflix
-------------------------------------------------------------------------------------
## Taller 2 - Arquitectura MVVM

### Respuestas a Preguntas Conceptuales


1.¿Qué problema resuelve el ViewModel?


Evita que los datos se pierdan cuando la pantalla cambia (como al rotar) y separa la lógica de la interfaz.

2. ¿Por qué LiveData es lifecycle-aware?

Porque solo actualiza la pantalla cuando está activa, evitando errores y crashes.

3. Flujo de datos en MVVM

La Vista (Fragment) pide datos al ViewModel, el ViewModel los obtiene del Repository, y luego los muestra en la Vista.

4. Ventaja de Fragments vs Activities

Permiten usar varias pantallas en una sola Activity, haciendo la app más simple y flexible.

5. ¿Para qué sirve el Repository?

Centraliza los datos y facilita cambiar entre base de datos o internet sin afectar el resto del código.
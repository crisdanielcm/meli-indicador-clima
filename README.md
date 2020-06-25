# meli-indicador-clima

## Especificación del caso

En una galaxia lejana, existen tres civilizaciones. Vulcanos, Ferengis y Betasoides. Cada civilización vive en paz en su respectivo planeta.
Dominan la predicción del clima mediante un complejo sistema informático. A continuación el diagrama del sistema solar.

### Premisas:

- El planeta Ferengi se desplaza con una velocidad angular de 1 grados/día en sentido horario. Su distancia con respecto al sol es de 500Km.
- El planeta Betasoide se desplaza con una velocidad angular de 3 grados/día en sentido horario. Su distancia con respecto al sol es de 2000Km.
- El planeta Vulcano se desplaza con una velocidad angular de 5 grados/día en sentido anti­horario, su distancia con respecto al sol es de 1000Km.
- Todas las órbitas son circulares.
Cuando los tres planetas están alineados entre sí y a su vez alineados con respecto al sol, el sistema solar experimenta un período de sequía.
Cuando los tres planetas no están alineados, forman entre sí un triángulo. Es sabido que en el momento en el que el sol se encuentra dentro del triángulo, el sistema solar experimenta un período de lluvia, teniendo éste, un pico de intensidad cuando el perímetro del triángulo está en su máximo.

Las condiciones óptimas de presión y temperatura se dan cuando los tres planetas están alineados entre sí pero no están alineados con el sol.
Realizar un programa informático para poder predecir en los próximos 10 años:
1. ¿Cuántos períodos de sequía habrá?
2. ¿Cuántos períodos de lluvia habrá y qué día será el pico máximo de lluvia?
3. ¿Cuántos períodos de condiciones óptimas de presión y temperatura habrá?

### Bonus:

Para poder utilizar el sistema como un servicio a las otras civilizaciones, los Vulcanos requieren tener una base de datos con las condiciones meteorológicas de todos los días y brindar una API REST de consulta sobre las condiciones de un día en particular.
1. Generar un modelo de datos con las condiciones de todos los días hasta 10 años en adelante utilizando un job para calcularlas.
2. Generar una API REST la cual devuelve en formato JSON la condición climática del día consultado.
3. Hostear el modelo de datos y la API REST en un cloud computing libre (como APP Engine o Cloudfoudry) y enviar la URL para consulta:
Ej: GET → http://....../clima?dia=566 → Respuesta: {“dia”:566, “clima”:”lluvia”}

## Premisas del desarrollador

- Al tener una órbita circular, se asumen como 360 dias la vuelta para el planeta mas pequeño teniendo en cuenta que su velocidad angular es 1 grado por dia.
- Los planetas inician su orbita desde el mismo punto y se tienen los registros almacenados hasta los proximos diez años.
- Se almacena como clima indefinido los casos en que no se cumple ninguna condición.

## Tecnologías implementadas

- Framework: Spring - Java.
- Spring boot.
- JDK 13.0.2.
- Persistencia: H2 Database
- Host de la aplicación: Heroku https://clima-sistema-solar.herokuapp.com/

## Aplicación

Una vez iniciada la aplicación, se cargaran los datos de pronosticos para 10 años, es decir 3600 dias. Los valores admitidos para las consultas de clima son Lluvia, Optimo, Sequia e indefinido.

### Servicios

- Cantidad de periodos para un clima recibido por parametro, Climas parametrizados {Lluvia, Optimo, Sequia, indefinido}

    URL: https://clima-sistema-solar.herokuapp.com/api/meli/cantidad_climatica?clima=Lluvia
    Respuesta: {"cantidadPeriodo":21,"clima":"Lluvia"}

- Periodos de lluvia y día del pico máximo de lluvia.

    URL: https://clima-sistema-solar.herokuapp.com/api/meli/pronostico_lluvia
    Respuesta: {"cantidadPeriodo":2400,"clima":"Lluvia","diaPicoLluvia":72,"perimetro":6262.295077113657}

- Condicion climática de un día en específico

    URL: https://clima-sistema-solar.herokuapp.com/api/meli/pronostico_dia?dia=23
    Respuesta: {"dia":23,"clima":"Lluvia","perimetro":6101.522852567894}

   
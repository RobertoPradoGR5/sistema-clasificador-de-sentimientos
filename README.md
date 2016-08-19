# Sistema-Clasificador-de-Sentimientos
El análisis de sentimiento utiliza el procesamiento de lenguaje natural, análisis de texto y lingüística computacional para identificar y extraer información subjetiva. Por ende, está relacionado con la sociología, con un enfoque en las emociones y sentimientos ya que estas emociones son a menudo parte del proceso de toma de decisiones de una persona.

# Propósito
Implementar e investigar el funcionamiento de un clasificador de sentimientos utilizando un libro del proyecto Gutenberg (http://www.gutenberg.org/)

El objetivo principal del proyecto consiste en crear un clasificador con una alta precisión “accuracy” utilizando lo que se conoce como “feature engineering”.

# Pruebas
Para realizar pruebas se utilizó los algoritmos naive Bayes o J48 utilizando el API de Weka para Java.

# Herramienta usada
 Eclipse es una plataforma de software compuesto por un conjunto de herramientas de programación de código abierto multiplataforma para desarrollar lo que el proyecto llama "Aplicaciones de Cliente Enriquecido", opuesto a las aplicaciones "Cliente-liviano" basadas en navegadores.
 
 Versión usada: Eclipse Luna Service Release 2 (4.4.2)
 
# Fases del proyecto

| Fases       | Descripción          |
| ------------- |:-------------:| 
| Clasificación      | El clasificador debe ser capaz de discernir qué tipo de información subjetiva expresa cada oración de un libro. Expresa información neutral, objetiva o factual| 
| Preprocesamiento     | Se debe convertir el archivo libro.txt en libro.arff de tal manera que se identifiquen claramente los atributos que se utilizaron para definir el modelo y la clase esté correcta.     |   
| Creación del modelo y evaluación del mismo | Una vez que se cuente con el archivo libro.arff (training dataset), se debe proceder a crear un modelo utilizando cualquier algoritmo de clasificación (Naive Bayes, árboles de decisión, redes neuronales, SVMs, etc.). En esta fase, deberán utilizar el modelo y evaluar con el algoritmo de cross-validation para determinar la precisión del mismo.      |
|Procesamiento de libro 2|Se lee un libro2.txt que contiene la información de otro libro, para que lo clasifiquen utilizando el modelo generado previamente. El resultado de esta fase debería ser un archivo llamado libro2.arff con su respectiva clasificación|
|Verificación|Se verificará la precisión de su clasificador utilizando un dataset previamente clasificado y comparando su archivo libro2.txt con el archivo de dicho libro ya clasificado.|

# Funcionamiento
## Requerimientos
* Java
* Eclipse IDE
* WEKA API para JAVA

## Archivos necesarios
* Libro en formato txt para crear el dataset de entrenamiento
* Libro en formato txt para crear el dataset sin etiquetas de clase
* Archivos en formato txt: palabras, adejetivos, sentimientos.

Una vez que se tiene todos los requerimientos se de importar el proyecto en eclipse y ejecutarlo.

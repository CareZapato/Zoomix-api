package com.zoomix.zoomix.utils;

public class Constants {
    final public static String resetColor = "ALTER SEQUENCE color_id_seq restart WITH 1";

    // PREGUNTAS PARA OPENAI

    final public static String estructura_response = "el texto de la pregunta, luego un punto y coma, finalmente el texto del color. Se deben omitir los saltos de linea de la respuesta";

    final public static String conocer = "Crea una lista de 1 pregunta para conocerse con un grupo de amigos y dame un color que defina el animo de la pregunta con la siguiente estructura:";
    
    final public static String cultural = "Crea una lista de 1 pregunta sobre cultura general para compartir con amigos";
}

package com.zoomix.zoomix.utils;

public class Constants {
    final public static String resetColor = "ALTER SEQUENCE color_id_seq restart WITH 1";

    final public static int CANTIDAD_INTENTOS_OPENAI = 3;

    // OPENAI CONFIG VALUES
    final public static String MODEL_TEXT_OPENAI = "text-davinci-003";
    final public static Long ID_JUGADOR_OPENAI = (long) 2;
    final public static Long ID_CATEGORIA_OPENAI = (long) 4;
    final public static int MAX_TOKENS = 100;
    final public static String OPENAI_KEY = "OPENAI_KEY";
    final public static String ESTRUCTURA_RESPONSE = "ESTRUCTURA_RESPONSE";

    // RUTAS OPENAI

    final public static String OPENAI_PREGUNTAS_API_URL = "https://api.openai.com/v1/completions";

    // PREGUNTAS PARA OPENAI

    //final public static String estructura_response = "Primero un punto y coma, luego el texto de la pregunta, luego un punto y coma, el nombre del color en ingles, luego punto y coma";
    final public static String CONOCER = "Crea una lista de 1 pregunta para conocerse con un grupo de amigos y dame un color que defina el animo de la pregunta con la siguiente estructura:";
    
    final public static String CULTURAL = "Crea una lista de 1 pregunta sobre cultura general para compartir con amigos";
}

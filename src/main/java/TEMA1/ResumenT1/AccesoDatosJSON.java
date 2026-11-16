package TEMA1.ResumenT1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/*
 * =====================================================================
 *  RESUMEN: LECTURA Y ESCRITURA DE FICHEROS JSON (con la librería Gson)
 * =====================================================================
 *
 *  AÑADIR GSON EN ECLIPSE (hace falta para que compile)
 *  - Descargar gson-2.10.1.jar (Maven Central: com.google.code.gson:gson).
 *  - Clic derecho en el proyecto > Build Path > Configure Build Path >
 *    Libraries > Classpath > Add External JARs... > elegir el .jar > Apply.
 *  - Si el proyecto es Maven, en el pom.xml:
 *        <dependency>
 *            <groupId>com.google.code.gson</groupId>
 *            <artifactId>gson</artifactId>
 *            <version>2.10.1</version>
 *        </dependency>
 *  - Si el proyecto tiene module-info.java hay que añadir: requires com.google.gson;
 *
 *  QUÉ ES JSON
 *  - Texto con pares "clave": valor. Objeto = { ... }, lista = [ ... ].
 *  - Valores: texto "..." (comillas dobles), números, true/false, null,
 *    objetos anidados y listas.
 *
 *  CLASES Y MÉTODOS DE GSON
 *  - Gson gson = new Gson();                                  (compacto)
 *  - Gson gson = new GsonBuilder().setPrettyPrinting().create();  (con sangrías)
 *  - gson.toJson(objeto)              -> String JSON
 *  - gson.toJson(objeto, writer)      -> escribe directamente en un fichero
 *  - gson.fromJson(String, Clase)     -> JSON en texto -> objeto
 *  - gson.fromJson(reader, Clase)     -> fichero -> objeto
 *  - Para LISTAS hay que usar TypeToken (por el borrado de tipos de Java):
 *        Type tipo = new TypeToken<List<Persona>>(){}.getType();
 *        List<Persona> lista = gson.fromJson(reader, tipo);
 *
 *  IDEAS CLAVE
 *  - Los nombres de los atributos de la clase = claves del JSON.
 *  - La clase debe ser static si es anidada (o estar en su propio fichero).
 *  - @SerializedName("otra_clave")  -> cambia el nombre de la clave en el JSON.
 *  - transient                      -> el atributo NO se guarda en el JSON.
 *  - Si el JSON está mal formado, fromJson lanza JsonSyntaxException
 *    (es unchecked, pero conviene capturarla).
 *  - Sin clase modelo se puede navegar el árbol: JsonParser.parseString(json)
 *    -> JsonObject / JsonArray (get("clave").getAsString(), getAsInt()...).
 *  - Usar UTF-8 (Files.newBufferedReader / newBufferedWriter con StandardCharsets.UTF_8).
 *
 *  ALTERNATIVAS (mismo esquema, otra API)
 *  - Jackson:
 *        ObjectMapper om = new ObjectMapper();
 *        om.writerWithDefaultPrettyPrinter().writeValue(new File("f.json"), lista);
 *        List<Persona> l = om.readValue(new File("f.json"), new TypeReference<List<Persona>>(){});
 *    (necesita getters/setters y constructor vacío en la clase).
 *  - org.json:
 *        JSONObject o = new JSONObject(textoJson);  o.getString("nombre");
 *        JSONArray a = new JSONArray(textoJson);    a.getJSONObject(0);
 */
public class AccesoDatosJSON {

    static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // ---------------------------------------------------------------
    // Clase modelo (POJO)
    // ---------------------------------------------------------------
    static class Persona {
        private String nombre;
        private int edad;
        private String ciudad;
        private List<String> aficiones = new ArrayList<>();   // lista dentro del objeto

        public Persona() { }                                  // constructor vacío (recomendado)

        public Persona(String nombre, int edad, String ciudad) {
            this.nombre = nombre;
            this.edad = edad;
            this.ciudad = ciudad;
        }

        public Persona(String nombre, int edad, String ciudad, List<String> aficiones) {
            this(nombre, edad, ciudad);
            this.aficiones = aficiones;
        }

        public String getNombre() { return nombre; }
        public int getEdad() { return edad; }
        public String getCiudad() { return ciudad; }
        public List<String> getAficiones() { return aficiones; }

        @Override
        public String toString() {
            return nombre + " (" + edad + ") - " + ciudad + " " + aficiones;
        }
    }

    // ---------------------------------------------------------------
    // 1) UN OBJETO <-> FICHERO JSON
    // ---------------------------------------------------------------
    static void escribirObjeto(String ruta, Persona p) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(ruta), StandardCharsets.UTF_8)) {
            GSON.toJson(p, bw);                       // objeto -> fichero
        } catch (IOException e) {
            System.out.println("Error al escribir el JSON: " + e.getMessage());
        }
    }

    static Persona leerObjeto(String ruta) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(ruta), StandardCharsets.UTF_8)) {
            return GSON.fromJson(br, Persona.class);  // fichero -> objeto
        } catch (IOException e) {
            System.out.println("Error al leer el JSON: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.out.println("JSON mal formado: " + e.getMessage());
        }
        return null;
    }

    // ---------------------------------------------------------------
    // 2) UNA LISTA DE OBJETOS <-> FICHERO JSON (con TypeToken)
    // ---------------------------------------------------------------
    static void escribirLista(String ruta, List<Persona> personas) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(ruta), StandardCharsets.UTF_8)) {
            GSON.toJson(personas, bw);
        } catch (IOException e) {
            System.out.println("Error al escribir la lista: " + e.getMessage());
        }
    }

    static List<Persona> leerLista(String ruta) {
        Type tipoLista = new TypeToken<List<Persona>>() { }.getType();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(ruta), StandardCharsets.UTF_8)) {
            List<Persona> lista = GSON.fromJson(br, tipoLista);
            return lista != null ? lista : new ArrayList<Persona>();   // fichero vacío -> null
        } catch (IOException e) {
            System.out.println("Error al leer la lista: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.out.println("JSON mal formado: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // ---------------------------------------------------------------
    // 3) JSON como String (sin fichero)
    // ---------------------------------------------------------------
    static void conversionesConString() {
        Persona p = new Persona("Ana", 21, "Madrid", Arrays.asList("cine", "ajedrez"));

        String json = new Gson().toJson(p);                 // objeto -> String (una línea)
        System.out.println(json);

        Persona copia = new Gson().fromJson(json, Persona.class);   // String -> objeto
        System.out.println(copia);
    }

    // ---------------------------------------------------------------
    // 4) NAVEGAR EL JSON SIN CLASE MODELO (JsonObject / JsonArray)
    //    (JsonParser.parseString existe desde Gson 2.8.6)
    // ---------------------------------------------------------------
    static void leerSinClase(String ruta) throws IOException {
        String texto = new String(Files.readAllBytes(Paths.get(ruta)), StandardCharsets.UTF_8);
        JsonArray array = JsonParser.parseString(texto).getAsJsonArray();
        for (int i = 0; i < array.size(); i++) {
            JsonObject o = array.get(i).getAsJsonObject();
            System.out.println("Árbol -> " + o.get("nombre").getAsString()
                    + " tiene " + o.get("edad").getAsInt() + " años");
        }
    }

    // ---------------------------------------------------------------
    // 5) CONVERSIONES CSV <-> JSON (ejercicio típico de examen)
    //    El CSV tiene el formato: nombre;edad;ciudad  (con cabecera)
    // ---------------------------------------------------------------
    static void csvAJson(String rutaCsv, String rutaJson) throws IOException {
        List<String> lineas = Files.readAllLines(Paths.get(rutaCsv), StandardCharsets.UTF_8);
        List<Persona> personas = new ArrayList<>();
        for (int i = 1; i < lineas.size(); i++) {            // saltamos la cabecera
            if (lineas.get(i).trim().isEmpty()) {
                continue;
            }
            String[] c = lineas.get(i).split(";", -1);
            personas.add(new Persona(c[0].trim(), Integer.parseInt(c[1].trim()), c[2].trim()));
        }
        escribirLista(rutaJson, personas);
    }

    static void jsonACsv(String rutaJson, String rutaCsv) throws IOException {
        List<Persona> personas = leerLista(rutaJson);
        List<String> lineas = new ArrayList<>();
        lineas.add("nombre;edad;ciudad");
        for (Persona p : personas) {
            lineas.add(p.getNombre() + ";" + p.getEdad() + ";" + p.getCiudad());
        }
        Files.write(Paths.get(rutaCsv), lineas, StandardCharsets.UTF_8);
    }

    // ---------------------------------------------------------------
    // PRUEBA
    // ---------------------------------------------------------------
    public static void main(String[] args) throws IOException {
        // Un objeto
        escribirObjeto("persona.json",
                new Persona("Ana", 21, "Madrid", Arrays.asList("cine", "ajedrez")));
        System.out.println("Objeto leído: " + leerObjeto("persona.json"));

        // Una lista
        List<Persona> personas = new ArrayList<>(Arrays.asList(
                new Persona("Ana", 21, "Madrid", Arrays.asList("cine", "ajedrez")),
                new Persona("Luis", 34, "Valencia"),
                new Persona("María", 28, "Bilbao", Arrays.asList("senderismo"))));
        escribirLista("personas.json", personas);
        for (Persona p : leerLista("personas.json")) {
            System.out.println("Lista leída: " + p);
        }

        conversionesConString();
        leerSinClase("personas.json");

        // CSV <-> JSON (creamos un CSV de prueba)
        Files.write(Paths.get("datos.csv"),
                Arrays.asList("nombre;edad;ciudad", "Pablo;19;Cádiz", "Lucía;25;Murcia"),
                StandardCharsets.UTF_8);
        csvAJson("datos.csv", "datos.json");
        jsonACsv("datos.json", "datos_convertido.csv");
        System.out.println("Conversiones CSV <-> JSON hechas");
    }
}

package console;

import config.Config;
import generator.IGenerator;
import generator.SimpleGenerator;
import sun.java2d.pipe.SpanShapeRenderer;

import java.io.IOException;
import java.util.Date;

public class Console {

    public static final IGenerator generator = new SimpleGenerator();

    public static void start(String...args) throws IOException {

        System.out.println("[INFORMACIÓN]: Inicializado el sistema de generación, se leerá el archivo generator.ini");
        System.out.println("[ACCIÓN]: Leyendo el archivo generator.ini");

        Config.load(args);

        System.out.println("[ACCIÓN]: Se ha cargado y se ejecutará con [threads=" + Config.VALUES.get("number_of_threads") + ", bees=" + Config.VALUES.get("number_of_bees") + "].");
        System.out.println("[ACCIÓN]: Generando archivo " + Config.OUTPUT.get("path") + "...");

        Date date = makeDate();
        generator.make(false);

        System.out.println("[INFORMACIÓN]: Se ha generado el archivo en " + (makeDate().getTime() - date.getTime()) + " ms.");

    }

    public static Date makeDate() {
        return new Date();
    }

}
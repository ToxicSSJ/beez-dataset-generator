package config;

import org.ini4j.Ini;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Config {

    public static final String FILENAME = "generator.ini";

    public static final Map<String, Boolean> EXTRAS = new HashMap<String, Boolean>();
    public static final Map<String, Double>  OFFSET = new HashMap<String, Double>();
    public static final Map<String, String>  OUTPUT = new HashMap<String, String>();
    public static final Map<String, Integer> VALUES = new HashMap<String, Integer>();

    public static void load(String...args) throws IOException {

        String filename = FILENAME;
        Ini ini = new Ini(new FileReader(filename));

        for(String key : ini.get("location_offset").keySet())
            OFFSET.put(key, Double.valueOf(ini.get("location_offset").fetch(key)));

        for(String key : ini.get("experimental").keySet())
            VALUES.put(key, Integer.valueOf(ini.get("experimental").fetch(key)));

        for(String key : ini.get("extras").keySet())
            EXTRAS.put(key, Boolean.valueOf(ini.get("extras").fetch(key)));

        for(String key : ini.get("output").keySet())
            OUTPUT.put(key, String.valueOf(ini.get("output").fetch(key)));

    }

}

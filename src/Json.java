import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Json {

    public static void appendToFile(String path, Object o)throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
        gson.toJson(o, writer);
        writer.close();
    }
}

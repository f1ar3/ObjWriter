package ru.vsu.cs.zagorodnev_g_a;

import ru.vsu.cs.zagorodnev_g_a.Model.Model;
import ru.vsu.cs.zagorodnev_g_a.ObjReader.ObjReader;
import ru.vsu.cs.zagorodnev_g_a.ObjReader.ObjReaderException;
import ru.vsu.cs.zagorodnev_g_a.ObjWriter.ObjWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.vsu.cs.zagorodnev_g_a.ObjWriter.ObjWriter.write;

public class Main {
    public static void main(String[] args) throws IOException {
        Path fileName = Path.of("/Users/goshaapolonov/IdeaProjects/ObjWriter/src/ru/vsu/cs/zagorodnev_g_a/Model.obj");
        String fileContent = Files.readString(fileName);

        System.out.println("Loading model ...");
        Model model = ObjReader.read(fileContent);

        System.out.println("Vertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals: " + model.normals.size());
        System.out.println("Polygons: " + model.polygons.size());

        String fileNameOut = "/Users/goshaapolonov/Downloads/output.obj";
        ObjWriter.write(fileNameOut, model);

        System.out.println("Model has been written to " + fileNameOut);

        Path fileName2 = Path.of("/Users/goshaapolonov/IdeaProjects/ObjWriter/src/ru/vsu/cs/zagorodnev_g_a/Model.obj");
        String fileContent2 = Files.readString(fileName2);

        System.out.println("Loading model ...");
        Model model2 = ObjReader.read(fileContent2);

        System.out.println("Vertices: " + model2.vertices.size());
        System.out.println("Texture vertices: " + model2.textureVertices.size());
        System.out.println("Normals: " + model2.normals.size());
        System.out.println("Polygons: " + model2.polygons.size());

        String fileNameOut2 = "/Users/goshaapolonov/Downloads/output2.obj";
        ObjWriter.write(fileNameOut2, model2);

        System.out.println("Model has been written to " + fileNameOut2);
    }
}
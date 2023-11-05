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

public class Main {
    public static void main(String[] args) {
        Model m1 = new Model();

        String fileSeparator = System.getProperty("file.separator");

        //чтение файла
        Path fileName = Path.of("E:/Projects/ru.vsu.cs.zagorodnev_g_a/ObjWriter/src/ru/vsu/cs/zagorodnev_g_a/TestModel.obj");

        Pattern pattern = Pattern.compile("[а-яё]");
        Matcher matcher = pattern.matcher(fileName.toString().toLowerCase());
        if (matcher.find()) {
            throw new ObjReaderException("Can't read file with outsider symbols in its name", 1);
        }

        try {
            String fileContent = Files.readString(fileName);
            System.out.println("Loading model ...");
            m1 = ObjReader.read(fileContent);

            System.out.println("Vertices: " + m1.getVertices().size());
            System.out.println("Texture vertices: " + m1.getTextureVertices().size());
            System.out.println("Normals: " + m1.getNormals().size());
            System.out.println("Polygons: " + m1.getPolygons().size());
        } catch (IOException exception) {
            throw new ObjReaderException("File not found", 1);
        }

        //запись файла
        String filePath = "E:" + fileSeparator + "test.obj";
        try {
            System.out.println("Создаём файл");
            ObjWriter.createObjFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File f = new File(filePath);
        try {
            System.out.println("Запись в файл");
            ObjWriter.writeToFile(m1, f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Модель сохранена в файл");
    }
}
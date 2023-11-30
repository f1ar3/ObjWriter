package ru.vsu.cs.zagorodnev_g_a.ObjWriter;

import ru.vsu.cs.zagorodnev_g_a.Model.Model;
import ru.vsu.cs.zagorodnev_g_a.Model.Polygon;
import ru.vsu.cs.zagorodnev_g_a.Model.Vector2f;
import ru.vsu.cs.zagorodnev_g_a.Model.Vector3f;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ObjWriter {

    public static void write(String fileName, Model model) {
        File file = new File(fileName);

        try {
            if (file.createNewFile()) {
                System.out.println("Файл успешно создан: " + file.getName());
            } else {
                System.out.println("Файл уже существует.");
            }
        } catch (IOException e) {
            throw new ObjWriterException(e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writeVertices(writer, model.vertices);
            writeTextureVertices(writer, model.textureVertices);
            writeNormals(writer, model.normals);
            writePolygons(writer, model.polygons);
        } catch (IOException e) {
            throw new ObjWriterException(e.getMessage());
        }
    }

    protected static void writeVertices(BufferedWriter writer, final ArrayList<Vector3f> v) throws IOException {

        for (Vector3f vector : v) {
            final String vx = String.format("%.4f", vector.x).replace(',', '.');
            final String vy = String.format("%.4f", vector.y).replace(',','.');
            final String vz = String.format("%.4f", vector.z).replace(',','.');
            writer.write("v " + vx + " " + vy + " " + vz);
            writer.newLine();
        }
    }

    protected static void writeTextureVertices(BufferedWriter writer, final ArrayList<Vector2f> vt) throws IOException {

        for (Vector2f vector : vt) {
            final String vtx = String.format("%.4f", vector.x).replace(',', '.');
            final String vty = String.format("%.4f", vector.y).replace(',', '.');
            writer.write("vt " + vtx + " " + vty);
            writer.newLine();
        }
    }

    protected static void writeNormals(BufferedWriter writer, final ArrayList<Vector3f> vn) throws IOException {

        for (Vector3f vector : vn) {
            final String vx = String.format("%.4f", vector.x).replace(',', '.');
            final String vy = String.format("%.4f", vector.y).replace(',','.');
            final String vz = String.format("%.4f", vector.z).replace(',','.');
            writer.write("vn " + vx + " " + vy + " " + vz);
            writer.newLine();
        }
    }

    protected static void writePolygons(BufferedWriter writer, final ArrayList<Polygon> p) throws IOException {

        for (Polygon pol : p){
            writer.write("f ");
            List<Integer> vertexIndices = pol.getVertexIndices();
            List<Integer> textureVertexIndices = pol.getTextureVertexIndices();
            List<Integer> normalIndices = pol.getNormalIndices();

            for (int j = 0; j < pol.getVertexIndices().size(); j++) {
                if (textureVertexIndices.isEmpty() && normalIndices.isEmpty()){
                    writer.write((vertexIndices.get(j) + 1) + " ");
                }
                if (!textureVertexIndices.isEmpty() && normalIndices.isEmpty()) {
                    writer.write((vertexIndices.get(j) + 1) + "/" + (textureVertexIndices.get(j) + 1) + " ");
                }
                if (!textureVertexIndices.isEmpty() && !normalIndices.isEmpty()) {
                     writer.write((vertexIndices.get(j) + 1) + "/" + (textureVertexIndices.get(j) + 1) + "/"
                            + (normalIndices.get(j) + 1) + " ");
                }
                if (textureVertexIndices.isEmpty() && !normalIndices.isEmpty()) {
                     writer.write((vertexIndices.get(j) + 1) + "//" + (normalIndices.get(j) + 1) + " ");
                }
            }
            writer.newLine();
        }
    }
}

package ru.vsu.cs.zagorodnev_g_a.ObjWriter;

public class ObjWriterException extends RuntimeException {
    public ObjWriterException(String errorMessage) {
        super("Error writing OBJ file:" + errorMessage);
    }


}

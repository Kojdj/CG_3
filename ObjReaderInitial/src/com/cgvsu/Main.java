package com.cgvsu;

import com.cgvsu.model.Model;
import com.cgvsu.objreader.ObjReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        Path fileName = null;

        if (args[0].equals("-i")) {
            fileName = Path.of(args[1]);
        }
        String fileContent = Files.readString(fileName);

        System.out.println("Loading model ...");
        Model model = ObjReader.read(fileContent);
        System.out.println("Before:");
        System.out.println("Vertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals: " + model.normals.size());
        System.out.println("Polygons: " + model.polygons.size());

        DeletePolygons.deletePolygons(new Integer[]{1, 2, 3, 4}, model, true);

        System.out.println();
        System.out.println("After:");
        System.out.println("Vertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals: " + model.normals.size());
        System.out.println("Polygons: " + model.polygons.size());
    }
}

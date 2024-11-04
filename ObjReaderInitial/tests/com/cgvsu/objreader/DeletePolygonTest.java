package com.cgvsu.objreader;

import com.cgvsu.DeletePolygons;
import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class DeletePolygonTest {

    @Test
    public void testCountVerticesWithout() {
        final Model model = new Model();
        model.vertices = new ArrayList<>(Arrays.asList(new Vector3f(0, 0, 0),
                new Vector3f(0, 10, 0),
                new Vector3f(0, 5, 10),
                new Vector3f(5, 5, 0)
        ));
        model.polygons = new ArrayList<>(Arrays.asList(new Polygon(), new Polygon()));
        model.polygons.get(0).setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));
        model.polygons.get(1).setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 3)));
        DeletePolygons.deletePolygons(new Integer[]{0},model, false);
        final int expectedResult = 4;
        Assertions.assertEquals(expectedResult, model.vertices.size());
    }

    @Test
    public void testCountVerticesWith() {
        final Model model = new Model();
        model.vertices = new ArrayList<>(Arrays.asList(new Vector3f(0, 0, 0),
                new Vector3f(0, 10, 0),
                new Vector3f(0, 5, 10),
                new Vector3f(5, 5, 0)
        ));
        model.polygons = new ArrayList<>(Arrays.asList(new Polygon(), new Polygon()));
        model.polygons.get(0).setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));
        model.polygons.get(1).setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 3)));
        DeletePolygons.deletePolygons(new Integer[]{0},model, true);
        final int expectedResult = 3;
        Assertions.assertEquals(expectedResult, model.vertices.size());
    }

    @Test
    public void testPointWith() {
        final Model model = new Model();
        model.vertices = new ArrayList<>(Arrays.asList(new Vector3f(0, 0, 0),
                new Vector3f(0, 10, 0),
                new Vector3f(0, 5, 10),
                new Vector3f(5, 5, 0)
        ));
        model.polygons = new ArrayList<>(Arrays.asList(new Polygon(), new Polygon()));
        model.polygons.get(0).setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));
        model.polygons.get(1).setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 3)));
        DeletePolygons.deletePolygons(new Integer[]{0},model, true);
        final Vector3f expectedResult = new Vector3f(5,5, 0);
        Assertions.assertTrue(expectedResult.equals(model.vertices.get(2)));
    }

    @Test
    public void testPointWithout() {
        final Model model = new Model();
        model.vertices = new ArrayList<>(Arrays.asList(new Vector3f(0, 0, 0),
                new Vector3f(0, 10, 0),
                new Vector3f(0, 5, 10),
                new Vector3f(5, 5, 0)
        ));
        model.polygons = new ArrayList<>(Arrays.asList(new Polygon(), new Polygon()));
        model.polygons.get(0).setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));
        model.polygons.get(1).setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 3)));
        DeletePolygons.deletePolygons(new Integer[]{0},model, false);
        final Vector3f expectedResult = new Vector3f(5,5, 0);
        Assertions.assertTrue(expectedResult.equals(model.vertices.get(3)));
    }

    @Test
    public void testErrorNumbers() {
        final Model model = new Model();
        model.vertices = new ArrayList<>(Arrays.asList(new Vector3f(0, 0, 0),
                new Vector3f(0, 10, 0),
                new Vector3f(0, 5, 10),
                new Vector3f(5, 5, 0)
        ));
        model.polygons = new ArrayList<>(Arrays.asList(new Polygon(), new Polygon()));
        model.polygons.get(0).setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));
        model.polygons.get(1).setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 3)));
        try {
            DeletePolygons.deletePolygons(new Integer[]{1, 2, 3}, model, false);
            Assertions.fail();
        } catch (Exception e) {
            final String expectedResult = "More polygons are being deleted than their number";
            Assertions.assertEquals(e.getMessage(), expectedResult);
        }
    }
}
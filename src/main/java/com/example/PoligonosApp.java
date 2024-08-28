package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.List;

public class DesenhoPoligonos extends Application {
    private final List<List<Point>> listaPontosPoligonos = List.of(
        // Definição dos polígonos
        List.of(new Point(50, 50), new Point(150, 50), new Point(150, 150), new Point(50, 150)),  // Quadrado
        List.of(new Point(200, 50), new Point(400, 50), new Point(400, 100), new Point(200, 100)), // Retângulo
        List.of(new Point(300, 250), new Point(350, 150), new Point(400, 250)),                    // Triângulo
        List.of(new Point(200, 250), new Point(250, 300), new Point(250, 350), new Point(150, 350), new Point(150, 300)),  // Pentágono
        List.of(new Point(320, 270), new Point(370, 320), new Point(370, 370), new Point(320, 420), new Point(270, 370), new Point(270, 320))  // Hexágono
    );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 800, 600);

        // Criação dos polígonos
        for (List<Point> pontos : listaPontosPoligonos) {
            Polygon polygon = new Polygon();
            pontos.forEach(p -> polygon.getPoints().addAll(p.x(), p.y()));
            polygon.setFill(Color.GREEN);
            polygon.setStroke(Color.RED);
            pane.getChildren().add(polygon);
        }

        // Criação das labels para mostrar informações
        Label labelPerimetros = criarLabel("Perímetros: " + calcularPerimetros(), 500);
        Label labelTipos = criarLabel("Tipos: " + determinarTiposPoligonos(), 530);
        pane.getChildren().addAll(labelPerimetros, labelTipos);

        primaryStage.setTitle("Desenho de Polígonos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Label criarLabel(String texto, int posY) {
        Label label = new Label(texto);
        label.setLayoutX(10);
        label.setLayoutY(posY);
        return label;
    }

    // Métodos para calcular perímetros e determinar tipos
    private List<Double> calcularPerimetros() {
        return listaPontosPoligonos.stream()
            .map(pontos -> {
                Point primeiroPonto = pontos.get(0);
                return pontos.stream()
                    .reduce(primeiroPonto, Point::new)
                    .distance();
            })
            .toList();
    }

    private List<String> determinarTiposPoligonos() {
        return listaPontosPoligonos.stream()
            .map(pontos -> switch (pontos.size()) {
                case 3 -> "Triângulo";
                case 4 -> "Quadrilátero";
                case 5 -> "Pentágono";
                case 6 -> "Hexágono";
                default -> "Polígono";
            })
            .toList();
    }
}

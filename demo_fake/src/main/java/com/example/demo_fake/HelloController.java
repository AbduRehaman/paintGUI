package com.example.demo_fake;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    @FXML
    private ColorPicker colorpicker;

    @FXML
    private TextField bsize;

    boolean toolSelected = false;

    @FXML
    private Canvas canvas;


    GraphicsContext brushTool;




    public static Image scale(Image source, int targetWidth, int targetHeight, boolean preserveRatio) {
        ImageView imageView = new ImageView(source);
        imageView.setPreserveRatio(preserveRatio);
        imageView.setFitWidth(targetWidth);
        imageView.setFitHeight(targetHeight);
        return imageView.snapshot(null, null);
    }
    private Button btn_Save;
    public void onSave(){
        try{
            Image snapshot = canvas.snapshot(null,null);
            snapshot=scale(snapshot, 28, 28, false);

            ImageIO.write(SwingFXUtils.fromFXImage(snapshot,null),"png", new File("paint.png"));

        }catch(Exception e){
            System.out.println("Failed to save image: " + e);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle rb){
        brushTool = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged( e ->  {
            double size= Double.parseDouble(bsize.getText());
            double x= e.getX() - size/2;  //gives mouse co-ordinates
            double y= e.getY() -size/2;

            if (toolSelected && !bsize.getText().isEmpty()){
                brushTool.setFill(colorpicker.getValue());
                brushTool.fillRoundRect(x, y, size ,size, size, size);

            }
        });
    }

    @FXML
    public void toolselected (ActionEvent e){
        toolSelected= true;
    }

}




package fruit.ninja;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 *
 * @author Mahmoud Etman
 */
public class FruitNinja extends Application {
    apple x = new apple();
    bomb y = new bomb();
    int score = 0;
     public class apple extends Circle {

        public double locationX;
        public double locationY;
        public double sceneWidth;
        public double sceneHeight;
        public double appleRadius;
        public double applescore = 250;
        public double speed_x = 12;
        public double speed_y = 12;

        public apple() {
            this.appleRadius = 50;
            applescore = 250;
            sceneWidth = 1024;
            sceneHeight = 600;
            locationX = Math.random() * (sceneWidth - appleRadius);
            locationY = Math.random() * (sceneHeight - appleRadius);
            setCenterX(locationX);
            setCenterY(locationY);
            setRadius(appleRadius);
            Timeline animation = new Timeline(
                    new KeyFrame(Duration.millis(35), e -> {
                        move();
                    }));
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();

        }
        
        private void move() {
            locationX += speed_x;
            locationY += speed_y;
            if (locationX > sceneWidth - appleRadius || locationX <= appleRadius) {
               // setCenterX(sceneWidth - appleRadius);
                speed_x *= -1;
            }
            if (locationY > sceneHeight - appleRadius || locationY <= appleRadius) {
                setCenterY(sceneHeight - appleRadius);
                speed_y *= -1;
            }
            setCenterX(locationX);
            setCenterY(locationY);

        }
     }
    public class bomb extends Circle {

        public double locationX;
        public double locationY;
        public double sceneWidth;
        public double sceneHeight;
        public double bombRadius;
        public double bombscore = -200;
        public double speed_x = 15;
        public double speed_y = 15;

        public bomb() {
            this.bombRadius = 50;
            bombscore = -200;
            sceneWidth = 1024;
            sceneHeight = 600;
            locationX = Math.random() * (sceneWidth - bombRadius);
            locationY = Math.random() * (sceneHeight - bombRadius);
            setCenterX(locationX);
            setCenterY(locationY);
            setRadius(bombRadius);
            Timeline animation = new Timeline(
                    new KeyFrame(Duration.millis(30), e -> {
                        move();
                    }));
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();

        }

        private void move() {
            locationX += speed_x;
            locationY += speed_y;
            if (locationX > sceneWidth - bombRadius || locationX <= bombRadius) {
                setCenterX(sceneWidth - bombRadius);
                speed_x *= -1;
            }
            if (locationY > sceneHeight - bombRadius || locationY <= bombRadius) {
                setCenterY(sceneHeight - bombRadius);
                speed_y *= -1;
            }
            setCenterX(locationX);
            setCenterY(locationY);

        }
    }    
    @Override
    public void start(Stage primaryStage) {
       Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(2000), e -> {
                    apple x=new apple();
                    x.setFill(new ImagePattern(img0));
                   //  y.setFill(new ImagePattern(img1));
                   root2.getChildren().addAll(x );
                      scene2.widthProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) -> {
            x.sceneWidth=scene2.getWidth();
            x.locationX=x.sceneWidth*Math.random()/2;  
        });
        scene2.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                x.sceneHeight=scene2.getHeight();

                x.locationY=scene2.getHeight()*Math.random()/2;    
            }
        });
          
                  x.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            score=((int) x.applescore)+score;
                            l1.setText(""+score);
                            root2.getChildren().remove(x);
                            
                        }
                    });
               
      }));
         Timeline animation1 = new Timeline(
                new KeyFrame(Duration.millis(3500), e -> {
                    bomb y = new bomb();
                     y.setFill(new ImagePattern(img1));
                   root2.getChildren().addAll(y );
                            scene2.widthProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) -> {
            y.sceneWidth=scene2.getWidth();
            y.locationX=y.sceneWidth*Math.random()/2;  
        });
        scene2.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                y.sceneHeight=scene2.getHeight();

                y.locationY=scene2.getHeight()*Math.random()/2;    
            }
        });
                 y.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            score  =((int) y.bombscore ) + score;
                            l1.setText(""+score);
                            root2.getChildren().remove(y);
                        }
                    });
            }));
      
          //apple count
        go1.setOnMousePressed(e->{
        animation.setCycleCount(11);
        l1.setText(""+(score=score-score));
        
        animation.play(); // Start animation
        // bomb count 
        animation1.setCycleCount(6);
        animation1.play();// Start animation
       // animation1.getKeyFrames().clear();
       

        
        });
        go3.setOnMousePressed(e->{
            primaryStage.setScene(scene1);
            t.setText("Your Score = "+ score);
            animation.stop();
            animation1.stop();
            root2.getChildren().removeAll(x,y);
           });
        if(score == 1000){
            x.speed_x+=10;
            x.speed_y+=10;
            y.speed_x+=1;
            y.speed_y+=1;
            
            }
        
        
    
        primaryStage.setScene(scene1);
        primaryStage.show();
        primaryStage.getIcons().add(new Image("7.png"));

    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}

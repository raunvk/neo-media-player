/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neomediaplayer;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 *
 * @author Raunak Das
 */
public class FXMLDocumentController implements Initializable 
{
    private String path;
    private MediaPlayer mediaPlayer;
    
    @FXML 
    private MediaView mediaView;
    
    @FXML
    private Slider progressBar;
    
    @FXML
    private Slider volumeBar;
    
    @FXML
    private Button playBtn;
    
    @FXML
    private Button pauseBtn;
     
    @FXML
    private Button stopBtn;
    
    @FXML
    private Button openBtn;
    
    @FXML
    private Button slowBtn;
    
    @FXML
    private Button fastBtn;
    
    @FXML
    private Button backBtn;
    
    @FXML
    private Button skipBtn;
    
    public void Open(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        path = file.toURI().toString();
        
        if (path!=null)
                {
                    Media media = new Media(path);
                    mediaPlayer = new MediaPlayer(media);
                    mediaView.setMediaPlayer(mediaPlayer);
                    
                    DoubleProperty widthProperty = mediaView.fitWidthProperty();
                    DoubleProperty heightProperty = mediaView.fitHeightProperty();
                    
                    widthProperty.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
                    heightProperty.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
                    
                    mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>()
                    {
                        @Override
                        public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue)
                        {
                            progressBar.setValue(newValue.toSeconds());       
                        }
                    });
                    
                    progressBar.setOnMousePressed(new EventHandler<MouseEvent>() 
                    {
                        @Override
                        public void handle(MouseEvent event) 
                        {
                            mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
                        }
                    });
                    
                    progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() 
                    {
                        @Override
                        public void handle(MouseEvent event) 
                        {
                            mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
                        }
                    });
                    
                    mediaPlayer.setOnReady(new Runnable() 
                    {
                        @Override
                        public void run() 
                        {
                            Duration total = media.getDuration();
                            progressBar.setMax(total.toSeconds());
                        }
                    });
                
                volumeBar.setValue(mediaPlayer.getVolume()*100);
                volumeBar.valueProperty().addListener(new InvalidationListener()
                {
                    @Override
                    public void invalidated(Observable observable) 
                    {
                        mediaPlayer.setVolume(volumeBar.getValue()/100);
                    }
                });
                
                mediaPlayer.play();
                }
    }
    
    public void Play(ActionEvent event)
    {
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }
    
    public void Pause(ActionEvent event)
    {
        mediaPlayer.pause();
    }
      
    public void Stop(ActionEvent event)
    {
        mediaPlayer.stop();
    }
      
    public void SlowRate(ActionEvent event)
    {
        mediaPlayer.setRate(0.75);
    }
    
    public void FastRate(ActionEvent event)
    {
        mediaPlayer.setRate(1.5);
    }
    
    public void Back10(ActionEvent event)
    {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(-10)));
    }
    
    public void Skip10(ActionEvent event)
    {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(10)));
    }
    
      
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try
        {
            playBtn.setGraphic(new ImageView(new javafx.scene.image.Image(new FileInputStream("src/neomediaplayer/play.png"))));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            pauseBtn.setGraphic(new ImageView(new javafx.scene.image.Image(new FileInputStream("src/neomediaplayer/pause.png"))));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            stopBtn.setGraphic(new ImageView(new javafx.scene.image.Image(new FileInputStream("src/neomediaplayer/stop.png"))));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            openBtn.setGraphic(new ImageView(new javafx.scene.image.Image(new FileInputStream("src/neomediaplayer/open1.png"))));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            slowBtn.setGraphic(new ImageView(new javafx.scene.image.Image(new FileInputStream("src/neomediaplayer/slow.png"))));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
         
        try
        {
            fastBtn.setGraphic(new ImageView(new javafx.scene.image.Image(new FileInputStream("src/neomediaplayer/fast.png"))));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            skipBtn.setGraphic(new ImageView(new javafx.scene.image.Image(new FileInputStream("src/neomediaplayer/skip.png"))));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
         
        try
        {
            backBtn.setGraphic(new ImageView(new javafx.scene.image.Image(new FileInputStream("src/neomediaplayer/back.png"))));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
}
   

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafxapplication2;



import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;



/**
 *
 * @author amr
 */
public class JavaFXApplication2 extends Application {
    BorderPane pane;
    TextArea area;
    String sampleText;
    File file2;
    MenuItem f_Item2;
    MenuItem f_Item3;
    @Override
    public void init() throws Exception{
        super.init(); 
        
        area  = new TextArea();
        
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu help = new Menu("Help");
        SeparatorMenuItem sep = new SeparatorMenuItem();
        SeparatorMenuItem sep2 = new SeparatorMenuItem();
        SeparatorMenuItem sep3 = new SeparatorMenuItem();
        
        
        //File Button
        MenuItem f_Item1 = new MenuItem("New");
        f_Item2 = new MenuItem("Open...");
        f_Item2.setAccelerator(KeyCombination.keyCombination("Ctrl + o"));
        f_Item3 = new MenuItem("Save");
        f_Item3.setAccelerator(KeyCombination.keyCombination("Ctrl + s"));
        MenuItem f_Item4 = new MenuItem("Exit");
        file.getItems().addAll(f_Item1, f_Item2, f_Item3, f_Item4);
        file.getItems().add(3, sep);
        
        menuBar.getMenus().addAll(file);
        
        //Edit Button
        MenuItem e_Item1 = new MenuItem("Undo");
        MenuItem e_Item2 = new MenuItem("Cut");
        MenuItem e_Item3 = new MenuItem("Copy");
        MenuItem e_Item4 = new MenuItem("Paste");
        MenuItem e_Item5 = new MenuItem("Delete");
        MenuItem e_Item6 = new MenuItem("SelectAll");
        edit.getItems().addAll(e_Item1, e_Item2, e_Item3, e_Item4,e_Item5,e_Item6);
        edit.getItems().add(1, sep2);
        edit.getItems().add(6, sep3);
        menuBar.getMenus().addAll(edit);
        
        //Help Button
        MenuItem h_Item1 = new MenuItem("About");
        help.getItems().addAll(h_Item1);
        menuBar.getMenus().addAll(help);
        
        
        
        /*Events*/
        
        //File items
        f_Item4.addEventHandler(ActionEvent.ACTION,(e)->{
           Platform.exit();
        });
   
        
        //Edit items
        e_Item1.addEventHandler(ActionEvent.ACTION,(e)->{
           area.undo();
        });
         
        e_Item2.addEventHandler(ActionEvent.ACTION,(e)->{
            area.cut();;
        });
        
        e_Item3.addEventHandler(ActionEvent.ACTION,(e)->{
           area.copy();
        });
           
        e_Item4.addEventHandler(ActionEvent.ACTION,(e)->{
           area.paste();
        });
        e_Item5.addEventHandler(ActionEvent.ACTION,(e)->{
           area.deleteText(area.getSelection());
        });
        e_Item6.addEventHandler(ActionEvent.ACTION,(e)->{
           area.selectAll();
        });
        
        //About
         h_Item1.addEventHandler(ActionEvent.ACTION,(e)->{
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("About Notepad");
                alert.setHeaderText("Notepad");
                String s ="Author: Amr Salah © ";
                alert.setContentText(s);
                alert.show();
        });
        
        pane = new BorderPane();
        pane.setTop(menuBar);
        pane.setCenter(area);
        
      
    sampleText = area.getText();
    }
    
    @Override
    public void start(Stage primaryStage) {
       
 
        //File chooser open      
         f_Item2.addEventHandler(ActionEvent.ACTION,(e)->{
            FileChooser fileChooser = new FileChooser();

            // Set extension filter, only PDF files will be shown
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            // Show open file dialog
            File file = fileChooser.showOpenDialog(primaryStage);
            
              try(Scanner input = new Scanner(file)) {
                    while (input.hasNextLine()) {
                    area.appendText(input.nextLine());
                }
                } catch (IOException m) {
                    m.printStackTrace();
                }

        });

        
        //File chooser Save
          f_Item3.addEventHandler(ActionEvent.ACTION,(e)->{
            FileChooser fileChooser = new FileChooser();
 
            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
 
            //Show save file dialog
            File file = fileChooser.showSaveDialog(primaryStage);
 
            if (file != null) {
                sampleText = area.getText();
                saveTextToFile(sampleText, file);
            }
            
        });
        
           
        Scene scene = new Scene(pane, 700, 450);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        
        primaryStage.setTitle("Text Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
        private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            
        }
    }

    /**
     * @param args the command line arguments
     */
    
    
}

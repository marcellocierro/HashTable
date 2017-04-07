package sample;


/**
 * Created by Marcello395 on 2/12/17.
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Controller extends Application {


    //all objects which are referenced in gui.
    Stage window;
    Scene scene;
    Button button;
    ComboBox<Card> comboBox;
    DataGet getHearth = new DataGet();
    HashTable hsTable = new HashTable();
    ListView listView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("HearthStone 365");
        button = new Button("Compare!");

        //grabs data from JSON array
        ArrayList<Card> grab = getHearth.grabber();

        //populates hashtable
        for(Card c : grab){
            hsTable.putEntry(c.getName(), c.getValue());
        }

        //combobox which holds all cards for selection
        comboBox = new ComboBox<>(FXCollections.observableArrayList());
        ObservableList<Card> list = FXCollections.observableArrayList(grab);
        comboBox.setItems(list);
        comboBox.setPromptText("Please select a card");
        //ComboBoxes also generate actions if you need to get value instantly
        comboBox.setOnAction(e -> System.out.println("User selected " + comboBox.getValue()));

        listView = new ListView<>();
        listView.getItems().addAll();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        //calls the similarity metric
       // button.setOnAction(e -> getDistance(comboBox.getValue().getName()));
        button.setOnAction(e -> buttonPress());

        //layout stuff below
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(comboBox, button, listView);
        scene = new Scene(layout, 700, 550);
        window.setScene(scene);
        window.show();
    }

    //debug method
    private void buttonPress() {
        ArrayList test1 = getDistance(comboBox.getValue().getName());
        ObservableList test2 = FXCollections.observableArrayList(test1);
        listView.setItems(test2);
    }

    //Euclidean distance sim metric.
    /*
    Takes key parameter which has a value pulled and stored in to x.
    Bound will take care of printing only similar things
    Indexes through entire HashTable to get all key's values;
    Performs Euclidean distance formula
    prints out key and distance, only for cards who's 'distance' is less than the bound
    Could make bound 0 to only print out exact matches
     */
    public ArrayList getDistance(String key) {
        double bound = 0.0;
        double x = hsTable.getValue(key);
        ArrayList distanceList = new ArrayList();
        for(int i = 0; i < hsTable.tableSize; i++){
            if(hsTable.entrys[i] != null) {
                double distance = Math.sqrt(Math.pow(x - hsTable.entrys[i].getValue(), 2));
                //System.out.println(i + " " + hsTable.entrys[i]);
                if (distance == bound) {
                    distanceList.add(hsTable.entrys[i].getKey() /*+ " " + distance*/);
                }
            }
        }
        return distanceList;
    }
}

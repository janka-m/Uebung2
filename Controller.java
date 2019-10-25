import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

class Controller implements Initializable {

    //---------------------------------------------
    // Membervariablen für Steuerelemente
    //---------------------------------------------        
    @FXML
    private TextField textFieldEingabe;

    @FXML
    private Button buttonDruecken;

    @FXML
    private RadioButton rdbCsv;

    @FXML
    private ToggleGroup output;

    @FXML
    private RadioButton rdbTxt;

    @FXML
    private Slider slrFarbe;

    @FXML
    private Label labelAusgabe;

    @FXML
    private RadioButton rdbMene;

    @FXML
    private ToggleGroup eneMene;

    @FXML
    private RadioButton rdbEne;

    @FXML
    private RadioButton rdbMu;

    @FXML
    private RadioButton rdbMiste;

    private PrintWriter writer;
    private HashMap<String, Integer> mapEneMene;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //---------------------------------------------
        // Eventhandler
        //---------------------------------------------    
        buttonDruecken.setOnAction(event -> {
            String eingabe = textFieldEingabe.getText();
            Double slrWert = slrFarbe.getValue();
            Integer zahl = 0;
            switch (getSelectedRadioBtnEneMene()) {
                case "ene":
                    zahl = mapEneMene.get("ene");
                    zahl++;
                    mapEneMene.put("ene", zahl);
                    break;
                case "mene":
                    zahl = mapEneMene.get("mene");
                    zahl++;
                    mapEneMene.put("mene", zahl);
                    break;
                case "miste":
                    zahl = mapEneMene.get("miste");
                    zahl++;
                    mapEneMene.put("miste", zahl);
                    break;
                case "mu":
                    zahl = mapEneMene.get("mu");
                    zahl++;
                    mapEneMene.put("mu", zahl);
                    break;
            }
            String ausgabe = eingabe + " " + slrWert.intValue() + "\nEne: " + mapEneMene.get("ene") + " - Mene: " + mapEneMene.get("mene") + " - Miste: " + mapEneMene.get("miste") + " - Mu: " + mapEneMene.get("mu");
            labelAusgabe.setText(ausgabe);

            switch (getSelectedRadioBtnOutput()) {
                case "txt":
                    writeTxt(ausgabe);
                    break;
                case "csv":
                    writeCSV(ausgabe);
                    break;
            }

        });
        //---------------------------------------------
        // Start
        //---------------------------------------------
        // Code wird ausgeführt,
        // wenn Fenster und Steuerelemente bereit sind
        mapEneMene = new HashMap<String, Integer>();
        mapEneMene.put("ene", 0);
        mapEneMene.put("mene", 0);
        mapEneMene.put("miste", 0);
        mapEneMene.put("mu", 0);
    }


    private void writeTxt(String string) {
        try {
            writer = new PrintWriter(new File("TXT_Ausgabe.txt"));
            writer.print(string);
            System.out.println("Done");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeCSV(String string) {
        try {
            writer = new PrintWriter(new File("CSV_Ausgabe.csv"));
            writer.print(string);
            System.out.println("Done");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getSelectedRadioBtnOutput() {
        String text;
        if (rdbCsv.isSelected()) {
            text = "csv";
        } else if (rdbTxt.isSelected()) {
            text = "txt";
        } else {
            text = "No Select";
        }
        return text;
    }

    public String getSelectedRadioBtnEneMene() {
        String text;
        if (rdbEne.isSelected()) {
            text = "ene";
        } else if (rdbMene.isSelected()) {
            text = "mene";
        } else if (rdbMiste.isSelected()) {
            text = "miste";
        } else if (rdbMu.isSelected()) {
            text = "mu";
        } else {
            text = "No Select";
        }
        return text;
    }

}


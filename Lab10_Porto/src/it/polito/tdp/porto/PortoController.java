/**
 * Sample Skeleton for 'Porto.fxml' Controller Class
 */

package it.polito.tdp.porto;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import it.polito.tdp.porto.model.Paper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxPrimo"
    private ComboBox<Author> boxPrimo; // Value injected by FXMLLoader

    @FXML // fx:id="boxSecondo"
    private ComboBox<Author> boxSecondo; // Value injected by FXMLLoader

    @FXML // fx:id="btnSequenza"
    private Button btnSequenza; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleCoautori(ActionEvent event) {
    	this.boxSecondo.getItems().clear();
    	this.txtResult.clear();
    	try {
    		Author author = this.boxPrimo.getValue();
    		for(Author a : model.trovaCoAutori(author)) {
    			txtResult.appendText(a+"\n");
    		}
    		this.boxSecondo.setDisable(false);
    		this.btnSequenza.setDisable(false);
    		this.boxSecondo.getItems().addAll(model.trovaAutoriCorrelati(author));
    	}
    	catch(Exception e) {
    		txtResult.appendText("Assicurati di aver selezionato un prof!\n");
    	}
    }

    @FXML
    void handleSequenza(ActionEvent event) {
    	this.txtResult.clear();
    	try {
    		Author author1 = this.boxPrimo.getValue();
    		Author author2 = this.boxSecondo.getValue();
    		for(Paper p : model.papersShortestPath(author1, author2)) {
    			txtResult.appendText(p+"\n");
    		}
    	}
    	catch(Exception e) {
    		txtResult.appendText("Assicurati di aver selezionato due prof!\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnSequenza != null : "fx:id=\"btnSequenza\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";

    }

	public void setModel(Model model) {
		this.model=model;
        this.boxPrimo.getItems().setAll(model.getAuthors());
	}
}


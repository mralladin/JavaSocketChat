package ChatApp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class Dialog {

	
	private static String[] data;
	private static TextField ipp;
	private static TextField name;
	private static TextField port;
	
	public static String[] getip(){
		data=null;
		
		Stage stage= new Stage();
		VBox vbox = new VBox();
		HBox hbox= new HBox();
		 ipp= new TextField();
		 name= new TextField();
		 port= new TextField();
		 name.setPromptText("Name");
		 ipp.setPromptText("IP-ADRESSE");
		 port.setPromptText("PORT");
		Button ok= new Button("OK");
		Button notok= new Button("Cancel");
Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				notok.requestFocus();
				
			}
		});
		ok.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(ipp.getText().matches("[0-9]+.[0-9]+.[0-9]+.[0-9]+")&&port.getText().matches("[0-9]+")){
					data= new String[3];
					data[0]=ipp.getText();
					data[1]=name.getText();
					data[2]=port.getText();
					stage.close();
				}
				else{
					ipp.clear();
					ipp.setPromptText("Ung�ltige IP");
				}
				
			}
		});
		notok.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				stage.close();
				
			}
		});
		vbox.setPadding(new Insets(22));
		
		vbox.getChildren().addAll(name,ipp,port,hbox);
		hbox.getChildren().addAll(ok,notok);
		hbox.setSpacing(35);
		ipp.setFont(Font.font("ARIAL",FontWeight.BOLD, 19));
		name.setFont(Font.font("ARIAL",FontWeight.BOLD, 19));
		port.setFont(Font.font("ARIAL",FontWeight.BOLD, 19));
		hbox.setAlignment(Pos.CENTER);
		vbox.setAlignment(Pos.CENTER);

		vbox.setSpacing(10);
		
		Scene scene= new Scene(vbox,350,200);
		scene.setFill(Color.BLANCHEDALMOND);
		stage.setTitle("IP Address to Connect");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.initStyle(StageStyle.DECORATED);
		stage.showAndWait();
		
		
		
		
		
		return data;
	}
	
	
	
	
	
	
	
	public static String[] setport(){
		data=null;
		name= new TextField();
		name.setPromptText("Name");
		Stage stage= new Stage();
		VBox vbox = new VBox();
		HBox hbox= new HBox();
		 ipp= new TextField();
		 ipp.setPromptText("Port");
		 
		Button ok= new Button("OK");
		Button notok= new Button("Cancel");
		ok.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(ipp.getText().matches("[0-9]+")){
					data= new String[2];
					data[0]=ipp.getText();
					data[1]=name.getText();
					stage.close();
				}
				else{
					ipp.clear();
					ipp.setPromptText("Ung�ltiger Port");
				}
				
			}
		});
		notok.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				stage.close();
				
			}
		});
		vbox.setPadding(new Insets(22));
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				notok.requestFocus();
				
			}
		});
		vbox.getChildren().addAll(name,ipp,hbox);
		hbox.getChildren().addAll(ok,notok);
		hbox.setSpacing(35);
		ipp.setFont(Font.font("ARIAL",FontWeight.BOLD, 19));
		name.setFont(Font.font("ARIAL",FontWeight.BOLD, 19));
		
		hbox.setAlignment(Pos.CENTER);
		vbox.setAlignment(Pos.CENTER);

		vbox.setSpacing(10);
		
		Scene scene= new Scene(vbox,350,200);
		scene.setFill(Color.BLANCHEDALMOND);
		stage.setTitle("Port for Server");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.initStyle(StageStyle.DECORATED);
		stage.showAndWait();
		
		
		
		
		
		return data;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

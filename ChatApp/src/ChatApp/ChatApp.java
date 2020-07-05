package ChatApp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChatApp extends Application {

	// SERVER
	private boolean isServer = false;
	private NetworkConnection connection;
	private String ipaddress;
	private String serverport;

	// GUI SHIT
	private Scene scene2;
	private TextArea messages;
	private Button exit;
	private TextField input;
	private GridPane pane;
	private String name = "Anonymous";
	private Label bezeichnung;
	private Label ip;
	private TextField ip3;
	private Label ipl;
	private TextField ip3l;
	private Label portl;
	private TextField portnr;

	@Override
	public void start(Stage stage) throws Exception {

		// IP ADRESSE BEKOMMEN#
		ipl = new Label();
		ip3l = new TextField();
		ipl.setText("Lokale IP Adresse: ");
		ip3l.setText(InetAddress.getLocalHost().getHostAddress());
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));

		String ip2 = in.readLine(); // you get the IP as a String
		//
		// ALLGEMEINES GUI LAYOUT
		HBox root = new HBox();
		Button host = new Button("HOST");
		Button client = new Button("CLIENT");
		stage.setTitle("CHAT PROGRAMM by PHILIPP L.");
		Stage app = new Stage();
		bezeichnung = new Label();
		ip = new Label();
		pane = new GridPane();
		scene2 = new Scene(pane, 500, 600);
		exit = new Button("EXIT");
		messages = new TextArea();
		messages.setPrefHeight(400);
		messages.setEditable(false);
		input = new TextField();
		input.setPromptText("NACHRICHT eingeben");
		ip3 = new TextField();
		portl = new Label();
		TextField nametf = new TextField();
		nametf.setPromptText("Name");
		
		portnr = new TextField();
		portnr.setEditable(false);
		ip3.setEditable(false);
		nametf.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		ip3.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		portnr.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		portl.setFont(Font.font(27));
		app.setScene(scene2);
		exit.setOnAction(e -> {
			System.exit(0);
		});

		GridPane.setHalignment(exit, HPos.RIGHT);
		GridPane.setHalignment(bezeichnung, HPos.CENTER);
		GridPane.setMargin(exit, new Insets(0, 0, 0, -50));
		GridPane.setHalignment(ip3, HPos.CENTER);
		GridPane.setMargin(ip3, new Insets(0, 0, 0, -300));
		GridPane.setHalignment(ip3l, HPos.CENTER);
		GridPane.setMargin(ip3l, new Insets(0, 0, 0, -300));

		GridPane.setHalignment(portnr, HPos.CENTER);
		GridPane.setMargin(portnr, new Insets(0, 0, 0, -300));

		bezeichnung.setFont(Font.font(28));
		ip.setFont(Font.font(28));
		ip3.setPrefWidth(100);
		ip3.setMinWidth(20);
		pane.setPadding(new Insets(8));

		// >>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		client.setOnAction((e) -> {
			// CLIENT BEGIN
			
			String[] data = Dialog.getip();
			
			
			
			if (data != null) {
				ipaddress= data[0];
				serverport=data[2];
				
				if(data[1].length()>2){
					name= data[1];
						
				}
				
				// CLIENT LAYOUT
				isServer = false;
				connection = createClient();
				try {
					connection.startConnection();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				ip3.setText(ip2);
				pane.setVgap(12);
				ip.setText("IP ADRESSE:");
				bezeichnung.setText("CLIENT");
				pane.add(bezeichnung, 0, 0);
				pane.add(exit, 2, 0);
				pane.add(ip, 0, 1, 1, 1);
				pane.add(ip3, 1, 1, 1, 1);
				pane.add(ipl, 0, 2, 1, 1);
				pane.add(ip3l, 1, 2, 1, 1);
				pane.add(messages, 0, 3);
				pane.add(input, 0, 4);
				// >>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
				// LOGIC CLIENT

				input.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						String message = input.getText();
						input.clear();
						messages.appendText(name+": " + message + "\n");
						try {
							connection.send(name+": "+message);
						} catch (Exception e) {
							messages.appendText("Nachricht konnte nicht gesendet werden :( \n");
						}

					}
				});

				// >>>>>>><<<<<<<<<<<

				app.initModality(Modality.APPLICATION_MODAL);
				app.showAndWait();
			}

		});

		host.setOnAction((e) -> {
			// SERVER LAYOUT

			String[] data = Dialog.setport();
			
			if (data != null) {
				isServer = true;
				serverport=data[0];
				name=data[1];
				connection = createServer();
				try {
					connection.startConnection();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				ip3.setText(ip2);
				pane.setVgap(12);
				portl.setText("ServerPort: ");
				
				portnr.setText(serverport);
				if(data[1].length()>2){
					name= data[1];
						
				}
				
				ip.setText("IP ADRESSE:");
				bezeichnung.setText("HOST");
				pane.add(bezeichnung, 0, 0);
				pane.add(exit, 2, 0);
				pane.add(ip, 0, 1, 1, 1);
				pane.add(ip3, 1, 1, 1, 1);
				pane.add(ipl, 0, 2, 1, 1);
				pane.add(ip3l, 1, 2, 1, 1);
				pane.add(portl, 0, 3, 1, 1);
				pane.add(portnr, 1, 3, 1, 1);
				pane.add(messages, 0, 4);
				pane.add(input, 0, 5);
				// >>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
				// SERVER LOGIC

				input.setOnAction((ep) -> {
					String message = input.getText();
					input.clear();
					messages.appendText(name+": " + message + "\n");
					try {
						connection.send(name+": "+message);
					} catch (Exception ef) {
						messages.appendText("Nachricht konnte nicht gesendet werden :( \n");
					}

				});

				app.initModality(Modality.APPLICATION_MODAL);
				app.show();

				//

			}
		});

		root.getChildren().addAll(host, client);
		root.setSpacing(110);
		root.setPadding(new Insets(0));
		root.setAlignment(Pos.CENTER);

		Scene scene = new Scene(root, 500, 200);
		stage.setScene(scene);
		stage.show();

	}

	public NetworkServer createServer() {
		
		return new NetworkServer(Integer.parseInt(serverport), data -> {

			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					messages.appendText(data.toString() + "\n");

				}
			});

		});
	}

	private NetworkClient createClient() {
		
		return new NetworkClient(ipaddress, Integer.parseInt(serverport), data -> {

			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					messages.appendText(data.toString() + "\n");

				}
			});

		});

	}

	public static void main(String[] args) {
		launch(args);

	}

}

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This program reads an input file, parses it into word tokens, count the
 * number of occurrence of each word and print them in descending order.
 */
public class HtmlAnalyzerApp extends Application {

	// Input file name
	private static final String FILE_NAME = "poem.txt";

	TableView<HtmlAnalyzer> freqTable = null;

	private ScrollPane tableScrollPane;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {

		createTable();
		tableScrollPane = new ScrollPane();
		tableScrollPane.setContent(freqTable);

		GridPane gridPane = new GridPane();

		gridPane.setMinSize(500, 500);

		gridPane.setPadding(new Insets(10, 10, 10, 10));

		gridPane.setVgap(5);
		gridPane.setHgap(5);

		gridPane.setAlignment(Pos.CENTER);

		gridPane.add(tableScrollPane, 0, 0);

		gridPane.setStyle("-fx-background-color: PURPLE, GREEN;");

		Scene scene = new Scene(gridPane);

		stage.setScene(scene);
		stage.setTitle("Html Analyzer");

		stage.show();

		showWordFrequency();
	}

	private void createTable() {
		freqTable = new TableView<>();

		TableColumn<HtmlAnalyzer, Integer> column1 = new TableColumn<HtmlAnalyzer, Integer>("No.");
		column1.setCellValueFactory(new PropertyValueFactory<HtmlAnalyzer, Integer>("serialNumber"));

		TableColumn<HtmlAnalyzer, String> column2 = new TableColumn<HtmlAnalyzer, String>("Word");
		column2.setCellValueFactory(new PropertyValueFactory<HtmlAnalyzer, String>("word"));

		TableColumn<HtmlAnalyzer, Integer> column3 = new TableColumn<HtmlAnalyzer, Integer>("Count");
		column3.setCellValueFactory(new PropertyValueFactory<HtmlAnalyzer, Integer>("count"));

		List<TableColumn<HtmlAnalyzer, ?>> list = new ArrayList<TableColumn<HtmlAnalyzer, ?>>();
		list.add(column1);
		list.add(column2);
		list.add(column3);

		freqTable.getColumns().addAll(list);
	}

	private void showWordFrequency() {
		HtmlAnalyzerProcessor wordFreqProcessor = new HtmlAnalyzerProcessor();
		try {
			wordFreqProcessor.readFile(FILE_NAME);
		} catch (IOException e) {
			showErrorAlert("Error", "Error Reading File: ", FILE_NAME);
			System.exit(0);
			return;
		}

		List<HtmlAnalyzer> list = wordFreqProcessor.getFrequency();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			HtmlAnalyzer wordFreq = (HtmlAnalyzer) list.get(i);
			freqTable.getItems().add(wordFreq);
		} // for
	}

	public static void showErrorAlert(String title, String headerText, String contentText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
}

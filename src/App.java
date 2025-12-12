import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.LinkedList;

import chapter1.Chapter1Problems;
import chapter2.Chapter2Problems;

public class App extends Application {
    
    private TextArea outputArea;
    private TextField inputField;
    private ComboBox<String> chapterSelector;
    private ComboBox<String> problemSelector;
    private RadioButton asciiRadio;
    private RadioButton unicodeRadio;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cracking the Coding Interview - Problems");
        
        // Create main container
        VBox mainPanel = new VBox(15);
        mainPanel.setPadding(new Insets(20));
        mainPanel.setAlignment(Pos.TOP_CENTER);
        
        // Build UI components
        mainPanel.getChildren().addAll(
            createTitleLabel(),
            createChapterSelector(),
            createProblemLabel(),
            createProblemSelector(),
            createEncodingPanel(),
            createInputPanel(),
            createRunButton(),
            createOutputLabel(),
            createOutputArea()
        );
        
        Scene scene = new Scene(mainPanel, 650, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Label createTitleLabel() {
        Label titleLabel = new Label("Cracking the Coding Interview");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        return titleLabel;
    }
    
    private HBox createChapterSelector() {
        HBox chapterPanel = new HBox(10);
        chapterPanel.setAlignment(Pos.CENTER);
        
        Label chapterLabel = new Label("Chapter:");
        chapterLabel.setStyle("-fx-font-size: 14px;");
        
        chapterSelector = new ComboBox<>();
        chapterSelector.getItems().addAll(
            "Chapter 1: Arrays and Strings",
            "Chapter 2: Linked Lists (Coming Soon)",
            "Chapter 3: Stacks and Queues (Coming Soon)"
        );
        chapterSelector.setValue("Chapter 1: Arrays and Strings");
        chapterSelector.setPrefWidth(300);
        chapterSelector.setOnAction(e -> updateProblemsForChapter());
        
        chapterPanel.getChildren().addAll(chapterLabel, chapterSelector);
        return chapterPanel;
    }
    
    private Label createProblemLabel() {
        Label problemLabel = new Label("Select a Problem:");
        problemLabel.setStyle("-fx-font-size: 14px;");
        return problemLabel;
    }
    
    private ComboBox<String> createProblemSelector() {
        problemSelector = new ComboBox<>();
        problemSelector.setPrefWidth(450);
        updateProblemsForChapter();
        return problemSelector;
    }
    
    private HBox createEncodingPanel() {
        HBox encodingPanel = new HBox(15);
        encodingPanel.setAlignment(Pos.CENTER);
        
        Label encodingLabel = new Label("Character Set:");
        asciiRadio = new RadioButton("ASCII (optimized)");
        unicodeRadio = new RadioButton("Unicode (HashMap)");
        asciiRadio.setSelected(true);
        
        ToggleGroup encodingGroup = new ToggleGroup();
        asciiRadio.setToggleGroup(encodingGroup);
        unicodeRadio.setToggleGroup(encodingGroup);
        
        encodingPanel.getChildren().addAll(encodingLabel, asciiRadio, unicodeRadio);
        return encodingPanel;
    }
    
    private HBox createInputPanel() {
        HBox inputPanel = new HBox(10);
        inputPanel.setAlignment(Pos.CENTER);
        
        Label inputLabel = new Label("Input:");
        inputField = new TextField();
        inputField.setPrefWidth(400);
        
        inputPanel.getChildren().addAll(inputLabel, inputField);
        return inputPanel;
    }
    
    private Button createRunButton() {
        Button runButton = new Button("Run Problem");
        runButton.setStyle("-fx-font-size: 14px;");
        runButton.setOnAction(e -> runSelectedProblem());
        return runButton;
    }
    
    private Label createOutputLabel() {
        Label outputLabel = new Label("Output:");
        return outputLabel;
    }
    
    private TextArea createOutputArea() {
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 12px;");
        outputArea.setWrapText(true);
        outputArea.setPrefHeight(400);
        return outputArea;
    }
    
    private void updateProblemsForChapter() {
        String selectedChapter = chapterSelector.getValue();
        problemSelector.getItems().clear();
        
        if (selectedChapter.startsWith("Chapter 1")) {
            problemSelector.getItems().addAll(
                "1.1 - Is Unique",
                "1.2 - Check Permutation",
                "1.3 - URLify",
                "1.4 - Palindrome Permutation",
                "1.5 - One Away",
                "1.6 - String Compression",
                "1.7 - Rotate Matrix",
                "1.8 - Zero Matrix",
                "1.9 - String Rotation"
            );
            problemSelector.setValue("1.1 - Is Unique");
        } else if (selectedChapter.startsWith("Chapter 2")) {
            problemSelector.getItems().addAll(
                "2.1 - Remove Dups",
                "2.2 - Return Kth to Last (Coming Soon)",
                "2.3 - Delete Middle Node (Coming Soon)",
                "2.4 - Partition (Coming Soon)",
                "2.5 - Sum Lists (Coming Soon)",
                "2.6 - Palindrome (Coming Soon)",
                "2.7 - Intersection (Coming Soon)",
                "2.8 - Loop Detection (Coming Soon)"
            );
            problemSelector.setValue("2.1 - Remove Dups");
        } else {
            problemSelector.getItems().add("Coming Soon");
            problemSelector.setValue("Coming Soon");
        }
    }
    
    private void runSelectedProblem() {
        String selected = problemSelector.getValue();
        if (selected == null || selected.equals("Coming Soon")) {
            outputArea.setText("This chapter is coming soon!");
            return;
        }
        
        String input = inputField.getText();
        String result = "";
        
        // Chapter 1 Problems
        if (selected.startsWith("1.1")) {
            result = Chapter1Problems.runIsUnique(input);
        } else if (selected.startsWith("1.2")) {
            result = Chapter1Problems.runCheckPermutation(input, unicodeRadio.isSelected());
        } else if (selected.startsWith("1.3")) {
            result = Chapter1Problems.runURLify(input);
        } else if (selected.startsWith("1.4")) {
            result = Chapter1Problems.runPalindromePermutation(input);
        } else if (selected.startsWith("1.5")) {
            result = Chapter1Problems.runOneAway(input);
        } else if (selected.startsWith("1.6")) {
            result = Chapter1Problems.runStringCompression(input);
        } else if (selected.startsWith("1.7")) {
            result = Chapter1Problems.runRotateMatrix(input);
        } else if (selected.startsWith("1.8")) {
            result = Chapter1Problems.runZeroMatrix(input);
        } else if (selected.startsWith("1.9")) {
            result = Chapter1Problems.runStringRotation(input);
        }
        
        // Chapter 2 Problems
        if (selected.startsWith("2.1")) {
            result = Chapter2Problems.runRemoveDups(input);
        }

        if(selected.startsWith("2.2")){
            result = "This problem is coming soon!";

        
         
        }
        
        outputArea.setText(result);
    }
}

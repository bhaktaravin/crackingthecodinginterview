import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {
    
    private TextArea outputArea;
    private TextField inputField;
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
        
        // Title label
        Label titleLabel = new Label("Select a Problem to Run:");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        // Problem selector
        problemSelector = new ComboBox<>();
        problemSelector.getItems().addAll(
            "1.1 - Is Unique (Check unique characters)",
            "1.2 - Check Permutation",
            "1.3 - URLify (Replace spaces with %20)",
            "1.4 - Palindrome Permutation",
            "1.5 - One Away",
            "1.6 - String Compression",
            "1.7 - Rotate Matrix",
            "1.8 - Zero Matrix",
            "1.9 - String Rotation"
        );
        problemSelector.setValue("1.1 - Is Unique (Check unique characters)");
        problemSelector.setPrefWidth(450);
        
        // Character encoding selector (for Problem 1.2)
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
        
        // Input panel
        HBox inputPanel = new HBox(10);
        inputPanel.setAlignment(Pos.CENTER);
        Label inputLabel = new Label("Input:");
        inputField = new TextField();
        inputField.setPrefWidth(400);
        inputPanel.getChildren().addAll(inputLabel, inputField);
        
        // Run button
        Button runButton = new Button("Run Problem");
        runButton.setStyle("-fx-font-size: 14px;");
        runButton.setOnAction(e -> runSelectedProblem());
        
        // Output label
        Label outputLabel = new Label("Output:");
        
        // Output area
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 12px;");
        outputArea.setWrapText(true);
        outputArea.setPrefHeight(400);
        
        // Add components
        mainPanel.getChildren().addAll(
            titleLabel,
            problemSelector,
            encodingPanel,
            inputPanel,
            runButton,
            outputLabel,
            outputArea
        );
        
        Scene scene = new Scene(mainPanel, 650, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void runSelectedProblem() {
        String selected = problemSelector.getValue();
        if (selected == null) {
            outputArea.setText("Please select a problem first!");
            return;
        }
        
        if (selected.startsWith("1.1")) {
            runIsUnique();
        } else if (selected.startsWith("1.2")) {
            runCheckPermutation();
        } else if (selected.startsWith("1.3")) {
            runURLify();
        } else if (selected.startsWith("1.4")) {
            runPalindromePermutation();
        } else if (selected.startsWith("1.5")) {
            runOneAway();
        } else if (selected.startsWith("1.6")) {
            runStringCompression();
        } else if (selected.startsWith("1.7")) {
            runRotateMatrix();
        } else if (selected.startsWith("1.8")) {
            runZeroMatrix();
        } else if (selected.startsWith("1.9")) {
            runStringRotation();
        }
    }
    
    private void runIsUnique() {
        String input = inputField.getText();
        if (input.isEmpty()) {
            input = "abc aaa";
        }
        
        StringBuilder output = new StringBuilder();
        output.append("=== Problem 1.1: Is Unique (Optimized with Bit Vector) ===\n\n");
        
        String[] testStrings = input.split("\\s+");
        
        for (String str : testStrings) {
            output.append(String.format("Testing: \"%s\"\n", str));
            StringBuilder detailedOutput = new StringBuilder();
            boolean result = hasUniqueCharactersOptimized(str, detailedOutput);
            output.append(detailedOutput.toString());
            output.append(String.format("Result: %s\n\n", result));
        }
        
        outputArea.setText(output.toString());
    }
    
    private void runCheckPermutation() {
        String input = inputField.getText();
        if (input.isEmpty()) {
            input = "abcdefg hello olleh apple padle kite";
        }
        
        boolean useUnicode = unicodeRadio.isSelected();
        String mode = useUnicode ? "Unicode (HashMap)" : "ASCII (Optimized)";
        
        StringBuilder output = new StringBuilder();
        output.append("=== Problem 1.2: Check Permutation ===\n");
        output.append("Mode: ").append(mode).append("\n\n");
        
        String[] testStrings = input.split("\\s+");
        
        if (testStrings.length < 2) {
            output.append("Please enter at least 2 strings separated by spaces.\n");
        } else {
            checkPermutation cp = new checkPermutation();
            for (int i = 0; i < testStrings.length - 1; i += 2) {
                String str1 = testStrings[i];
                String str2 = (i + 1 < testStrings.length) ? testStrings[i + 1] : "";
                try {
                    boolean result = useUnicode ? 
                        cp.checkPermutationUnicode(str1, str2) : 
                        cp.checkPermutationASCII(str1, str2);
                    output.append(String.format("\"%s\" and \"%s\" are permutations: %s\n", 
                        str1, str2, result));
                } catch (IllegalArgumentException e) {
                    output.append(String.format("\"%s\" and \"%s\" - ERROR: %s\n", 
                        str1, str2, e.getMessage()));
                }
            }
        }
        
        outputArea.setText(output.toString());
    }
    
    private void runURLify() {
        String input = inputField.getText();
        if (input.isEmpty()) {
            input = "Mr John Smith";
        }
        
        StringBuilder output = new StringBuilder();
        output.append("=== Problem 1.3: URLify ===\n\n");
        
        String result = urlify(input);
        output.append(String.format("Original: \"%s\"\n", input));
        output.append(String.format("URLified: \"%s\"\n", result));
        
        outputArea.setText(output.toString());
    }
    
    // Problem 1.1: Is Unique implementation (Optimized with Bit Vector)
    // O(n) time, O(1) space - uses single integer as bit vector
    private boolean hasUniqueCharactersOptimized(String str, StringBuilder output) {
        int checker = 0; // Bit vector to track seen characters
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int val = c - 'a'; // Get position (0-25 for lowercase a-z)
            
            // Check if character is outside lowercase a-z range
            if (val < 0 || val > 25) {
                output.append(String.format("  '%c' is not a lowercase letter, skipping\n", c));
                continue;
            }
            
            output.append(String.format("  Checking '%c' (bit position %d)\n", c, val));
            output.append(String.format("    Checker: %s\n", Integer.toBinaryString(checker)));
            
            // Check if bit at position 'val' is already set
            if ((checker & (1 << val)) > 0) {
                output.append(String.format("    Bit %d is already set! Duplicate found.\n", val));
                return false;
            }
            
            // Set the bit at position 'val'
            checker |= (1 << val);
            output.append(String.format("    Bit %d set. New checker: %s\n", val, Integer.toBinaryString(checker)));
        }
        
        return true;
    }
    
    // Problem 1.3: URLify implementation
    private String urlify(String str) {
        // Trim trailing spaces and replace spaces with %20
        return str.trim().replace(" ", "%20");
    }
    
    private void runPalindromePermutation() {
        String input = inputField.getText();
        if (input.isEmpty()) {
            input = "taco cat | hello";
        }
        
        StringBuilder output = new StringBuilder();
        output.append("=== Problem 1.4: Palindrome Permutation (Optimized with Bit Vector) ===\n");
        output.append("(Separate multiple strings with | )\n\n");
        
        // Split by pipe to test multiple strings
        String[] testStrings = input.split("\\|");
        
        for (String str : testStrings) {
            str = str.trim();
            if (!str.isEmpty()) {
                output.append(String.format("Testing: \"%s\"\n", str));
                StringBuilder detailedOutput = new StringBuilder();
                boolean result = isPalindromePermutationOptimized(str, detailedOutput);
                output.append(detailedOutput.toString());
                output.append(String.format("Result: %s\n\n", result));
            }
        }
        
        output.append("Note: Spaces and non-letters are ignored.\n");
        
        outputArea.setText(output.toString());
    }
    
    // Problem 1.4: Palindrome Permutation implementation (Optimized with Bit Vector)
    // O(n) time, O(1) space - uses single integer to track odd/even counts
    private boolean isPalindromePermutationOptimized(String str, StringBuilder output) {
        // Convert to lowercase and remove non-letter characters
        String cleanStr = str.toLowerCase().replaceAll("[^a-z]", "");
        output.append(String.format("  Cleaned string: \"%s\"\n", cleanStr));
        
        int bitVector = 0; // Each bit represents odd(1) or even(0) count
        
        for (char c : cleanStr.toCharArray()) {
            int val = c - 'a'; // Position 0-25
            output.append(String.format("  Processing '%c' (bit %d)\n", c, val));
            output.append(String.format("    Before: %s\n", Integer.toBinaryString(bitVector)));
            
            // Toggle the bit at position 'val'
            // If bit is 0 (even count), set to 1 (odd)
            // If bit is 1 (odd count), set to 0 (even)
            bitVector ^= (1 << val);
            output.append(String.format("    After:  %s\n", Integer.toBinaryString(bitVector)));
        }
        
        output.append(String.format("  Final bit vector: %s\n", Integer.toBinaryString(bitVector)));
        
        // For palindrome permutation, at most 1 character can have odd count
        // This means bitVector should have at most 1 bit set
        // Check if bitVector is 0 or a power of 2
        boolean result = (bitVector == 0) || ((bitVector & (bitVector - 1)) == 0);
        
        if (bitVector == 0) {
            output.append("  All characters have even counts (can form even-length palindrome)\n");
        } else if ((bitVector & (bitVector - 1)) == 0) {
            output.append("  Exactly one character has odd count (can form odd-length palindrome)\n");
        } else {
            output.append("  More than one character has odd count (cannot form palindrome)\n");
        }
        
        return result;
    }
    
    private void runOneAway() {
        String input = inputField.getText();
        if (input.isEmpty()) {
            input = "pale ple | pales pale | pale bale | pale bake";
        }
        
        StringBuilder output = new StringBuilder();
        output.append("=== Problem 1.5: One Away ===\n");
        output.append("(Separate pairs with | )\n\n");
        
        // Split by pipe to test multiple string pairs
        String[] testPairs = input.split("\\|");
        
        for (String pair : testPairs) {
            pair = pair.trim();
            String[] strings = pair.split("\\s+");
            
            if (strings.length >= 2) {
                String str1 = strings[0];
                String str2 = strings[1];
                oneAway oa = new oneAway();
                boolean result = oa.isOneAway(str1, str2);
                output.append(String.format("\"%s\" and \"%s\" → %s\n", str1, str2, result));
            }
        }
        
        output.append("\nEdits: insert, remove, or replace a character\n");
        
        outputArea.setText(output.toString());
    }
    
    private void runStringCompression() {
        String input = inputField.getText();
        if (input.isEmpty()) {
            input = "aabcccccaaa";
        }
        
        StringBuilder output = new StringBuilder();
        output.append("=== Problem 1.6: String Compression ===\n\n");
        
        String result = compressString(input);
        output.append(String.format("Original: \"%s\" (length: %d)\n", input, input.length()));
        output.append(String.format("Compressed: \"%s\" (length: %d)\n", result, result.length()));
        
        outputArea.setText(output.toString());
    }
    
    // Problem 1.6: String Compression
    private String compressString(String str) {
        StringBuilder compressed = new StringBuilder();
        int countConsecutive = 0;
        
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;
            
            // If next character is different or we're at the end
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }
        
        // Return original if compressed is not smaller
        return compressed.length() < str.length() ? compressed.toString() : str;
    }
    
    private void runRotateMatrix() {
        String input = inputField.getText();
        if (input.isEmpty()) {
            input = "3";
        }
        
        StringBuilder output = new StringBuilder();
        output.append("=== Problem 1.7: Rotate Matrix ===\n\n");
        
        try {
            int n = Integer.parseInt(input.trim());
            if (n < 1 || n > 10) {
                output.append("Please enter a number between 1 and 10\n");
            } else {
                // Create a sample matrix
                int[][] matrix = new int[n][n];
                int counter = 1;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        matrix[i][j] = counter++;
                    }
                }
                
                output.append("Original Matrix:\n");
                output.append(matrixToString(matrix));
                
                rotateMatrix(matrix);
                
                output.append("\nRotated 90° Clockwise:\n");
                output.append(matrixToString(matrix));
            }
        } catch (NumberFormatException e) {
            output.append("Please enter a valid number\n");
        }
        
        outputArea.setText(output.toString());
    }
    
    // Problem 1.7: Rotate Matrix 90 degrees
    private void rotateMatrix(int[][] matrix) {
        int n = matrix.length;
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int top = matrix[first][i];
                
                // left -> top
                matrix[first][i] = matrix[last - offset][first];
                
                // bottom -> left
                matrix[last - offset][first] = matrix[last][last - offset];
                
                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];
                
                // top -> right
                matrix[i][last] = top;
            }
        }
    }
    
    private void runZeroMatrix() {
        String input = inputField.getText();
        if (input.isEmpty()) {
            input = "3x3";
        }
        
        StringBuilder output = new StringBuilder();
        output.append("=== Problem 1.8: Zero Matrix ===\n\n");
        
        try {
            String[] parts = input.trim().toLowerCase().split("x");
            if (parts.length != 2) {
                output.append("Please enter dimensions as: MxN (e.g., 3x4)\n");
            } else {
                int m = Integer.parseInt(parts[0].trim());
                int n = Integer.parseInt(parts[1].trim());
                
                if (m < 1 || m > 10 || n < 1 || n > 10) {
                    output.append("Please enter dimensions between 1 and 10\n");
                } else {
                    // Create sample matrix with some zeros
                    int[][] matrix = new int[m][n];
                    int counter = 1;
                    for (int i = 0; i < m; i++) {
                        for (int j = 0; j < n; j++) {
                            matrix[i][j] = counter++;
                        }
                    }
                    // Add some zeros
                    if (m > 1 && n > 1) {
                        matrix[1][1] = 0;
                        if (m > 2) matrix[2][0] = 0;
                    }
                    
                    output.append("Original Matrix:\n");
                    output.append(matrixToString(matrix));
                    
                    setZeros(matrix);
                    
                    output.append("\nAfter Setting Zeros:\n");
                    output.append(matrixToString(matrix));
                }
            }
        } catch (NumberFormatException e) {
            output.append("Please enter valid dimensions (e.g., 3x4)\n");
        }
        
        outputArea.setText(output.toString());
    }
    
    // Problem 1.8: Zero Matrix
    private void setZeros(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] column = new boolean[matrix[0].length];
        
        // Store the row and column index with value 0
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }
        
        // Set arr[i][j] to 0 if either row i or column j has a 0
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (row[i] || column[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    
    private void runStringRotation() {
        String input = inputField.getText();
        if (input.isEmpty()) {
            input = "waterbottle erbottlewat | hello llohe | test fest";
        }
        
        StringBuilder output = new StringBuilder();
        output.append("=== Problem 1.9: String Rotation ===\n");
        output.append("(Separate pairs with | )\n\n");
        
        String[] testPairs = input.split("\\|");
        
        for (String pair : testPairs) {
            pair = pair.trim();
            String[] strings = pair.split("\\s+");
            
            if (strings.length >= 2) {
                String s1 = strings[0];
                String s2 = strings[1];
                boolean result = isRotation(s1, s2);
                output.append(String.format("\"%s\" is rotation of \"%s\": %s\n", s2, s1, result));
            }
        }
        
        output.append("\nTrick: s2 is rotation of s1 if s2 is substring of s1+s1\n");
        
        outputArea.setText(output.toString());
    }
    
    // Problem 1.9: String Rotation
    private boolean isRotation(String s1, String s2) {
        if (s1.length() != s2.length() || s1.length() == 0) {
            return false;
        }
        // Check if s2 is a substring of s1+s1
        String s1s1 = s1 + s1;
        return s1s1.contains(s2);
    }
    
    // Helper method to convert matrix to string
    private String matrixToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            for (int val : row) {
                sb.append(String.format("%4d", val));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
   
}


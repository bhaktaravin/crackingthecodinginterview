import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class App extends JFrame {
    
    private JTextArea outputArea;
    private JTextField inputField;
    private JComboBox<String> problemSelector;
    private JRadioButton asciiRadio;
    private JRadioButton unicodeRadio;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }
    
    public App() {
        setTitle("Cracking the Coding Interview - Problems");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title label
        JLabel titleLabel = new JLabel("Select a Problem to Run:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Problem selector
        problemSelector = new JComboBox<>(new String[]{
            "1.1 - Is Unique (Check unique characters)",
            "1.2 - Check Permutation",
            "1.3 - URLify (Replace spaces with %20)",
            "1.4 - Palindrome Permutation"
        });
        problemSelector.setMaximumSize(new Dimension(450, 30));
        problemSelector.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Character encoding selector (for Problem 1.2)
        JPanel encodingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        JLabel encodingLabel = new JLabel("Character Set:");
        asciiRadio = new JRadioButton("ASCII (optimized)", true);
        unicodeRadio = new JRadioButton("Unicode (HashMap)");
        ButtonGroup encodingGroup = new ButtonGroup();
        encodingGroup.add(asciiRadio);
        encodingGroup.add(unicodeRadio);
        encodingPanel.add(encodingLabel);
        encodingPanel.add(asciiRadio);
        encodingPanel.add(unicodeRadio);
        encodingPanel.setMaximumSize(new Dimension(600, 40));
        
        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JLabel inputLabel = new JLabel("Input:");
        inputField = new JTextField(30);
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.setMaximumSize(new Dimension(600, 40));
        
        // Run button
        JButton runButton = new JButton("Run Problem");
        runButton.setFont(new Font("Arial", Font.PLAIN, 14));
        runButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        runButton.addActionListener(this::runSelectedProblem);
        
        // Output label
        JLabel outputLabel = new JLabel("Output:");
        outputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Output area
        outputArea = new JTextArea(15, 50);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add components with spacing
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(problemSelector);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(encodingPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(runButton);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(outputLabel);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(scrollPane);
        
        add(mainPanel);
    }
    
    private void runSelectedProblem(ActionEvent e) {
        String selected = (String) problemSelector.getSelectedItem();
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
        }
    }
    
    private void runIsUnique() {
        String input = inputField.getText();
        if (input.isEmpty()) {
            input = "abcdefg hello world test";
        }
        
        StringBuilder output = new StringBuilder();
        output.append("=== Problem 1.1: Is Unique ===\n\n");
        
        String[] testStrings = input.split("\\s+");
        
        for (String str : testStrings) {
            boolean result = hasUniqueCharacters(str);
            output.append(String.format("\"%s\" has unique characters: %s\n", str, result));
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
    
    // Problem 1.1: Is Unique implementation
    private boolean hasUniqueCharacters(String str) {
        java.util.Set<Character> seen = new java.util.HashSet<>();
        
        for (char c : str.toCharArray()) {
            if (seen.contains(c)) {
                return false;
            }
            seen.add(c);
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
            input = "Tact Coa";
        }
        
        StringBuilder output = new StringBuilder();
        output.append("=== Problem 1.4: Palindrome Permutation ===\n\n");
        
        // Test the entire input as one string (spaces will be ignored in the algorithm)
        boolean result = isPalindromePermutation(input);
        output.append(String.format("\"%s\" is a palindrome permutation: %s\n", input, result));
        output.append("\nNote: Spaces and non-letters are ignored in the check.\n");
        output.append("Example palindromes: \"taco cat\", \"atco cta\"\n");
        
        outputArea.setText(output.toString());
    }
    
    // Problem 1.4: Palindrome Permutation implementation
    // Check if a string is a permutation of a palindrome
    private boolean isPalindromePermutation(String str) {
        // Convert to lowercase and remove non-letter characters
        String cleanStr = str.toLowerCase().replaceAll("[^a-z]", "");
        
        // Count character frequencies
        java.util.HashMap<Character, Integer> charCount = new java.util.HashMap<>();
        for (char c : cleanStr.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // For a palindrome permutation, at most one character can have odd count
        int oddCount = 0;
        for (int count : charCount.values()) {
            if (count % 2 == 1) {
                oddCount++;
            }
            if (oddCount > 1) {
                return false;
            }
        }
        
        return true;
    }
}


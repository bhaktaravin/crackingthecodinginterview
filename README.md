# Cracking the Coding Interview - Java Solutions

A JavaFX application for practicing and testing solutions to problems from "Cracking the Coding Interview" by Gayle Laakmann McDowell.

## Overview

This project implements all Chapter 1 (Arrays and Strings) problems with a modern JavaFX GUI for easy testing and visualization of algorithms.

## Features

- 🎯 **All 9 Chapter 1 Problems Implemented**
- 🚀 **Optimized Solutions** with bit manipulation where applicable
- 💻 **Interactive JavaFX GUI** for testing each problem
- 📊 **Detailed Step-by-Step Output** showing algorithm execution
- 🔧 **Separate Class Files** for clean code organization

## Problems Included

### Chapter 1: Arrays and Strings

1. **1.1 - Is Unique** - Check if string has all unique characters (with bit vector optimization)
2. **1.2 - Check Permutation** - Check if one string is a permutation of another
3. **1.3 - URLify** - Replace spaces with %20
4. **1.4 - Palindrome Permutation** - Check if string is permutation of palindrome (with bit vector optimization)
5. **1.5 - One Away** - Check if strings are one edit away
6. **1.6 - String Compression** - Compress repeated characters
7. **1.7 - Rotate Matrix** - Rotate NxN matrix 90 degrees
8. **1.8 - Zero Matrix** - Set row/column to zero if element is zero
9. **1.9 - String Rotation** - Check if one string is rotation of another

## Prerequisites

- **Java 21** (JDK 21.0.8 or higher)
- **JavaFX SDK 21** - Download from [Gluon](https://gluonhq.com/products/javafx/)
- Place JavaFX SDK in: `C:\Users\<username>\javafx-sdk-21.0.9\`

## Setup

1. Clone the repository:
```bash
git clone https://github.com/bhaktaravin/crackingthecodinginterview.git
cd crackingthecodinginterview
```

2. Download and extract JavaFX SDK 21 to your user directory

3. Update batch files if your JavaFX path differs

## Running the Application

### Windows

Compile:
```bash
compile-javafx.bat
```

Run:
```bash
run-javafx.bat
```

### Manual Compilation

```bash
cd src
javac --module-path "C:\Users\<username>\javafx-sdk-21.0.9\lib" --add-modules javafx.controls *.java
```

### Manual Run

```bash
java --module-path "C:\Users\<username>\javafx-sdk-21.0.9\lib" --add-modules javafx.controls App
```

## Project Structure

```
CodingProblems/
├── src/
│   ├── App.java              # Main JavaFX application
│   ├── IsUnique.java         # Problem 1.1 implementation
│   ├── checkPermutation.java # Problem 1.2 implementation
│   ├── Urlify.java           # Problem 1.3 implementation
│   ├── oneAway.java          # Problem 1.5 implementation
│   └── *.class               # Compiled classes
├── bin/                      # Compiled output
├── lib/                      # Dependencies
├── compile-javafx.bat        # Compilation script
├── run-javafx.bat           # Run script
└── README.md
```

## Key Optimizations

### Problem 1.1 - Is Unique
- **Approach**: Bit vector using single integer
- **Complexity**: O(n) time, O(1) space
- **Benefit**: Uses 32 bits instead of HashSet

### Problem 1.4 - Palindrome Permutation
- **Approach**: Bit vector with XOR toggle
- **Complexity**: O(n) time, O(1) space
- **Trick**: `(bitVector & (bitVector - 1)) == 0` checks if ≤1 bit is set

## Usage Examples

### Problem 1.1 - Is Unique
Input: `abc aaa`
- Shows bit manipulation at each step
- Displays binary representation of checker

### Problem 1.4 - Palindrome Permutation
Input: `taco cat | hello`
- Use `|` to separate multiple test cases
- Shows character frequency tracking with bits

### Problem 1.5 - One Away
Input: `pale ple | pales pale | pale bale | pale bake`
- Tests insert, delete, and replace operations

## Interview Prep Tips

✅ Understand both brute force and optimized approaches  
✅ Explain time/space complexity trade-offs  
✅ Test with edge cases (empty strings, single characters)  
✅ Walk through your thought process step-by-step  
✅ Ask clarifying questions about constraints

## Contributing

Feel free to add more problems or optimizations!

## License

This is a personal learning project based on "Cracking the Coding Interview" by Gayle Laakmann McDowell.

## Author

Ravin Bhakta - Interview Prep 2025

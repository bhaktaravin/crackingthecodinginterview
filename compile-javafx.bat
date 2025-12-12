@echo off
cd src
javac --module-path "C:\Users\ravin\javafx-sdk-21.0.9\lib" --add-modules javafx.controls *.java
if %errorlevel% equ 0 (
    echo Compilation successful!
) else (
    echo Compilation failed!
)

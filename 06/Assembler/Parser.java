import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

    public Scanner sc; // scanner for read the asm file
    public String currentInst;

    // Constrctor, open the source file
    public Parser(String sourcePath) {
        File file = new File(sourcePath);
        try {
            this.sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    // Check if the asm file has more lines to read.
    public boolean hasMoreLines() {
        return this.sc.hasNextLine();
    }

    // Advance to the next instruction.
    public void advance() {
        currentInst = sc.nextLine().trim();
        if (currentInst.length() != 0 && currentInst.indexOf(" ") != -1) {
            currentInst = currentInst.substring(0, currentInst.indexOf(" "));
        }
        // ignore blanks lines and comments lines.
        while ((currentInst.length() == 0 || currentInst.indexOf('/') == 0)
                && hasMoreLines()) {
            currentInst = sc.nextLine().trim();
            if (currentInst.length() != 0 && currentInst.indexOf(" ") != -1) {
                currentInst = currentInst.substring(0, currentInst.indexOf(" "));
            }
        }
    }

    // Return the current instruction type.
    public String instructionType() {
        if (currentInst.contains("@"))
            return "A_INSTRUCTION";
        else if (currentInst.contains("=") || currentInst.contains(";"))
            return "C_INSTRUCTION";
        else
            return "L_INSTRUCTION";
    }

    // If A_INSTRUCTION or L_INSTRUCTION return symbol name.
    public String symbol() {
        if (instructionType() == "C_INSTRUCTION")
            return null;
        else if (instructionType() == "A_INSTRUCTION") {
            return currentInst.substring(currentInst.indexOf('@') + 1);
        } else {
            // L_INSTRUCTION
            int firstPar = currentInst.indexOf('(');
            int lastPar = currentInst.lastIndexOf(')');
            return currentInst.substring(firstPar + 1, lastPar);
        }
    }

    // Methods For C_INSTRUCTION
    // Returns the instruction's dest field
    public String dest() {
        int index = currentInst.indexOf('=');
        if (index == -1)
            return null;
        else {
            return currentInst.substring(0, index);
        }
    }

    // Returns the instruction's comp field
    public String comp() {
        int indexOfSemicolon = currentInst.indexOf(';');
        int indexOfEqual = currentInst.indexOf('=');
        if (dest() == null) {
            return currentInst.substring(0, indexOfSemicolon);
        } else if (jump() == null) {
            return currentInst.substring(indexOfEqual + 1);
        } else {
            return currentInst.substring(indexOfEqual + 1, indexOfSemicolon);
        }
    }

    // Returns the instruction's jump field
    public String jump() {
        int index = currentInst.indexOf(';');
        if (index == -1)
            return null;
        else {
            return currentInst.substring(index + 1);
        }
    }
    //END of Methods For C_INSTRUCTION.

}

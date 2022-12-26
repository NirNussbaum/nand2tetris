import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

    public Scanner sc; // scanner for read the vm file
    public String VMcommand;

    // Constrctor, open the source file or Dic --> file.vm
    public Parser(File file) {
        try {
            this.sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    // Check if the .vm file has more lines to read.
    public boolean hasMoreLines() {
        return this.sc.hasNextLine();
    }

    // Advance to the next instruction.
    public void advance() {
        VMcommand = sc.nextLine().trim();
        if (VMcommand.indexOf('/') > 0) {
            VMcommand = VMcommand.substring(0, VMcommand.indexOf('/')).trim();
        }
        // ignore blanks lines and comments lines.
        while ((VMcommand.length() == 0 || VMcommand.indexOf('/') == 0)
                && hasMoreLines()) {
            VMcommand = sc.nextLine().trim();
            if (VMcommand.indexOf('/') > 0) {
                VMcommand = VMcommand.substring(0, VMcommand.indexOf('/')).trim();
            }
        }
    }

    // Return a constant representing the type of the current command.
    public String commandType() {
        boolean checkArithmetic = VMcommand.contains("add") || VMcommand.contains("sub") || VMcommand.contains("neg") ||
                VMcommand.contains("eq") || VMcommand.contains("gt") || VMcommand.contains("lt")
                || VMcommand.contains("and") ||
                VMcommand.contains("or") || VMcommand.contains("not");

        if (VMcommand.contains("call"))
            return "C_CALL";
        else if (VMcommand.contains("pop"))
            return "C_POP";
        else if (VMcommand.contains("push"))
            return "C_PUSH";
        else if (VMcommand.contains("label"))
            return "C_LABEL";
        else if (VMcommand.contains("if")) // mabye need to change depend on if go rel!!!!!!!!!!!!!!!!!!!!!
            return "C_IF";
        else if (VMcommand.contains("goto"))
            return "C_GOTO";
        else if (VMcommand.contains("function"))
            return "C_FUNCTION";
        else if (VMcommand.contains("return"))
            return "C_RETURN";
        else if (checkArithmetic)
            return "C_ARITHMETIC";
        else return "commandType-Error";
    }

    // Returns the first argument of the current command.
    public String arg1() {
        String commandType = commandType();
        if (commandType.equals("C_RETURN"))
            return "arg1Error"; // For project 8.
        else if (commandType.equals("C_ARITHMETIC"))
            return VMcommand;
        else {
            int firstSpace = VMcommand.indexOf(" ");
            int secondSpace = VMcommand.indexOf(" ", firstSpace + 1);
            if(secondSpace == -1) return VMcommand.substring(firstSpace + 1);
            else return VMcommand.substring(firstSpace + 1, secondSpace);
        }
    }

    // Returns the Second argument of the current command.
    public int arg2() {
        String currentCommandType = commandType();
        if (!currentCommandType.equals("C_PUSH") && !currentCommandType.equals("C_POP") &&
                !currentCommandType.equals("C_FUNCTION") && !currentCommandType.equals("C_CALL"))
            return -999; // For project 7 and 8.
        else {
            int firstSpace = VMcommand.indexOf(" ");
            int secondSpace = VMcommand.indexOf(" ", firstSpace + 1);
            return Integer.parseInt(VMcommand.substring(secondSpace + 1));
        }
    }
}

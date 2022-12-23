import java.io.File;

public class VMTranslator {
    public static void main(String[] args) {
            String sourceString = args[0];
            Parser inputFile = new Parser(sourceString);
            // create a new out put file with the same name like input file but end with .asm
            CodeWriter outPutFile = new CodeWriter(new File(args[0].replace(".vm", ".asm")));
            while (inputFile.hasMoreLines()) {
                inputFile.advance(); // next VM command
                String commandType = inputFile.commandType();
                if(commandType.equals("C_ARITHMETIC")) {
                    outPutFile.writeArithmetic(inputFile.arg1());
                } else if(commandType.equals("C_POP") || commandType.equals("C_PUSH")) {
                    outPutFile.writePushPop(commandType, inputFile.arg1(), inputFile.arg2());
                } 
            }
            //close file.
            outPutFile.close();
    }
}

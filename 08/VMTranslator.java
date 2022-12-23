import java.io.File;

public class VMTranslator {

    public static CodeWriter outPutFile;

    public static void main(String[] args) {
        String sourcePath = "FunctionCalls/NestedCall";
        File fileOrDir = new File(sourcePath);
        boolean isDirectory = fileOrDir.isDirectory(); // Check if it's a directory
        if (isDirectory) {
            //create output file with the same path but end with .asm
            outPutFile = new CodeWriter(new File("FunctionCalls/NestedCall" + ".asm"));
            File[] files = fileOrDir.listFiles();
            for (File file : files) {
                if (file.getName().contains(".vm")) {
                    Parser parser = new Parser(file);
                    processFile(parser);
                }
            }
        } else {
            // create a new out put file with the same name like input file but end with
            // .asm
            outPutFile = new CodeWriter(new File("FunctionCalls/NestedCall".replace(".vm", ".asm")));
            Parser file = new Parser(fileOrDir);
            processFile(file);
        }
        // close file.
        outPutFile.close();
    }

    public static void processFile(Parser file) {
        while (file.hasMoreLines()) {
            file.advance(); // next VM command
            String commandType = file.commandType();
            if (commandType.equals("C_ARITHMETIC")) {
                outPutFile.writeArithmetic(file.arg1());
            } else if (commandType.equals("C_POP") || commandType.equals("C_PUSH")) {
                outPutFile.writePushPop(commandType, file.arg1(), file.arg2());
            } else if (commandType.equals("C_LABEL")) {
                outPutFile.writeLabel(file.arg1());
            } else if (commandType.equals("C_GOTO")) {
                outPutFile.writeGoto(file.arg1());
            } else if (commandType.equals("C_IF")) {
                outPutFile.writeIf(file.arg1());
            } else if (commandType.equals("C_FUNCTION")) {
                outPutFile.writeFunction(file.arg1(), file.arg2());
            } else if (commandType.equals("C_RETURN")) {
                outPutFile.writeReturn();
            } else if (commandType.equals("C_CALL")) {
                outPutFile.writeCall(file.arg1(), file.arg2());
            }
        }

    }
}

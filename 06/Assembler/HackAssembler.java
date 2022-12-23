import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class HackAssembler {

    public static void main(String[] args) {
        FileWriter outPutFile;
        try {
            String sourceString = args[0];
            Parser asm = new Parser(sourceString);
            // create a new out put file with the same name like input file but end with
            // hack.
            File outFile = new File(args[0].replace(".asm", ".hack"));
            outPutFile = new FileWriter(outFile);
            BufferedWriter bw = new BufferedWriter(outPutFile);
            // step 1 run on Lables
            SymbolTable symbolTable = createSymbolTableFirstPass(new Parser(sourceString));
            //step 2 run on A Instructions
            symbolTable = AddSymbolTableSecondPass(new Parser(sourceString), symbolTable);
            while (asm.hasMoreLines()) {
                StringBuilder outPutLine = new StringBuilder();
                asm.advance();
                if (asm.instructionType() == "A_INSTRUCTION") {
                    outPutLine.append("0");
                    // check if symbol is number.
                    String symbol = asm.symbol();
                    try {
                        int intSymbol = Integer.parseInt(symbol);
                        outPutLine.append(convertTo15Bit(intSymbol));
                    } catch (NumberFormatException e) {
                        int address = symbolTable.getAddress(symbol);
                        outPutLine.append(convertTo15Bit(address));
                    }
                }
                if (asm.instructionType() == "C_INSTRUCTION") {
                    outPutLine.append("111");
                    outPutLine.append(Code.comp(asm.comp()));
                    outPutLine.append(Code.dest(asm.dest()));
                    outPutLine.append(Code.jump(asm.jump()));
                }
                if (outPutLine.toString().length() > 0) {
                    bw.write(outPutLine.toString());
                    bw.newLine();
                }
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("IOException");
            return;
        }
    }

    // Create defalut symbol table and add L_INSTRUCTION.
    public static SymbolTable createSymbolTableFirstPass(Parser asm) {
        SymbolTable symbolTable = new SymbolTable();
        int counter = 0;
        while (asm.hasMoreLines()) {
            asm.advance();
            String currentSymbol = asm.symbol();
            // check if null is not symbol.
            if (currentSymbol != null) {
                if (asm.instructionType() == "L_INSTRUCTION" && (!symbolTable.contains(currentSymbol))) {
                    symbolTable.addEntry(currentSymbol, counter);
                    continue;
                }
            }
            counter++;
        }
        return symbolTable;
    }

    // Second Pass of create Symbol table --> A_INSTRUCTION
    public static SymbolTable AddSymbolTableSecondPass(Parser asm, SymbolTable symbolTable) {
        int counterParms = 16;
        while (asm.hasMoreLines()) {
            asm.advance();
            String currentSymbol = asm.symbol();
            // check if null is not symbol.
            // If A_INSTRUCTION set value depend on number of parmaters.
            if (asm.instructionType() == "A_INSTRUCTION" && (currentSymbol != null)) {
                // check if num.
                try {
                    Integer.parseInt(currentSymbol);
                    continue;
                } catch (NumberFormatException e) {
                    if (!symbolTable.contains(currentSymbol)) {
                        symbolTable.addEntry(currentSymbol, counterParms);
                        counterParms++;
                    }
                }
            }
        }
        return symbolTable;
    }

    // return a 15 bit number from a regular number.
    public static String convertTo15Bit(int num) {
        StringBuilder ans = new StringBuilder();
        String binaryNum = Integer.toBinaryString(num);
        int check = 15 - binaryNum.length();
        for (int i = 0; i < check; i++) {
            ans.append('0');
        }
        ans.append(binaryNum);
        return ans.toString();
    }

}

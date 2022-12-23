import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class CodeWriter {

    public BufferedWriter bw;

    // Opens an output file and gets ready to write into it.
    public CodeWriter(File outPutFile) {
        try {
            FileWriter outPutFileWriter = new FileWriter(outPutFile);
            bw = new BufferedWriter(outPutFileWriter);
        } catch (IOException e) {
            System.out.println("IOException");
            return;
        }
    }

    // template for arithmetic and logical commands.
    private void createBasicTemplate() {
        try {
            bw.write("@SP");
            bw.newLine();
            bw.write("A=M-1");
            bw.newLine();
            bw.write("D=M");
            bw.newLine();
            bw.write("@SP");
            bw.newLine();
            bw.write("M=M-1");
            bw.newLine();
            bw.write("A=M-1");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("IOException createTemplate");
            return;
        }

    }

    // template for arithmetic and logical commands.
    static int numberOfLogical = 0;
    private void createLogicalTemplate(String speicalCommand, String nameOfFunction) {
        numberOfLogical++;
        try {
            bw.write("D=M-D");
            bw.newLine();
            bw.write("@IS_" + nameOfFunction + "_" + numberOfLogical);
            bw.newLine();
            bw.write(speicalCommand);
            bw.newLine();
            bw.write("@SP");
            bw.newLine();
            bw.write("A=M-1");
            bw.newLine();
            bw.write("M=0");
            bw.newLine();
            bw.write("@END_" + nameOfFunction + "_" + numberOfLogical);
            bw.newLine();
            bw.write("0;JMP");
            bw.newLine();
            bw.write("(IS_" + nameOfFunction + "_" + numberOfLogical + ")");
            bw.newLine();
            bw.write("@SP");
            bw.newLine();
            bw.write("A=M-1");
            bw.newLine();
            bw.write("M=-1");
            bw.newLine();
            bw.write("(END_" + nameOfFunction + "_" + numberOfLogical + ")");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("IOException writeArithmetic");
            return;
        }
    }

    // Writes to output file the assemble code that implements the given
    // arithmetic-logical command.
    public void writeArithmetic(String command) {
        try {
            if (command.equals("add")) {
                bw.write("//add");
                bw.newLine();
                createBasicTemplate();
                bw.write("M=M+D");
                bw.newLine();
            } else if (command.equals("sub")) {
                bw.write("//sub");
                bw.newLine();
                createBasicTemplate();
                bw.write("M=M-D");
                bw.newLine();
            } else if (command.equals("neg")) {
                bw.write("//neg");
                bw.newLine();
                bw.write("@SP");
                bw.newLine();
                bw.write("A=M-1");
                bw.newLine();
                bw.write("D=-M");
                bw.newLine();
                bw.write("M=D");
                bw.newLine();
            } else if (command.equals("eq")) {
                bw.write("//equal");
                bw.newLine();
                createBasicTemplate();
                createLogicalTemplate("D;JEQ", "EQ");
            } else if (command.equals("gt")) {
                bw.write("//greater than");
                bw.newLine();
                createBasicTemplate();
                createLogicalTemplate("D;JGT", "GT");
            } else if (command.equals("lt")) {
                bw.write("//lower than");
                bw.newLine();
                createBasicTemplate();
                createLogicalTemplate("D;JLT", "LT");
            } else if (command.equals("and")) {
                bw.write("//and");
                bw.newLine();
                createBasicTemplate();
                bw.write("M=M&D");
                bw.newLine();
            } else if (command.equals("or")) {
                bw.write("//or");
                bw.newLine();
                createBasicTemplate();
                bw.write("M=M|D");
                bw.newLine();
            } else if (command.equals("not")) {
                bw.write("//not");
                bw.newLine();
                bw.write("@SP");
                bw.newLine();
                bw.write("A=M-1");
                bw.newLine();
                bw.write("D=!M");
                bw.newLine();
                bw.write("M=D");
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("IOException writeArithmetic");
            return;
        }
    }

    // Writes to the output file the assembly code that implements the given push or
    // pop command.
    public void writePushPop(String command, String segment, int index) {
        // change segment name to correct asm name.
        if (segment.equals("local"))
            segment = "LCL";
        else if (segment.equals("argument"))
            segment = "ARG";
        else if (segment.equals("this"))
            segment = "THIS";
        else if (segment.equals("that"))
            segment = "THAT";

        try {
            if (command.equals("C_PUSH")) {
                bw.write("//PUSH " + segment + " " + index);
                bw.newLine();
                if (segment.equals("temp")) {
                    // temp
                    bw.write("@" + (5 + index));
                    bw.newLine();
                    bw.write("D=M");
                    bw.newLine();
                } else if (segment.equals("pointer")) {
                    // pointer
                    bw.write("@" + (3 + index));
                    bw.newLine();
                    bw.write("D=M");
                    bw.newLine();
                } else {
                    bw.write("@" + index);
                    bw.newLine();
                    bw.write("D=A");
                    bw.newLine();
                    if (!segment.equals("constant")) {
                        bw.write("@" + segment);
                        bw.newLine();
                        bw.write("A=M+D"); // addr = LCL + i
                        bw.newLine();
                        bw.write("D=M");
                        bw.newLine();
                    }
                }
                bw.write("@SP");
                bw.newLine();
                bw.write("A=M");
                bw.newLine();
                bw.write("M=D"); // RAM[SP] = RAM[addr]
                bw.newLine();
                bw.write("@SP");
                bw.newLine();
                bw.write("M=M+1"); // SP++
                bw.newLine();
            } else {
                bw.write("//POP " + segment + " " + index);
                bw.newLine();
                if (segment.equals("temp")) {
                    //temp
                    bw.write("@" + (5 + index));
                    bw.newLine();
                    bw.write("D=A");
                    bw.newLine();
                } else if (segment.equals("pointer")) {
                    // pointer
                    bw.write("@" + (3 + index));
                    bw.newLine();
                    bw.write("D=A");
                    bw.newLine();
                } else {
                    bw.write("@" + index);
                    bw.newLine();
                    bw.write("D=A");
                    bw.newLine();
                    if (!segment.equals("constant")) {
                        bw.write("@" + segment);
                        bw.newLine();
                        bw.write("D=M+D"); // addr = LCL + i
                        bw.newLine();
                    }
                }
                bw.write("@R13");
                bw.newLine();
                bw.write("M=D"); // save addr in RAM 13
                bw.newLine();
                bw.write("@SP");
                bw.newLine();
                bw.write("M=M-1"); // SP--
                bw.newLine();
                bw.write("A=M");
                bw.newLine();
                bw.write("D=M");
                bw.newLine();
                bw.write("@R13");
                bw.newLine();
                bw.write("A=M");
                bw.newLine();
                bw.write("M=D"); // RAM[addr] = RAM[SP]
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("IOException close function.");
            return;
        }
    }

    // Closes the output file.
    public void close() {
        try {
            bw.close();
        } catch (IOException e) {
            System.out.println("IOException close function.");
            return;
        }
    }

}
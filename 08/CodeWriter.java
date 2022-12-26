import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class CodeWriter {

    public BufferedWriter bw;
    public String fileName;
    public int callCounter = 0;

    // Opens an output file and gets ready to write into it.
    public CodeWriter(File outPutFile) {
        try {
            FileWriter outPutFileWriter = new FileWriter(outPutFile);
            bw = new BufferedWriter(outPutFileWriter);
            this.callCounter = 0;
        } catch (IOException e) {
            System.out.println("IOException");
            return;
        }
    }

    // Informs that the translation of a new VM file hat started.
    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public void writeInitProg() {
        try {
            bw.write("//InitProg");
            bw.newLine();
            bw.write("@256");
            bw.newLine();
            bw.write("D=A");
            bw.newLine();
            bw.write("@SP");
            bw.newLine();
            bw.write("M=D");
            bw.newLine();
            writeCall("Sys.init", 0);
        } catch (IOException e) {
            System.out.println("IOException writeLabel");
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
                    // temp
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

    // Writes assembly code that effects the label command.
    public void writeLabel(String label) {
        try {
            bw.write("//label " + fileName + "$" + label);
            bw.newLine();
            bw.write("(" + fileName + "$" + label + ")");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("IOException writeLabel");
            return;
        }
    }

    // Writes assembly code that effects the goto command.
    public void writeGoto(String label) {
        try {
            bw.write("//goto " + fileName + "$" + label);
            bw.newLine();
            bw.write("@"  + fileName + "$" + label);
            bw.newLine();
            bw.write("0;JMP");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("IOException writeGoto");
            return;
        }
    }

    // Writes assembly code that effects the if-goto command.
    public void writeIf(String label) {
        try {
            bw.write("//if-goto " + fileName + "$" + label);
            bw.newLine();
            bw.write("@SP");
            bw.newLine();
            bw.write("M=M-1"); // SP--
            bw.newLine();
            bw.write("A=M");
            bw.newLine();
            bw.write("D=M");
            bw.newLine();
            bw.write("@" + fileName + "$" + label);
            bw.newLine();
            bw.write("D;JNE"); // if D != 0 jump to label
            bw.newLine();
        } catch (IOException e) {
            System.out.println("IOException writeIf");
            return;
        }
    }

    // Writes assembly code that saves segments.
    private void writeSaveSegment(String segment) {
        try {
            bw.write("@" + segment);
            bw.newLine();
            bw.write("D=M");
            bw.newLine();
            bw.write("@SP");
            bw.newLine();
            bw.write("A=M");
            bw.newLine();
            bw.write("M=D"); // saved segment
            bw.newLine();
            bw.write("@SP");
            bw.newLine();
            bw.write("M=M+1"); // SP++
            bw.newLine();
        } catch (IOException e) {
            System.out.println("IOException writeCall");
            return;
        }
    }

    // Writes assembly code that effects the call-function command.
    public void writeCall(String functionName, int nArgs) {
        try {
            bw.write("//Call " + functionName + " " + nArgs);
            bw.newLine();
            // Save return address
            bw.write("@" + functionName + "$ret." + callCounter);
            bw.newLine();
            bw.write("D=A");
            bw.newLine();
            bw.write("@SP");
            bw.newLine();
            bw.write("A=M");
            bw.newLine();
            bw.write("M=D");
            bw.newLine();
            bw.write("@SP");
            bw.newLine();
            bw.write("M=M+1");
            bw.newLine();
            writeSaveSegment("LCL");
            writeSaveSegment("ARG");
            writeSaveSegment("THIS");
            writeSaveSegment("THAT");
            // ARG = SP - 5 - nArgs
            bw.write("@SP");
            bw.newLine();
            bw.write("A=M");
            bw.newLine();
            bw.write("M=A");
            bw.newLine();
            bw.write("@SP");
            bw.newLine();
            bw.write("M=M+1"); // SP++
            bw.newLine();
            writePushPop("C_PUSH", "constant", 5);
            writeArithmetic("sub");
            writePushPop("C_PUSH", "constant", nArgs);
            writeArithmetic("sub");
            bw.write("@SP");
            bw.newLine();
            bw.write("A=M-1");
            bw.newLine();
            bw.write("D=M");
            bw.newLine();
            bw.write("@SP");
            bw.newLine();
            bw.write("M=M-1"); // SP--
            bw.newLine();
            bw.write("@ARG");
            bw.newLine();
            bw.write("M=D");
            bw.newLine();
            //LCL = SP
            bw.write("@SP");
            bw.newLine();
            bw.write("D=M");
            bw.newLine();
            bw.write("@LCL");
            bw.newLine();
            bw.write("M=D");
            bw.newLine();
            bw.write("@" + functionName );
            bw.newLine();
            bw.write("0;JMP");
            bw.newLine();
            bw.write("(" + functionName + "$ret." + callCounter + ")");
            bw.newLine();
            callCounter++;
        } catch (IOException e) {
            System.out.println("IOException writeCall");
            return;
        }
    }

    // Writes assembly code that effects the function command.
    public void writeFunction(String functionName, int nVars) {
        try {
            bw.write("// " + functionName + " " + nVars);
            bw.newLine();
            //Function's entry point
            bw.write("(" + functionName + ")");
            bw.newLine();
            //repeat push nVars local var
            for (int i = 0; i < nVars; i++) {
                writePushPop("C_PUSH", "constant", 0);
            }
        } catch (IOException e) {
            System.out.println("IOException writeFunction");
            return;
        }
    }

    // Writes assembly code that Restore Segments.
    private void writeRestoreSegments(String segment) {
        try {
            bw.write("@R15");
            bw.newLine();
            bw.write("D=M");
            bw.newLine();
            if (segment.equals("THAT")) bw.write("@1");
            else if (segment.equals("THIS")) bw.write("@2");
            else if (segment.equals("ARG")) bw.write("@3");
            else if (segment.equals("LCL")) bw.write("@4");
            bw.newLine();
            bw.write("D=D-A");
            bw.newLine();
            bw.write("A=D");
            bw.newLine();
            bw.write("D=M");
            bw.newLine();
            bw.write("@" + segment);
            bw.newLine();
            bw.write("M=D");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("IOException writeReturn");
            return;
        }
    }

    // Writes assembly code that effects the function command.
    public void writeReturn() {
        try {
            bw.write("//Return");
            bw.newLine();
            // endFrame = LCL
            bw.write("@LCL");
            bw.newLine();
            bw.write("D=M");
            bw.newLine();
            bw.write("@R15");
            bw.newLine();
            bw.write("M=D");
            bw.newLine();
            // retAddr = RAM[endFrame - 5]
            bw.write("@5");
            bw.newLine();
            bw.write("D=D-A");
            bw.newLine();
            bw.write("A=D");
            bw.newLine();
            bw.write("D=M");
            bw.newLine();
            bw.write("@R14");
            bw.newLine();
            bw.write("M=D");
            bw.newLine();
            // RAM[ARG] = pop()
            bw.write("@SP");
            bw.newLine();
            bw.write("A=M-1");
            bw.newLine();
            bw.write("D=M");
            bw.newLine();
            bw.write("@ARG");
            bw.newLine();
            bw.write("A=M");
            bw.newLine();
            bw.write("M=D");
            bw.newLine();
            bw.write("@SP");
            bw.newLine();
            bw.write("M=M-1");
            bw.newLine();
            // SP = ARG + 1
            bw.write("@ARG");
            bw.newLine();
            bw.write("D=M");
            bw.newLine();
            bw.write("@SP");
            bw.newLine();
            bw.write("M=D+1");
            bw.newLine();
            writeRestoreSegments("THAT");
            writeRestoreSegments("THIS");
            writeRestoreSegments("ARG");
            writeRestoreSegments("LCL");
            //Goto retAddr
            bw.write("@R14");
            bw.newLine();
            bw.write("A=M");
            bw.newLine();
            bw.write("0;JMP");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("IOException writeReturn");
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
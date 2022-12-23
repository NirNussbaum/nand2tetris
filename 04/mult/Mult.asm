// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
//
// This program only needs to handle arguments that satisfy
// R0 >= 0, R1 >= 0, and R0*R1 < 32768.

//RAM[2] == 0
@2
M=0

//if (RAM[0] == 0) jump to END
@0
D=M
@END
D;JEQ

//if (RAM[1] == 0) jump to END
(LOOP)
@1
D=M
@END
D;JEQ

//RAM[2] += RAM[1]
@1
D=M
@2
M=M + D

//if(--RAM[0] == 0) jump to END 
@0
D=M-1
M=D
@END
D;JEQ
@LOOP
D;JGT
(END)
//END of program
@END
0;JMP
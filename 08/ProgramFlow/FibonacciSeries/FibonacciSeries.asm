//PUSH ARG 1
@1
D=A
@ARG
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1
//POP pointer 1
@4
D=A
@R13
M=D
@SP
M=M-1
A=M
D=M
@R13
A=M
M=D
//PUSH constant 0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
//POP THAT 0
@0
D=A
@THAT
D=M+D
@R13
M=D
@SP
M=M-1
A=M
D=M
@R13
A=M
M=D
//PUSH constant 1
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
//POP THAT 1
@1
D=A
@THAT
D=M+D
@R13
M=D
@SP
M=M-1
A=M
D=M
@R13
A=M
M=D
//PUSH ARG 0
@0
D=A
@ARG
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 2
@2
D=A
@SP
A=M
M=D
@SP
M=M+1
//sub
@SP
A=M-1
D=M
@SP
M=M-1
A=M-1
M=M-D
//POP ARG 0
@0
D=A
@ARG
D=M+D
@R13
M=D
@SP
M=M-1
A=M
D=M
@R13
A=M
M=D
(FibonacciSeries$MAIN_LOOP_START)
//PUSH ARG 0
@0
D=A
@ARG
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1
//if-goto FibonacciSeries$COMPUTE_ELEMENT
@SP
M=M-1
A=M
D=M
@FibonacciSeries$COMPUTE_ELEMENT
D;JNE
@FibonacciSeries$END_PROGRAM
0;JMP
(FibonacciSeries$COMPUTE_ELEMENT)
//PUSH THAT 0
@0
D=A
@THAT
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1
//PUSH THAT 1
@1
D=A
@THAT
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1
//add
@SP
A=M-1
D=M
@SP
M=M-1
A=M-1
M=M+D
//POP THAT 2
@2
D=A
@THAT
D=M+D
@R13
M=D
@SP
M=M-1
A=M
D=M
@R13
A=M
M=D
//PUSH pointer 1
@4
D=M
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 1
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
//add
@SP
A=M-1
D=M
@SP
M=M-1
A=M-1
M=M+D
//POP pointer 1
@4
D=A
@R13
M=D
@SP
M=M-1
A=M
D=M
@R13
A=M
M=D
//PUSH ARG 0
@0
D=A
@ARG
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 1
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
//sub
@SP
A=M-1
D=M
@SP
M=M-1
A=M-1
M=M-D
//POP ARG 0
@0
D=A
@ARG
D=M+D
@R13
M=D
@SP
M=M-1
A=M
D=M
@R13
A=M
M=D
@FibonacciSeries$MAIN_LOOP_START
0;JMP
(FibonacciSeries$END_PROGRAM)
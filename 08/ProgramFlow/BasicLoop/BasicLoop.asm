//PUSH constant 0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
//POP LCL 0
@0
D=A
@LCL
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
(BasicLoop$LOOP_START)
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
//PUSH LCL 0
@0
D=A
@LCL
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
//POP LCL 0
@0
D=A
@LCL
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
//if-goto BasicLoop$LOOP_START
@SP
M=M-1
A=M
D=M
@BasicLoop$LOOP_START
D;JNE
//PUSH LCL 0
@0
D=A
@LCL
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1
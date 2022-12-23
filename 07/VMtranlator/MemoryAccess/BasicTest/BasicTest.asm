
//PUSH constant 10
@10
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

//PUSH constant 21
@21
D=A
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 22
@22
D=A
@SP
A=M
M=D
@SP
M=M+1
//POP ARG 2
@2
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

//POP ARG 1
@1
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

//PUSH constant 36
@36
D=A
@SP
A=M
M=D
@SP
M=M+1
//POP THIS 6
@6
D=A
@THIS
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

//PUSH constant 42
@42
D=A
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 45
@45
D=A
@SP
A=M
M=D
@SP
M=M+1
//POP THAT 5
@5
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

//PUSH constant 510
@510
D=A
@SP
A=M
M=D
@SP
M=M+1
//POP temp 6
@11
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
//PUSH THAT 5
@5
D=A
@THAT
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1//add

@SP
A=M-1
D=M
@SP
M=M-1
A=M-1
M=M+D

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
M=M+1//sub

@SP
A=M-1
D=M
@SP
M=M-1
A=M-1
M=M-D

//PUSH THIS 6
@6
D=A
@THIS
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1
//PUSH THIS 6
@6
D=A
@THIS
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1//add

@SP
A=M-1
D=M
@SP
M=M-1
A=M-1
M=M+D
//sub

@SP
A=M-1
D=M
@SP
M=M-1
A=M-1
M=M-D

//PUSH temp 6
@11
D=M
@SP
A=M
M=D
@SP
M=M+1//add

@SP
A=M-1
D=M
@SP
M=M-1
A=M-1
M=M+D


//PUSH constant 111
@111
D=A
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 333
@333
D=A
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 888
@888
D=A
@SP
A=M
M=D
@SP
M=M+1
//POP static 8
@8
D=A
@static
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

//POP static 3
@3
D=A
@static
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

//POP static 1
@1
D=A
@static
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

//PUSH static 3
@3
D=A
@static
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1
//PUSH static 1
@1
D=A
@static
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

//PUSH static 8
@8
D=A
@static
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

//InitProg
@256
D=A
@SP
M=D
//Call Sys.init 0
@Sys.init$ret.0
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M
M=A
@SP
M=M+1
//PUSH constant 5
@5
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
//PUSH constant 0
@0
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
@SP
A=M-1
D=M
@SP
M=M-1
@ARG
M=D
@SP
D=M
@LCL
M=D
@Sys.init
0;JMP
(Sys.init$ret.0)
// Class1.set 0
(Class1.set)
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
//POP static 0
@Class1.0
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
//POP static 1
@Class1.1
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
//Return
@LCL
D=M
@R15
M=D
@5
D=D-A
A=D
D=M
@R14
M=D
@SP
A=M-1
D=M
@ARG
A=M
M=D
@SP
M=M-1
@ARG
D=M
@SP
M=D+1
@R15
D=M
@1
D=D-A
A=D
D=M
@THAT
M=D
@R15
D=M
@2
D=D-A
A=D
D=M
@THIS
M=D
@R15
D=M
@3
D=D-A
A=D
D=M
@ARG
M=D
@R15
D=M
@4
D=D-A
A=D
D=M
@LCL
M=D
@R14
A=M
0;JMP
// Class1.get 0
(Class1.get)
//PUSH static 0
@Class1.0
D=M
@SP
A=M
M=D
@SP
M=M+1
//PUSH static 1
@Class1.1
D=M
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
//Return
@LCL
D=M
@R15
M=D
@5
D=D-A
A=D
D=M
@R14
M=D
@SP
A=M-1
D=M
@ARG
A=M
M=D
@SP
M=M-1
@ARG
D=M
@SP
M=D+1
@R15
D=M
@1
D=D-A
A=D
D=M
@THAT
M=D
@R15
D=M
@2
D=D-A
A=D
D=M
@THIS
M=D
@R15
D=M
@3
D=D-A
A=D
D=M
@ARG
M=D
@R15
D=M
@4
D=D-A
A=D
D=M
@LCL
M=D
@R14
A=M
0;JMP
// Class2.set 0
(Class2.set)
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
//POP static 0
@Class2.0
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
//POP static 1
@Class2.1
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
//Return
@LCL
D=M
@R15
M=D
@5
D=D-A
A=D
D=M
@R14
M=D
@SP
A=M-1
D=M
@ARG
A=M
M=D
@SP
M=M-1
@ARG
D=M
@SP
M=D+1
@R15
D=M
@1
D=D-A
A=D
D=M
@THAT
M=D
@R15
D=M
@2
D=D-A
A=D
D=M
@THIS
M=D
@R15
D=M
@3
D=D-A
A=D
D=M
@ARG
M=D
@R15
D=M
@4
D=D-A
A=D
D=M
@LCL
M=D
@R14
A=M
0;JMP
// Class2.get 0
(Class2.get)
//PUSH static 0
@Class2.0
D=M
@SP
A=M
M=D
@SP
M=M+1
//PUSH static 1
@Class2.1
D=M
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
//Return
@LCL
D=M
@R15
M=D
@5
D=D-A
A=D
D=M
@R14
M=D
@SP
A=M-1
D=M
@ARG
A=M
M=D
@SP
M=M-1
@ARG
D=M
@SP
M=D+1
@R15
D=M
@1
D=D-A
A=D
D=M
@THAT
M=D
@R15
D=M
@2
D=D-A
A=D
D=M
@THIS
M=D
@R15
D=M
@3
D=D-A
A=D
D=M
@ARG
M=D
@R15
D=M
@4
D=D-A
A=D
D=M
@LCL
M=D
@R14
A=M
0;JMP
// Sys.init 0
(Sys.init)
//PUSH constant 6
@6
D=A
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 8
@8
D=A
@SP
A=M
M=D
@SP
M=M+1
//Call Class1.set 2
@Class1.set$ret.1
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M
M=A
@SP
M=M+1
//PUSH constant 5
@5
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
@SP
A=M-1
D=M
@SP
M=M-1
@ARG
M=D
@SP
D=M
@LCL
M=D
@Class1.set
0;JMP
(Class1.set$ret.1)
//POP temp 0
@5
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
//PUSH constant 23
@23
D=A
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 15
@15
D=A
@SP
A=M
M=D
@SP
M=M+1
//Call Class2.set 2
@Class2.set$ret.2
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M
M=A
@SP
M=M+1
//PUSH constant 5
@5
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
@SP
A=M-1
D=M
@SP
M=M-1
@ARG
M=D
@SP
D=M
@LCL
M=D
@Class2.set
0;JMP
(Class2.set$ret.2)
//POP temp 0
@5
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
//Call Class1.get 0
@Class1.get$ret.3
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M
M=A
@SP
M=M+1
//PUSH constant 5
@5
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
//PUSH constant 0
@0
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
@SP
A=M-1
D=M
@SP
M=M-1
@ARG
M=D
@SP
D=M
@LCL
M=D
@Class1.get
0;JMP
(Class1.get$ret.3)
//Call Class2.get 0
@Class2.get$ret.4
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M
M=A
@SP
M=M+1
//PUSH constant 5
@5
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
//PUSH constant 0
@0
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
@SP
A=M-1
D=M
@SP
M=M-1
@ARG
M=D
@SP
D=M
@LCL
M=D
@Class2.get
0;JMP
(Class2.get$ret.4)
//label Sys$WHILE
(Sys$WHILE)
//goto Sys$WHILE
@Sys$WHILE
0;JMP

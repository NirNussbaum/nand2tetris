
//PUSH constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1//equal

@SP
A=M-1
D=M
@SP
M=M-1
A=M-1

D=M-D
@IS_EQ_1
D;JEQ
@SP
A=M-1
M=0
@END_EQ_1
0;JMP
(IS_EQ_1)
@SP
A=M-1
M=-1
(END_EQ_1)

//PUSH constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1//equal

@SP
A=M-1
D=M
@SP
M=M-1
A=M-1

D=M-D
@IS_EQ_2
D;JEQ
@SP
A=M-1
M=0
@END_EQ_2
0;JMP
(IS_EQ_2)
@SP
A=M-1
M=-1
(END_EQ_2)

//PUSH constant 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1//equal

@SP
A=M-1
D=M
@SP
M=M-1
A=M-1

D=M-D
@IS_EQ_3
D;JEQ
@SP
A=M-1
M=0
@END_EQ_3
0;JMP
(IS_EQ_3)
@SP
A=M-1
M=-1
(END_EQ_3)

//PUSH constant 892
@892
D=A
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1//lower than

@SP
A=M-1
D=M
@SP
M=M-1
A=M-1

D=M-D
@IS_LT_4
D;JLT
@SP
A=M-1
M=0
@END_LT_4
0;JMP
(IS_LT_4)
@SP
A=M-1
M=-1
(END_LT_4)

//PUSH constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 892
@892
D=A
@SP
A=M
M=D
@SP
M=M+1//lower than

@SP
A=M-1
D=M
@SP
M=M-1
A=M-1

D=M-D
@IS_LT_5
D;JLT
@SP
A=M-1
M=0
@END_LT_5
0;JMP
(IS_LT_5)
@SP
A=M-1
M=-1
(END_LT_5)

//PUSH constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1//lower than

@SP
A=M-1
D=M
@SP
M=M-1
A=M-1

D=M-D
@IS_LT_6
D;JLT
@SP
A=M-1
M=0
@END_LT_6
0;JMP
(IS_LT_6)
@SP
A=M-1
M=-1
(END_LT_6)

//PUSH constant 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1//greater than

@SP
A=M-1
D=M
@SP
M=M-1
A=M-1

D=M-D
@IS_GT_7
D;JGT
@SP
A=M-1
M=0
@END_GT_7
0;JMP
(IS_GT_7)
@SP
A=M-1
M=-1
(END_GT_7)

//PUSH constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1//greater than

@SP
A=M-1
D=M
@SP
M=M-1
A=M-1

D=M-D
@IS_GT_8
D;JGT
@SP
A=M-1
M=0
@END_GT_8
0;JMP
(IS_GT_8)
@SP
A=M-1
M=-1
(END_GT_8)

//PUSH constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1//greater than

@SP
A=M-1
D=M
@SP
M=M-1
A=M-1

D=M-D
@IS_GT_9
D;JGT
@SP
A=M-1
M=0
@END_GT_9
0;JMP
(IS_GT_9)
@SP
A=M-1
M=-1
(END_GT_9)

//PUSH constant 57
@57
D=A
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 31
@31
D=A
@SP
A=M
M=D
@SP
M=M+1
//PUSH constant 53
@53
D=A
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

//PUSH constant 112
@112
D=A
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
//neg
@SP
A=M-1
D=-M
M=D
//and

@SP
A=M-1
D=M
@SP
M=M-1
A=M-1
M=M&D

//PUSH constant 82
@82
D=A
@SP
A=M
M=D
@SP
M=M+1//or

@SP
A=M-1
D=M
@SP
M=M-1
A=M-1
M=M|D
//not
@SP
A=M-1
D=!M
M=D

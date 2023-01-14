from JackTokenizer import JackTokenizer

SPACES = 2
OP_LIST = ["+", "-", "*", "/", "&", "|", "<", ">", "="]
UNARY_OP = ['-', '~']
KEY_WORD_CONSTANT = ['true', 'false', 'null', 'this']
STATMENTS = ['let', 'if', 'while', 'do', 'return']

class CompilationEngine:
    def __init__(self, input_path_file, output_path_file):
        """Creates a new compilation engine with the given input and output"""
        self.tokenizer = JackTokenizer(input_path_file)
        self.output_file = open(output_path_file, "w+")
        self.number_of_spaces = 0

    def compile_class(self):
        """Compiles a complete class."""

        if self.tokenizer.hasMoreTokens():
            self.tokenizer.advance()

            # Check the correctness of writing the class.
            if self.tokenizer.tokenType() == "KEYWORD" and self.tokenizer.keyWord() == "class":
                self.output_file.write("<class>\n")
                self.number_of_spaces += SPACES
                self.write_key_word("class")
                self.tokenizer.advance()
            else:
                return "Class ERROR"

            if self.tokenizer.tokenType() == "IDENTIFIER":
                self.write_identifier(self.tokenizer.identifier())
                self.tokenizer.advance()
            else:
                return "Identifier ERROR"

            if self.tokenizer.current_token == "{":
                self.write_symbol("{")
                self.tokenizer.advance()
            else:
                return "{ ERROR"

            # Class var dec.
            if self.tokenizer.current_token == "field" or self.tokenizer.current_token == "static":
                # Write to the output file the class var.
                while self.tokenizer.current_token == "field" or self.tokenizer.current_token == "static":
                    self.output_file.write(
                        " " * self.number_of_spaces + "<classVarDec>\n")
                    self.number_of_spaces += SPACES

                    self.compile_class_var_dec()

                    self.number_of_spaces -= SPACES
                    self.output_file.write(
                        " " * self.number_of_spaces + "</classVarDec>\n")

            # Subroutine
            while (self.tokenizer.current_token == "constructor" or self.tokenizer.current_token == "function"
                                                   or self.tokenizer.current_token == "method"):
                self.output_file.write(
                    " " * self.number_of_spaces + "<subroutineDec>\n")
                self.number_of_spaces += SPACES

                self.compile_subroutine()

                self.number_of_spaces -= SPACES
                self.output_file.write(
                    " " * self.number_of_spaces + "</subroutineDec>\n")

    def compile_class_var_dec(self):
        """Compiles a static veriable declaration or a field declaration."""

        self.write_key_word(self.tokenizer.current_token)
        self.tokenizer.advance()

        if self.check_type() == "KEYWORD":
            self.write_key_word(self.tokenizer.keyWord())
            self.tokenizer.advance()
        elif self.check_type() == "IDENTIFIER":
            self.write_identifier(self.tokenizer.identifier())
            self.tokenizer.advance()
        else:
            return "Type ERROR"

        if self.tokenizer.tokenType() == "IDENTIFIER":
            self.write_identifier(self.tokenizer.identifier())
            self.tokenizer.advance()
        else:
            return "Identifier ERROR"

        while self.tokenizer.current_token == ",":
            self.write_symbol(self.tokenizer.symbol())
            self.tokenizer.advance()
            if self.tokenizer.tokenType() == "IDENTIFIER":
                self.write_identifier(self.tokenizer.identifier())
                self.tokenizer.advance()
            else:
                return "Identifier ERROR"

        if self.tokenizer.current_token == ";":
            self.write_symbol(self.tokenizer.symbol())
            self.tokenizer.advance()
        else:
            return "Symbol ERROR"

    def compile_subroutine(self):
        self.write_key_word(self.tokenizer.keyWord())
        self.tokenizer.advance()

        if self.check_type() == "KEYWORD" or self.tokenizer.current_token == "void":
            self.write_key_word(self.tokenizer.keyWord())
            self.tokenizer.advance()
        elif self.check_type() == "IDENTIFIER":
            self.write_identifier(self.tokenizer.identifier())
            self.tokenizer.advance()
        else:
            return "Not a type or void"

        if self.tokenizer.tokenType() == "IDENTIFIER":
            self.write_identifier(self.tokenizer.identifier())
            self.tokenizer.advance()
        else:
            return "IDENTIFIER ERROR"

        if self.tokenizer.current_token == "(":
            self.write_symbol("(")
            self.tokenizer.advance()
        else:
            return "Not ("

        self.compile_parameter_list()

        if self.tokenizer.current_token == ")":
            self.write_symbol(")")
            self.tokenizer.advance()
        else:
            return "Not )"

        self.output_file.write(
            " " * self.number_of_spaces + "<subroutineBody>\n")
        self.number_of_spaces += SPACES

        if self.tokenizer.current_token == "{":
            self.write_symbol("{")
            self.tokenizer.advance()
        else:
            return "Not {"

        self.compile_subroutine_body()

        if self.tokenizer.current_token == "}":
            self.write_symbol("}")
            self.tokenizer.advance()
        else:
            return "Not }"

        self.number_of_spaces -= SPACES
        self.output_file.write(
            " " * self.number_of_spaces + "</subroutineBody>\n")

    def compile_parameter_list(self):
        """
        Compiles a (possible empty) parameter list.
        Does not handle the enclosing parentheses tokenes (and).
        """

        self.output_file.write(
            " " * self.number_of_spaces + "<parameterList>\n")
        self.number_of_spaces += SPACES

        if not self.tokenizer.current_token == ")":
            while self.check_type() != "Not a type":
                if self.check_type() == "KEYWORD":
                    self.write_key_word(self.tokenizer.keyWord())
                    self.tokenizer.advance()
                else:
                    self.write_identifier(self.tokenizer.identifier())
                    self.tokenizer.advance()

                if self.tokenizer.tokenType() == "IDENTIFIER":
                    self.write_identifier(self.tokenizer.identifier())
                    self.tokenizer.advance()
                else:
                    return "IDENTIFIER ERROR"

                if self.tokenizer.current_token == ",":
                    self.write_symbol(",")
                    self.tokenizer.advance()

        self.number_of_spaces -= SPACES
        self.output_file.write(
            " " * self.number_of_spaces + "</parameterList>\n")

    def compile_subroutine_body(self):
        """Compiles subroutine body."""
        while self.tokenizer.current_token == "var":
            self.compile_vardec()

        self.compile_statements()

    def compile_vardec(self):
        """Compiles var declartion"""
        self.output_file.write(
            " " * self.number_of_spaces + "<varDec>\n")
        self.number_of_spaces += SPACES

        self.write_key_word(self.tokenizer.keyWord())
        self.tokenizer.advance()

        if self.check_type() == "KEYWORD":
            self.write_key_word(self.tokenizer.keyWord())
            self.tokenizer.advance()
        elif self.check_type() == "IDENTIFIER":
            self.write_identifier(self.tokenizer.identifier())
            self.tokenizer.advance()
        else:
            return "Not a type"

        if self.tokenizer.tokenType() == "IDENTIFIER":
            self.write_identifier(self.tokenizer.identifier())
            self.tokenizer.advance()
        else:
            return "IDENTIFIER ERROR"

        while self.tokenizer.current_token == ",":
            self.write_symbol(self.tokenizer.symbol())
            self.tokenizer.advance()

            if self.tokenizer.tokenType() == "IDENTIFIER":
                self.write_identifier(self.tokenizer.identifier())
                self.tokenizer.advance()
            else:
                return "IDENTIFIER ERROR"

        if self.tokenizer.current_token == ";":
            self.write_symbol(self.tokenizer.symbol())
            self.tokenizer.advance()
        else:
            return "; ERROR"

        self.number_of_spaces -= SPACES
        self.output_file.write(
            " " * self.number_of_spaces + "</varDec>\n")

    def compile_statements(self):
        self.output_file.write(
            " " * self.number_of_spaces + "<statements>\n")
        self.number_of_spaces += SPACES

        while self.tokenizer.current_token in STATMENTS:

            if self.tokenizer.current_token == "let":
                self.compile_let()

            elif self.tokenizer.current_token == "if":
                self.compile_if()

            elif self.tokenizer.current_token == "while":
                self.compile_while()

            elif self.tokenizer.current_token == "do":
                self.compile_do()

            elif self.tokenizer.current_token == "return":
                self.compile_return()

            else:
                return "Statements ERROR"

        self.number_of_spaces -= SPACES
        self.output_file.write(
            " " * self.number_of_spaces + "</statements>\n")

    def compile_let(self):
        self.output_file.write(
            " " * self.number_of_spaces + "<letStatement>\n")
        self.number_of_spaces += SPACES

        self.write_key_word(self.tokenizer.keyWord())
        self.tokenizer.advance()

        if self.tokenizer.tokenType() == "IDENTIFIER":
            self.write_identifier(self.tokenizer.identifier())
            self.tokenizer.advance()
        else:
            return "IDENTIFIER ERROR"

        if self.tokenizer.current_token == "[":
            self.write_symbol("[")
            self.tokenizer.advance()

            self.compile_expression()

            self.write_symbol("]")
            self.tokenizer.advance()

        if self.tokenizer.current_token == "=":
            self.write_symbol("=")
            self.tokenizer.advance()

        self.compile_expression()

        if self.tokenizer.current_token == ";":
            self.write_symbol(";")
            self.tokenizer.advance()

        self.number_of_spaces -= SPACES
        self.output_file.write(
            " " * self.number_of_spaces + "</letStatement>\n")

    def compile_if(self):
        self.output_file.write(
            " " * self.number_of_spaces + "<ifStatement>\n")
        self.number_of_spaces += SPACES

        self.write_key_word(self.tokenizer.keyWord())
        self.tokenizer.advance()

        if self.tokenizer.current_token == "(":
            self.write_symbol(self.tokenizer.symbol())
            self.tokenizer.advance()
        else:
            return "symbol ERROR"

        self.compile_expression()

        if self.tokenizer.current_token == ")":
            self.write_symbol(self.tokenizer.symbol())
            self.tokenizer.advance()
        else:
            return "symbol ERROR"

        if self.tokenizer.current_token == "{":
            self.write_symbol("{")
            self.tokenizer.advance()
        else:
            return "symbol ERROR"

        self.compile_statements()

        if self.tokenizer.current_token == "}":
            self.write_symbol("}")
            self.tokenizer.advance()
        else:
            return "symbol ERROR"

        if self.tokenizer.current_token == "else":
            self.write_key_word(self.tokenizer.keyWord())
            self.tokenizer.advance()

            if self.tokenizer.current_token == "{":
                self.write_symbol("{")
                self.tokenizer.advance()
            else:
                return "symbol ERROR"

            self.compile_statements()

            if self.tokenizer.current_token == "}":
                self.write_symbol("}")
                self.tokenizer.advance()
            else:
                return "symbol ERROR"

        self.number_of_spaces -= SPACES
        self.output_file.write(
            " " * self.number_of_spaces + "</ifStatement>\n")

    def compile_while(self):
        self.output_file.write(
            " " * self.number_of_spaces + "<whileStatement>\n")
        self.number_of_spaces += SPACES

        self.write_key_word(self.tokenizer.keyWord())
        self.tokenizer.advance()

        if self.tokenizer.tokenType() == "(":
            self.write_symbol(self.tokenizer.symbol())
            self.tokenizer.advance()
        else:
            return "symbol ERROR"

        self.compile_expression()

        if self.tokenizer.tokenType() == ")":
            self.write_symbol(self.tokenizer.symbol())
            self.tokenizer.advance()
        else:
            return "symbol ERROR"

        if self.tokenizer.current_token == "{":
            self.write_symbol("{")
            self.tokenizer.advance()
        else:
            return "symbol ERROR"

        self.compile_statements()

        if self.tokenizer.current_token == "}":
            self.write_symbol("}")
            self.tokenizer.advance()
        else:
            return "symbol ERROR"

        self.number_of_spaces -= SPACES
        self.output_file.write(
            " " * self.number_of_spaces + "</whileStatement>\n")

    def compile_do(self):
        self.output_file.write(
            " " * self.number_of_spaces + "<doStatement>\n")
        self.number_of_spaces += SPACES

        self.write_key_word(self.tokenizer.keyWord())
        self.tokenizer.advance()

        if self.tokenizer.tokenType() == "IDENTIFIER":
            self.write_identifier(self.tokenizer.identifier())
            self.tokenizer.advance()
        else:
            "Identifier ERROR"

        # case two of subroutine call.
        if self.tokenizer.current_token == ".":
            self.write_symbol(".")
            self.tokenizer.advance()

            if self.tokenizer.tokenType() == "IDENTIFIER":
                self.write_identifier(self.tokenizer.identifier())
                self.tokenizer.advance()
            else:
                return "IDENTIFIER ERROR"

            self.write_symbol("(")
            self.tokenizer.advance()

            self.compile_expression_list()

            self.write_symbol(")")
            self.tokenizer.advance()

        # case one of subroutine call.
        elif self.tokenizer.current_token == "(":
            self.write_symbol("(")
            self.tokenizer.advance()

            self.compile_expression_list()

            self.write_symbol(")")
            self.tokenizer.advance()

        else:
            return "subroutine ERROR"

        if self.tokenizer.current_token == ";":
            self.write_symbol(";")
            self.tokenizer.advance()
        else:
            return "; ERROR"

        self.number_of_spaces -= SPACES
        self.output_file.write(
            " " * self.number_of_spaces + "</doStatement>\n")


    def compile_return(self):
        self.output_file.write(
            " " * self.number_of_spaces + "<returnStatement>\n")
        self.number_of_spaces += SPACES

        self.write_key_word(self.tokenizer.keyWord())
        self.tokenizer.advance()

        if self.tokenizer.current_token != ";":
            self.compile_expression()

        if self.tokenizer.current_token == ";":
            self.write_symbol(";")
            self.tokenizer.advance()
        else:
            return "; ERROR"

        self.number_of_spaces -= SPACES
        self.output_file.write(
            " " * self.number_of_spaces + "</returnStatement>\n")

    def compile_expression(self):
        self.output_file.write(
            " " * self.number_of_spaces + "<expression>\n")
        self.number_of_spaces += SPACES

        self.compile_term()

        while self.tokenizer.current_token in OP_LIST:
            self.write_symbol(self.tokenizer.current_token)
            self.tokenizer.advance()

            self.compile_term()

        self.number_of_spaces -= SPACES
        self.output_file.write(
            " " * self.number_of_spaces + "</expression>\n")

    def compile_term(self):
        self.output_file.write(
            " " * self.number_of_spaces + "<term>\n")
        self.number_of_spaces += SPACES

        if self.tokenizer.tokenType() == "STRING_CONST":
            self.write_string_const(self.tokenizer.stringVal())
            self.tokenizer.advance()
        elif self.tokenizer.tokenType() == "INT_CONST":
            self.write_int_const(self.tokenizer.intVal())
            self.tokenizer.advance()
        elif self.tokenizer.current_token in KEY_WORD_CONSTANT:
            self.write_key_word(self.tokenizer.keyWord())
            self.tokenizer.advance()
        elif self.tokenizer.current_token == "(":
            self.write_symbol("(")
            self.tokenizer.advance()

            self.compile_expression()

            self.write_symbol(")")
            self.tokenizer.advance()

        elif self.tokenizer.tokenType() == "IDENTIFIER":
            self.write_identifier(self.tokenizer.identifier())
            self.tokenizer.advance()

            #varName [ expression ]
            if self.tokenizer.current_token == "[":
                self.write_symbol("[")
                self.tokenizer.advance()

                self.compile_expression()

                self.write_symbol("]")
                self.tokenizer.advance()
            # .subrountine case
            elif self.tokenizer.current_token == ".":
                self.write_symbol(".")
                self.tokenizer.advance()

                if self.tokenizer.tokenType() == "IDENTIFIER":
                    self.write_identifier(self.tokenizer.identifier())
                    self.tokenizer.advance()
                else:
                    return "IDENTIFIER ERROR"

                self.write_symbol("(")
                self.tokenizer.advance()

                self.compile_expression_list()

                self.write_symbol(")")
                self.tokenizer.advance()
            elif self.tokenizer.current_token == "(":
                self.write_symbol("(")
                self.tokenizer.advance()

                self.compile_expression_list()

                self.write_symbol(")")
                self.tokenizer.advance()

            elif self.tokenizer.current_token in UNARY_OP:
                self.write_symbol(self.tokenizer.current_token)
                self.tokenizer.advance()

                self.compile_term()

        self.number_of_spaces -= SPACES
        self.output_file.write(
            " " * self.number_of_spaces + "</term>\n")

    def compile_expression_list(self) -> int:
        self.output_file.write(
            " " * self.number_of_spaces + "<expressionList>\n")
        self.number_of_spaces += SPACES

        counter_expression = 0
        if self.tokenizer.current_token != ")":
            self.compile_expression()
            counter_expression += 1

        while self.tokenizer.current_token == ",":
            self.write_symbol(",")
            self.tokenizer.advance()

            self.compile_expression()
            counter_expression += 1

        self.number_of_spaces -= SPACES
        self.output_file.write(
            " " * self.number_of_spaces + "</expressionList>\n")

        return counter_expression

    # Helper methods.
    def check_type(self) -> str:
        """Check if current token is type"""
        token = self.tokenizer.current_token
        if token == "int" or token == "char" or token == "boolean":
            return "KEYWORD"
        elif self.tokenizer.tokenType() == "IDENTIFIER":
            return "IDENTIFIER"
        else:
            return "Not a type"

    def write_identifier(self, str_identifier):
        """Write identifier tag to the output file."""
        instruction = " " * self.number_of_spaces + \
            "<identifier> " + str_identifier + " </identifier>\n"
        self.output_file.write(instruction)

    def write_symbol(self, str_symbol):
        """Write symbol tag to the output file."""
        instruction = " " * self.number_of_spaces + \
            "<symbol> " + str_symbol + " </symbol>\n"
        self.output_file.write(instruction)

    def write_key_word(self, str_key_word):
        """Write key word tag to the output file."""
        instruction = " " * self.number_of_spaces + \
            "<keyword> " + str_key_word + " </keyword>\n"
        self.output_file.write(instruction)

    def write_string_const(self, str_const):
        """Write string word tag to the output file."""
        instruction = " " * self.number_of_spaces + \
            "<stringConstant> " + str_const + " </stringConstant>\n"
        self.output_file.write(instruction)

    def write_int_const(self, int_const):
        """Write integer tag to the output file."""
        instruction = " " * self.number_of_spaces + \
            "<integerConstant> " + int_const + " </integerConstant>\n"
        self.output_file.write(instruction)


# Testing!!!
test_object = CompilationEngine("./test.txt", "./test.xml")
test_object.compile_class()

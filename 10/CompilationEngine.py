from JackTokenizer import JackTokenizer

SPACES = 2


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
            
            #Check the correctness of writing the class.
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

            if self.tokenizer.current_token == "field" or self.tokenizer.current_token == "static":
                #Write to the output file the class var.
                while self.tokenizer.current_token == "field" or self.tokenizer.current_token == "static":
                    self.output_file.write(
                        " " * self.number_of_spaces + "<classVarDec>\n")
                    self.number_of_spaces += SPACES

                    self.compile_class_var_dec()

                    self.number_of_spaces -= SPACES
                    self.output_file.write(
                    " " * self.number_of_spaces + "</classVarDec>\n")

    def compile_class_var_dec(self):
        """Compiles a static veriable declaration or a field declaration."""

        self.write_key_word(self.tokenizer.current_token)
        self.tokenizer.advance()

        if self.tokenizer.tokenType() == "KEYWORD":
            self.write_key_word(self.tokenizer.keyWord())
            self.tokenizer.advance()
        elif self.tokenizer.tokenType() == "IDENTIFIER":
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
        pass




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


# Testing!!!
test_object = CompilationEngine("./test.txt", "./test.xml")
test_object.compile_class()

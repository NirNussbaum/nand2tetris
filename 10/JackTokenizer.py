import re

# All the patternes in JackTokenizer
KEY_WORD_PATTERN = "(class|constructor|function|method|static|field|var|int|char|boolean|void|true|false|null|this|let|do|if|else|while|return)"
SYMBOL_PATTERN = "([{}()\[\].,;+\-*/&|<>=~])"
INT_PATTERN = "(\d+)"
IDENTIFIER_PATTERN = "(^[a-zA-Z_][a-zA-Z1-9_]*$)"
ELEMENTS_PATTERN = f"{KEY_WORD_PATTERN}|{SYMBOL_PATTERN}|{INT_PATTERN}|{IDENTIFIER_PATTERN}"

STRING_PATTERN = "\"(.*)\""


def get_array_of_tokens(string):
    """get array of tokens form a file string. """
    arr = remove_comments(string)
    # Delete \n
    arr = arr.replace("\n", "")
    arr = re.split(re.compile(ELEMENTS_PATTERN), arr)
    # Delete None from arr
    arr = [word.strip() for word in arr if (word != None)]
    arr = [word for word in arr if len(word) > 0]
    return arr


def remove_comments(string):
    """ remove all inline comments and regular comments from string. """
    # regular comments
    string = re.sub(re.compile(r"/\*\*.*?\*/", re.DOTALL), "", string)
    # inline comments
    return re.sub(re.compile("//.*?\n"), "", string)


class JackTokenizer:
    def __init__(self, path_file):
        """Opens the input .jack file and gets ready to tokenize it."""
        with open(path_file, "r") as file:
            self.array_of_words = file.read()
        self.array_of_words = get_array_of_tokens(self.array_of_words)
        self.current_token_index = -1
        self.current_token = ""

    def hasMoreTokens(self) -> bool:
        """There are more tokens in the input file?"""
        if self.current_token_index < len(self.array_of_words) - 1:
            return True
        else:
            return False

    def advance(self):
        """Gets the next token from the input."""
        if self.hasMoreTokens():
            self.current_token_index += 1
            self.current_token = self.array_of_words[self.current_token_index]

    def tokenType(self):
        """Returns the type of the current token, as a constant."""
        if re.fullmatch(KEY_WORD_PATTERN, self.current_token):
            return "KEYWORD"
        elif re.fullmatch(SYMBOL_PATTERN, self.current_token):
            return "SYMBOL"
        elif re.fullmatch(IDENTIFIER_PATTERN, self.current_token):
            return "IDENTIFIER"
        elif re.fullmatch(INT_PATTERN, self.current_token):
            return "INT_CONST"
        elif re.fullmatch(STRING_PATTERN, self.current_token):
            return "STRING_CONST"
        else:
            return "ERROR"

    def keyWord(self) -> str:
        """Returns the keyword which is the current token."""
        return self.current_token

    def symbol(self) -> str:
        """Returns the character which is the current token."""
        return self.current_token

    def identifier(self) -> str:
        """Returns the character which is the current token."""
        return self.current_token

    def intVal(self) -> str:
        """Returns the integer which is the current token."""
        return self.current_token

    def stringVal(self) -> str:
        """Returns the string which is the current token."""
        return self.current_token.replace("\"", "")


# Testing!!!
# test = JackTokenizer("./test.txt")

# for i in range(len(test.array_of_words)):
#     test.advance()
#     if test.tokenType() == "STRING_CONST":
#         print(test.stringVal())
import re

#All the patternes in JackTokenizer
KEY_WORD_PATTERN = "(class|constructor|function|method|static|field|var|int|char|boolean|void|true|false|null|this|let|do|if|else|while|return)"
SYMBOL_PATTERN = "([{}()\[\].,;+\-*/&|<>=~])"
INT_PATTERN = "(\d+)"
STRING_PATTERN = "\"(.*)\""
IDENTIFIER_PATTERN = "([a-zA-Z_][a-zA-Z1-9_]*)"
ELEMENTS_PATTERN = f"{KEY_WORD_PATTERN}|{SYMBOL_PATTERN}|{INT_PATTERN}|{STRING_PATTERN}|{IDENTIFIER_PATTERN}"

def get_array_of_tokens(string):
  """get array of tokens form a file string. """
  arr = remove_comments(string)
  #Delete \n
  arr = arr.replace("\n", "")
  arr = re.split(re.compile(ELEMENTS_PATTERN), arr)
  #Delete None from arr
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
      self.current_token = ""
      self.next_token = ""

  # def hasMoreTokens(self):
  #   """There are more tokens in the input file?"""
  #   if not self.next_token:
  #       return True
  #   return False

#  def advance(self):
#     """Gets the next token from the input."""
#     if self.hasMoreTokens():


test = JackTokenizer("./test.txt")

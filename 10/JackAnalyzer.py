import sys
from CompilationEngine import CompilationEngine
import os  

def jack_analyzer(input_path: str):
    '''Main function of JackAnalyzer'''
    #Dictionary.
    if os.path.isdir(input_path): 
        for file_name in os.listdir(input_path) :
            file_path = os.path.join(input_path, file_name)
            # checking if it is a file
            if os.path.isfile(file_path) and file_path.rfind(".jack") != -1:
                output_path_file = str(file_path).replace(".jack", ".xml")
                engine = CompilationEngine(file_path, output_path_file) 
                engine.compile_class()

    #One single file.    
    elif os.path.isfile(input_path) and input_path.rfind(".jack") != -1: 
        output_path_file = str(input_path).replace(".jack", ".xml")
        engine = CompilationEngine(input_path, output_path_file) 
        engine.compile_class()

# if __name__ == "__main__" and len(sys.argv) == 2:
#     jack_analyzer(sys.argv[1])

if __name__ == "__main__":
    jack_analyzer("./text.cmp")
import java.io.*;
import java.util.*;

public class brainfuck_interpreter {

    static int count(char c, String string) {

        int freq = 0;

        for (int array_index = 0; array_index < string.length(); array_index++) {
            if (string.charAt(array_index) == c)
                freq++;

        }
        return freq;

    }

    boolean checkInvalidCharacters(String input_string) {
        //><+-.,[] are valid characters.
        if (count('>', input_string) + count('<', input_string) + count('+', input_string) + count('-', input_string)
                + count('.', input_string) + count(',', input_string) + count('[', input_string)
                + count(']', input_string) != input_string.length())
            return true;

        return false;

    }

    void interpret_brainfuck(String inputString, short brainfuck_tape[]) {

        //Initialising Scanner Class for taking input.
        Scanner sc = new Scanner(System.in);

        //Char Array For Storing Input String
        char input_array[] = new char[inputString.length()];

        //Copying String to Char array
        input_array = inputString.toCharArray();

        //Used so that nested brackets (loops) are not ignored
        int c=0;

        int tape_index = 0;

        for (int array_index = 0; array_index < input_array.length; array_index++) {

            char character = input_array[array_index];

            //System.out.println("Processing character "+character);

            switch (character) {

            case '+':
                brainfuck_tape[tape_index]++;

                break;

            case '-':
                brainfuck_tape[tape_index] = brainfuck_tape[tape_index] > 0 ? --brainfuck_tape[tape_index]
                        : brainfuck_tape[tape_index];

                break;

            case '>': //if tape index limit is reached then bring it back to zero

                tape_index = (tape_index == 30000) ? 0 : ++tape_index;

                break;

            case '<': // if it is zero  send it further back to len-1 of tape
                tape_index = (tape_index == 0) ? (30000-1) : --tape_index;

                break;

            case '.':

                System.out.println(brainfuck_tape[tape_index]);

                System.out.println((char) brainfuck_tape[tape_index]);

                break;

            case ',':

                System.out.print("Enter a character for input :");
                char inputchar = sc.nextLine().charAt(0);
                brainfuck_tape[tape_index] = (short) inputchar;

                break;

            case '[':

                

                if (brainfuck_tape[tape_index] == 0) {
                {
                    
                    while (c > 0 || input_array[array_index] != ']')
                    {
                        if (input_array[array_index] == '[')
                            c++;
                        else if (input_array[array_index] == ']')
                            c--;
                        array_index++;
                    }
                }

            }

                break;

            case ']':

            if (brainfuck_tape[tape_index] != 0)
            {
                array_index--;
                while (c > 0 ||input_array[array_index] != '[')
                {
                    if (input_array[array_index] == ']')
                        c ++;
                    else if (input_array[array_index] == '[')
                        c --;
                    array_index --;
                }
                array_index--;
            }

            

        }

      }

    }

    public static void main(String args[]) throws IOException {

        //Initialising Scanner Class for taking input.
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter String");

        //variable to store input String
        String input_string = "";

        //Reading input String from Input
         input_string = sc.nextLine();


        
        //Initialize Brainfuck data storage tape
        short brainfuck_tape[] = new short[30000];

        System.out.println("You Entered : " + input_string);

        brainfuck_interpreter obj = new brainfuck_interpreter(); //Instantiating Object

        if (obj.checkInvalidCharacters(input_string))

            System.out.println("The Input String contains some invalid characters, those characters will be ignored");

        obj.interpret_brainfuck(input_string, brainfuck_tape);

    }

}


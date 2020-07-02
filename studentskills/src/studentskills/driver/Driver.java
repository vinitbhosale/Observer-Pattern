package studentskills.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import studentskills.processor.InputDataProcessor;
import studentskills.util.FileProcessor;

public class Driver {
    public static void main(String[] args) throws InvalidPathException, FileNotFoundException, IOException {
        try {
            /*
             * As the build.xml specifies the arguments as input,output or metrics, in case
             * the argument value is not given java takes the default value specified in
             * build.xml. To avoid that, below condition is used
             */
            if ((args.length != 7) || (args[0].equals("${input}")) || (args[1].equals("${modify}"))
                    || (args[2].equals("${output1}")) || (args[3].equals("${output2}"))
                    || (args[4].equals("${output3}")) || (args[5].equals("${error}")) || (args[6].equals("${debug}"))) {
                System.err.println("Error: Incorrect number of arguments. Program accepts 7 arguments.");
                System.exit(0);
            }
            System.out.println("Hello World! Lets get started with the assignment");

            // FileProcessor object
            FileProcessor fp = new FileProcessor(args[0]);
            
            // InputDataProcessor object with FileProcessor object and ContextI object as parameter.
			InputDataProcessor iDp = new InputDataProcessor(fp);

			// call of the process method in the InputDataprocessor.
			iDp.process();


        } catch (InvalidPathException | IOException e) {

        }

    }
}
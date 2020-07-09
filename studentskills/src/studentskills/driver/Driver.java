package studentskills.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import studentskills.mytree.Tree;
import studentskills.mytree.TreeHelper;
import studentskills.processor.InputDataProcessor;
import studentskills.util.FileDisplayInterface;
import studentskills.util.FileProcessor;
import studentskills.util.Results;
import studentskills.util.ResultsI;
import studentskills.util.StdoutDisplayInterface;



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

            Tree replica_0 = new Tree();
            Tree replica_1 = new Tree();
            Tree replica_2 = new Tree();

            // Results objects
            ResultsI rs0 = new Results(args[2]);
            ResultsI rs1 = new Results(args[3]);
            ResultsI rs2 = new Results(args[4]);

            TreeHelper tH = new TreeHelper(rs0,rs1,rs2, replica_0, replica_1, replica_2);

            // FileProcessor object
            FileProcessor inputFp = new FileProcessor(args[0]);
            FileProcessor modifyFp = new FileProcessor(args[1]);

            ;

            // InputDataProcessor object with FileProcessor object and ContextI object as
            // parameter.
            InputDataProcessor iDp = new InputDataProcessor(inputFp, modifyFp, tH);
           

            // call of the process method in the InputDataprocessor.
            iDp.InputFileProcess();
            iDp.ModifyFileProcess();
            tH.storeTreeResult();

            printResults((StdoutDisplayInterface) rs0,(StdoutDisplayInterface) rs1, (StdoutDisplayInterface) rs2);
            printResultsToFile((FileDisplayInterface) rs0,(FileDisplayInterface) rs1, (FileDisplayInterface) rs2);

            

        } catch (InvalidPathException | IOException e) {

        }

    }
    private static void printResults(StdoutDisplayInterface ...arr){
        for (StdoutDisplayInterface result : arr) {
            result.writeToStdout();
        }
    }
    private static void printResultsToFile(FileDisplayInterface ...arr) throws IOException {
        for (FileDisplayInterface result : arr) {
            result.writeToFile();
        }
    }
}
package sdd.AJ.painterBSP.util;

/**
 * Exception thrown when an input file does not match the expected format.
 */
public class FileFormatException extends Exception
{
    FileFormatException()
    {
        super("The file was not conform to the expected format.");
    }

}

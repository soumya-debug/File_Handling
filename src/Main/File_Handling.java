package Main;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class File_Handling {

	// Taking runHandling as flag and assigning path
	boolean runHandling=true;
	Scanner sc = new Scanner(System.in);
	Path currentPath;
	Path finalPath;
	String currentFile;


	File_Handling(){
		currentPath = Paths.get(System.getProperty("user.dir"));
		currentFile = "SystemDefault.txt";
		finalPath = Paths.get(currentPath.toString(),currentFile);
	}
	// Flag != true
	public void close() {
		runHandling=false;
	}
	// writeFile method to create a new file
	public void writeFile(Path filePath) {
		String content ="";
		System.out.println("Enter text for file creation:\n");
		content = this.sc.nextLine();// discard trash and enter
		content = this.sc.nextLine();
		System.out.println("Want to add new line \\n ? [Y\\N]");
		if((this.sc.next("[nNyY]")).toLowerCase().contains("y")) {content=content+"\n";}
		try {
			Files.write(filePath, content.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.WRITE);
		} catch (IOException e) {
			runHandling=false;
			e.printStackTrace();
		}
	}
	// apendFile method to append to a existing file
	public void appendFile(Path filePath) {
		String content;
		sc.reset();
		System.out.println("Add text for append to file:\n");
		content = this.sc.nextLine();// discard trash and enter
		content = this.sc.nextLine();
		System.out.println("Add new line \\n ? [Y\\N]");
		if((this.sc.next("[nNyY]")).toLowerCase().contains("y")) {content=content+"\n";}
		try {
			Files.write(filePath, content.getBytes(), StandardOpenOption.APPEND);
		}   catch (NoSuchFileException e) {
			System.out.println("File "+ filePath.toString()+" doesn't exit");
		}
		catch (IOException e) {
			runHandling=false;
			e.printStackTrace();
		}
	}
	// readFile method to read existing file
	public void readFile(Path filePath) {
		try {
			System.out.println(Files.readAllLines(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// deleteFile method to delete an existing file
	public void deleteFile(Path filePath) {
		try {
			Files.delete(filePath);
		}  catch (NoSuchFileException e) {
			System.out.println("File "+ filePath.toString()+" doesn't exit");
		}
		catch (IOException e) {
			runHandling=false;
			e.printStackTrace();
		}
	}
	// config method to meet the current user directory
	public void config() {
		String confInput;
		System.out.println("File Handling Console. @@@ Curent Path: "+this.currentPath.toString()+"\n Please define new Path :");
		confInput = this.sc.next();
		this.currentPath = Paths.get(confInput);
		System.out.println("File Handling Console. @@@ Curent File: "+this.currentFile.toString()+"\n Please define new File :");
		confInput = this.sc.next();
		this.currentFile = confInput;
		this.finalPath = Paths.get(this.currentPath.toString(),this.currentFile);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String userInput;

		File_Handling fileHandler = new File_Handling();

		do {
			System.out.println("File Handling Console. @@@ Curent Path: "+fileHandler.currentPath.toString()+" @@@ Curent File Name: "+fileHandler.currentFile.toString()+"\nOptions:\n1-Create New File\n2-Append to existing File\n3-Read Existing File\n4-Delete existing File.\n5-Define Path and Filename\nQ-Exit.");
			userInput = fileHandler.sc.next("[1-5qQ]");
			switch(userInput.toLowerCase()) {
			case "q":
				fileHandler.close();
				break;
			case "1":
				fileHandler.writeFile(fileHandler.finalPath);
				break;
			case "2":
				fileHandler.appendFile(fileHandler.finalPath);
				break;
			case "3":
				fileHandler.readFile(fileHandler.finalPath);
				break;
			case "4":
				fileHandler.deleteFile(fileHandler.finalPath);
				break;
			case "5":
				fileHandler.config();
				break;
			}
		}
		while(fileHandler.runHandling);

		System.out.println("Terminating after execution of file operations... see you agian!");
		fileHandler.sc.close();

	}

}

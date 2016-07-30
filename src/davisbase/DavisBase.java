/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davisbase;

import command.CreateTableCommand;
import java.util.Scanner;

/**
 *
 * @author Swetha
 */
public class DavisBase {

    /**
     * @param args the command line arguments
     */
    /* This can be changed to whatever you like */
	static String prompt = "davisql> ";

	/* 
	 *  The Scanner class is used to collect user commands from the prompt
	 *  There are many ways to do this. This is just one.
	 *
	 *  Each time the semicolon (;) delimiter is entered, the userCommand 
	 *  String is re-populated.
	 */
	static Scanner scanner = new Scanner(System.in).useDelimiter(";");
	
	
    public static void main(String[] args) {

		/* Display the welcome screen */
		splashScreen();

		/* Variable to collect user input from the prompt */
		String userCommand = ""; 

		while(!userCommand.equals("exit")) {
			System.out.print(prompt);
			/* toLowerCase() renders command case insensitive */
			userCommand = scanner.next().replace("\n", "").replace("\r", "").trim().toLowerCase();
			// userCommand = userCommand.replace("\n", "").replace("\r", "");
			parseUserCommand(userCommand);
                        System.out.println("Command completed");
		}
		System.out.println("Exiting...");


	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	//
	//  Method definitions
	//

	/**
	 *  Display the splash screen
	 */
	public static void splashScreen() {
		System.out.println(line("-",80));
        System.out.println("Welcome to DavisBaseLite"); // Display the string.
		// version();
		System.out.println("Type \"help;\" to display supported commands.");
		System.out.println(line("-",80));
	}
	
	/**
	 * @param s The String to be repeated
	 * @param num The number of time to repeat String s.
	 * @return String A String object, which is the String s appended to itself num times.
	 */
	public static String line(String s,int num) {
		String a = "";
		for(int i=0;i<num;i++) {
			a += s;
		}
		return a;
	}
	
		/**
		 *  Help: Display supported commands
		 */
		public static void help() {
			System.out.println(line("*",80));
			System.out.println("SUPPORTED COMMANDS");
			System.out.println("All commands below are case insensitive");
			System.out.println();
                        System.out.println("\tSHOW TABLES                                      Displays a list of all tables in DavisBase.");
                        System.out.println("\tCREATE TABLE                                     Creates a new table schema, i.e., a new empty table");
                        System.out.println("\tINSERT INTO TABLE                                Inserts a single record into the table");
                        System.out.println("\tDELETE FROM                                      Deletes one or more records from a table");
                        System.out.println("\tUPDATE                                           Modifies one or more records in a table");
			System.out.println("\tSELECT * FROM table_name;                        Display all records in the table.");
			System.out.println("\tSELECT * FROM table_name WHERE rowid = <value>;  Display records whose rowid is <id>.");
			System.out.println("\tDROP TABLE table_name;                           Remove table data and its schema.");
			System.out.println("\tHELP;                                            Show this help information");
			System.out.println("\tEXIT;                                            Exit the program");
			System.out.println();
			System.out.println();
			System.out.println(line("*",80));
		}

	/** Display the DavisBase version */
	public static void version() {
		System.out.println("DavisBaseLite v1.0\n");
	}
	
	
	public static void parseUserCommand (String userCommand) {
		/*
		*  This switch handles a very small list of hardcoded commands of known syntax.
		*  You will want to rewrite this method to interpret more complex commands. 
		*/
		
		/* commandTokens is an array of Strings that contains one token per array element 
		 * The first token can be used to determine the type of command 
		 * The other tokens can be used to pass relevant parameters to each command-specific
		 * method inside each case statement */
                 /*if(!userCommand.endsWith(";")){
                     System.out.println("Syntax error");
                     return;
                 }*/
		String[] commandTokens = userCommand.split(" ");
		
		
		switch (commandTokens[0]) {
			case "select":
				System.out.println("DEBUG: Call your method to process queries");
				break;
                        case "update":
                            System.out.println("Update called");
                            break;
                        case "insert":
                            System.out.println("Insert called");
                            break;
                        case "show":
                            System.out.println("Show called");
                            CommandExecuter.getSingle().showTables();
                            break;
                        case "delete":
                            System.out.println("delete called");
                            break;
                        case "create":
                            System.out.println("create called");
                            CreateTableCommand createCmd = parseCreateTableCmd(userCommand);
                            CommandExecuter.getSingle().createTable(createCmd.getTableName(), createCmd.getColumns());
                            break;
			case "drop":
				System.out.println("DEBUG: Call your method to remove items");
				break;
			case "help":
				help();
				break;
			case "version":
				version();
				break;
			case "exit":
				break;
			default:
				System.out.println("I didn't understand the command: \"" + userCommand + "\"");
                                help();
				break;
		}
	}
        
        private static CreateTableCommand parseCreateTableCmd(String sCmd){
		CreateTableCommand cmd = null;
		sCmd = renameKey(sCmd);
		String tblName = ParsingUtility.getPatternMatchKey("create table (.*?)\\(", sCmd);
		tblName = tblName.trim();		
		if(ParsingUtility.checkIsEmpty(tblName)){			
			cmd = new CreateTableCommand();
			cmd.setTableName(tblName);
			String midStr = ParsingUtility.getParanthesisValue(sCmd);;
			String columsInfors[] = midStr.split(",");
			for(int i = 0; i <columsInfors.length;i++){
				String column=columsInfors[i];
				column = column.trim();
				String colElements[] = column.split(" ");
				ParsingUtility.trim(colElements);
				ColumnStructure sysCol = TableFactory.getSingle().createColumn(tblName,colElements[0].trim());
				sysCol.columnType = colElements[1].trim();
				if(colElements.length > 2){					
					if(colElements[2].equalsIgnoreCase("pri")){
						sysCol.isPrimaryKey = "pri";
					}
					if(colElements[2].equalsIgnoreCase("no")){
						sysCol.isNullable = "no";
					}
				}
				cmd.addColumn(sysCol);
			}			
		}
		return cmd;		
	}
        
        private static String renameKey(String str){
		//replace short int with short
		str = str.replaceAll("short int", "short");
		str = str.replaceAll("long int", "long");
		str = str.replaceAll("primary key", "pri");
		str = str.replaceAll("not null", "no");		
		return str; 
	}
    
}

package nusalDemo;
import java.util.regex.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;



public class EmailClient implements Serializable{
	
	public static HashMap< String,Integer> client_map = new HashMap<String,Integer>();
	public static ArrayList<SendEmail> allEmailsSent = new ArrayList<>();
	 
	public static void main(String[] args) throws FileNotFoundException, IOException {
		 String[] inputArr;//to handle user input

		 creatObjFromFile();
		 restoreEmailObjs();

		 
		 do {
		 String input,to,subject,message;
		 Scanner scanner = new Scanner(System.in);
			System.out.println("Enter option type: \n"
			      + "1 - Adding a new recipient\n"
			      + "2 - Sending an email\n"
			      + "3 - Printing out all the recipients who have birthdays\n"
			      + "4 - Printing out details of all the emails sent\n"
			      + "5 - Printing out the number of recipient objects in the application");
			int option = 0;
			try {
			 option = scanner.nextInt();
			}catch(NumberFormatException e) {
				System.out.println("Wrong input");

				 continue;
			}catch(InputMismatchException e) {
				System.out.println("Wrong input");

				 continue;
			}
			
			Scanner in = new Scanner(System.in);
			
			switch(option){
			      case 1:
                      System.out.println("Input details of a recipient");
			    	  input = in.nextLine();
			    	  String key=null;
			    	  inputArr =input.split(",");
			    	  if ((inputArr.length==4) || (inputArr.length==3)) {
			    		  if(inputArr.length==4) {
			    			  if(mailVarify(inputArr[1])==true) {
			    				  key=inputArr[1];
			    			  }
			    			  else {
			    			  key=inputArr[2];}
			    			  }
			    		  if(inputArr.length==3) {
			    			  key=inputArr[1];} 
			    		  if(client_map.containsKey(key)) {
			    			  System.out.println("Given email already exists");
			    			  break;
			    		  }
			    		  if(mailVarify(key)==true) {
			    			  client_map.put(key,1);
			    			  makeObj(input);}
			    		  else {
			    			  System.out.println("Given email is wrong!!");
			    			  break;  
			    		  }
				    	  try {
		    				    Files.write(Paths.get("clientList.txt"), (input+"\n").getBytes(), StandardOpenOption.APPEND);
	    				  }catch (IOException e) {
		    				    //exception handling left as an exercise for the reader
	    				  }

			    	  }else {
			    		  System.out.println("Wrong input!!!");
			    		 
			    		  
			    	  }

			    	  
			          break;
			      case 2:
			          // input format - email, subject, content 
			    	  System.out.println("To :");
			    	  to = in.nextLine();
			    	  if(mailVarify(to)!=true) {
			    		  System.out.println("Check the given email address");
			    		  break;
			    	  }
			    	  System.out.println("Subject :");
			    	  subject = in.nextLine();
			    	  System.out.println("Message :");
			    	  message = in.nextLine();
			    	  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY/MM/dd");  
			  		  LocalDateTime now = LocalDateTime.now();  
			  		  String currentDate = dtf.format(now).toString();
			    	  SendEmail email1= new SendEmail(to,subject,message,currentDate);
			    	  email1.sendActualEmail();
			    	  writeNewEmail(email1, Boolean.FALSE);

			
			          break;
			      case 3:

			    	  System.out.println("Input the date: ");
			    	  input = in.nextLine();

			    	  printBithdayData(input);
			          break;
			      case 4:
			    	  
			    	  System.out.println("Input the date: ");
			    	  input = in.nextLine();
			    	  byte e_count=0;
			    	  for(SendEmail email: allEmailsSent) {
			    		  if(email.getDate().equals(input)) {
			    			  e_count++;
			    			  System.out.println("To: "+email.getTo()+" Subject: "+email.getSubject());
			    		  }
			    	  }
			    	  if(e_count==0) {System.out.println("No emails have sent on "+input);}

			    			
			          break;
			      case 5:

			          // code to print the number of recipient objects in the application
			    	  System.out.println(Clients.getClientCount()+" Clients");
			          break;
			      default:
			    	  System.out.println("Wrong input!!!");
			    	  continue;
			    	  
			    	  
			}

		
		}while(true);
	}
//function to get the client type from a given input 
	public static int clientType(String email) {

		String[] inputArr = email.split(",");

		if (mailVarify(inputArr[1])) {
			return 1;
		}else if (mailVarify(inputArr[2])) {
			return 2;
		}else {
			return -1;
		}
		
		
	}
	//function to check whether the given email is valid
	public static boolean mailVarify(String address) {
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}"; 
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(address);
		return matcher.matches();
	}
	//function to check whether two date are the same
	public static boolean dateVarify(String date) {
		String readedDate, currentDate;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd");  
		LocalDateTime now = LocalDateTime.now();  
		currentDate = dtf.format(now).toString();
		boolean same =false;
		readedDate =date;
		if (currentDate.equals(readedDate)) {
			same =true;
		}
		
		return same;
		
	}
//function to read birthdays from clientList.txt
	public static void printBithdayData(String inputdate) {
		
		inputdate = inputdate.substring(5);
		FileInputStream fstream = null;
		
		try {
			fstream = new FileInputStream("clientList.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	  		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	  		String strLine = null;
			try {
				strLine = br.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		if (strLine != null) {
			try {
				int b_count=0;
				while ((strLine = br.readLine()) != null) {
					
					String[] inputArr = strLine.split(",");
					if (inputArr.length == 4) {


						if (inputArr[3].substring(5).equals(inputdate)) {
							b_count++;
							//	    	      TODO : genarate email
							if (clientType(strLine) == 1) {
								System.out.println(inputArr[0]);
							} else if (clientType(strLine) == 2) {
								System.out.println(inputArr[0]);
							}
						}
					}
				}
				if(b_count==0) {System.out.println("No Birthdays on this date");}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fstream.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		else {System.out.println("Client list is empty");}
			
	}
	//to create client objects 
	public static  void makeObj(String line) throws IOException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY/MM/dd");  
  		LocalDateTime now = LocalDateTime.now();  
  		String currentDate = dtf.format(now).toString();

		String[] lineArr = line.split(",");
        if (lineArr.length == 4) {

      	  int option2 = clientType (line);
      	  if (option2 ==1) {
      		  Friends newclient = new OfficeFriends(lineArr[0],lineArr[1],lineArr[3],lineArr[2]);

      		  if (dateVarify(newclient.getBrithday())) {
      			SendEmail newemail =new SendEmail(newclient.getEmail(),"Birthday wish",newclient.getWish(),currentDate);
      			newemail.sendActualEmail();
      			writeNewEmail(newemail, Boolean.FALSE);
      		  }
      		  
      	  }
      	  else if (option2 ==2) {
      		  Friends newclient = new PersonalFriends(lineArr[0],lineArr[2],lineArr[3],lineArr[1]);

      		if (dateVarify(newclient.getBrithday())) {
      			SendEmail newemail =new SendEmail(newclient.getEmail(),"Birthday wish",newclient.getWish(),currentDate);
      			newemail.sendActualEmail();
      			writeNewEmail(newemail, Boolean.FALSE);
      		  }
      	  }
      	  
	    }
        else if (lineArr.length == 3) {
        	Clients newclient = new OfficalCliants(lineArr[0],lineArr[1],lineArr[2]);
        	}
        else {System.out.println("Wrong input in the clientFile ");}
	}
	//function to create objects from clientList.txt when program restart
	public static void creatObjFromFile() {
		try {
			BufferedReader read = new BufferedReader(new FileReader("clientList.txt"));
			String line = read.readLine();
			
			while ((line != null)) {
				makeObj(line);
				line = read.readLine();}
			read.close();
			
		    }
			catch(IOException ex){
				ex.printStackTrace();
				}
		
	}
	
//function to restore email objects in the array list when the program restart
	public static void restoreEmailObjs() throws IOException {
		
		FileInputStream filein = new FileInputStream("emails.ser"); 		
		ObjectInputStream in = new ObjectInputStream(filein);  		
		try {
			allEmailsSent = (ArrayList<SendEmail>) in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  		
		in.close(); 									
		filein.close();
	}
	
	public static void writeNewEmail(SendEmail sendemail, Boolean option) throws IOException  {
		
		
		FileInputStream filein = new FileInputStream("emails.ser"); 		
		ObjectInputStream in = new ObjectInputStream(filein);  		
		try {
			allEmailsSent = (ArrayList<SendEmail>) in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  		
		in.close(); 									
		filein.close(); 
		if (option) {
			return;
		}
		
		allEmailsSent.add(sendemail);
			FileOutputStream fileout = new FileOutputStream("emails.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileout);
			out.writeObject(allEmailsSent);  
			out.close();  
			fileout.close();
				
	}
}
	

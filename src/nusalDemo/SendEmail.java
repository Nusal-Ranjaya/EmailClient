package nusalDemo;
import java.util.Properties;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; 
///////////////////////////////////
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmail implements Serializable{
	private String to;
	private String subject;
	private String text;
	private String date;
	
	public SendEmail() {
		super();
	}
	

	public SendEmail(String to, String subject, String text) {
		super();
		this.to = to;
		this.subject = subject;
		this.text = text;
	}

	


	public SendEmail(String to, String subject, String text, String date) {
		super();
		this.to = to;
		this.subject = subject;
		this.text = text;
		this.date = date;
	}
	

	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public void sendActualEmail( ) {

	
	//my email
		String from="nuswarwick@gmail.com";
	//host
		String host="smtp.gmail.com";
	//properties
		Properties prop=System.getProperties();
		
	//setting the server
		prop.put("mail.smtp.host",host);
		prop.put("mail.smtp.port","465");
		prop.put("mail.smtp.ssl.enable","true");
		prop.put("mail.smtp.auth","true");
		
		
	//passing user name and password to session object
		Session session=Session.getInstance(prop,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("nuswarwick@gmail.com","xpbpjfnqjkzlqmzy");
			}
		});	
		
		session.setDebug(true);
		try {
			MimeMessage message= new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setSubject(subject);
			message.setText(text);
			
			Transport.send(message);
			DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime date = LocalDateTime.now();

			
		}catch(MessagingException mex) {
			mex.printStackTrace();
		}
			}
	public static void SendInstantEmail(String to, String subject, String text ) {
		
	
	//my email
		String from="nuswarwick@gmail.com";
	//host
		String host="smtp.gmail.com";
	//properties
		Properties prop=System.getProperties();
		
	//setting the server
		prop.put("mail.smtp.host",host);
		prop.put("mail.smtp.port","465");
		prop.put("mail.smtp.ssl.enable","true");
		prop.put("mail.smtp.auth","true");
		
		
	//passing user name and password to session object
		Session session=Session.getInstance(prop,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("nuswarwick@gmail.com","xpbpjfnqjkzlqmzy");
			}
		});	
		
		session.setDebug(true);
		try {
			MimeMessage message= new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setSubject(subject);
			message.setText(text);
			
			Transport.send(message);
			DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime date = LocalDateTime.now();

			System.out.println("email sent ");
			
		}catch(MessagingException mex) {
			mex.printStackTrace();
		}
			}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}

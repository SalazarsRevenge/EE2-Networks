/*
 * Created on 01-Mar-2016
 */
package common;

import java.io.Serializable;

/**
 * Utility class that encapsulates the message information to
 * be passed from client to server.  Information can be extracted
 * or constructed as a String for use by the UDP example.
 *
 */
public class MessageInfo implements Serializable {

	public static final long serialVersionUID = 52L;

	public int totalMessages = 0;
	public int messageNum = 0;

	public MessageInfo(int total, int msgNum) {
		totalMessages = total;
		messageNum = msgNum;
	}

	public MessageInfo(String msg) throws Exception {
		String[] fields = msg.split(";");
		/*
		for(int i = 0; i < fields.length; i++) {
			System.out.println(fields[i]);
		}
		System.out.println(fields.length);
		*/
		if (fields.length != 2) {
			//System.out.println("this is an error cause of the 2 thingy");
			throw new Exception("MessageInfo: Invalid string for message construction: " + msg);
		}

		try{
			totalMessages = Integer.parseInt(fields[0].trim());
			//System.out.println(totalMessages);
			messageNum = Integer.parseInt(fields[1].trim());
			//System.out.println(messageNum);
		} catch (Exception e){
			System.out.println("parse string error");
			System.exit(-1);
		}

	}

	public String toString(){
		return new String(totalMessages+";"+messageNum+"\n");
	}


}

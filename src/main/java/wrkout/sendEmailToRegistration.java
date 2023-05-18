package wrkout;

import java.util.Random;

import javax.servlet.http.HttpSession;

public class sendEmailToRegistration {

	public static void sendOtpForUser(String email, HttpSession httpSession) {
		Random rand = new Random();
        int maxDigits = 6;
        int randomNumber = rand.nextInt((int) Math.pow(10, maxDigits));
        
        EmailImplementation.send(email, randomNumber);
        
        httpSession.setAttribute("OTP", randomNumber);
		
	}

}

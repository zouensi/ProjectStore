package com.zouensi.threadhandler;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.zouensi.utils.MailUtils;
/**
 * 
 * @author zouensi
 * @date 2017年7月7日
 * 描述:邮件任务
 */
public class EmailRunnable implements Runnable{
	private String emailUrl;
	private String emailMsg;
	public  EmailRunnable(String emailUrl,String emailMsg) {
		this.emailUrl = emailUrl;
		this.emailMsg = emailMsg;
	}
	@Override
	public void run() {
		try {
			MailUtils.sendMail(emailUrl, emailMsg);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}

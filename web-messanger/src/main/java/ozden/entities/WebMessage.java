package ozden.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class WebMessage implements Serializable{
	
	@Id
	@GeneratedValue
	private Integer messageId;
	private String sender;
	private String receiver;
	private String messageContent;
	private Date createDate;
	
	public WebMessage(){
		
	}

	
	public WebMessage(Integer messageId, String sender, String receiver, String messageContent, Date createDate) {
		super();
		this.messageId = messageId;
		this.sender = sender;
		this.receiver = receiver;
		this.messageContent = messageContent;
		this.createDate = createDate;
	}


	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	@Override
	public String toString() {
		return "WebMessage [messageId=" + messageId + ", sender=" + sender + ", receiver=" + receiver
				+ ", messageContent=" + messageContent + ", createDate=" + createDate + "]";
	}
	
	
}

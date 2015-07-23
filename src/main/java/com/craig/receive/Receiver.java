package com.craig.receive;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {
	
	public static void main(String[] args) {
		
		ConnectionFactory connectionFactory;
		
		Connection connection = null;
		
		Session session = null;
		
		Destination  destination;
		
		MessageConsumer messageConsumer;
		
		connectionFactory = new ActiveMQConnectionFactory(  
                ActiveMQConnection.DEFAULT_USER,  
                ActiveMQConnection.DEFAULT_PASSWORD, "tcp://192.168.5.240:61616");  
		
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(Boolean.TRUE,  
	                    Session.AUTO_ACKNOWLEDGE);
			
			destination = session.createQueue("FirstQueue");
			messageConsumer = session.createConsumer(destination);
			
			while (true) {  
                // 设置接收者接收消息的时间，为了便于测试，这里谁定为100s  
                TextMessage message = (TextMessage) messageConsumer.receive(100);  
                if (null != message) {  
//                	message.getText();
                    System.out.println("收到消息" + message.getText());  
                } else {  
                    break;  
                }  
            }  
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				try {
					if(connection != null)
						connection.close();
					
					if(null != null)
						session.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
}

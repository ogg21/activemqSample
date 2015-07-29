package com.craig.send;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {
	
	private static final int SEND_NUMBER = 5;
	
	public static void main(String[] args) {
		ConnectionFactory  connectionFactory;
		
		Connection connection = null; 
		
		Session session = null;
		
		Destination  destination;
		
		MessageProducer messageProducer;
		 
		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD,"tcp://192.168.5.240:61616");
		
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			
			session = connection.createSession(Boolean.TRUE,  
	                    Session.AUTO_ACKNOWLEDGE);  
			
			destination = session.createQueue("FirstQueue");  
			
			messageProducer = session.createProducer(destination);
			messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
			
			sendMessage(session,messageProducer);
			session.commit();
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			
			try {
				if(null != connection)
					connection.close();
				if(session != null)
					session.close();
			} catch (JMSException e) {
				
				e.printStackTrace();
			}
		}
		
		 
	}
	
	
	public static void sendMessage(Session session, MessageProducer producer)  
            throws Exception {  
        for (int i = 1; i <= 10; i++) {  
            TextMessage message = session.createTextMessage("ActiveMq 发送的消息"  
                    + i);  
            // 发送消息到目的地方  
  
//            System.out.println("发送消息：" + "ActiveMq 发送的消息" + i);  
            producer.send(message);  
        }  
    }  
	
}

package com.craig.send;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
    	
		new Thread(){
			public void run(){
				while(true){
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
						System.out.println("连接成功");
					} catch (JMSException e) {
						System.out.println("连接失败");
						e.printStackTrace();
					}finally{
						if(null != connection)
							try {
								connection.close();
							} catch (JMSException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				}
			}
			
		}.start();
		
    }
}

package com.test;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model.User;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			User userObj=(User) context.getBean("userBean");
			userObj.setName("my name is little sheep!!");
			System.out.println(userObj.getName());
	}

}

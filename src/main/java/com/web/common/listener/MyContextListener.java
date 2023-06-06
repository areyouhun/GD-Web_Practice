package com.web.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.web.common.AESEncryptor;

@WebListener
public class MyContextListener implements ServletContextListener {

    public MyContextListener() {}

    public void contextDestroyed(ServletContextEvent sce)  {}

    public void contextInitialized(ServletContextEvent sce)  { 
    	new AESEncryptor();
    }
	
}

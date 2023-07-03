package com.example.admin;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

//@Named
@ManagedBean(name = "helloWorld", eager = true)
//@RequestedScope
public class HelloWorld implements Serializable{

   private static final long serialVersionUID = 1L;

   //@Inject
   private String str = "Hello World!";

	public HelloWorld() {
      System.out.println("Page HelloWorld started!");
   }

   public String getMessage() {
      return this.str;
   }
}
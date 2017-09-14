package com.example.ajay.comedyproject.task.src;

import android.util.Log;

import java.util.Date;

public class Main {

	public static void main(String[] args) {
		
		StudentGroup grp=new StudentGroup(10);

		for(int i=0;i<10;i++)
		{
			grp.add(new Student(i,"ajay"+i,new Date(),1.2+i),i);
		}
		for(int i=0;i<10;i++)
		{
			Log.d("main",grp.getStudent(i+1).toString());
		}


	}

}

package com.example.ajay.comedyproject.task.src;


import java.util.ArrayList;
import java.util.Date;

/**
 * A fix-sized array of students
 * array length should always be equal to the number of stored elements
 * after the element was removed the size of the array should be equal to the number of stored elements
 * after the element was added the size of the array should be equal to the number of stored elements
 * null elements are not allowed to be stored in the array
 * 
 * You may add new methods, fields to this class, but DO NOT RENAME any given class, interface or method
 * DO NOT PUT any classes into packages
 *
 */
public class StudentGroup implements StudentArrayOperation {

	private Student[] students;
	
	/**
	 * DO NOT remove or change this constructor, it will be used during task check
	 * @param length
	 */
	public StudentGroup(int length) {
		this.students = new Student[length];
	}

	@Override
	public Student[] getStudents() {
		return students;
	}

	@Override
	public void setStudents(Student[] students) {
		if (students == null) {
			throw new IllegalArgumentException();
		}
		this.students=students;
	}

	private void checkIndex(int index)
	{
		if(index<0 || index>=students.length)
			throw new IllegalArgumentException();
	}
	private void checkStudent(Student student)
	{
		if(student==null)
			throw new IllegalArgumentException();
	}
	@Override
	public Student getStudent(int index) {
		checkIndex(index);
		return students[index];
	}

	@Override
	public void setStudent(Student student, int index) {
		checkIndex(index);
		checkStudent(student);
		students[index]=student;
	}

	@Override
	public void addFirst(Student student) {
		add(student,0);
	}

	@Override
	public void addLast(Student student) {
		add(student,students.length-1);
	}

	@Override
	public void add(Student student, int index) {
		checkStudent(student);
		checkIndex(index);

		Student[] newStudents= new Student[students.length+1];

		for(int i=0,j=0;i<students.length+1;i++)
		{
			if(i==index)
			{
				newStudents[i]=student;
				continue;
			}
			newStudents[i]=students[j++];
		}

		this.students=newStudents;
	}

	@Override
	public void remove(int index) {
		checkIndex(index);

		Student[] newStudents=new Student[students.length-1];

		for(int i=0,j=0;j<students.length;i++,j++)
		{
			if(j==index)
			{
				i--;
				continue;
			}
			newStudents[i]=students[j];
		}
		students=newStudents;
	}

	@Override
	public void remove(Student student) {
		checkStudent(student);

		Student[] newStudents=new Student[students.length-1];

		for(int i=0,j=0;j<students.length;i++,j++)
		{
			if(students[j].equals(student))
			{
				i--;
				continue;
			}
			newStudents[i]=students[j];
		}
		students=newStudents;
	}

	@Override
	public void removeFromIndex(int index) {
		checkIndex(index);

		removePartOfArray(index+1,students.length-1);
	}

	@Override
	public void removeFromElement(Student student) {
		checkStudent(student);
		int j=0;

		for(j=0;j<students.length;j++)
		{
			if(students[j].equals(student))
			{
				break;
			}
		}
		if(j==students.length)
			return;

		removePartOfArray(j+1,students.length-1);
	}

	@Override
	public void removeToIndex(int index) {
		checkIndex(index);

		removePartOfArray(0,index-1);
	}

	@Override
	public void removeToElement(Student student) {
		checkStudent(student);
		int j=0;

		for(j=students.length;j>=0;j--)
		{
			if(students[j].equals(student))
			{
				break;
			}
		}
		if(j==-1)
			return;

		removePartOfArray(0,j-1);
	}

	@Override
	public void bubbleSort() {
		int i=0,j=0;

		for(i=0;i<students.length;i++)
		{
			for(j=0;j<students.length-1;j++)
			{
				if(students[j].compareTo(students[j+1])>0)
				{
					Student dummy=students[j+1];
					students[j+1]=students[j];
					students[j]=dummy;
				}
			}
		}
	}

	@Override
	public Student[] getByBirthDate(Date date) {
		if(date==null)
			throw new IllegalArgumentException();

		ArrayList<Student> resultStudents=new ArrayList<>();
		for(int j=0;j<students.length;j++)
		{
			if(date.getTime()-students[j].getBirthDate().getTime()>0)
			{
				resultStudents.add(students[j]);
			}
		}
		return resultStudents.toArray(new Student[resultStudents.size()]);
	}

	@Override
	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
		if(firstDate==null||lastDate==null)
			throw new IllegalArgumentException();

		ArrayList<Student> resultStudents=new ArrayList<>();
		for(int j=0;j<students.length;j++)
		{
			if((lastDate.getTime()-students[j].getBirthDate().getTime()>0)&&(firstDate.getTime()-students[j].getBirthDate().getTime())<0)
			{
				resultStudents.add(students[j]);
			}
		}
		return resultStudents.toArray(new Student[resultStudents.size()]);
	}

	@Override
	public Student[] getNearBirthDate(Date date, int days) {
		Date firstDate=(Date)date.clone();
		date.setTime(date.getTime()+((long)days*86400*1000));
		return getBetweenBirthDates(firstDate,date);
	}

	@Override
	public int getCurrentAgeByDate(int indexOfStudent) {
		checkIndex(indexOfStudent);

		Date date=students[indexOfStudent].getBirthDate();
		long diff=new Date().getTime()-date.getTime();
		return (int)(diff/((long)365*86400*1000));
	}

	@Override
	public Student[] getStudentsByAge(int age) {

		ArrayList<Student> studentsList=new ArrayList<>();

		for(int j=0;j<students.length;j++)
		{
			Date date=students[j].getBirthDate();
			long diff=new Date().getTime()-date.getTime();
			int studentAge=(int)(diff/(365*86400*1000));
			if(studentAge==age)
				studentsList.add(students[j]);
		}
		return studentsList.toArray(new Student[studentsList.size()]);
	}

	@Override
	public Student[] getStudentsWithMaxAvgMark() {
		Double maxAvgAMarks=0.0;
		for(int i=0;i<students.length;i++){
			if(students[i].getAvgMark()>maxAvgAMarks)
				maxAvgAMarks=students[i].getAvgMark();
		}

		ArrayList<Student> newStudents=new ArrayList<>();

		for(int i=0;i<students.length;i++)
		{
			if(Math.abs(maxAvgAMarks-students[i].getAvgMark())<0.05)
				newStudents.add(students[i]);
		}
	return newStudents.toArray(new Student[newStudents.size()]);
	}

	@Override
	public Student getNextStudent(Student student) {
		checkStudent(student);

		for(int i=0;i<students.length;i++)
		{
			if(student.equals(students[i]))
				if(i+1<students.length)
					return students[i+1];
		}
		return null;
	}

	private void removePartOfArray(int startIndex,int lastIndex)
	{
		Student[] newStudents=new Student[students.length-(lastIndex-startIndex+1)];

		for(int newIdx=0,oldIdx=0;oldIdx<students.length;oldIdx++)
		{
			if(oldIdx>=startIndex&&oldIdx<=lastIndex)
			{
				continue;
			}
			newStudents[newIdx++]=students[oldIdx];
		}
		students=newStudents;
	}
}

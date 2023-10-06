package com.hollow.saint;

import java.io.*;

public class Student extends Person implements Serializable {
	public String no;
	public String sex;

	public Student(String name, String no, String sex) {
		this.name = name;
		this.sex = sex;
		this.no = no;
	}

	public static void main(String[] args) throws IOException {
		Student s1 = new Student("tom", "1", "male");
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try (ObjectOutputStream output = new ObjectOutputStream(buffer)) {
			output.writeObject(s1);
		}
		ByteArrayInputStream b = new ByteArrayInputStream(buffer.toByteArray());
		try (ObjectInputStream input = new ObjectInputStream(b)) {
			Student s2 = (Student) input.readObject();
			System.out.println(s2);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

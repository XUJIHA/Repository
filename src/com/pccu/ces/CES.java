package com.pccu.ces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class CES extends Student {

	private static ArrayList<Student> list ;
	
	private static ArrayList<Student> ReadFile (String filepath) {
		Scanner scanner = null;
		int mId = 0;
		int mChi = 0;
		int mEng = 0;
		int mMath = 0;
		float mAvg = 0;
		String mName = "";
		try {
			 scanner = new Scanner(new File("src/score.txt"));
			 while (scanner.hasNext()) {
			 // handle customer name
			 mName = scanner.next();
			
			 // handle amount
			 mAvg = scanner.nextInt();
			// mId += mAvg;
			
			 System.out.println("Name: " + mName +
			 " " + "Amount: " +
			 mAvg);
			 }
			 } catch (FileNotFoundException e) {
			 e.printStackTrace();
			 } finally {
				 scanner.close();
				  }
				 System.out.println("Total: " + mId);

		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader("src/abstract1.txt");
			bufferedReader = new BufferedReader(fileReader);
			String flag = "";
					
					}catch (FileNotFoundException e) {
						 e.printStackTrace();
						 } catch (IOException e) {
						 e.printStackTrace();
						 }finally {
							 if (bufferedReader != null) {
								try {
								bufferedReader.close();
								 } catch (IOException e) {
								 e.printStackTrace();
								  }
							}
						 }if (fileReader != null) {
							  try {
								  fileReader.close();
								  } catch (IOException e) {
								  e.printStackTrace();
								  }  
						 }
		/**
		 *  Your Read File Work
		 */
		
		return list;
	}
	
	private static void WriteFile (ArrayList<Student> list, String filepath) {
		
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        String record = "";
        
        try {
			fileWriter = new FileWriter(filepath);
			printWriter = new PrintWriter(fileWriter);
	        
	        Iterator<Student> it = list.iterator();
	        while (it.hasNext()) {
	        	Student s = it.next();
	        	record = "";
	        	record = s.getId()   + " " + 
	        	         s.getName() + " " + 
	        			 s.getChi()  + " " + 
	        	         s.getEng()  + " " + 
	        			 s.getMath() + " " +
	        	         s.mAvg ;
	        	
	        	printWriter.println(record);
			}
	        
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            
        	if (printWriter != null) {
                printWriter.close();
            }
            
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	private static void ScoreEnrollment () {
		
		int cnt = 3;
		for (int i=0; i<cnt; i++) {
			Student s = new Student();

			System.out.println("Enter Id");
			s.setId(new Scanner(System.in).nextInt());
			
			System.out.println("Enter Name");
			s.setName(new Scanner(System.in).nextLine());
	
			System.out.println("Enter Chinese Score");
			s.setChi(new Scanner(System.in).nextInt());
	
			System.out.println("Enter Englist Score");
			s.setEng(new Scanner(System.in).nextInt());
	
			System.out.println("Enter Math Score");
			s.setMath(new Scanner(System.in).nextInt());
			
			s.calAvg();
	
			list.add(s);
			
		}
		WriteFile(list, "src/score.txt");
		
	}
	
	private static void InqueryByIDFromFile (String filepath) {
		int id;
		System.out.println("Enter ID :");
		id = new Scanner(System.in).nextInt();
		
		list = ReadFile(filepath);

		Iterator<Student> it = list.iterator();
		while (it.hasNext()) {
			Student s = it.next();
			if (s.getId() == id) {
				System.out.println("Name: " + s.getName() + " " + "Avg: " + s.mAvg);
			}
		}
		System.out.println("*************************");
	}
	
	private static void InqueryByID () {
		int id;
		System.out.println("Enter ID :");
		id = new Scanner(System.in).nextInt();
		
		Iterator<Student> it = list.iterator();
		while (it.hasNext()) {
			Student s = it.next();
			if (s.getId() == id) {
				System.out.println("Avg: " + s.mAvg);
			}
		}
		System.out.println("*************************");
	}
	
	private static void ListByScore () {
		list.sort(new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				// TODO Auto-generated method stub
				if (o1.mAvg < o2.mAvg)
					return -1;
				else if (o1.mAvg == o2.mAvg) {
					return 0;
				} else {
					return 1;
				}
			}
			
		});
		Collections.reverse(list);
		
		Iterator<Student> it = list.iterator();
		while (it.hasNext()) {
			Student s = it.next();
			System.out.println("Id: " + s.getId() + " Name: " + s.getName() + " Avg : " + s.mAvg);
			System.out.println("-----------------------------");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean exit = false;
		list = new ArrayList<Student>();
		while (exit != true) {
			int option ;
			System.out.println("");
			System.out.println("");
			System.out.println("*************************");
			System.out.println("Score Enrollment Press 1");
			System.out.println("Inquery by ID 	 Press 2");
			System.out.println("List by Score    Press 3");
			System.out.println("Exit             Press 0");
			System.out.println("*************************");
			
			option = new Scanner(System.in).nextInt();		
			switch (option) {
			case 1:
				ScoreEnrollment ();
				break;
			case 2:
				//InqueryByID ();
				InqueryByIDFromFile("src/score.txt");
				break;
			case 3:
				ListByScore ();
				break;
				
			case 0:
			default:
				exit = true;
				break ;
			}
		}		
		System.out.println("Bye !");
	}
}

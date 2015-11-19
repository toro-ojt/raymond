import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors
import java.util.stream.Stream



class MainClass {
	static void main(String...args){
		
		
		//BufferedReader in = new BufferedReader(new FileReader("trial.rtf"));

		Stream<String> stream = Files.lines(Paths.get('test1.txt'))

		String fileContents = new File('test1.txt').text
		
		def list = (ArrayList<?>) stream.collect(Collectors.toList());
		
		def line = (String) list.get(1)

		def dual = new String[10][10]
		for(aa in 0..5){
			def a = list.get(aa)
			def b = a.split(',')
			int bb = 0
			b.each{

				dual[aa][bb] = it
				bb++
			}
		}
		

		println 'loaded class ' + dual[1][0] + ', section ' + dual[1][1]
		


		
		
		def other = new others()
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
		here:
		print '>'
		def input = br.readLine()
		
		switch (input){
			case 'help':
				other.help()
				break
			
			case 'student':
				other.students(dual)
				
				break
				
			case 'grades':
				other.grade(dual)
				break
				
			case 'assignments':
				other.assignment()
				break
			
			case 'exit':
				println 'CLEAR'
				System.exit(0)
				break
		}

		continue here
		

	}
}

class others{
	
	def help()
	{
		println 'Accepted Commands: '
		println 'exit'
		println 'help'
		println 'load [filename]'
		println 'assignments'
		println 'grades'
		println 'student [student name]'
		println 'assignment [assignment name]'
	}
	
	//students
	def students(def array)
	{
		
		println 'First Name \t\t Last Name \t\t Points \t Grade'
		println '---------- \t\t --------- \t\t ------ \t -----'
		for(i in 2..5)
		{
			println array[i][0] + '  \t\t ' + array[i][1] + ' \t\t ' + studentpoints(array[i]) + ' \t\t ' + gradeEquivalent(studentpoints(array[i]))
		}

	}
	
	def studentpoints(def array)
	{
		int total = 0
		for(AA in 2..6){
			total += (array[AA] as int)
		}
		return total
	}
	
	def gradeEquivalent(int grade)
	{
		if(grade >= 90) return 'A'
		if(grade >= 80 && grade < 90) return 'B'
		if(grade >= 70 && grade < 80) return 'C'
		if(grade >= 60 && grade < 70) return 'D'
		if(grade < 60)	return 'F'
	}
	
	
	//assignment
	def assignment()
	{
		println 'Assignments for TITLE, Section'
		println '\nAssignment \t\t Points'
		println '----------  \t\t ------'
		println 'essay 1 \t\t 5'
		println 'essay 2 \t\t 20'
		println 'test 1 \t\t\t 5'
		println 'test 2 \t\t\t 20'
		println 'final   \t\t 50'
	}
	
	def assignmentTotal(input, array)
	{
		int caseI=0
		
		switch(input){
			case 'essay 1':
				caseI = 2
				assignmentSummary(array, caseI)
				break
				
			case 'essay 2':
				caseI = 3
				break
				
			case 'test 1':
			
				break
				
			case 'test 2':
			
				break
				
			case 'final':
				
				break 
		}
	}
	
	def assignmentSummary(array, number){
		int A = 0, B = 0, C = 0, D = 0, F = 0
		
		
	}
	
	
	//grades
	def grade(array)
	{
		
		println 'Grade breakdown for Philosophy 101, section 1\n'
		int total, A=0, B=0,C=0,D=0,F=0, iii=0
		def sorts = new int[4]
		for(aa in 2..5)
		{
			def too = new others()
			total += studentpoints(array[aa])
			sorts[iii] = too.studentpoints(array[aa])
			iii++
			if(gradeEquivalent(studentpoints(array[aa])) == 'A') { A++ }
			if(gradeEquivalent(studentpoints(array[aa])) == 'B') { B++ }
			if(gradeEquivalent(studentpoints(array[aa])) == 'C') { C++ }
			if(gradeEquivalent(studentpoints(array[aa])) == 'D') { D++ }
			if(gradeEquivalent(studentpoints(array[aa])) == 'F') { F++ }
		}
		def temp
		for(writes in 0..sorts.length){
			for(aaa in 0..sorts.length-1)
			{
				if(sorts[aaa] > sorts[aaa+1])
				{
					temp = sorts[aaa + 1];
					sorts[aaa + 1] = sorts[aaa];
					sorts[aaa] = temp;
				}
			}
		}
		int ave = total/4
		println 'Low: '+ sorts[0]
		println 'High: ' + sorts[sorts.length-1]
		println 'Ave: ' + ave
		
		println 'A: ' + A
		println 'B: ' + B
		println 'C: ' + C
		println 'D: ' + D
		println 'F: ' + F
	}
	
}//end other

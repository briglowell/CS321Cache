import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Driver class to test Cache class. This class can be used to implement either a single
 * or double cache.
 * 
 * User Input must be one of the following command line options as follows:
 *   First:
 *    -javac Test.java
 *   Then either:
 *    -java Test 1 <cache size> <input textfile name>
 *    -java Test 2 <1st level cache size> <2nd level cache size> <input textfile name>
 *    
 * @author BrigLowell
 *
 */
public class Test {
	
	private static double HR, HR1, HR2;								//total, 1st level, and 2nd level cache Hit ratios
	private static int NH, NH1, NH2;								//total, 1st level, and 2nd level number of cache hits
	private static int NR, NR1, NR2;								//total, 1st level, and 2nd level cache hit references
		
	private static int cache1Max;									//maximum size of cache1
	private static int cache2Max;									//maximum size of cache2
	
	static Cache<Object> cache1;
	static Cache<Object> cache2;
	
	static DecimalFormat df = new DecimalFormat("#.##");			//Decimal format rounded to hundredth place
	
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		/*
		 * Runs cache with only one level
		 */
		if(Integer.parseInt(args[0]) == 1) {
			cache1Max = Integer.parseInt(args [1]);
			
			try {
				cache1 = new Cache<Object>(cache1Max);
				
				File file = new File(args[2]); 
				  
				BufferedReader br = new BufferedReader(new FileReader(file)); 
		
				String line; 
				StringTokenizer st;
				Object s;
				
				while ((line = br.readLine()) != null) {
					
					st = new StringTokenizer(line, "\t ");
					
					while (st.hasMoreElements()) {
						s = st.nextElement();
						if(cache1.hasObject(s)) {
							NH++;
							NR1++;
						}
						NR++;
						cache1.addObject(s);
//						System.out.println(s);
					}
				}
				HR = (double)NH/NR;
				
				System.out.println("First level cache with " + cache1Max + " entries has been created");
				System.out.println("Second level cache with " + cache2Max + " entries has been created");
				System.out.println();
				System.out.println("-------------------------------------------------------------");
				System.out.println();
				System.out.println("The number of global references: " + NR);
				System.out.println("The number of global cache hits: " + NH);
				System.out.println("The global hit ratio           : " + df.format(HR));
				System.out.println();
				System.out.println("-------------------------------------------------------------");
				
			} catch(IndexOutOfBoundsException e){
				System.out.println(e);
			} catch(NoSuchElementException e) {
				System.out.println(e);
			} catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		/*
		 * Runs Cache with two level
		 */
		else if(Integer.parseInt(args[0]) == 2) {
			cache1Max = Integer.parseInt(args [1]);
			cache2Max = Integer.parseInt(args [2]);
			
			if (cache1Max >= cache2Max) {
				throw new IndexOutOfBoundsException("Cache 2 must be greater than cache 1.");
			}
			
			try {
				File file = new File(args[3]); 
				
				cache1 = new Cache<Object>(cache1Max);
				cache2 = new Cache<Object>(cache2Max);
				  
				BufferedReader br = new BufferedReader(new FileReader(file)); 
		
				String line; 
				StringTokenizer st;
				Object s;
				
				while ((line = br.readLine()) != null) {
					
					st = new StringTokenizer(line, "\t ");
					
					while (st.hasMoreElements()) {
						s = st.nextElement();
						if(cache1.hasObject(s)) {
							NH++;
							NH1++;
							NR1++;
						}
						else if(cache2.hasObject(s)) {
							NR2++;
							NH2++;
							NH++;
							NR1++;
						}else {
							NR1++;
							NR2++;
						}
						NR++;
						
						cache1.addObject(s);
						cache2.addObject(s);
//						System.out.println(s);
					}
				} 
				HR = (double)(NH1 + NH2)/NR1;
				HR1 = (double)NH1/NR1;
				HR2 = (double)NH2/NR2;

				System.out.println("First level cache with " + cache1Max + " entries has been created");
				System.out.println("Second level cache with " + cache2Max + " entries has been created");
				System.out.println();
				System.out.println("-------------------------------------------------------------");
				System.out.println();
				System.out.println("The number of global references: " + NR);
				System.out.println("The number of global cache hits: " + NH);
				System.out.println("The global hit ratio           : " + df.format(HR));
				System.out.println();
				
				System.out.println("The number of 1st-level references: " + NR1);
				System.out.println("The number of 1st-level cache hits: " + NH1);
				System.out.println("The 1st-level cache hit ratio     : " + df.format(HR1));
				System.out.println();
				
				System.out.println("The number of 2nd-level references: " + NR2);
				System.out.println("The number of 2nd-level cache hits: " + NH2);
				System.out.println("The 2nd-level cache hit ratio     : " + df.format(HR2));
				System.out.println();
				System.out.println("-------------------------------------------------------------");
				
//				System.out.println();
//				for (int i = 0; i < cache1.getSize(); i++) {
//					System.out.println(cache1.getObject(i));
//				}
//				System.out.println();
//				for (int i = 0; i < cache2.getSize(); i++) {
//					System.out.println(cache2.getObject(i));
//				}
	
			} catch(IndexOutOfBoundsException e){
				System.out.println(e);
			} catch(NoSuchElementException e) {
				System.out.println(e);
			} catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}

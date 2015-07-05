package view;

import java.util.ArrayList;
import java.util.Scanner;

import dto.Article;
public class UI {
	int width=79;
	/**
	 * To show sequence of symbols in the first line of table 
	 */
	public void line1(){
		for(int i=0;i<width;i++){
			if(i==0){
				System.out.print("+");
			}else if(i>=(width-1)){
				System.out.print("+");
			}else{
				System.out.print("=");
			}
		}
		System.out.println();
	}
	
	/**
	 * To show sequence of symbols in the second line of table (the first row of contents)
	 * @param	str		the content to display in table
	 * @param	length  the length of table 
	 */
	
	public void line2(String str, int length){
		
		length = length - str.length()+2;
		int pos =  length/2;
				
		for(int i=0;i<length;i++){
			if(i==0){
				System.out.print("|");
			}else{
				if(i<length && i!=pos){
					System.out.print(" ");
				}else if(i==pos){
					System.out.print(str);
				}
			}
		}		
	}
	
	/**
	 * To show sequence of symbols in the third line of table
	 * @param length	the length of table to show
	 */
	public void line3(int length){
		
		for(int i=0; i<length;i++){
			if(i==0)
				{ System.out.print("+");
					continue;
				}
			System.out.print("=");
			
				
		}
	}
	
	/**
	 * To show sequence of symbols in the forth line of table
	 * @param length 	the length of table to show
	 */
	public void line4(int length){
		
		for(int i=0; i<length;i++){
			if(i==0)
				{ System.out.print("+");
					continue;
				}
			System.out.print("-");
			
				
		}
	}
	/**
	 * Display header of table 
	 */
	public void table_head(){
		String[] title_head = {"NO","Title","Author","Date"}; 
		line1();
		
		for(int i=0;i<4;i++){
			if(i==0 || i==3)
				line2(title_head[i],13);
			else if(i==1 || i==2)
				line2(title_head[i],24);
		}
		System.out.print("|");
		System.out.println();
				
		for(int i=0;i<4;i++){
			if(i==0 || i==3)
				line3(14);
			else if(i==1 || i==2)
				line3(25);
		}
		System.out.print("+");
		System.out.println();
		
	}
	
	/**
	 * Display content of table in a row
	 * @param str 	content of table to show, if length of @param str greater than 15 it will concatenate with ...
	 * @param length	the length of table to show
	 */
	public void table_body(String str,int length){
		
		if (str.length()>15){
			str = str.substring(0, 15)+"..."; 
		}
		
		int len = length - str.length();
		
		for(int i=0;i<len;i++){
			if(i==0){
				 System.out.print("|");
				 System.out.print(" "+str);
			}
			else
				System.out.print(" ");
		}		
	}
	
	/**
	 * Display sequence of symbols concatenate with content in a row
	 * @param str	content in each column
	 */
	public void tbl_row(String[] str){
		
		for(int i=0;i<str.length;i++){
			if(i==0 || i==3)
				table_body(str[i],13);
			else if(i==1 || i==2)
				table_body(str[i],24);
		}
		System.out.print("|");
		System.out.println();

		for(int i=0;i<str.length;i++){
			if(i==0 || i==3)
				line4(14);
			else if(i==1 || i==2)
				line4(25);
		}
		System.out.print("+");
		System.out.println();

	}
	
	/**
	 * Display sequence of symbols concatenate with page and total records to display in footer of table
	 * @param cur_page	number represent current page
	 * @param count_page	number represent total page
	 * @param record	number represent total record
	 * @param length	number represent length of table
	 */
	public void tbl_footer(int cur_page, int count_page, int record, int length){
		int space = length - ("| pages: " + cur_page + "/" + count_page + "Total records: "+record+" ").length();
		System.out.print("| pages: " + cur_page + "/" + count_page); 
		for(int i=0;i<space-1;i++){
			System.out.print(" ");
		}
		System.out.print("Total records: "+record+" |");
		System.out.println();
		line1();
	}
	

	/**
	 * Descript the members of group who make this application 
	 */
	public void head(){ 
		System.out.println("+======================>} Korean Software HRD Center {<=======================+");
		System.out.println("|                                                                             |");
		System.out.println("|                             Article Management                              |");
		System.out.println("|                                                                             |");
		System.out.println("|                         *Team 2 Of Battambong                               |");
		System.out.println("|                            -> Chann Vichet (GL)                             |");
		System.out.println("|                            -> Chan Sophath                                  |");
		System.out.println("|                            -> Nao Narith                                    |");
		System.out.println("|                            -> Prem Chanthorn                                |");
		System.out.println("|                            -> Sry Leangheng                                 |");
		System.out.println("|                            -> Thorn Sereyvong                               |");
		System.out.println("|_____________________________________________________________________________|");
		System.out.println("");
	}
	
	/**
	 * Display Menu for user on startup 
	 */
	public void menu(){
		System.out.println("+=================================>} MENU {<==================================+");
		System.out.println("|                        HM)Home SB)Restore Backup X)Exit H)Help              |");
		System.out.println("|   F)First | P)Previous | N)Next | L)Last | G)Goto | R)Set Row | SO)Sort     |");
		System.out.println("|   S)Search| RD)Read | A)Add | E)Edit | D)Delete | CA)CLEAR-All | B)Backup   |");
		System.out.println("|_____________________________________________________________________________|");
		//System.out.print("\n*Choose: ");
	}
	/**
	 * Display Thank when user exit 
	 */
	public void thanks(){
		System.out.println("____________________________________-Thanks-___________________________________");
	}
	
	/**
	 * Show how to use all commands with example for user to use this application 
	 */
	public void help(){
		System.out.println("+=================================>} HELP {<==================================+");
		System.out.println("| 1.  F)First : Goto the first page. (*Choose: F)                             |");
		System.out.println("| 2.  P)Previous : Previous page. (*Choose: P)                                |");
		System.out.println("| 3.  N)Next : Next page. (*Choose: N)                                        |");
		System.out.println("| 4.  L)Last : Goto the last page. (*Choose: L)                               |");
		System.out.println("| 5.  G)Goto : Goto the page. (*Choose: G 12)                                 |");
		System.out.println("| 6.  R)Set Row : Set row to dispay in page. (*Choose: R 20)                  |");
		System.out.println("| 7.  RD)Read : Read by ID. (*Choose: RD 8)                                   |");
		System.out.println("| 8.  S)Search : Filter by Title, Contain or Author (*Choose: S)              |");
		System.out.println("| 9.  A)Add : Add new Article. (*Choose: I)                                   |");
		System.out.println("| 10. E)Edit : Edit Article by ID. (*Choose: E 10)                            |");
		System.out.println("| 11. D)Delete : Delete Article by ID. (*Choose: D 10)                        |");
		System.out.println("| 12. CA)CLEAR-All : CLEAR All Article. (*Choose: CA)                         |");
		System.out.println("| 13. H)Help : Guiline application. (*Choose: H)                              |");
		System.out.println("| 14. X)Exit : Exit application. (*Choose: X)                                 |");
		System.out.println("| 15. HM)Home : Home page. (*Choose: HM)                                      |");
		System.out.println("| 16. SO)Sort : Sort Article by Id, Title, Author. (*Choose: SO)              |");
		System.out.println("|_____________________________________________________________________________|");
	}
	/**
	 * Tell user to input id and display to user if id is invalid
	 * @param scan	take id from user input	
	 * @return	return any id back or zero if id is invalid
	 */
	public Integer enterData(Scanner scan){
		try{
			System.out.print("Enter ID: ");
			int id = scan.nextInt();
			return id;
		}catch(Exception ex){
			System.out.println("ID invalid.");
			return 0;
		}
	}
	public void listContent(ArrayList<Article> arr, int page,int totalRecord, int total_page){
		table_head();
		// display body table 
		for(Article art: arr){
			String[] str ={""+art.getId(),art.getTitle(),art.getAuthorName(),art.getDate()};
			tbl_row(str);		
		}
		// dislay footer table
		tbl_footer(page, total_page, totalRecord,width);
	}

}

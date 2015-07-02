package view;
import java.util.ArrayList;
import java.util.Collections;

import dto.Article;

public class Pagination {
	/**
	 * @param page is current page  
	 * @param perpage is number of rows
	 */
	public static int page=1;
	public static int perpage=5;	
	
	/**
	 * Method showPage for to show article by page 
	 * @param arr is arrayList store articles 
	 * @param value is variable for change page and perpage  
	 * @param act is variable check action 
	 * @param ui is object for to drawing ui
	 */
	public void showPage(ArrayList<Article> arr, int value,String act,UI ui){
		
		//ArrayList<Article> reversList = new ArrayList<Article>(arr);// Copy element from arr to reversList to reverse order
		//reverse order
		 Collections.reverse(arr);
		
		// cArr count size of arrList arr 
		int cArr = arr.size();	
		
		// check arrList arr have store record or not
		if(cArr>0){
			// total_page count page of record
			int total_page = (int) Math.ceil((cArr/(float)perpage));
			switch (act) {
				// Home page 
				case "HM":
					page=1;
					perpage=5;
					total_page = (int) Math.ceil((cArr/(float)perpage));
					break;
				// Goto page 
				case "G":
					page=value;
					break;
				// Last page
				case "L":
					page=total_page;
					break;
				// Previous page
				case "P":
					if(page!=1){
						page--;
					}else{
						System.out.println("This is the first page. Can not Previous.");
					}
					break;
				// First Page
				case "F":
					page=1;
					break;
				// Next page
				case "N":
					if(page==total_page){
						System.out.println("This is the last page. Can not Next.");
					}else{
						page++;
					}
					break;
				// Set Row 
				case "R":
					if(value>cArr){
						System.out.println("Show row can be not bigger than record.");
					}else{
						System.out.println("R"+value);
						perpage=value;
						page=1;
						total_page = (int) Math.ceil((cArr/(float)perpage));
					}
					break;
				default:
					break;
			}
			
			// start variable use start index arrayList arr			
			int start = (page-1)*perpage;
			// stop variable use end index arrayList arr
			int stop = start+perpage;
			
			
			if(page == total_page){
				if(cArr%perpage != 0)
					stop= start+cArr%perpage;
			}
		
			if(page>total_page){ 
				System.out.println("page not found!!!");
				start=0;
				stop=0;
			}
			
			// display head table 
			ui.table_head();
			// display body table 
			for(int i=start;i<stop;i++){
				String[] str ={""+arr.get(i).getId(),arr.get(i).getTitle(),arr.get(i).getAuthorId()+""};
				ui.tbl_row(str);			
			}
			// dislay footer table
			ui.tbl_footer(page, total_page, cArr,ui.width);
		}else{ 
			System.out.println("No record!");
		}
	}
	/**
	 * Method showPage is overload from showPage use when we have search function already to display article 
	 * @param arrList is arrayList store articles 
	 * @param arr is arrayList store index of arrlist 
	 * @param value is variable for change page and perpage  
	 * @param act is variable check action 
	 * @param ui is object for to drawing ui
	 */
	public void showPage(ArrayList<Article> arrList,ArrayList<Integer> arr, int value,String act,UI ui){
		
		int cArr = arr.size();
		if(cArr>0){
			int total_page = (int) Math.ceil((cArr/(float)perpage));			
			switch (act) {
				// Home page 
				case "HM":
					page=1;
					perpage=5;
					total_page = (int) Math.ceil((cArr/(float)perpage));
					break;
				// Goto page
				case "G":
					page=value;
					break;
				// last page
				case "L":
					page=total_page;
					break;
				// Previous page
				case "P":
					if(page!=1){
						page--;
					}else{
						System.out.println("This is the first page. Can not Previous.");
					}
					break;
				// First page
				case "F":
					page=1;
					break;
				// Next page
				case "N":
					if(page==total_page){
						System.out.println("This is the last page. Can not Next.");
					}else{
						page++;
					}
					break;
				// Set Row
				case "R":
					if(value>cArr){
						System.out.println("Show row can be not bigger than record.");
					}else{
						System.out.println("R"+value);
						perpage=value;
						page=1;
						total_page = (int) Math.ceil((cArr/(float)perpage));
					}
					break;
				default:
					break;
			}
			
			int start = (page-1)*perpage;
			int stop = start+perpage;
			
			
			if(page == total_page){
				if(cArr%perpage != 0){
					stop= start+cArr%perpage;
				}
			}
		
			if(page>total_page){ 
				System.out.println("page not found!!!");
				start=0;
				stop=0;
			}
			
			// display head table
			ui.table_head();
			// display body table 
			for(int i=start;i<stop;i++){
				String[] str ={""+arrList.get(arr.get(i)).getId(),arrList.get(arr.get(i)).getTitle(),arrList.get(arr.get(i)).getAuthorId()+""};
				ui.tbl_row(str);			
			}
			// display footer table
			ui.tbl_footer(page, total_page, cArr,ui.width);
		}else{
			System.out.println("No record!");
		}
	}
}

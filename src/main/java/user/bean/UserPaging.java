package user.bean;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Setter
@Getter
public class UserPaging {
	private int currentPgae; // 현재 페이지 
	private int pageBlock; // [이전] [1] [2] [3] [다음]
	private int pageSize; // 1페이지당 게시글 수 
	private int totalA; // 총 게시글 수
	private StringBuffer pagingHTML; 
	
	public void makePagingHTML(String methodName) {
		System.out.println("makePagingHTML() 호출");
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA + pageSize - 1) / pageSize; // 마지막 페이지 수 
		int startPage = (currentPgae-1) / pageBlock * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		System.out.println("start: " + startPage);
		System.out.println("end: " + endPage);
		if(endPage > totalP) endPage = totalP;
		
		if(startPage != 1) {
			pagingHTML.append("<span id='paging' onclick=" + methodName + "(" + (startPage - 1) + ")'>이전</span> ");
		}
		
		for(int i=startPage; i<=endPage; i++) {
			if(i == currentPgae) {
				pagingHTML.append("<span id='currentpaging' onclick='" + methodName + "(" + i + ")'>" + i + "</span> ");
			}else {
				pagingHTML.append("<span id='paging' onclick='" + methodName + "(" + i+ ")'>" + i + "</span> ");
			}
		}
		
		if(endPage < totalP) {
			pagingHTML.append("<span id='paging' onclick='" + methodName + "(" + (endPage + 1) + ")'>다음</span>");
		}
		
		System.out.println("start: " + startPage);
	}
}

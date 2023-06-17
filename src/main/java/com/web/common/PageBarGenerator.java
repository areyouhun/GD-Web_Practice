package com.web.common;

import java.util.ArrayList;
import java.util.List;

public class PageBarGenerator {
	// context 또는 파일로 불러오기
	private static final String BTN_TO_FIRST_UNACTIVE = "<span>[처음]</span>";
	private static final String BTN_TO_FIRST_ACTIVE = "<a href='%s'>[처음]</a>";
	private static final String PREV_BTN_UNACTIVE = "<span>[이전]</span>";
	private static final String PREV_BTN_ACTIVE = "<a href='%s'>[이전]</a>";
	private static final String NEXT_BTN_UNACTIVE = "<span>[다음]</span>";
	private static final String NEXT_BTN_ACTIVE = "<a href='%s'>[다음]</a>";
	private static final String PAGE_BTN_UNACTIVE = "<span style='font-weight: bolder'>%d </span>";
	private static final String PAGE_BTN_ACTIVE = "<a href='%s'>%d </a>";
	private static final String BTN_TO_LAST_ACTIVE = "<a href='%s'>[마지막]</a>";
	private static final String BTN_TO_LAST_UNACTIVE = "<span>[마지막]</span>";
	
	public static final int INDEX_TO_FIRST = 0;
	public static final int INDEX_TO_PREV = 1;
	public static final int INDEX_TO_PAGE_NO = 2;
	public static final int INDEX_TO_NEXT = 3;
	public static final int INDEX_TO_LAST = 4;
	
	private String uri;
	private int currentPage;
	private int numPerPage;
	private int totalData;
	private int totalPages;
	private int pageBarSize;
	private int pageStartNo;
	private int pageEndNo;
	private List<String> pageBar;
	
	public PageBarGenerator(Builder builder) {
		this.uri = builder.uri;
		this.currentPage = builder.currentPage;
		this.numPerPage = builder.numPerPage;
		this.totalData = builder.totalData;
		this.totalPages = (int) Math.ceil((double) this.totalData / this.numPerPage);
		this.pageBarSize = builder.pageBarSize;
		this.pageStartNo = ((this.currentPage - 1) / this.pageBarSize) * this.pageBarSize + 1;
		this.pageEndNo = this.pageStartNo + this.pageBarSize - 1;
		init();
	}
	
	public String getBtnToFirst() {
		return pageBar.get(INDEX_TO_FIRST);
	}
	
	public String getPrevBtn() {
		return pageBar.get(INDEX_TO_PREV);
	}
	
	public String getPageBtn() {
		return pageBar.get(INDEX_TO_PAGE_NO);
	}
	
	public String getNextBtn() {
		return pageBar.get(INDEX_TO_NEXT);
	}
	
	public String getBtnToLast() {
		return pageBar.get(INDEX_TO_LAST);
	}
	
	public static int toInteger(String str, int num) {
		int strToInteger;
		try {
			strToInteger = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return num;
		}
		return strToInteger;
	}
	
	public static class Builder {
		private String uri;
		private int currentPage;
		private int numPerPage;
		private int totalData;
		private int pageBarSize;
		
		public Builder uri(String uri) {
			this.uri = uri;
			return this;
		}
		
		public Builder currentPage(int currentPage) {
			this.currentPage = currentPage;
			return this;
		}
		
		public Builder numPerPage(int numPerPage) {
			this.numPerPage = numPerPage;
			return this;
		}
		
		public Builder totalData(int totalData) {
			this.totalData = totalData;
			return this;
		}
		
		public Builder pageBarSize(int pageBarSize) {
			this.pageBarSize = pageBarSize;
			return this;
		}
		
		public PageBarGenerator build() {
			return new PageBarGenerator(this);
		}
	}
	
	private void init() {
		pageBar = new ArrayList<String>();
		pageBar.add(generateBtnToFirst());
		pageBar.add(generatePrevBtn());
		pageBar.add(generatePageBtn());
		pageBar.add(generateNextBtn());
		pageBar.add(generateBtnToLast());
	}
	
	private String generateBtnToFirst() {
		if (currentPage == 1) {
			return BTN_TO_FIRST_UNACTIVE;
		}
		return String.format(BTN_TO_FIRST_ACTIVE, 
									uri + changeSymbol("?currentPage=", uri) + 1
										+ "&numPerPage=" + numPerPage);
	}
	
	private String generatePrevBtn() {
		if (pageStartNo == 1) {
			return PREV_BTN_UNACTIVE;
		} else {
			return String.format(PREV_BTN_ACTIVE, 
										uri + changeSymbol("?currentPage=", uri) + (pageStartNo - 1) 
											+ "&numPerPage=" + numPerPage);
		}
	}
	

	private String generatePageBtn() {
		StringBuilder pageBtn = new StringBuilder();
		
		while (pageStartNo <= pageEndNo && pageStartNo <= totalPages) {
			if (pageStartNo == currentPage) {
				pageBtn.append(String.format(PAGE_BTN_UNACTIVE, pageStartNo));
			} else {
				pageBtn.append(String.format(PAGE_BTN_ACTIVE, 
											uri + changeSymbol("?currentPage=", uri) 
											+ pageStartNo
											+ "&numPerPage=" + numPerPage, 
											pageStartNo));
			}
			pageStartNo++;
		}
		return pageBtn.toString();
	}
	
	private String generateNextBtn() {
		if (pageStartNo > totalPages) {
			return NEXT_BTN_UNACTIVE;
		}
		return String.format(NEXT_BTN_ACTIVE, 
									uri + changeSymbol("?currentPage=", uri) + pageStartNo 
										+ "&numPerPage=" + numPerPage);
	}
	
	private String generateBtnToLast() {
		if (currentPage == totalPages) {
			return BTN_TO_LAST_UNACTIVE;
		}
		return String.format(BTN_TO_LAST_ACTIVE, 
									uri + changeSymbol("?currentPage=", uri) + totalPages
										+ "&numPerPage=" + numPerPage);
	}
	
	private String changeSymbol(String querystring, String uri) {
		if (uri.contains("?")) {
			return querystring.replace("?", "&");
		}
		return querystring;
	}
}

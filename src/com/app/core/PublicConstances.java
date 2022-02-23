package com.app.core;

public class PublicConstances {
	
	public static final String TICKET_STATUS_WAIT_FOR_CHUPIAO = "000";
	public static final String TICKET_STATUS_CHUPIAOING = "001";
	public static final String TICKET_STATUS_CHUPIAO_SUCCESS = "002";
	public static final String TICKET_STATUS_CHUPIAO_ZUOFEI = "003";
	
	public static final String[][] SEATS = new String[10][15] ;
	
	public static final String TICKET_SEAT_FREE = "000";
	public static final String TICKET_SEAT_OCCUPYED = "001";
	
	static {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
				SEATS[i][j] = "第" + (i + 1) + "排 - 第" + (j + 1) + "座";
			}
			
		}
	}

}

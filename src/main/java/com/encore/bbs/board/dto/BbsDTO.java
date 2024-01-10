package com.encore.bbs.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BbsDTO {

	private int bbsId;
	private int memberCode;
	private String title;
	private String contents;
	private LocalDateTime createdAt;
	private LocalDateTime modifyAt;
	private int viewCnt;
	private int likeCnt;
	private int tagId;
	private boolean isDeleted;
	private Long countryId;
	
	
}

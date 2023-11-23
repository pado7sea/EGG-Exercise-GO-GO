package com.ssafy.fit.model.dto;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;

@ApiModel(value="게시판 바구니", description = "게시글 정보")
public class Board {
	private int id;
	private String title;
	private String writer;
	private String content;
	private String regDate;
	private int viewCnt;
	private String img;
	private String orgImg;

	public Board() {
	}

	public Board(int id, String title, String writer, String content, String regDate, int viewCnt, String img,
			String orgImg) {
		super();
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regDate = regDate;
		this.viewCnt = viewCnt;
		this.img = img;
		this.orgImg = orgImg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getOrgImg() {
		return orgImg;
	}

	public void setOrgImg(String orgImg) {
		this.orgImg = orgImg;
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regDate="
				+ regDate + ", viewCnt=" + viewCnt + ", img=" + img + ", orgImg=" + orgImg + "]";
	}

	
}

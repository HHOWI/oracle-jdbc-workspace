package com.youtube.model.vo;

import java.util.Date;

public class VideoLike {
	private int vLikeCode;
	private Date vLikdeDate;
	private Video video;
	private Member member;
	public VideoLike() {
		// TODO Auto-generated constructor stub
	}
	public VideoLike(int vLikeCode, Date vLikdeDate, Video video, Member member) {
		this.vLikeCode = vLikeCode;
		this.vLikdeDate = vLikdeDate;
		this.video = video;
		this.member = member;
	}
	public int getvLikeCode() {
		return vLikeCode;
	}
	public void setvLikeCode(int vLikeCode) {
		this.vLikeCode = vLikeCode;
	}
	public Date getvLikdeDate() {
		return vLikdeDate;
	}
	public void setvLikdeDate(Date vLikdeDate) {
		this.vLikdeDate = vLikdeDate;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	@Override
	public String toString() {
		return "VideoLike [vLikeCode=" + vLikeCode + ", vLikdeDate=" + vLikdeDate + ", video=" + video + ", member="
				+ member + "]";
	}
	
	
}

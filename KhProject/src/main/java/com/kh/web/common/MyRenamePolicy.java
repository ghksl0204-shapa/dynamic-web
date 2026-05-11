package com.kh.web.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

// FileRenamePolicy라는 인터페이스를 구현해서
// 이름바꾸기 정책을 만들어주기
public class MyRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File origin) {
		
		// "a.jpg" , "gf.jpg"
		String originName = origin.getName();
		
		// 우리 입맛대로 바꾸기 => 최대한 안겹치게
		// KH_년월일시분초_랜덤값 + 원본파일확장자
		/*
		 * a.jpg        =>    KH_20260511174320_324.jpg
		 */
		
		// 1. 원본파일 확장자
		String ext = originName.substring(originName.lastIndexOf("."));
		
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		int randomNum = (int)(Math.random() * 900) + 100;
		
		String changeName = "KH_" + currentTime + "_" + randomNum + ext;
		
		return null;
	}

}

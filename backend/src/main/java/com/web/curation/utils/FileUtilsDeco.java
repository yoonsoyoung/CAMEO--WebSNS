package com.web.curation.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.common.error.AttachFileException;
import com.web.curation.model.decoration.Decoration;

@Component
public class FileUtilsDeco {
	
	/** 오늘 날짜 */
//	private final String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
	
	
	/** 업로드 경로 */
//	private final String uploadPath = Paths.get("C:", "decoration", "upload" ).toString();
	
	/**
	 * 서버에 생성할 파일명을 처리할 랜덤 문자열 반환
	 * */
	private final String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 서버에 첨부 파일을 생성하고, 업로드 파일 목록 반환
	 * 
	 */
	public List<Decoration> uploadFiles(MultipartFile[] files){
//		System.out.println(2);
		// 업로드 파일 정보를 담을 비어이있는 리스트
		List<Decoration> attachList = new ArrayList<>();
		
		String absolutePath = new File("").getAbsolutePath() + "/";
		String uploadPath = "/home/ubuntu/S05P12C202/frontend/src/assets/images/decoration/";
		
		// uploadpath에 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉토리 생성
		File dir = new File(uploadPath);
		if(dir.exists() == false) {
//			System.out.println(3);
//			System.out.println(dir);
			dir.mkdirs();
		}
		// 파일이 비어있으면 비어있는 리스트 반환
		
		// 파일 개수만큼 forEach 실행
		for(MultipartFile file : files) {
			System.out.println(3);
			if(file.getSize() < 1) {
				continue;
			}
			try {
//				System.out.println("여기가 들어오나요?");
				//파일 확장자
				final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				//서버에 저장할 파일명 (랜던 + 확장자)
				final String saveName = getRandomString() + "." + extension;
				
				// 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성
				File target = new File(uploadPath, saveName);
				file.transferTo(target);
				
				// 파일 정보 저장
				Decoration attach = new Decoration();
				// 여기에 굳이 decoration_id 를 담아야할ㄲ까??
				attach.setOriginalName(file.getOriginalFilename());
				attach.setSaveName(saveName);
				attach.setSize(file.getSize());
				
				// 파일정보 추가
				attachList.add(attach);
//				System.out.println("여기는용?");
				
			}catch(IOException e) {
				throw new  AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");
			}catch (Exception e) {
				throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");
			}
		}
//		System.out.println("설마여기ㅗㄷ??");
		return attachList;
	}

}

package com.yedam.app.board.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	// @Value 환경변수, properties 외부값읽어드림
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@GetMapping("uploadForm")
	public void getUploadForm() {}
	
	@PostMapping("/uploadsAjax")
	@ResponseBody
	public List<String> uploadFile(@RequestPart MultipartFile[] uploadFiles) {
	    						   // @RequestPart MultipartFile[] 한쌍
		List<String> imageList = new ArrayList<>();
		
	    for(MultipartFile uploadFile : uploadFiles){ // 배열은 무조건 반복문
	    	if(uploadFile.getContentType().startsWith("image") == false){ // 내가 가지고 있는 파일이 이미지인지 판단
	    		System.err.println("this file is not image type");
	    		return null;
	        }
	  
	        String originalName = uploadFile.getOriginalFilename(); // 실제로 사용자가 업로드한 파일명을 가지고 온다. 
	        String fileName = originalName.substring(originalName.lastIndexOf("//")+1); // substring으로 주소명을 자르고 파일명만 가질려고.
	        
	        System.out.println("fileName : " + fileName);
	        
	        // -------------------------------------------------------
	        // 이것들은 우리가 저장하는 경로 // 이름은 중요 X 데이터가 중요 O
	        // 여기까지 서버 내에 별도의 공간 안에 내가 저장할 이름을 구하는 코드
	        // 날짜를 기준으로 분류
	        //날짜 폴더 생성 
	        String folderPath = makeFolder(); // 년 월 일로 폴더 생성 됨
	        //UUID // 쉽게 말하면 랜덤값 - 사과라는 사용자가 지정한 이름 앞에 랜덤값을 붙임
	        String uuid = UUID.randomUUID().toString();  
	        //저장할 파일 이름 중간에 "_"를 이용하여 구분
	        
	        String uploadFileName = folderPath +File.separator + uuid + "_" + fileName;
	        
	        // 실제 저장되는 경로 이름
	        String saveName = uploadPath + File.separator + uploadFileName; 
	        // -------------------------------------------------------
	        
	        Path savePath = Paths.get(saveName);
	        //Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
	        System.out.println("path : " + saveName);
	        try{
	        	uploadFile.transferTo(savePath); // 실제 파일이 가지고 있는 코드는 이거 하나! multipart가 가지고 있음
	            //uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
	        } catch (IOException e) {
	             e.printStackTrace();	             
	        }
	        // DB에 해당 경로 저장
	        // 1) 사용자가 업로드할 때 사용한 파일명
	        // 2) 실제 서버에 업로드할 때 사용한 경로
	        imageList.add(setImagePath(uploadFileName));
	     }
	    return imageList;
	}
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// LocalDate를 문자열로 포멧
		String folderPath = str.replace("/", File.separator); // 주소 c//asd// 이 /를 인식 못 해서 만들어주는 거임
		File uploadPathFoler = new File(uploadPath, folderPath); // string으론 사용불가능(파일이므로)
		// File newFile= new File(dir,"파일명");
		if (uploadPathFoler.exists() == false) { // 실제로 존재하는지 안 하는지 체크 
			uploadPathFoler.mkdirs(); // file.upload.path 이 경로를 기반으로 해서 없으면 강제로 생성해줌 있으면 있는대로 가는거임
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		return folderPath;
	}
	
	private String setImagePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
	}
}

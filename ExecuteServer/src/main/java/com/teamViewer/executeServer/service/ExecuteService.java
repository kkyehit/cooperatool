package com.teamViewer.executeServer.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.teamViewer.executeServer.model.ExecuteModel;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExecuteService {
	//Spring의 ApplicationContext는 ResourceLoader라는 인터페이스를 상속한다.
	//이 인터페이스는 이름에서부터 알 수 있듯 리소스를 읽어오는 기능을 제공한다.
	//@Autowired
	//ResourceLoader resourceLoader;

	public String ExecuteC(ExecuteModel executeModel) throws IOException, InterruptedException {
		String cFilepath = executeModel.getRoomId()+".cpp";
		String cFilename = executeModel.getRoomId()+".cpp";
		String execFilename = ""+executeModel.getRoomId()+".cpp.exec";
		String execCommand = "./";
		//String resultFilename = executeModel.getRoomId()+".cpp.result";
		String command =  "g++ -o ./"+execFilename+" ./"+cFilename;

		return execute(cFilepath, cFilename, execCommand, execFilename, executeModel.getContents(), command,"c");
	}

	public String ExecuteJava(ExecuteModel executeModel) throws IOException, InterruptedException {
		String javaFilepath = executeModel.getRoomId()+".java";
		String javaFilename = executeModel.getRoomId()+".java";
		String execFilename = ""+executeModel.getMainClass();
		String execCommand = "java ";
		//String resultFilename = executeModel.getRoomId()+".cpp.result";
		String command =  "javac "+javaFilename;

		return execute(javaFilepath, javaFilename, execCommand, execFilename, executeModel.getContents(), command, "java");
	}

	public String execute(String filepath, String filename,String execCommand, String execFilename, String contents, String commend, String type) throws
		IOException, InterruptedException {
		String res = "";
		String compileError = "";
		String RunError = "";
		byte[] bytes = new byte[1024];

		FileOutputStream fos = new FileOutputStream(filepath);
		fos.write(contents.toString().getBytes());
		fos.close();
		Runtime runtime = Runtime.getRuntime();
		Process compile = runtime.exec(commend);
		compileError = getErrorMsg(compile, type);
		compile.waitFor();
		try{
			log.info(execCommand+execFilename);
			Process exec = runtime.exec(execCommand+execFilename);
			res += getMsg(exec);
			exec.waitFor();
			if(type.compareTo("c") == 0)runtime.exec("rm ./"+execFilename);
			else runtime.exec("rm ./"+execFilename+".class");
			log.info("res "+res);
			return res;
		}catch (Exception e) {
			log.info("compile error "+compileError);
			return compileError;
		}
	}

	public String getMsg(Process exec){
		String res = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(exec.getInputStream()));
		Scanner scanner = new Scanner(br);
		scanner.useDelimiter(System.getProperty("line.separator"));
		while(scanner.hasNext())
			res += scanner.next();
		return res;
	}

	public String getErrorMsg(Process exec, String type){
		String res = "";
		BufferedReader br;
		if(type.compareTo("c") == 0) br = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
		else br =  new BufferedReader(new InputStreamReader(exec.getInputStream()));
		Scanner scanner = new Scanner(br);
		scanner.useDelimiter(System.getProperty("line.separator"));
		while(scanner.hasNext())
			res += scanner.next();
		return res;
	}
}

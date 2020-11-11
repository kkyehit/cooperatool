package com.teamviewer.paintserver.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teamviewer.paintserver.model.PaintModel;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaintService {
	public void doPaint(String roomId, PaintModel paintModel){
		File file = new File("./"+roomId);
		try {
			if (!file.exists())
				file.createNewFile();
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
			bufferedWriter.write(paintModel.toString());
			bufferedWriter.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public List<PaintModel> getPaint(String roomId){
		List<PaintModel> res = new ArrayList<>();
		File file = new File("./"+roomId);
		log.info("getPaint()");
		try {
			if (!file.exists())
				file.createNewFile();
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String str = null;
			while ((str = bufferedReader.readLine()) != null){
				try {
					log.info("### "+str);
					res.add(buildPaint(str));
				}catch (Exception e){
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return res;
	}

	private PaintModel buildPaint(String _str) throws Exception{
		String[] str = _str.split(" ");
		if(str.length != 4) throw new Exception();
		else return PaintModel.builder().x(str[0]).y(str[1]).color(str[2]).width(str[3]).build();
	}
}

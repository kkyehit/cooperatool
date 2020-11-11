package com.teamViewer.executeServer.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teamViewer.executeServer.model.ExecuteModel;
import com.teamViewer.executeServer.service.ExecuteService;

@RestController
@RequestMapping("/execute")
public class executeController {
	@Autowired
	ExecuteService executeService;
	@RequestMapping(path = "/c_cpp", method = RequestMethod.POST)
	public String executeC(@RequestBody ExecuteModel executeModel) throws IOException, InterruptedException {
		return executeService.ExecuteC(executeModel);
	}

	@RequestMapping(path = "/java", method = RequestMethod.POST)
	public String executeJava(@RequestBody ExecuteModel executeModel) throws IOException, InterruptedException {
		return executeService.ExecuteJava(executeModel);
	}
}

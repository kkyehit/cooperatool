package com.teamViewer.executeServer.model;

import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExecuteModel {
	Integer roomId;
	String language;
	String contents;
	String mainClass;
}

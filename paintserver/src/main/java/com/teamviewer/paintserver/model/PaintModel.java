package com.teamviewer.paintserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaintModel {
	String x,y,color,width;

	@Override
	public String toString() {
		return x+" "+y+" "+color+" "+width+"\n";
	}
}

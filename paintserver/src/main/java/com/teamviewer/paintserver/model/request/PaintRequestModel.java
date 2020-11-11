package com.teamviewer.paintserver.model.request;

import com.teamviewer.paintserver.model.PaintModel;
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
public class PaintRequestModel {
	String roomId,x,y,color,width;

	@Override
	public String toString() {
		return roomId+" "+x+" "+y+" "+color+" "+width+"\n";
	}

	public PaintModel getPaintModel(){
		return PaintModel.builder().x(x).y(y).color(color).width(width).build();
	}
}

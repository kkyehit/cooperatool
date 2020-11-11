package com.teamViewer.userServer.services;

import java.util.Optional;

import com.teamViewer.userServer.model.LoginRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamViewer.userServer.exception.DuplicateDataError;
import com.teamViewer.userServer.exception.NoDataException;
import com.teamViewer.userServer.model.UserModel;
import com.teamViewer.userServer.repository.UserRepository;
import com.teamViewer.userServer.model.SignUpRequestModel;

/*Service의 역할은 Dao가 DB에서 받아온 데이터를 전달받아 가공하는 것*/
@Service
public class UserService {
	@Autowired
	private UserRepository userRepositoy;

	public void signUp(SignUpRequestModel signUpRequestModel) throws DuplicateDataError {
		UserModel userModel = new UserModel(signUpRequestModel.getUserId(), signUpRequestModel.getUserPw(), signUpRequestModel.getName());
		/*Id 값이 중복되면 Error 발생*/
		if(userRepositoy.findByUserId(signUpRequestModel.getUserId()).isPresent()) throw new DuplicateDataError();
		/*Id 값이 중복되지 않으면 추가*/
		userRepositoy.save(userModel);

	}

	public UserModel login(LoginRequestModel loginRequestModel) throws NoDataException {
		return userRepositoy.findByUserIdAndUserPw(loginRequestModel.getUserId(), loginRequestModel.getUserPw()).orElseThrow(NoDataException::new);
	}

	public UserModel findByUserId(String id) throws NoDataException {
		/*issue#5 lambda식을 활용*/
		return userRepositoy.findByUserId(id).orElseThrow(NoDataException::new);
	}

	public Optional<UserModel> findByName(String name){
		return userRepositoy.findByName(name);
	}
}
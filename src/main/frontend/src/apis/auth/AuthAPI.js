import { request } from "../Api"

// 회원가입 요청 API
export const signupRequest = async (data) => {

  try {
    const response = await request.post("/auth/signup", { ...data });
    // 성공적으로 응답을 받은 경우
    return response.data;
  } catch (error) {
    console.error("Signup Request Error:", error);
    throw error; 
  }

};


// 로그인 요청 API
export const loginRequest = async (data) => {

  try {
    const response = await request.post("auth/login", { ...data } );
    // 성공적으로 로그인한 경우

    storeTokens(response)

    return response.data;

  } 
  catch (error) {
    console.error("login Request Error:", error);
    throw error; 
  }

};

export const storeTokens = (response) => {

  const { data } = response;
    const { accessToken, refreshToken, accessTokenExpiresIn } = data;
  
   // localStorage에 저장
      localStorage.setItem("accessToken", accessToken);
      localStorage.setItem("refreshToken", refreshToken);
      localStorage.setItem("expiresAt", Date.now() + accessTokenExpiresIn);
      localStorage.setItem("isLogin", true);

      console.log("accessToken: "+accessToken);
      console.log("refreshToken: "+refreshToken);
}






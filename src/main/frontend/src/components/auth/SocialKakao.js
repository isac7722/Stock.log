import axios from "axios";
import { useEffect, useState } from "react";
import { Button } from "react-bootstrap";


const REST_API_KEY = process.env.REACT_APP_REST_API_KEY;
const REDIRECT_URI = 'http://localhost:3000/login';

const KAKAO_AUTH_URI = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;


export const SocialKakao = () => {

  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogin = () => {
    console.log("카카오 로그인 버튼 클릭");
    window.location.href = KAKAO_AUTH_URI;
  };






  const isAccessTokenValid = (accessToken) => {
    const now = Date.now();
    const expiresAt = localStorage.getItem("expiresAt");

    if (!expiresAt) {
      return false;
    }

    return now < expiresAt;
  };


  // accessToken 만료를 체크하는 메소드
  const checkAccessTokenExpiration = () => {
    const now = Date.now();
    const expiresAt = localStorage.getItem("expiresAt");

    // 만료시간이 없으면 return
    if (!expiresAt) return;

    if (now >= expiresAt) {
      // 액세스 토큰 만료
      console.log("액세스 토큰 만료");
      reissueAccessToken();
    } else {
      // 액세스 토큰 유효
      console.log("액세스 토큰 유효");
    }
  };

  // accessToken이 만료된 경우 reissue
  const reissueAccessToken = async () => {
    const refreshToken = localStorage.getItem("refreshToken");

    if (!refreshToken) return;

    try {
      const response = await axios.post(
        "http://localhost:8080/auth/reissue",
        { refreshToken },
        { withCredentials: true }
      );

      const { data } = response;
      const { accessToken, accessTokenExpiresIn } = data;

      // setAccessToken(accessToken);

      // 갱신된 액세스 토큰 저장
      localStorage.setItem("accessToken", accessToken);
      localStorage.setItem("expiresAt", Date.now() + accessTokenExpiresIn * 1000);

      console.log("액세스 토큰 갱신 성공");
    } catch (error) {
      console.error("액세스 토큰 갱신 실패:", error);
    }
  };









  const handleKakaoCallback = async () => {

    console.log("handleKakaoCallback 함수 호출");

    const code = new URLSearchParams(window.location.search).get("code");
    if (!code) {
      console.log("code 없음");
      return;
    }

    // Axios를 사용하여 백엔드 서버로 코드 전송
    try {
      console.log("카카오 로그인 코드 전송 시작");
      const response = await axios.post("http://localhost:8080/oauth/kakao", { code }, { withCredentials: true }
      );

      const { data } = response;
      const { accessToken, refreshToken, accessTokenExpiresIn } = data;

      // 로그인 성공 처리
      console.log("카카오 로그인 성공:", data);
      console.log("accessToken:", accessToken);
      console.log("refreshToken:", refreshToken);
      console.log("accessTokenExpiresIn:", accessTokenExpiresIn);

       // localStorage에 저장
       localStorage.setItem("accessToken", accessToken);
       localStorage.setItem("refreshToken", refreshToken);
       localStorage.setItem("accessTokenExpiresIn", accessTokenExpiresIn);

      setIsLoggedIn(true);

    } catch (error) {
      console.error("카카오 로그인 실패:", error);
    }
  };

  useEffect(() => {
    console.log("useEffect 호출");

    handleKakaoCallback();

    // 로그인 정보 확인
    const accessToken = localStorage.getItem("accessToken");
    if (accessToken) {
      // accessToken 유효성 검사 로직 추가
      // ...

      // 유효하면 로그인 상태 유지
      setIsLoggedIn(true);
    }
  }, []);

  // return (
  //   <>
  //     {isLoggedIn ? (
  //       // 로그인 후 보여줄 내용
  //       <div>
  //         <p>로그인 성공!</p>
  //         <p>accessToken: {localStorage.getItem("accessToken")}</p>
  //         <p>refreshToken: {localStorage.getItem("refreshToken")}</p>
  //       </div>
  //     ) : (
  //       // 로그인 전 보여줄 내용
  //       <Button onClick={handleLogin}>카카오 로그인</Button>
  //     )}
  //   </>
  // );

  return (
    <>
      {isLoggedIn ? (
        // 로그인 후 보여줄 내용
        <div>
          <p>로그인 성공!</p>
          <p>accessToken: {localStorage.getItem("accessToken")}</p>
          <p>refreshToken: {localStorage.getItem("refreshToken")}</p>
          <Button onClick={handleLogin}>카카오 로그인</Button>
        </div>

      ) : (
        // 로그인 전 보여줄 내용
        <Button onClick={handleLogin}>카카오 로그인</Button>
      )}
    </>
  );
};
import axios from "axios";
import { useEffect, useState } from "react";
import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { useTokenStore } from "../../dataStore/TokenStore";


const REST_API_KEY = process.env.REACT_APP_REST_API_KEY;
const REDIRECT_URI = 'http://localhost:3000/login';

const KAKAO_AUTH_URI = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;


export const SocialKakao = () => {

  const [isLoggedIn, setIsLoggedIn] = useState(false);
  // const [accessToken, setAccessToken] = useState("");
  // const [refreshToken, setRefreshToken] = useState("");

  const { accessToken, setAccessToken, refreshToken, setRefreshToken } = useTokenStore();

  const navigate = useNavigate();


  const handleLogin = () => {
    window.location.href = KAKAO_AUTH_URI;
  };

  // 카카오 로그인 handler
  const handleKakaoCallback = async () => {
    const code = new URLSearchParams(window.location.search).get("code");
    if (!code) return;

    try {
      const response = await axios.post(
        "http://localhost:8080/oauth/kakao",
        { code },
        { withCredentials: true }
      );

      const { data } = response;
      const { accessToken, refreshToken, accessTokenExpiresIn } = data;

      setAccessToken(accessToken);
      setRefreshToken(refreshToken);

      // localStorage에 저장
      localStorage.setItem("accessToken", accessToken);
      localStorage.setItem("refreshToken", refreshToken);
      localStorage.setItem("expiresAt", Date.now() + accessTokenExpiresIn);

      // localStorage 에 로그인 상태를 추가
      setIsLoggedIn(true)
      localStorage.setItem("isLogin", true);

      // main 페이지로 redirect
      navigate("/");

    } catch (error) {
      console.error("카카오 로그인 실패:", error);
    }
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

    if (!refreshToken) return;

    try {
      const response = await axios.post(
        "http://localhost:8080/auth/reissue",
        { refreshToken },
        { withCredentials: true }
      );

      const { data } = response;
      const { accessToken, accessTokenExpiresIn } = data;

      localStorage.setItem("accessToken", accessToken);
      localStorage.setItem("expiresAt", Date.now() + accessTokenExpiresIn);

      console.log("액세스 토큰 갱신 성공");
    } catch (error) {
      console.error("액세스 토큰 갱신 실패:", error);
    }
  };

  useEffect(() => {
    console.log("useEffect 호출");

    handleKakaoCallback();

    // 10분 마다 엑세스 토큰 유효성 체크
    const interval = setInterval(checkAccessTokenExpiration, 1000 * 60 * 10);

  }, []);



  return (
    <>
      {isLoggedIn ? (
        // 로그인 후 보여줄 내용
        <div>
          <p>로그인 성공!</p>
          <p>accessToken: {accessToken}</p>
          <p>refreshToken: {refreshToken}</p>
          <Button onClick={handleLogin}>카카오 로그인</Button>
        </div>

      ) : (
        // 로그인 전 보여줄 내용
        <Button onClick={handleLogin}>카카오 로그인</Button>
      )}
    </>
  );
};
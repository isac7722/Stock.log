import axios from "axios";
import { useEffect } from "react";
import { Button } from "react-bootstrap";


const REST_API_KEY = process.env.REACT_APP_REST_API_KEY;
const REDIRECT_URI = 'http://localhost:3000/login';

const KAKAO_AUTH_URI = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;


export const SocialKakao = () => {
    
  
    const handleLogin = () => {
      console.log("카카오 로그인 버튼 클릭");
      window.location.href = KAKAO_AUTH_URI;
    };
  
    const handleKakaoCallback = async () => {

      console.log("handleKakaoCallback 함수 호출");
      
      const code = new URLSearchParams(window.location.search).get("code");
      if (!code) {
        console.error("카카오 로그인 실패");
        return;
      }
  
      // Axios를 사용하여 백엔드 서버로 코드 전송
      try {
        console.log("카카오 로그인 코드 전송 시작");
        const response = await axios.post("http://localhost:8080/oauth/kakao", {code}, { withCredentials: true }
        );
        const { data } = response;
        // 로그인 성공 처리
        console.log("카카오 로그인 성공:", data);
      } catch (error) {
        console.error("카카오 로그인 실패:", error);
      }
    };
  
    useEffect(() => {
      console.log("useEffect 호출");
      // 로그인 후 URL에 code가 존재하면 handleKakaoCallback 실행

      handleKakaoCallback();
      // if (window.location.pathname ==  "/auth") {
      //   handleKakaoCallback();
      // }
    }, [window.location.pathname]);
  
    return (
      <>
        <Button onClick={handleLogin}>카카오 로그인</Button>
      </>
    );
  };
import React from 'react';


// const handleClick = async () => {
//   setLoading(true);
//   try {
//     // signupRequest 또는 loginRequest 호출
//     const response = await (type === "signup" ? signupRequest : loginRequest)({ data });
//     // 서버 응답 처리
//     if (response.success) {
//       // 로그인 성공 시 처리 (예: 메인 페이지로 이동)
//       console.log("로그인 성공!");
//     } else {
//       // 로그인 실패 시 처리 (예: 오류 메시지 표시)
//       console.log("로그인 실패:", response.error);
//     }
//   } catch (error) {
//     console.log("오류 발생:", error);
//   } finally {
//     setLoading(false);
//   }
// };





const AuthButton = ({ buttonText, onClick }) => {
  return (
    <button onClick={onClick} className="authBtn">
      {buttonText}
    </button>
  );
}

export default AuthButton;

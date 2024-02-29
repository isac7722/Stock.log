import { useState } from "react";
import TextInput from "../../components/auth/TextInput";

import CertificationButton from "../../components/auth/CertificationButton";
import AuthButton from "../../components/auth/AuthBtn";
import { signupRequest } from "../../apis/auth/AuthAPI";
import { useNavigate } from "react-router-dom";

const Signup = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [checkPassword, setCheckPassword] = useState('');
    const [passwordMatch, setPasswordMatch] = useState(null);

    const navigate = useNavigate();
  
    const handleEmailChange = (e) => {
      setEmail(e.target.value);
    }
  
    const handlePasswordChange = (e) => {
      const newPassword = e.target.value;
      setPassword(newPassword);
  
      if (checkPassword !== '') {
        setPasswordMatch(newPassword === checkPassword);
      }
    }
  
    const handleCheckPasswordChange = (e) => {
      const newPasswordCheck = e.target.value;
      setCheckPassword(newPasswordCheck);
  
      setPasswordMatch(password === newPasswordCheck);
    }
  
    const handleSignup = async () => {
      const data = {
        email: email,
        password: password,
      };
  
      console.log(data);
      const result = await signupRequest(data);
      alert(result)
      navigate("/login")
    }
  
    return (
      <div className="authDiv">
        <h1>회원가입</h1>
        <div className="emailDiv">
          <TextInput
            type="email"
            placeholder="이메일 주소"
            value={email}
            onChange={handleEmailChange}
          />
          <div className="cerBtn">
            <CertificationButton buttonText="인증번호 발송" />
          </div>
        </div>
        <TextInput
          type="password"
          placeholder="비밀번호"
          value={password}
          onChange={handlePasswordChange}
        />
  
        <TextInput
          type="password"
          placeholder="비밀번호 확인"
          value={checkPassword}
          onChange={handleCheckPasswordChange}
        />
  
        {passwordMatch !== null && (
          <p style={{ color: passwordMatch ? "green" : "red" }}>
            {passwordMatch
              ? "입력하신 비밀번호가 일치합니다"
              : "입력하신 비밀번호가 일치하지 않습니다"}
          </p>
        )}
  
        {/* AuthButton에 handleSignup 함수를 전달 */}
        <AuthButton buttonText="회원가입" onClick={handleSignup} />
      </div>
    );
  }
  
  export default Signup;
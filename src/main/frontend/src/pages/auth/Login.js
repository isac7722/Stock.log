import React from 'react';
import { useState } from "react";
import { Link, useNavigate } from 'react-router-dom';
import AuthButton from '../../components/auth/AuthBtn';
import TextInput from '../../components/auth/TextInput';
import { SocialKakao } from '../../components/auth/SocialKakao';
import { loginRequest } from '../../apis/auth/AuthAPI';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate();

    const emailOnchangeHandler = (e) => {
        setEmail(e.target.value);
    }

    const passwordOnchangeHandler = (e) => {
        setPassword(e.target.value);
    }

    const handleLogin = async () => {
        const data = {
          email: email,
          password: password,
        };
    
        console.log(data);
        const result = await loginRequest(data);
        console.log(result)
        alert("로그인 성공")
        navigate("/")
      }

    return(
        <div className='authDiv'>
            <h1>환영합니다!</h1>
            <TextInput
                type="email"
                placeholder="이메일 주소"
                value={email}
                onChange={emailOnchangeHandler}
            />

            <TextInput
                type="password"
                placeholder="비밀번호"
                value={password}
                onChange={passwordOnchangeHandler}
            />

            <div className='loginCheckBox'><input type="checkbox"/> 로그인 상태 유지</div>
            <AuthButton className="loginBtn" buttonText="로그인" onClick={handleLogin}/>
            <div className='userDiv'><Link to="/signup">회원가입</Link> | <Link to="/findPass">비밀번호 찾기</Link></div>
            <hr/>
            <SocialKakao/>
        </div>
    )
}

export default Login;
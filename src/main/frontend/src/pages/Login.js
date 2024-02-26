import React from 'react';
import { useState } from "react";
import { Link } from 'react-router-dom';
import AuthButton from '../components/auth/AuthBtn';
import TextInput from '../components/auth/TextInput';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const emailOnchangeHandler = (e) => {
        setEmail(e.target.value);
    }

    const passwordOnchangeHandler = (e) => {
        setPassword(e.target.value);
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
            <AuthButton className="loginBtn" buttonText="로그인"/>
            <div className='userDiv'><Link to="/signup">회원가입</Link> | <Link to="/findPass">비밀번호 찾기</Link></div>
            <hr/>
            소셜 로그인
        </div>
    )
}

export default Login;
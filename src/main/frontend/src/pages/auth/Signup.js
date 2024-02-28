import { useState } from "react";
import TextInput from "../../components/auth/TextInput";
import AuthButton from "../../components/auth/AuthBtn";
import CertificationButton from "../../components/auth/CertificationButton";

const Signup = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [checkPassword, setCheckPassword] = useState('');
    const [passwordMatch, setPasswordMatch] = useState(null); // 추가: 비밀번호 일치 여부 상태 추가

    const emailOnchangeHandler = (e) => {
        setEmail(e.target.value);
    }

    const passwordOnchangeHandler = (e) => {
        setPassword(e.target.value);
        // 비밀번호 변경 시, 비밀번호 일치 여부를 체크
        if (checkPassword !== '') {
            setPasswordMatch(e.target.value === checkPassword);
        }
    }

    const checkPasswordOnchangeHandler = (e) => {
        setCheckPassword(e.target.value);
        // 비밀번호 확인 변경 시, 비밀번호 일치 여부를 체크
        setPasswordMatch(password === e.target.value);
    }

    return(
        <div className="authDiv">
             <h1>회원가입</h1>
             <div className="emailDiv">
                <TextInput
                    type="email"
                    placeholder="이메일 주소"
                    value={email}
                    onChange={emailOnchangeHandler}
                />
                <div className="cerBtn"><CertificationButton buttonText="인증번호 발송"/></div>
            </div>
            <TextInput
                type="password"
                placeholder="비밀번호"
                value={password}
                onChange={passwordOnchangeHandler}
            />
        
            <TextInput
                type="password"
                placeholder="비밀번호 확인"
                value={checkPassword}
                onChange={checkPasswordOnchangeHandler}
            />
        
            {/* 비밀번호 일치 여부에 따라 메시지 출력 */}
            {passwordMatch !== null && (
            <p style={{ color: passwordMatch ? "green" : "red" }}>
                {passwordMatch ? "입력하신 비밀번호가 일치합니다" : "입력하신 비밀번호가 일치하지 않습니다"}
            </p>
            )}

        <AuthButton buttonText="회원가입"/>
        </div>
    )
}

export default Signup;
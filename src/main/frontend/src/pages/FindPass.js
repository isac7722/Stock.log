import { useState } from "react";
import AuthButton from "../components/auth/AuthBtn";
import CertificationButton from "../components/auth/CertificationButton";
import TextInput from "../components/auth/TextInput";

const FindPass = () => {

    const [email, setEmail] = useState('');

    const emailOnchangeHandler = (e) => {
        setEmail(e.target.value);
    }

    return(
        <div className="authDiv">
            <h1>비밀번호 찾기</h1>

            <div className="emailDiv">
                <TextInput
                    type="email"
                    placeholder="이메일 주소"
                    value={email}
                    onChange={emailOnchangeHandler}
                />
                <div className="cerBtn"><CertificationButton buttonText="인증번호 발송"/></div>
            </div>

            <AuthButton buttonText={"확인"}/>
        </div>
    )
}

export default FindPass
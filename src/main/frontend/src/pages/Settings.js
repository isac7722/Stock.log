import SettingBtn from "../components/setting/SettingBtn";
import SettingDisabledInput from "../components/setting/SettingDisabledInput";
import SettingInput from "../components/setting/SettingInput";

const Settings = () => {

    return (
        <div className="settingDiv">
            <SettingDisabledInput
                placeholder="email"
                type='email'
           />
           <div className="passSettingDiv">
            <SettingDisabledInput
                placeholder="비밀번호"
                type='password'
           />
           <SettingBtn
                title="비밀번호 변경"
                modalTitle="비밀번호 변경"
           />

           </div>

           <div className="assetInput">
            <SettingInput
                placeholder="현재 자산을 입력해주세요"
                type="number"
            /><p className="settingUnit">원</p>
            <SettingBtn
                    title="입금/출금"
                    modalTitle="입금 혹은 출금할 금액을 적어주세요"
            />
            </div>

            <div className="assetInput">
            <SettingInput
                placeholder="수수료를 입력해주세요"
                type="number"
            /><p className="settingUnit">%</p>
            </div>

            <div>
                <SettingBtn
                    title="데이터 초기화"
                    modalTitle="데이터 초기화"
                />
                <SettingBtn
                    title="회원 탈퇴"
                    modalTitle="회원 탈퇴"
                />
            </div>
        </div>
        
    )
}
export default Settings;